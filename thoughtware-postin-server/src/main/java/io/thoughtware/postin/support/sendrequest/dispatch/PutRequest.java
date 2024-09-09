package io.thoughtware.postin.support.sendrequest.dispatch;

import io.thoughtware.postin.support.sendrequest.util.DataProcessCommon;
import io.thoughtware.postin.support.sendrequest.HttpRequest;
import io.thoughtware.postin.support.sendrequest.util.HttpMethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;

@Service
public class PutRequest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DataProcessCommon dataProcessCommon;

    @Autowired
    HttpMethodUtils httpMethodUtils;



    public void dispatch(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest) {
        // 请求开始时间
        Instant startTime = Instant.now();
        try {
            // 构建PUT请求的entity
            RequestEntity<byte[]> requestEntity = this.buildRequestEntityForJson(request, httpRequest);

            // 使用PUT方法转发请求
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                    requestEntity.getUrl(),
                    HttpMethod.PUT,
                    requestEntity,
                    byte[].class
            );

            // 处理响应
            String timeString = dataProcessCommon.getTime(startTime);
            int size = responseEntity.getBody().length;

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
     * @param request
     * @param httpRequest
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    RequestEntity<byte[]> buildRequestEntityForJson(HttpServletRequest request, HttpRequest httpRequest)
            throws URISyntaxException, IOException {

        // 1、头，url method
        MultiValueMap<String, String> headers = httpRequest.getHeaders();

        String method = httpRequest.getMethod();
        HttpMethod httpMethod = httpMethodUtils.getMehtod(method);

        String url = httpRequest.getUrl();


        // 2、封装请求体
        InputStream inputStream = request.getInputStream();
        byte[] body = StreamUtils.copyToByteArray(inputStream);

        // 3、构造出RestTemplate能识别的RequestEntity
        return new RequestEntity<byte[]>(body, headers, httpMethod, new URI(url));
    }




}
