package net.tiklab.postin.api.http.mock.servlet;

import net.tiklab.postin.api.http.mock.model.ResponseHeaderMock;
import net.tiklab.postin.api.http.mock.model.ResponseHeaderMockQuery;
import net.tiklab.postin.api.http.mock.model.ResponseMock;
import net.tiklab.postin.api.http.mock.model.ResponseResultMock;
import net.tiklab.postin.api.http.mock.service.ResponseHeaderMockService;
import net.tiklab.postin.api.http.mock.service.ResponseMockService;
import net.tiklab.postin.api.http.mock.service.ResponseResultMockService;
import net.tiklab.postin.api.http.mock.utils.MockProcess;
import net.tiklab.core.exception.ApplicationException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
     * 响应处理
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
    public  void setHttpCode(String mockId, HttpServletResponse response){
        ResponseMock responseMock = responseMockService.findResponseMock(mockId);
        int HttpCode =Integer.parseInt(responseMock.getHttpCode());
        response.setStatus(HttpCode);
    }

    /**
     * 从数据库获取header，设置到servlet header中
     */
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

    /**
     * 从数据库获取body类型，设置对应的body类型
     */
    public void setBody(String mockId,  HttpServletResponse response) throws IOException {
        ResponseResultMock responseResultMock = responseResultMockService.findResponseResultMock(mockId);
        String jsonMockData = responseResultMock.getResult();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(jsonMockData.getBytes("UTF-8"));
    }


}
