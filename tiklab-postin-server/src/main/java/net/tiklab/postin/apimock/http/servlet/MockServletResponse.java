package net.tiklab.postin.apimock.http.servlet;

import net.tiklab.postin.apimock.http.model.*;
import net.tiklab.postin.apimock.http.service.*;
import net.tiklab.postin.apimock.http.model.*;
import net.tiklab.postin.apimock.http.service.*;
import net.tiklab.postin.apimock.http.utils.MockProcess;
import net.tiklab.core.exception.ApplicationException;
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
    ResponseMockService responseMockService;

    @Autowired
    ResponseHeaderMockService responseHeaderMockService;

    @Autowired
    JsonResponseMockService jsonResponseMockService;

    @Autowired
    RawResponseMockService rawResponseMockService;

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
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Expose-Headers ",headers);
    }

    //从数据库获取body类型，设置对应的body类型
    public void setBody(String mockId,  HttpServletResponse response) throws IOException {
        ResponseMock responseMock = responseMockService.findResponseMock(mockId);

        String responseType = responseMock.getBodyType();
        if(responseType.equals("json")){
            setJson(mockId,response);
        }else {
            setRaw(mockId,response);
        }
    }

    //从数据库获取json，设置到servlet json中
    public void setJson(String mockId, HttpServletResponse response) throws IOException {
        JsonResponseMock jsonResponseMock = jsonResponseMockService.findJsonResponseMock(mockId);
        String jsonMockData = jsonResponseMock.getResult();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(jsonMockData.getBytes("UTF-8"));
    }

    //从数据库获取raw，设置到servlet raw中
    public void setRaw( String mockId, HttpServletResponse response) throws IOException {
        RawResponseMock rawResponseMock = rawResponseMockService.findRawResponseMock(mockId);
        String rawMockData = rawResponseMock.getResult();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(rawMockData.getBytes("UTF-8"));
    }

}
