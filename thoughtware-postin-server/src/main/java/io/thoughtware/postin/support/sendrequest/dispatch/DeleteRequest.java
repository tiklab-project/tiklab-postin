package io.thoughtware.postin.support.sendrequest.dispatch;

import io.thoughtware.postin.support.sendrequest.HttpRequest;
import io.thoughtware.postin.support.sendrequest.util.DataProcessCommon;
import io.thoughtware.postin.support.sendrequest.util.HttpMethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Service
public class DeleteRequest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DataProcessCommon dataProcessCommon;

    @Autowired
    HttpMethodUtils httpMethodUtils;



    public void dispatch( HttpServletResponse response, HttpRequest httpRequest){
        //请求开始时间
        Instant startTime = Instant.now();

        try {
            //方法
            String method = httpRequest.getMethod();
            HttpMethod httpMethod = httpMethodUtils.getMehtod(method);

            //url
            String dispatchUrl = httpRequest.getUrl();

            HttpHeaders headers = httpRequest.getHeaders();
            //构建请求entity, get无body
            HttpEntity<String> entity = new HttpEntity<>(null, headers);

            //转发请求
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(dispatchUrl, httpMethod,entity,byte[].class);

            //处理响应
            //response headers

            //获取请求时间
            String timeString = dataProcessCommon.getTime(startTime);
            int size = responseEntity.getBody().length;
            //响应头处理
            dataProcessCommon.buildResponse(responseEntity,response,timeString, size);
        }catch (HttpClientErrorException errorResponse) {
            String timeString = dataProcessCommon.getTime(startTime);
            dataProcessCommon.buildErrorResponse(errorResponse,response,timeString);
        } catch (Exception e) {
            dataProcessCommon.buildErrorResponseHeader(response,e.getMessage());
        }
    }



}
