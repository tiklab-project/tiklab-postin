package com.doublekit.apibox.apimock.servlet;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.doublekit.apibox.apidef.dao.MethodDao;
import com.doublekit.apibox.apidef.entity.MethodEntity;
import com.doublekit.apibox.apidef.model.MethodExQuery;
import com.doublekit.apibox.apimock.dao.*;
import com.doublekit.apibox.apimock.entity.*;
import com.doublekit.apibox.apimock.model.*;
import com.doublekit.apibox.category.dao.CategoryDao;
import com.doublekit.apibox.category.entity.CategoryEntity;
import com.doublekit.apibox.category.model.CategoryQuery;
import com.doublekit.dfs.client.DfsClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "mockx",urlPatterns = "/mockx/*")
public class MockServlet extends HttpServlet {

    @Autowired
    DfsClient dfsClient;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    MethodDao methodDao;

    @Autowired
    MockDao mockDao;

    @Autowired
    RequestHeaderMockDao requestHeaderMockDao;

    @Autowired
    QueryParamMockDao queryParamMockDao;

    @Autowired
    RequestBodyMockDao requestBodyMockDao;

    @Autowired
    FormParamMockDao formParamMockDao;

    @Autowired
    JsonParamMockDao jsonParamMockDao;

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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //浏览器用utf8来解析返回的数据
        response.setContentType("text/html;charset=utf-8");
        //servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("utf-8");
        //获取路径
        String mockPath = request.getRequestURI().replaceAll("/mockx","");
        String workspaceId = mockPath.substring(1,33);
        String path = mockPath.substring(33);

        //获取请求头信息
        Enumeration headerNames = request.getHeaderNames();

