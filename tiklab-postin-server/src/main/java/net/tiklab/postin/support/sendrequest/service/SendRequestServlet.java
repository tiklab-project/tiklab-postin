package net.tiklab.postin.support.sendrequest.service;


import net.tiklab.postin.support.sendrequest.dispatch.*;
import net.tiklab.postin.support.sendrequest.util.DataProcessCommon;
import net.tiklab.postin.support.sendrequest.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;

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
        if(method.equals("get")){
            routeDispatchForGet.dispatch(request,response,httpRequest);
        }else {
            switch (request.getContentType()){
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

        }


    }






}