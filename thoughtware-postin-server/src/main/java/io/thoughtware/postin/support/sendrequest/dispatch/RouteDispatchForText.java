package io.thoughtware.postin.support.sendrequest.dispatch;

import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.core.exception.SystemException;
import io.thoughtware.postin.support.sendrequest.HttpRequest;
import io.thoughtware.postin.support.sendrequest.util.HttpMethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

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


    public void dispatch(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest){

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

            //处理响应
            //response headers
            HttpHeaders httpHeaders = responseEntity.getHeaders();
            response.setContentType(httpHeaders.getContentType().toString());

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
            throw new ApplicationException(e);
        }
    }
}
