package io.tiklab.postin.api.http.mock.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * mock地址拦截
 */
@WebServlet(name = "mockx",urlPatterns = "/mockx/*")
public class MockServlet extends HttpServlet {

    @Autowired
    MockServletRequest mockServletRequest;

    /**
     * get类型请求
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //浏览器用utf8来解析返回的数据
        response.setContentType("text/html;charset=utf-8");
        //servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("utf-8");

        mockServletRequest.actRequest(request,response);

    }

    /**
     * post类型请求
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

}
