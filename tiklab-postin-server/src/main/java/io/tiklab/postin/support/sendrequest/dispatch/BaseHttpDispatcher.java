package io.tiklab.postin.support.sendrequest.dispatch;

import io.tiklab.postin.support.sendrequest.HttpRequest;
import io.tiklab.postin.support.sendrequest.util.DataProcessCommon;
import io.tiklab.postin.support.sendrequest.util.HttpMethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

/**
 * HTTP请求分发器
 */
@Service
public class BaseHttpDispatcher {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected HttpMethodUtils httpMethodUtils;

    @Autowired
    protected DataProcessCommon dataProcessCommon;

    /**
     * 处理HTTP请求的通用方法
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param httpRequest 请求信息
     * @param hasBody 是否有请求体
     */
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response, 
                                HttpRequest httpRequest, boolean hasBody) {
        try {
            // 获取请求头
            HttpHeaders headers = httpRequest.getHeaders();
            
            // 构建请求实体
            HttpEntity<?> entity = buildRequestEntity(request, headers, hasBody);
            
            // 使用统一的请求处理逻辑
            handleRequestInternal(request, response, httpRequest, entity);
        } catch (IOException e) {
            dataProcessCommon.buildErrorResponseHeader(response, e.getMessage());
        }
    }

    /**
     * 处理HTTP请求的通用方法
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param httpRequest 请求信息
     * @param customEntity 自定义请求实体
     */
    public void handleRequestWithCustomEntity(HttpServletRequest request, HttpServletResponse response, 
                                               HttpRequest httpRequest, HttpEntity<?> customEntity) {
        // 使用统一的请求处理逻辑
        handleRequestInternal(request, response, httpRequest, customEntity);
    }

    /**
     * 统一的HTTP请求
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param httpRequest 请求信息
     * @param entity 请求实体
     */
    private void handleRequestInternal(HttpServletRequest request, HttpServletResponse response, 
                                     HttpRequest httpRequest, HttpEntity<?> entity) {
        // 请求开始时间
        Instant startTime = Instant.now();

        try {
            // 获取HTTP方法
            String method = httpRequest.getMethod();
            HttpMethod httpMethod = httpMethodUtils.getMehtod(method);

            // 获取目标URL
            String dispatchUrl = httpRequest.getUrl();

            // 转发请求
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                    dispatchUrl, 
                    httpMethod, 
                    entity, 
                    byte[].class
            );

            // 处理响应
            String timeString = dataProcessCommon.getTime(startTime);
            byte[] body = responseEntity.getBody();
            int size = body != null ? body.length : 0;
            
            dataProcessCommon.buildResponse(responseEntity, response, timeString, size);

        } catch (HttpClientErrorException errorResponse) {
            String timeString = dataProcessCommon.getTime(startTime);
            dataProcessCommon.buildErrorResponse(errorResponse, response, timeString);
        } catch (Exception e) {
            dataProcessCommon.buildErrorResponseHeader(response, e.getMessage());
        }
    }

    /**
     * 构建请求实体
     * @param request HTTP请求对象
     * @param headers 请求头
     * @param hasBody 是否有请求体
     * @return 请求实体
     * @throws IOException IO异常
     */
    private HttpEntity<?> buildRequestEntity(HttpServletRequest request, HttpHeaders headers, boolean hasBody) 
            throws IOException {
        if (hasBody) {
            // 有请求体的情况
            InputStream inputStream = request.getInputStream();
            byte[] body = StreamUtils.copyToByteArray(inputStream);
            return new HttpEntity<>(body, headers);
        } else {
            // 无请求体的情况
            return new HttpEntity<>(null, headers);
        }
    }

    /**
     * 处理无请求体的HTTP方法
     * @param response HTTP响应对象
     * @param httpRequest 请求信息
     */
    public void dispatchWithoutBody(HttpServletResponse response, HttpRequest httpRequest) {
        handleRequest(null, response, httpRequest, false);
    }

    /**
     * 处理有请求体的HTTP方法
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param httpRequest 请求信息
     */
    public void dispatchWithBody(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest) {
        handleRequest(request, response, httpRequest, true);
    }
}
