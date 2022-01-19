package com.doublekit.apibox.apimock.servlet;

import com.alibaba.fastjson.JSONPath;
import com.doublekit.apibox.apidef.dao.MethodDao;
import com.doublekit.apibox.apidef.entity.MethodEntity;
import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apidef.model.MethodExQuery;
import com.doublekit.apibox.apidef.service.MethodService;
import com.doublekit.apibox.apimock.dao.*;
import com.doublekit.apibox.apimock.entity.*;
import com.doublekit.apibox.apimock.model.*;
import com.doublekit.apibox.apimock.service.*;
import com.doublekit.apibox.category.dao.CategoryDao;
import com.doublekit.apibox.category.entity.CategoryEntity;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.category.model.CategoryQuery;
import com.doublekit.apibox.category.service.CategoryService;
import com.doublekit.common.exception.SystemException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class MockServletRequest {

    @Autowired
    MockService mockService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    MethodService methodService;

    @Autowired
    RequestHeaderMockService requestHeaderMockService;

    @Autowired
    QueryParamMockService queryParamMockService;

    @Autowired
    RequestBodyMockService requestBodyMockService;

    @Autowired
    FormParamMockService formParamMockService;

    @Autowired
    JsonParamMockService jsonParamMockService;

    @Autowired
    MockServletResponse mockServletResponse;



    public void actRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取路径
        String mockPath = request.getRequestURI().replaceAll("/mockx","");

        mockOperate(request,response,mockPath);

    }

    private void mockOperate(HttpServletRequest request, HttpServletResponse response, String mockPath) throws IOException {
        //workspaceId
        String workspaceId = mockPath.substring(1,33);
        //接口路径
        String path = mockPath.substring(33);

        String methodId = getMethodId(workspaceId,path);

        //通过methodid查询所有mock
        List<Mock> mockList = mockService.findMockList(new MockQuery().setMethodId(methodId));
        for(Mock mock:mockList){
            String mockId = mock.getId();
            int enabled = mock.getEnable();
            //启用：1, 停用：0
            if(enabled == 1){
                //Header
                boolean headerStatus = getHeaderStatus( mockId, request);

                //query
                boolean queryStatus = getQueryStatus( mockId, request);

                //body
                boolean bodyStatus = getRequestTypeStatus(mockId,request);

                //如果都匹配返回数据
                if(headerStatus&&queryStatus&&bodyStatus){
                    mockServletResponse.actResponse(mockId,response);
                }
            }else {
                continue;
            }
        }
    }

    //获取methodId
    public  String getMethodId(String workspaceId, String path){
        List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setWorkspaceId(workspaceId));
        String methodId = null;
        for (Category category:categoryList){
            //查询所有接口
            List<MethodEx> methodList = methodService.findMethodList(new MethodExQuery().setCategoryId(category.getId()));
            if (CollectionUtils.isNotEmpty(methodList)){
                //通过path查询接口
                List<MethodEx> collect = methodList.stream().filter(a -> path.equals(a.getPath())).collect(Collectors.toList());
                if(CollectionUtils.isNotEmpty(collect)){
                    MethodEx methodEx = collect.get(0);
                    String id= methodEx.getId();
                    methodId=id;
                }
            }
        }
        return methodId;
    }

    //获取header
    public  Enumeration getHeader(HttpServletRequest request){
        Enumeration headerNames = request.getHeaderNames();
        return headerNames;
    }

    //获取url后面参数
    public  Enumeration<String> getUrlParam(HttpServletRequest request){
        Enumeration<String> enumeration = request.getParameterNames();
        return enumeration;
    }

    //获取form
    public  List<Map> getForm (HttpServletRequest request){
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
        } catch (FileUploadException | UnsupportedEncodingException e) {
            throw new SystemException(e);
        }
        return formdataList;
    }

    //获取json
    public  String getJson(HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();
        return jsonData;
    }


    public  boolean getHeaderStatus( String mockId, HttpServletRequest request){
        Enumeration headerNames =getHeader(request);

        boolean headerStatus = false;

        RequestHeaderMockQuery requestHeaderMockQuery = new RequestHeaderMockQuery().setMockId(mockId);
        List<RequestHeaderMock> requestHeaderMockList = requestHeaderMockService.findRequestHeaderMockList(requestHeaderMockQuery);
        if(CollectionUtils.isNotEmpty(requestHeaderMockList)){
            for(RequestHeaderMock mockHeader:requestHeaderMockList){
                String mockHeaderName =mockHeader.getHeaderName();
                String mockHeaserValue = mockHeader.getValue();
                while (headerNames.hasMoreElements()){
                    String headerName = (String) headerNames.nextElement();
                    String headerValue = request.getHeader(headerName);
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
        return headerStatus;
    }

    public  boolean getQueryStatus(String mockId,  HttpServletRequest request){
        Enumeration<String> urlParam = getUrlParam(request);
        boolean queryStatus = false;
        List<QueryParamMock> queryParamMockList = queryParamMockService.findQueryParamMockList(new QueryParamMockQuery().setMockId(mockId));

        if(CollectionUtils.isNotEmpty(queryParamMockList)){
            for(QueryParamMock mockQuery:queryParamMockList){
                String mockQueryName =mockQuery.getParamName();
                String mockQueryValue = mockQuery.getValue();

                if(!ObjectUtils.isEmpty(urlParam)){
                    while(urlParam.hasMoreElements()){
                        String parm=urlParam.nextElement();
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
        return queryStatus;
    }

    public  boolean getRequestTypeStatus(String mockId, HttpServletRequest request) throws IOException {
        List<Map> formdataList = getForm(request);

        String jsonData = getJson(request);
        RequestBodyMock requestBodyMock = requestBodyMockService.findRequestBodyMock(new RequestBodyMockQuery().setMockId(mockId));
        String bodyType = requestBodyMock.getBodyType();
        boolean bodyStatus = false;
        if(bodyType.equals("formdata")){
            bodyStatus = getFormStatus(mockId,formdataList);
        }else {
            bodyStatus = getJsonStatus(mockId,jsonData);
        }
        return bodyStatus;
    }

    public  boolean getFormStatus( String mockId, List<Map> formdataList){
        boolean bodyStatus = false;
        List<FormParamMock> formParamMockList = formParamMockService.findFormParamMockList(new FormParamMockQuery().setMockId(mockId));
        if(CollectionUtils.isNotEmpty(formParamMockList)){
            for(FormParamMock mockForm: formParamMockList){
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
        return bodyStatus;
    }

    public  boolean getJsonStatus(String mockId, String jsonData){
        boolean bodyStatus = false;
        List<JsonParamMock> jsonParamMockList = jsonParamMockService.findJsonParamMockList(new JsonParamMockQuery().setMockId(mockId));
        if(CollectionUtils.isNotEmpty(jsonParamMockList)){
            for(JsonParamMock jsonParamMock:jsonParamMockList){
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
        return bodyStatus;
    }

}
