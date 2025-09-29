package io.tiklab.postin.api.http.mock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

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
        sendMock(request,response);
    }

    /**
     * post类型请求
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sendMock(req, resp);
    }

    /**
     * put类型请求
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sendMock(req, resp);
    }

    /**
     * delete类型请求
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sendMock(req, resp);
    }

    /**
     * patch类型请求 - 通过service方法处理
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if ("PATCH".equals(req.getMethod())) {
            sendMock(req, resp);
        } else {
            super.service(req, resp);
        }
    }

    /**
     * options类型请求
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sendMock(req, resp);
    }

    /**
     * head类型请求
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sendMock(req, resp);
    }

    private void sendMock(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //浏览器用utf8来解析返回的数据
        response.setContentType("text/html;charset=utf-8");
        //servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("utf-8");

        mockServletRequest.actRequest(request,response);
    }

}
