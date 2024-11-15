package io.tiklab.postin.support.sendrequest.service;


import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.support.sendrequest.dispatch.*;
import io.tiklab.postin.support.sendrequest.util.DataProcessCommon;
import io.tiklab.postin.support.sendrequest.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
* 拦截request接口，发送请求
*/
@WebServlet(name = "request",urlPatterns = "/request")
public class SendRequestServlet extends HttpServlet {

    @Autowired
    DataProcessCommon dataProcessCommon;

    @Autowired
    RouteDispatchForGET routeDispatchForGet;

    @Autowired
    RouteDispatchForFormData routeDispatchForFormData;

    @Autowired
    RouteDispatchForJson routeDispatchForJson;

    @Autowired
    RouteDispatchForFormUrl routeDispatchForFormUrl;

    @Autowired
    DeleteRequest deleteRequest;

    @Autowired
    RouteDispatchForText routeDispatchForText;


    /**
     * 获取post请求 根据不同的media类型进行转发
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        //解析头部数据，pi-header,pi-url,pi-method
        HttpRequest httpRequest = dataProcessCommon.buildRequestInfo(request);
        String method = httpRequest.getMethod().toLowerCase();

        //get请求不需要content-type
        switch (method){
            case MagicValue.API_METHOD_TYPE_GET:
                routeDispatchForGet.dispatch(response,httpRequest);
                break;
            case MagicValue.API_METHOD_TYPE_POST:
            case MagicValue.API_METHOD_TYPE_PUT:
                postAndPutFn(request,response,httpRequest);
                break;
            case MagicValue.API_METHOD_TYPE_DELETE:
                deleteRequest.dispatch(response,httpRequest);
                break;
            case MagicValue.API_METHOD_TYPE_PATCH:
                postAndPutFn(request,response,httpRequest);
                break;
            default:
                break;

        }
    }

    /**
     * post 请求
     * @param request
     * @param response
     * @param httpRequest
     * @throws IOException
     */
    private void postAndPutFn(HttpServletRequest request, HttpServletResponse response,HttpRequest httpRequest) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();

        if(headers.getContentType()!=null){
            String contentType = headers.getContentType().toString();

            if(contentType.contains("multipart/form-data")){
                contentType = "multipart/form-data";
            }

            switch (contentType){
                case "multipart/form-data":
                    routeDispatchForFormData.dispatch(request,response,httpRequest);
                    break;
                case "application/x-www-form-urlencoded":
                    routeDispatchForFormUrl.dispatch(request,response,httpRequest);
                    break;
                case "application/json":
                    routeDispatchForJson.dispatch(request,response,httpRequest);
                    break;
                case "text/plain":
                case "application/javascript":
                case "text/xml":
                case "text/html":
                    routeDispatchForText.dispatch(request,response,httpRequest);
                    break;
                default:
                    break;
            }
        }else {
            routeDispatchForText.dispatch(request,response,httpRequest);
        }
    }




}