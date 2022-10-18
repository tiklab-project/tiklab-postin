package net.tiklab.postin.apimock.http.servlet;

import net.tiklab.postin.apimock.http.model.*;
import net.tiklab.postin.apimock.http.service.*;
import net.tiklab.postin.apimock.http.utils.MockProcess;
import net.tiklab.core.exception.ApplicationException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class MockServletResponse {

    @Autowired
    ResponseMockService responseMockService;

    @Autowired
    ResponseHeaderMockService responseHeaderMockService;

    @Autowired
    ResponseResultMockService responseResultMockService;

    public void actResponse(String mockId, HttpServletResponse response) throws IOException {
        setHttpCode(mockId,response);

        setHeader(mockId,response);

        setBody(mockId,response);
    }

    //从数据库获取httpcode，设置到servlet中
    public  void setHttpCode(String mockId, HttpServletResponse response){
        ResponseMock responseMock = responseMockService.findResponseMock(mockId);
        int HttpCode =Integer.parseInt(responseMock.getHttpCode());
        response.setStatus(HttpCode);
    }

    //从数据库获取header，设置到servlet header中
    public void setHeader(String mockId, HttpServletResponse response){
        ResponseHeaderMockQuery responseHeaderMockQuery = new ResponseHeaderMockQuery().setMockId(mockId);
        List<ResponseHeaderMock> responseHeaderMockList = responseHeaderMockService.findResponseHeaderMockList(responseHeaderMockQuery);
        String headers = null;
        if(CollectionUtils.isNotEmpty(responseHeaderMockList)){
            for(ResponseHeaderMock responseHeaderMock : responseHeaderMockList){
                String headerName = responseHeaderMock.getHeaderName();
                String headerValue = responseHeaderMock.getValue();
                String mockVal = null;
                try {
                    mockVal =  MockProcess.mock(headerValue);
                } catch (ScriptException e) {
                    throw new ApplicationException(e);
                }
                response.setHeader(headerName,mockVal);
                if(headers!=null){
                    headers =headers+headerName+",";
                }else {
                    headers =headerName+",";
                }
            }
        }


        ResponseMock responseMock = responseMockService.findResponseMock(mockId);
        String responseType = responseMock.getBodyType();

        response.setHeader("content-type", responseType);
    }

    //从数据库获取body类型，设置对应的body类型
    public void setBody(String mockId,  HttpServletResponse response) throws IOException {
        ResponseResultMock responseResultMock = responseResultMockService.findResponseResultMock(mockId);
        String jsonMockData = responseResultMock.getResult();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(jsonMockData.getBytes("UTF-8"));
    }


}
