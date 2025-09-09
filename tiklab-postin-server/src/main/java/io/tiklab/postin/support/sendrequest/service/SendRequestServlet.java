package io.tiklab.postin.support.sendrequest.service;


import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.support.sendrequest.dispatch.BaseHttpDispatcher;
import io.tiklab.postin.support.sendrequest.util.DataProcessCommon;
import io.tiklab.postin.support.sendrequest.HttpRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


/**
* 拦截request接口，发送请求
* 支持所有标准HTTP方法：GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS, TRACE
*/
@WebServlet(name = "request",urlPatterns = "/request")
public class SendRequestServlet extends HttpServlet {

    @Autowired
    DataProcessCommon dataProcessCommon;

    @Autowired
    BaseHttpDispatcher baseHttpDispatcher;


    /**
     * 处理所有HTTP请求方法
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解析头部数据，pi-header,pi-url,pi-method
        HttpRequest httpRequest = dataProcessCommon.buildRequestInfo(request);
        String method = httpRequest.getMethod().toLowerCase();

        // 根据HTTP方法类型进行分发
        switch (method){
            case MagicValue.API_METHOD_TYPE_GET:
            case MagicValue.API_METHOD_TYPE_HEAD:
            case MagicValue.API_METHOD_TYPE_OPTIONS:
            case MagicValue.API_METHOD_TYPE_TRACE:
            case MagicValue.API_METHOD_TYPE_DELETE:
                // 无请求体的方法
                baseHttpDispatcher.dispatchWithoutBody(response, httpRequest);
                break;
            case MagicValue.API_METHOD_TYPE_POST:
            case MagicValue.API_METHOD_TYPE_PUT:
            case MagicValue.API_METHOD_TYPE_PATCH:
                // 有请求体的方法，根据Content-Type分发
                handleRequestWithBody(request, response, httpRequest);
                break;
            default:
                // 不支持的方法类型
                response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                response.getWriter().write("Unsupported HTTP method: " + method);
                break;
        }
    }

    /**
     * 处理带请求体的HTTP方法（POST, PUT, PATCH）
     * 根据Content-Type进行分发
     * @param request
     * @param response
     * @param httpRequest
     * @throws IOException
     */
    private void handleRequestWithBody(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        String contentType = getContentType(headers);

        // 根据Content-Type分发到对应的处理器
        switch (contentType){
            case MagicValue.MEDIA_TYPE_FORM_DATA:
                handleFormDataRequest(request, response, httpRequest);
                break;
            case MagicValue.MEDIA_TYPE_FORM_URLENCODED:
                handleFormUrlRequest(request, response, httpRequest);
                break;
            case MagicValue.MEDIA_TYPE_JSON:
            case MagicValue.MEDIA_TYPE_RAW:
            case MagicValue.MEDIA_TYPE_JAVASCRIPT:
            case MagicValue.MEDIA_TYPE_XML:
            case MagicValue.MEDIA_TYPE_HTML:
                // 直接使用基础分发器处理
                baseHttpDispatcher.dispatchWithBody(request, response, httpRequest);
                break;
            default:
                // 默认按文本处理
                baseHttpDispatcher.dispatchWithBody(request, response, httpRequest);
                break;
        }
    }

    /**
     * 获取并标准化Content-Type
     * @param headers
     * @return 标准化的Content-Type字符串
     */
    private String getContentType(HttpHeaders headers) {
        if (headers == null || headers.getContentType() == null) {
            return MagicValue.MEDIA_TYPE_RAW;
        }
        
        String contentType = headers.getContentType().toString();
        
        // multipart/form-data
        if (contentType.contains("multipart/form-data")) {
            return MagicValue.MEDIA_TYPE_FORM_DATA;
        }
        
        return contentType;
    }

    /**
     * 处理form-data请求
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param httpRequest 请求信息
     */
    private void handleFormDataRequest(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest) {
        try {
            // 构建form-data请求实体
            HttpEntity<MultiValueMap<String, Object>> requestEntity = buildFormDataEntity(request, httpRequest);
            
            // 使用基础分发器处理
            baseHttpDispatcher.handleRequestWithCustomEntity(request, response, httpRequest, requestEntity);
            
        } catch (Exception e) {
            dataProcessCommon.buildErrorResponseHeader(response, e.getMessage());
        }
    }

    /**
     * 处理form-url请求
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param httpRequest 请求信息
     */
    private void handleFormUrlRequest(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest) {
        try {
            // 构建form-url请求实体
            HttpEntity<MultiValueMap<String, String>> requestEntity = buildFormUrlEntity(request, httpRequest);
            
            // 使用基础分发器处理
            baseHttpDispatcher.handleRequestWithCustomEntity(request, response, httpRequest, requestEntity);
            
        } catch (Exception e) {
            dataProcessCommon.buildErrorResponseHeader(response, e.getMessage());
        }
    }

    /**
     * 构建form-data请求实体
     * @param request HTTP请求对象
     * @param httpRequest 请求信息
     * @return form-data请求实体
     */
    private HttpEntity<MultiValueMap<String, Object>> buildFormDataEntity(HttpServletRequest request, HttpRequest httpRequest) 
            throws IOException, FileUploadException {
        
        // 获取请求头
        HttpHeaders headers = httpRequest.getHeaders();

        // 创建文件上传处理器
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        List<FileItem> items = fileUpload.parseRequest(request);

        // 创建表单数据容器
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();

        // 处理表单字段
        for (FileItem item : items) {
            String fieldName = item.getFieldName();
            
            if (item.isFormField()) {
                // 普通表单字段
                String fieldValue = item.getString(StandardCharsets.UTF_8.name());
                formData.add(fieldName, fieldValue);
            } else {
                // 文件表单字段
                String fileName = item.getName();
                if (StringUtils.isNotEmpty(fileName)) {
                    byte[] fileBytes = IOUtils.toByteArray(item.getInputStream());
                    ByteArrayResource resource = new ByteArrayResource(fileBytes) {
                        @Override
                        public String getFilename() {
                            return fileName;
                        }
                    };
                    formData.add(fieldName, resource);
                }
            }
        }

        return new HttpEntity<>(formData, headers);
    }

    /**
     * 构建form-url请求实体
     * @param request HTTP请求对象
     * @param httpRequest 请求信息
     * @return form-url请求实体
     */
    private HttpEntity<MultiValueMap<String, String>> buildFormUrlEntity(HttpServletRequest request, HttpRequest httpRequest) {
        // 获取请求头
        HttpHeaders headers = httpRequest.getHeaders();

        // 提取请求参数
        MultiValueMap<String, String> params = extractParameters(request);

        return new HttpEntity<>(params, headers);
    }

    /**
     * 提取请求参数
     * @param request HTTP请求对象
     * @return 参数映射
     */
    private MultiValueMap<String, String> extractParameters(HttpServletRequest request) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            for (String value : values) {
                params.add(key, value);
            }
        }
        
        return params;
    }
}