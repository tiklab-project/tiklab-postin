package io.thoughtware.postin.support.sendrequest.dispatch;

import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.postin.support.sendrequest.util.DataProcessCommon;
import io.thoughtware.postin.support.sendrequest.util.HttpMethodUtils;
import io.thoughtware.postin.support.sendrequest.HttpRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
/**
 * form-data请求路由转发
 */
@Service
public class RouteDispatchForFormData {

    private static Logger logger = LoggerFactory.getLogger(RouteDispatchForFormData.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpMethodUtils httpMethodUtils;

    @Autowired
    DataProcessCommon dataProcessCommon;
    /**
     * formdata请求的转发
     * */
    public void dispatch(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest) throws IOException {

        //请求开始时间
        Instant startTime = Instant.now();

        try {
            //构建请求entity
            HttpEntity<MultiValueMap<String, Object>> requestEntity = buildRequestEntityForFormData(request,httpRequest);


            String method = httpRequest.getMethod();
            HttpMethod httpMethod = httpMethodUtils.getMehtod(method);

            //url
            String dispatchUrl = httpRequest.getUrl();

            //转发请求
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(dispatchUrl, httpMethod,requestEntity,byte[].class);

            //处理响应
            String timeString = dataProcessCommon.getTime(startTime);
            int size = responseEntity.getBody().length;
            dataProcessCommon.buildResponse(responseEntity, response, timeString, size);

        }catch (HttpClientErrorException errorResponse) {
            String timeString = dataProcessCommon.getTime(startTime);
            dataProcessCommon.buildErrorResponse(errorResponse,response,timeString);
        } catch (Exception e) {
            dataProcessCommon.buildErrorResponseHeader(response,e.getMessage());
        }
    }


    /**
     * 构造formdata
     * @param request
     * @param httpRequest
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    HttpEntity<MultiValueMap<String, Object>> buildRequestEntityForFormData(HttpServletRequest request, HttpRequest httpRequest)
            throws IOException, FileUploadException {

        // 1、请求头
        HttpHeaders headers = httpRequest.getHeaders();


        // 2、封装请求体
        // form-data 请求体可能会有文件参数
        // 创建一个DiskFileItemFactory对象，用于处理文件上传路径和文件大小的限制
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 创建一个ServletFileUpload对象，用于解析上传的文件
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        // 解析上传的文件
        List<FileItem> items = fileUpload.parseRequest( request);


        // 创建一个MultiValueMap对象，用于存储请求参数
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();

        // 遍历上传的参数
        for (FileItem item : items) {
            // 如果是普通表单参数
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                formData.add(fieldName, fieldValue);
            } else {
                String fieldName = item.getFieldName();
                String fileName = item.getName();
                // 获取上传的文件名
                if (StringUtils.isNotEmpty(fileName)) {
                    // 获取文件的输入流
                    InputStream inputStream = item.getInputStream();
                    // 添加文件参数
                    ByteArrayResource resource = new ByteArrayResource(IOUtils.toByteArray(inputStream));
                    formData.add(fieldName, resource);
                }
            }
        }

        // 3、构造出RestTemplate能识别的RequestEntity
        return new HttpEntity<>(formData, headers);
    }
}
