package io.tiklab.postin.support.sendrequest.dispatch;

import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.support.sendrequest.util.DataProcessCommon;
import io.tiklab.postin.support.sendrequest.util.HttpMethodUtils;
import io.tiklab.postin.support.sendrequest.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * get
 */
@Service
public class RouteDispatchForGET {

    private static Logger logger = LoggerFactory.getLogger(RouteDispatchForGET.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpMethodUtils httpMethodUtils;

    @Autowired
    DataProcessCommon dataProcessCommon;

    /**
     * get
     * */
    public void dispatch(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest){

        try {
            //方法
            String method = httpRequest.getMethod();
            HttpMethod httpMethod = httpMethodUtils.getMehtod(method);

            //url
            String dispatchUrl = httpRequest.getUrl();

            HttpHeaders headers = httpRequest.getHeaders();
            //构建请求entity, get无body
            HttpEntity<String> entity = new HttpEntity<>(null, headers);

            //请求开始时间
            Instant startTime = Instant.now();

            //转发请求
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(dispatchUrl, httpMethod,entity,byte[].class);


            //处理响应
            //response headers

            //获取请求时间
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            long millis = duration.toMillis();
            String timeString = String.format("%d", millis);

            //响应头处理
            dataProcessCommon.buildResponseHeader(responseEntity,response,timeString);


            //response body
            if (responseEntity.hasBody()) {
                try {
                    ServletOutputStream outputStream = response.getOutputStream();
                    outputStream.write(responseEntity.getBody());
                    outputStream.flush();
                } catch (IOException e) {
                    throw new SystemException(e);
                }
            }
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

}