        //form
        List<Map> formdataList = new ArrayList<>();
        try {
            DiskFileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            servletFileUpload.setHeaderEncoding("UTF-8");
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            if(CollectionUtils.isNotEmpty(fileItems)){
                for(FileItem item :fileItems){
                    if (item.isFormField()) {
                        Map<String, String> formdatavalue = new HashMap<>();
                        String value = item.getString("utf-8");
                        formdatavalue.put(item.getFieldName(),value);
                        formdataList.add(formdatavalue);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        //url后面的参数
        Enumeration<String> enumeration = request.getParameterNames();

        //请求体
        //json
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        //将json字符串转换为json对象
        String jsonData = sb.toString();
        System.out.println("json"+jsonData);

        //mock中的数据对比
        //查询所有分组获取methodId
        List<CategoryEntity> categoryList = categoryDao.findCategoryList(new CategoryQuery().setWorkspaceId(workspaceId));
        String methodId = null;
        for (CategoryEntity category:categoryList){
            //查询所有接口
            List<MethodEntity> methodList = methodDao.findMethodList(new MethodExQuery().setCategoryId(category.getId()));
            if (CollectionUtils.isNotEmpty(methodList)){
                //通过path查询接口
                List<MethodEntity> collect = methodList.stream().filter(a -> path.equals(a.getPath())).collect(Collectors.toList());
                if(CollectionUtils.isNotEmpty(collect)){
                    MethodEntity methodEntity = collect.get(0);
                    String id= methodEntity.getId();
                    methodId=id;
                }
            }
        }

        //通过methodid查询所有mock
        List<MockEntity> mockList=mockDao.findMockList(new MockQuery().setMethodId(methodId));
        for(MockEntity mock:mockList){
            String mockId = mock.getId();
            int enabled = mock.getEnable();
            //启用：1, 停用：0
            if(enabled == 1){
                //HeaderList
                boolean headerStatus = false;
                List<RequestHeaderMockEntity> requestHeaderMockList = requestHeaderMockDao.findRequestHeaderMockList(new RequestHeaderMockQuery().setMockId(mockId));
                if(CollectionUtils.isNotEmpty(requestHeaderMockList)){
                    for(RequestHeaderMockEntity mockHeader:requestHeaderMockList){
                        String mockHeaderName =mockHeader.getHeaderName();
                        String mockHeaserValue = mockHeader.getValue();
                        while (headerNames.hasMoreElements()){
                            String headerName = (String) headerNames.nextElement();
                            String headerValue = request.getHeader(headerName);
                            System.out.println(headerName+":"+headerValue);
                            if( mockHeaderName.equals(headerName)&&mockHeaserValue.equals(headerValue)){
                                headerStatus=true;
                            }else {
                                continue;
                            }
                        }
                    }
                }else {
                    headerStatus=true;
                }

                //queryList
                boolean queryStatus = false;
                List<QueryParamMockEntity> queryParamMockList = queryParamMockDao.findQueryParamMockList(new QueryParamMockQuery().setMockId(mockId));
                if(CollectionUtils.isNotEmpty(queryParamMockList)){
                    for(QueryParamMockEntity mockQuery:queryParamMockList){
                        String mockQueryName =mockQuery.getParamName();
                        String mockQueryValue = mockQuery.getValue();
                        if(!ObjectUtils.isEmpty(enumeration)){
                            while(enumeration.hasMoreElements()){
                                String parm=enumeration.nextElement();
                                String values=request.getParameter(parm);
                                if(mockQueryName.equals(parm)&&mockQueryValue.equals(values)){
                                    queryStatus=true;
                                }else {
                                    continue;
                                }
                            }
                        }
                    }
                }else {
                    queryStatus=true;
                }

                RequestBodyMockEntity requestBodyMock = requestBodyMockDao.findRequestBodyMock(new RequestBodyMockQuery().setMockId(mockId));
                String bodyType = requestBodyMock.getBodyType();
                boolean bodyStatus = false;
                if(bodyType.equals("formdata")){
                    //formdataList
                    List<FormParamMockEntity> formParamMockList = formParamMockDao.findFormParamMockList(new FormParamMockQuery().setMockId(mockId));
                    if(CollectionUtils.isNotEmpty(formParamMockList)){
                        for(FormParamMockEntity mockForm: formParamMockList){
                            String formName = mockForm.getParamName();
                            String formValue = mockForm.getValue();
                            if(CollectionUtils.isNotEmpty(formdataList)){
                                for(Map<String, Object> map:formdataList){
                                    for(String key : map.keySet()){
                                        if(formName.equals(key)&&formValue.equals(map.get(key))){
                                            bodyStatus=true;
                                        }else {
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                    }else {
                        bodyStatus=true;
                    }
                }else {
                    //jsonList
                    List<JsonParamMockEntity> jsonParamMockList = jsonParamMockDao.findJsonParamMockList(new JsonParamMockQuery().setMockId(mockId));
                    if(CollectionUtils.isNotEmpty(jsonParamMockList)){
                        for(JsonParamMockEntity jsonParamMock:jsonParamMockList){
                            String jsonKey = jsonParamMock.getExp();
                            String jsonValue = jsonParamMock.getValue();
                            Object jsonDataValue =  JSONPath.read(jsonData,"$."+jsonKey);
                            if(jsonValue.equals(jsonDataValue)){
                                bodyStatus=true;
                            }else {
                                continue;
                            }
                        }
                    }else {
                        bodyStatus=true;
                    }
                }

                //如果都匹配返回数据
                if(headerStatus&&queryStatus&&bodyStatus){

                ResponseMockEntity responseMock = responseMockDao.findResponseMock(new ResponseMockQuery().setMockId(mockId));
                int HttpCode =Integer.parseInt(responseMock.getHttpCode());
                response.setStatus(HttpCode);

                List<ResponseHeaderMockEntity> responseHeaderMockList = responseHeaderMockDao.findResponseHeaderMockList(new ResponseHeaderMockQuery().setMockId(mockId));
                String headers = null;
                if(CollectionUtils.isNotEmpty(responseHeaderMockList)){
                    for(ResponseHeaderMockEntity responseHeaderMock : responseHeaderMockList){
                        String headerName = responseHeaderMock.getHeaderName();
                        String headerValue = responseHeaderMock.getValue();
                        response.setHeader(headerName,headerValue);
                        if(headers!=null){
                            headers =headers+headerName+",";
                        }else {
                            headers =headerName+",";
                        }


                    }
                }
                response.setHeader("Content-Type", "text/html;charset=UTF-8");
                response.setHeader("Access-Control-Expose-Headers ",headers);

                ResponseResultMockEntity responseResultMock = responseResultMockDao.findResponseResultMock(new ResponseResultMockQuery().setMockId(mockId));
                String responseType = responseResultMock.getResultType();
                if(responseType.equals("json")){
                    JsonResponseMockEntity jsonResponseMock = jsonResponseMockDao.findJsonResponseMock(new JsonResponseMockQuery().setMockId(mockId));
                    String jsonMockData = jsonResponseMock.getResult();
                    ServletOutputStream servletOutputStream = response.getOutputStream();
                    servletOutputStream.write(jsonMockData.getBytes("UTF-8"));
                }else {
                    RawResponseMockEntity rawResponseMock = rawResponseMockDao.findRawResponseMock(new RawResponseMockQuery().setMockId(mockId));
                    String rawMockData = rawResponseMock.getResult();
                    ServletOutputStream servletOutputStream = response.getOutputStream();
                    servletOutputStream.write(rawMockData.getBytes("UTF-8"));
                }
            }
            }else {
                continue;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }



}
