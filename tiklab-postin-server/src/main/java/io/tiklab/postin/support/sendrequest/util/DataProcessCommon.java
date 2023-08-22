package io.tiklab.postin.support.sendrequest.util;

import io.tiklab.postin.support.sendrequest.HttpRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

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
    public HttpRequest buildRequestInfo(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String Url = null;
        String method = null;


        //网址中的参数
        String urlParameter=request.getQueryString();

        for (String pair : urlParameter.split("&")) {
            String[] keyValue = pair.split("=");


            if (keyValue[0].equals("pi-header")) {
                String headerValue = keyValue[1];

                // Split header value into smaller key-value pairs
                for (String headerPair : headerValue.split(",")) {
                    String[] headerKeyValue = headerPair.split(":");
                    String headerKey = headerKeyValue[0];
                    String headersValue = headerKeyValue[1];

                    headers.add(headerKey, headersValue);
                }
            }

            if (keyValue[0].equals("pi-url")) {
                Url=keyValue[1];
            }

            if (keyValue[0].equals("pi-method")) {
                method=keyValue[1];
            }
        }

//        List<String> headerNames = Collections.list(request.getHeaderNames());
//
//        for (String headerName : headerNames) {
//            String headerNameLow = headerName.toLowerCase();
//
//            if(Objects.equals(headerNameLow,"pi-header")){
//                List<String> headerValues = Collections.list(request.getHeaders(headerName));
//                for (String headerValue : headerValues) {
//                    for (String pair : headerValue.split(",")) {
//                        String[] keyValue = pair.split("=");
//                        headers.add(keyValue[0], keyValue[1]);
//                    }
//                }
//            }
//
//
//            if(Objects.equals(headerNameLow,"pi-url")){
//                List<String> headerValues = Collections.list(request.getHeaders(headerName));
//                for (String headerValue : headerValues) {
//                    Url=headerValue;
//                }
//            }
//
//            if(Objects.equals(headerNameLow,"pi-method")){
//                List<String> headerValues = Collections.list(request.getHeaders(headerName));
//                for (String headerValue : headerValues) {
//                    method=headerValue;
//                }
//            }
//        }


        HttpRequest httpRequest = new HttpRequest();

        httpRequest.setHeaders(headers);
        httpRequest.setUrl(Url);
        httpRequest.setMethod(method);


        return httpRequest;
    }

    /**
     * 处理响应头，把请求过后的响应头，和基本信息 重新构造传出去
     * @param responseEntity
     * @param response
     * @param timeString
     * @param size
     */
    public void buildResponseHeader(ResponseEntity<byte[]> responseEntity, HttpServletResponse response, String timeString, int size){
        //把响应头返回回去
        HttpHeaders httpHeaders = responseEntity.getHeaders();

        //获取状态码  如果存在 pi-mock-status 就设置 pi-mock-status 中的值

        String piBaseInfo;
        String piResHeader;

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


    }


}
