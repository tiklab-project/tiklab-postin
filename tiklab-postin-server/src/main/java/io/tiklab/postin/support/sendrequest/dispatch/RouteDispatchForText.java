package io.tiklab.postin.support.sendrequest.dispatch;

import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.support.sendrequest.HttpRequest;
import io.tiklab.postin.support.sendrequest.util.DataProcessCommon;
import io.tiklab.postin.support.sendrequest.util.HttpMethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;

/**
 * raw 里面的各种类型
 */
@Service
public class RouteDispatchForText {

    private static Logger logger = LoggerFactory.getLogger(RouteDispatchForText.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpMethodUtils httpMethodUtils;

    @Autowired
    DataProcessCommon dataProcessCommon;

    public void dispatch(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest){
        //请求开始时间
        Instant startTime = Instant.now();

        try {
            //方法
            String method = httpRequest.getMethod();
            HttpMethod httpMethod = httpMethodUtils.getMehtod(method);

            //url
            String dispatchUrl = httpRequest.getUrl();

            //请求头
            HttpHeaders headers = httpRequest.getHeaders();

            //请求体
            InputStream inputStream = request.getInputStream();
            byte[] body = StreamUtils.copyToByteArray(inputStream);

            //构建请求entity
            HttpEntity<byte[]> entity = new HttpEntity<byte[]>(body, headers);

            //转发请求
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(dispatchUrl, httpMethod,entity,byte[].class);

            //获取请求时间
            String timeString = dataProcessCommon.getTime(startTime);
            int size = responseEntity.getBody().length;

            dataProcessCommon.buildResponse(responseEntity,response,timeString,size);

        }catch (HttpClientErrorException errorResponse) {
            String timeString = dataProcessCommon.getTime(startTime);
            dataProcessCommon.buildErrorResponse(errorResponse,response,timeString);
        } catch (Exception e) {
            dataProcessCommon.buildErrorResponseHeader(response,e.getMessage());
        }
    }
}
