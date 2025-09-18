package io.tiklab.postin.support.sendrequest.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.support.sendrequest.HttpRequest;

/**
 * 数据处理
 */
@Component
public class DataProcessCommon {

    /**
     * 构建请求基本信息
     * header，Url，method
     *
     * @param request
     * @return
     */
    public HttpRequest buildRequestInfo(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        String Url = null;
        String method = null;


        //网址中的参数
        String urlParameter=request.getQueryString();

        // 解析获取header、url、method
        for (String pair : urlParameter.split("&")) {
            String[] keyValue = pair.split("=", 2); // 限制只分成两个部分

            if (keyValue.length < 2) continue;

            String key = keyValue[0];
            String value = URLDecoder.decode(keyValue[1], "UTF-8"); // 对所有参数值进行URL解码

            switch (key) {
                case "pi-header":
                    parseHeaders(headers, value);
                    break;
                case "pi-url":
                    Url = value;
                    break;
                case "pi-method":
                    method = value;
                    break;
                default:
                    // 忽略其他参数
                    break;
            }
        }



        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setHeaders(headers);
        httpRequest.setUrl(Url);
        httpRequest.setMethod(method);

        return httpRequest;
    }

    /**
     * 解析请求头字符串
     * @param headers HttpHeaders对象
     * @param headerValue 编码后的请求头字符串
     */
    private void parseHeaders(HttpHeaders headers, String headerValue) {
        if (headerValue == null || headerValue.trim().isEmpty()) {
            return;
        }

        for (String headerPair : headerValue.split(",")) {
            String[] headerKeyValue = headerPair.split(":", 2); // 防止值中包含":"
            if (headerKeyValue.length == 2) {
                String headerKey = headerKeyValue[0].trim();
                String headerVal = headerKeyValue[1].trim();

                // 验证请求头名称不为空
                if (!headerKey.isEmpty()) {
                    headers.add(headerKey, headerVal);
                }
            }
        }
    }


    /**
     * 处理响应头，把请求过后的响应头，和基本信息 重新构造传出去
     * @param responseEntity
     * @param response
     * @param timeString
     * @param size
     */
    public void buildResponse(ResponseEntity<byte[]> responseEntity, HttpServletResponse response, String timeString, int size){
        //把响应头返回回去
        HttpHeaders httpHeaders = responseEntity.getHeaders();

        String piBaseInfo;
        String piResHeader;

        //获取状态码  如果存在 pi-mock-status 就设置 pi-mock-status 中的值
        //如果存在pi-mock-status 则 走了mock地址
        if(httpHeaders.containsKey("pi-mock-baseInfo")){

            piBaseInfo=httpHeaders.getFirst("pi-mock-baseInfo");
            piResHeader=httpHeaders.getFirst("pi-mock-header");

        }else {
            int statusCode=responseEntity.getStatusCodeValue();
            piResHeader =  httpHeaders.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + ":" + String.join(",", "[" + String.join(",", entry.getValue()) + "]"))
                    .collect(Collectors.joining(","));

            piBaseInfo = String.format("statusCode=%d,time=%s,size=%d", statusCode,  timeString,size);

        }

        response.setHeader("pi-header",piResHeader);
        response.setHeader("pi-base",piBaseInfo);


        //响应体
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] body = responseEntity.getBody();
            if (body != null) {
                outputStream.write(body);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }


    public void buildErrorResponseHeader(HttpServletResponse response, String message) {
        response.setHeader("pi-error",message);
    }

    public void buildErrorResponse(HttpClientErrorException errorResponse, HttpServletResponse response, String time) {
        HttpHeaders httpHeaders = errorResponse.getResponseHeaders();

        String piBaseInfo;
        String piResHeader;

        //获取状态码  如果存在 pi-mock-status 就设置 pi-mock-status 中的值
        //如果存在pi-mock-status 则 走了mock地址
        if(httpHeaders.containsKey("pi-mock-baseInfo")){

            piBaseInfo=httpHeaders.getFirst("pi-mock-baseInfo");
            piResHeader=httpHeaders.getFirst("pi-mock-header");

        }else {
            int statusCode=errorResponse.getRawStatusCode();
            int size = errorResponse.getResponseBodyAsString().length();
            piResHeader =  httpHeaders.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + ":" + String.join(",", "[" + String.join(",", entry.getValue()) + "]"))
                    .collect(Collectors.joining(","));

            piBaseInfo = String.format("statusCode=%d,time=%s,size=%d", statusCode, time,size);

        }

        response.setHeader("pi-header",piResHeader);
        response.setHeader("pi-base",piBaseInfo);


        //响应体
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            String responseBody = errorResponse.getResponseBodyAsString();
            if (responseBody != null) {
                outputStream.write(responseBody.getBytes());
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }


    /**
     * 获取响应时间
     * @param startTime
     * @return
     */
    public String getTime(Instant startTime){
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        long millis = duration.toMillis();
        String timeString = String.format("%d", millis);

        return timeString;
    }



}
