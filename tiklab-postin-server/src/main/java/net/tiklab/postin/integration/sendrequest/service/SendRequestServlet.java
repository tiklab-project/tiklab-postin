package net.tiklab.postin.integration.sendrequest.service;

import net.tiklab.postin.integration.sendrequest.dispatch.*;
import net.tiklab.postin.integration.sendrequest.model.HttpRequest;
import net.tiklab.postin.integration.sendrequest.util.DataProcessCommon;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
* 用户服务业务处理
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