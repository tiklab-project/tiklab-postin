package io.tiklab.postin.api.http.mock.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.http.definition.model.ApiResponse;
import io.tiklab.postin.api.http.definition.model.ApiResponseQuery;
import io.tiklab.postin.api.http.definition.model.ResponseHeader;
import io.tiklab.postin.api.http.definition.model.ResponseHeaderQuery;
import io.tiklab.postin.api.http.definition.service.ApiResponseService;
import io.tiklab.postin.api.http.definition.service.ResponseHeaderService;
import io.tiklab.postin.api.http.mock.model.ResponseHeaderMock;
import io.tiklab.postin.api.http.mock.model.ResponseHeaderMockQuery;
import io.tiklab.postin.api.http.mock.model.ResponseMock;
import io.tiklab.postin.api.http.mock.model.ResponseResultMock;
import io.tiklab.postin.api.http.mock.service.ResponseHeaderMockService;
import io.tiklab.postin.api.http.mock.service.ResponseMockService;
import io.tiklab.postin.api.http.mock.service.ResponseResultMockService;
import io.tiklab.postin.common.ErrorCode;

/**
 * mock
 * servlet 响应处理
 */
@Service
public class MockServletResponse {

    @Autowired
    ResponseMockService responseMockService;

    @Autowired
    ResponseHeaderMockService responseHeaderMockService;

    @Autowired
    ResponseResultMockService responseResultMockService;

    @Autowired
    ApiResponseService apiResponseService;

    @Autowired
    ResponseHeaderService responseHeaderService;

    @Autowired
    JsonGenerator jsonGenerator;

    /**
     * mock中响应
     * @param mockId
     * @param response
     * @throws IOException
     */
    public void actResponse(String mockId, HttpServletResponse response){
        ResponseMock responseMock = responseMockService.findResponseMock(mockId);

        StringBuilder headersBuilder= processMockHeader(mockId);
        setHeader(headersBuilder,responseMock.getBodyType(),response);

        String bodyData = processMockBody(mockId);
        setBody(bodyData,response);

        int length = bodyData.length();
        setHttpCode(response,responseMock.getTime(),responseMock.getHttpCode(),length);
    }

    /**
     * 接口定义中的响应
     */
    public void definitionResponse(HttpServletResponse response,String apiId){
        ApiResponseQuery apiResponseQuery = new ApiResponseQuery();
        apiResponseQuery.setHttpId(apiId);
        List<ApiResponse> apiResponseList = apiResponseService.findApiResponseList(apiResponseQuery);
        ApiResponse apiResponse = apiResponseList.get(0);

        //响应体
        String bodyData = processApiBody(apiResponse);
        setBody(bodyData,response);
        //响应头
        StringBuilder headersBuilder = processApiHeader(apiId);
        setHeader(headersBuilder,apiResponse.getDataType(),response);

        int length = bodyData.length();
        //响应码
        setHttpCode(response,0,apiResponse.getHttpCode().toString(), length);
    };



    /**
     * 处理mock中的请求头
     */
    private StringBuilder processMockHeader(String mockId){
        ResponseHeaderMockQuery responseHeaderMockQuery = new ResponseHeaderMockQuery().setMockId(mockId);
        List<ResponseHeaderMock> responseHeaderMockList = responseHeaderMockService.findResponseHeaderMockList(responseHeaderMockQuery);

        if(responseHeaderMockList != null) {
            StringBuilder headersBuilder = new StringBuilder();
            for (ResponseHeaderMock responseHeaderMock : responseHeaderMockList) {
                String headerName = responseHeaderMock.getHeaderName();
                String headerValue = responseHeaderMock.getValue();

                if (headerName == null || headerValue == null) {
                    continue;
                }

                if (headersBuilder.length() > 0) {
                    headersBuilder.append(",");
                }

                headersBuilder.append(headerName).append(":[").append(headerValue).append("]");
            }

            return headersBuilder;
        }

        return null;
    }

    /**
     * 处理mock中的响应体
     */
    private String processMockBody(String mockId){
        ResponseResultMock responseResultMock = responseResultMockService.findResponseResultMock(mockId);
        if(responseResultMock != null && responseResultMock.getResult() != null){
            return responseResultMock.getResult();
        }

        // 如果找不到mock数据，返回默认的空JSON对象
        return "{}";
    }

    /**
     * 处理 接口定义 中响应体
     */
    private String processApiBody(ApiResponse apiResponse){
        if("json".equals(apiResponse.getDataType())){
            String jsonText = apiResponse.getJsonText();
            return jsonGenerator.generateJson(jsonText);
        }else {
            String rawText = apiResponse.getRawText();
            return jsonGenerator.generateRaw(rawText);
        }
    }

    /**
     * 处理 接口定义 的请求头
     */
    private StringBuilder processApiHeader(String apiId){

        ResponseHeaderQuery responseHeaderQuery = new ResponseHeaderQuery();
        responseHeaderQuery.setHttpId(apiId);
        List<ResponseHeader> responseHeaderList = responseHeaderService.findResponseHeaderList(responseHeaderQuery);

        if(responseHeaderList != null) {
            StringBuilder headersBuilder = new StringBuilder();
            for (ResponseHeader responseHeader : responseHeaderList) {
                String headerName = responseHeader.getHeaderName();
                String headerValue = responseHeader.getValue();

                if (headerName == null || headerValue == null) {
                    continue;
                }

                if (headersBuilder.length() > 0) {
                    headersBuilder.append(",");
                }

                headersBuilder.append(headerName).append(":[").append(headerValue).append("]");
            }

            return headersBuilder;
        }

        return null;
    }


    /**
     *  设置到servlet中
     */
    public void setHttpCode(HttpServletResponse response, Integer delayTime, String httpCode, int length){
        //添加随机毫秒
        int randomDelay = new Random().nextInt(1000);
        int time = delayTime + randomDelay;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new ApplicationException(e);
        }

        String result = String.format("statusCode=%s,time=%d,size=%d", httpCode, time,length);

        response.setHeader("pi-mock-baseInfo",result);
    }

    /**
     * 设置到servlet header中
     */
    public void setHeader(StringBuilder headersBuilder, String mockBodyType, HttpServletResponse response){
        String mediaType;
        if(Objects.equals(mockBodyType,"json")){
            mediaType = "application/json";
        }else {
            mediaType = "text/plain";
        }

        headersBuilder.append("content-type").append(":[").append(mediaType).append("]");

        if (headersBuilder.length() > 0) {
            response.setHeader("pi-mock-header", headersBuilder.toString());
        }
    }

    /**
     * 设置到servlet 响应数据
     */
    public void setBody(String bodyData,  HttpServletResponse response)  {
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            // 添加null检查，避免空指针异常
            if (bodyData != null) {
                servletOutputStream.write(bodyData.getBytes("UTF-8"));
            } else {
                // 如果bodyData为null，写入空字符串
                servletOutputStream.write("".getBytes("UTF-8"));
            }
        } catch (IOException e) {
            throw new ApplicationException(ErrorCode.EXECUTE_ERROR,"set response body error :"+e.getMessage());
        }

    }

}
