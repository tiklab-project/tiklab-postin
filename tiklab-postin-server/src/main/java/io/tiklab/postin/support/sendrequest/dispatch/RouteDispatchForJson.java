package io.tiklab.postin.support.sendrequest.dispatch;

import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.support.sendrequest.util.DataProcessCommon;
import io.tiklab.postin.support.sendrequest.HttpRequest;
import io.tiklab.postin.support.sendrequest.util.HttpMethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;

/**
 * json类型转发
 */
@Component
public class RouteDispatchForJson {

    private static Logger logger = LoggerFactory.getLogger(RouteDispatchForJson.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpMethodUtils httpMethodUtils;

    @Autowired
    DataProcessCommon dataProcessCommon;

    /**
     * 发送请求
     * @param request
     * @param response
     * @param httpRequest
     */
    public void dispatch(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest){
        //请求开始时间
        Instant startTime = Instant.now();
        try {
            //构建请求entity
            RequestEntity<byte[]> requestEntity = this.buildRequestEntityForJson(request,httpRequest);


            //转发请求
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(requestEntity, byte[].class);

            //处理响应
            //获取请求时间
            String timeString = dataProcessCommon.getTime(startTime);
            int size = responseEntity.getBody().length;

            dataProcessCommon.buildResponse(responseEntity,response,timeString,size);
        }catch (HttpClientErrorException errorResponse) {
            String timeString = dataProcessCommon.getTime(startTime);
            dataProcessCommon.buildErrorResponse(errorResponse,response,timeString);
        }catch (Exception e) {
            dataProcessCommon.buildErrorResponseHeader(response,e.getMessage());
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
