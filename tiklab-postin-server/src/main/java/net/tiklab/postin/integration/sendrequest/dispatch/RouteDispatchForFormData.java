package net.tiklab.postin.integration.sendrequest.dispatch;

import net.tiklab.core.exception.SystemException;
import net.tiklab.postin.integration.sendrequest.model.HttpRequest;
import net.tiklab.postin.integration.sendrequest.util.DataProcessCommon;
import net.tiklab.postin.integration.sendrequest.util.HttpMethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

/**
 * form-data请求路由转发
 */
@Service
public class RouteDispatchForFormData {

    private static Logger logger = LoggerFactory.getLogger(RouteDispatchForFormData.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpMethodUtils httpMethodUtils;

    @Autowired
    DataProcessCommon dataProcessCommon;
    /**
     * formdata请求的转发
     * */
    public void dispatch(HttpServletRequest request, HttpServletResponse response, HttpRequest httpRequest){


        try {
            //构建请求entity
            HttpEntity<MultiValueMap<String, String>> requestEntity = buildRequestEntityForFormData(request,httpRequest);


            String method = httpRequest.getMethod();
            HttpMethod httpMethod = httpMethodUtils.getMehtod(method);

            //url
            String dispatchUrl = httpRequest.getUrl();

            //请求开始时间
            Instant startTime = Instant.now();

            //转发请求
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(dispatchUrl, httpMethod,requestEntity,byte[].class);

            //处理响应
            //获取请求时间
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            long millis = duration.toMillis();
            String timeString = String.format("%d", millis);


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

    /**
     * 构造formdata
     * @param request
     * @param httpRequest
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    HttpEntity<MultiValueMap<String, String>> buildRequestEntityForFormData(HttpServletRequest request, HttpRequest httpRequest)
            throws URISyntaxException, IOException {

        // 1、请求头
        HttpHeaders headers = httpRequest.getHeaders();


        // 2、封装请求体
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();

        Map<String,String[]> parameterMap = request.getParameterMap();
        for(Map.Entry<String,String[]> entry : parameterMap.entrySet()){
            String key = entry.getKey();
            String[] values = entry.getValue();
            for(String value:values){
                params.add(key,value);
            }
        }

        // 3、构造出RestTemplate能识别的RequestEntity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        return requestEntity;
    }
}
