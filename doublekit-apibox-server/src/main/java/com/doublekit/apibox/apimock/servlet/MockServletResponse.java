package com.doublekit.apibox.apimock.servlet;

import com.doublekit.apibox.apimock.dao.*;
import com.doublekit.apibox.apimock.entity.*;
import com.doublekit.apibox.apimock.model.*;
import com.doublekit.apibox.apimock.utils.MockProcess;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.ScriptException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class MockServletResponse {

    @Autowired
    ResponseMockDao responseMockDao;

    @Autowired
    ResponseResultMockDao responseResultMockDao;

    @Autowired
    ResponseHeaderMockDao responseHeaderMockDao;

    @Autowired
    JsonResponseMockDao jsonResponseMockDao;

    @Autowired
    RawResponseMockDao rawResponseMockDao;

    public void actResponse(String mockId, HttpServletResponse response) throws IOException {
        setHttpCode(mockId,response);

        setHeader(mockId,response);

        setBody(mockId,response);
    }

    public  void setHttpCode(String mockId, HttpServletResponse response){
        ResponseMockEntity responseMock = responseMockDao.findResponseMock(new ResponseMockQuery().setMockId(mockId));
        int HttpCode =Integer.parseInt(responseMock.getHttpCode());
        response.setStatus(HttpCode);
    }

    public  void setHeader(String mockId, HttpServletResponse response){
        List<ResponseHeaderMockEntity> responseHeaderMockList = responseHeaderMockDao.findResponseHeaderMockList(new ResponseHeaderMockQuery().setMockId(mockId));
        String headers = null;
        if(CollectionUtils.isNotEmpty(responseHeaderMockList)){
            for(ResponseHeaderMockEntity responseHeaderMock : responseHeaderMockList){
                String headerName = responseHeaderMock.getHeaderName();
                String headerValue = responseHeaderMock.getValue();
                String mockVal = null;
                try {
                    mockVal =  MockProcess.mock(headerValue);
                    System.out.println(MockProcess.mock("@naturel(50,66)"));
                    System.out.println(MockProcess.mock("@naturel(100)"));
                    System.out.println(MockProcess.mock("@float(1,100)"));
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
                response.setHeader(headerName,mockVal);
                if(headers!=null){
                    headers =headers+headerName+",";
                }else {
                    headers =headerName+",";
                }
            }
        }
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Expose-Headers ",headers);
    }

    public  void setBody(String mockId,  HttpServletResponse response) throws IOException {
        ResponseResultMockEntity responseResultMock = responseResultMockDao.findResponseResultMock(new ResponseResultMockQuery().setMockId(mockId));
        String responseType = responseResultMock.getResultType();
        if(responseType.equals("json")){
            setJson(mockId,response);
        }else {
            setRaw(mockId,response);
        }
    }

    public  void setJson(String mockId, HttpServletResponse response) throws IOException {
        JsonResponseMockEntity jsonResponseMock = jsonResponseMockDao.findJsonResponseMock(new JsonResponseMockQuery().setMockId(mockId));
        String jsonMockData = jsonResponseMock.getResult();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(jsonMockData.getBytes("UTF-8"));
    }

    public  void setRaw( String mockId, HttpServletResponse response) throws IOException {
        RawResponseMockEntity rawResponseMock = rawResponseMockDao.findRawResponseMock(new RawResponseMockQuery().setMockId(mockId));
        String rawMockData = rawResponseMock.getResult();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(rawMockData.getBytes("UTF-8"));
    }

}
