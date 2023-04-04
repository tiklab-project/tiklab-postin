package io.tiklab.postin.api.http.mock.servlet;

import io.tiklab.postin.api.http.mock.model.ResponseHeaderMock;
import io.tiklab.postin.api.http.mock.model.ResponseHeaderMockQuery;
import io.tiklab.postin.api.http.mock.model.ResponseMock;
import io.tiklab.postin.api.http.mock.model.ResponseResultMock;
import io.tiklab.postin.api.http.mock.service.ResponseHeaderMockService;
import io.tiklab.postin.api.http.mock.service.ResponseMockService;
import io.tiklab.postin.api.http.mock.service.ResponseResultMockService;
import io.tiklab.postin.api.http.mock.utils.MockProcess;
import io.tiklab.core.exception.ApplicationException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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

    /**
     * 响应
     * @param mockId
     * @param response
     * @throws IOException
     */
    public void actResponse(String mockId, HttpServletResponse response) throws IOException {
        setHttpCode(mockId,response);

        setHeader(mockId,response);

        setBody(mockId,response);
    }

    /**
     *  从数据库获取httpcode，设置到servlet中
     */
    public void setHttpCode(String mockId, HttpServletResponse response){
        ResponseMock responseMock = responseMockService.findResponseMock(mockId);


        //添加随机毫秒
        int randomDelay = new Random().nextInt(1000);
        int time = responseMock.getTime() + randomDelay;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new ApplicationException(e);
        }

        String result = String.format("statusCode=%s,time=%d", responseMock.getHttpCode(),  time);

        response.setHeader("pi-mock-baseInfo",result);
    }

    /**
     * 从数据库获取header，设置到servlet header中
     */
    public void setHeader(String mockId, HttpServletResponse response){
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

                String mockVal;
                try {
                    mockVal = MockProcess.mock(headerValue);
                } catch (ScriptException e) {
                    throw new ApplicationException(e);
                }

                if (headersBuilder.length() > 0) {
                    headersBuilder.append(",");
                }

                headersBuilder.append(headerName).append(":[").append(mockVal).append("]");
            }

            if (headersBuilder.length() > 0) {
                response.setHeader("pi-mock-header", headersBuilder.toString());
            }
        }
    }

    /**
     * 从数据库获取body类型，设置对应的body类型
     */
    public void setBody(String mockId,  HttpServletResponse response) throws IOException {
        ResponseResultMock responseResultMock = responseResultMockService.findResponseResultMock(mockId);
        if(responseResultMock != null){
            String jsonMockData = responseResultMock.getResult();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(jsonMockData.getBytes("UTF-8"));
        }

    }


}
