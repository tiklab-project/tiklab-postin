package net.tiklab.postin.integration.sendrequest.dispatch;

import net.tiklab.core.exception.SystemException;
import net.tiklab.postin.integration.sendrequest.model.HttpRequest;
import net.tiklab.postin.integration.sendrequest.util.DataProcessCommon;
import net.tiklab.postin.integration.sendrequest.util.HttpMethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
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
import java.util.stream.Collectors;

@Component
public class RouteDispatchForJson {

    private static Logger logger = LoggerFactory.getLogger(RouteDispatchForJson.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpMethodUtils httpMethodUtils;

    @Autowired
    DataProcessCommon dataProcessCommon;

    public void dispatch(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest){
        try {
            //构建请求entity
            RequestEntity<byte[]> requestEntity = this.buildRequestEntityForJson(request,httpRequest);


            //请求开始时间
            Instant startTime = Instant.now();

            //转发请求
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(requestEntity, byte[].class);

            //处理响应
            //获取请求时间
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            long millis = duration.toMillis();
            String timeString = String.format("%d", millis);


            dataProcessCommon.buildResponseHeader(responseEntity,response,timeString);


            //获取状态码
//            int statusCode = responseEntity.getStatusCodeValue();
//            String statusText = responseEntity.getStatusCode().getReasonPhrase();
//
//            String result = String.format("statusCode=%d,statusText=%s,time=%s", statusCode, statusText, timeString);
//            response.setHeader("pi-base",result);
//
//
//            //把响应头返回回去
//            HttpHeaders httpHeaders = responseEntity.getHeaders();
//
//            String piResHeader =  httpHeaders.entrySet()
//                    .stream()
//                    .map(entry -> entry.getKey() + ":" + String.join(",", entry.getValue()))
//                    .collect(Collectors.joining(","));
//
//            response.setHeader("pi-header",piResHeader);





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
        RequestEntity<byte[]> requestEntity = new RequestEntity<byte[]>(body, headers, httpMethod, new URI(url));
        return requestEntity;
    }

}
