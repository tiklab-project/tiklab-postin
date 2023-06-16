package io.tiklab.postin.api.http.mock.servlet;

import com.alibaba.fastjson.JSONPath;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.http.mock.model.*;
import io.tiklab.postin.api.http.mock.service.*;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.model.CategoryQuery;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.core.exception.SystemException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * mock
 * servlet 请求处理
 */
@Service
public class MockServletRequest {

    @Autowired
    MockService mockService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApixService apixService;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    RequestHeaderMockService requestHeaderMockService;

    @Autowired
    QueryParamMockService queryParamMockService;

    @Autowired
    RequestMockService requestMockService;

    @Autowired
    FormParamMockService formParamMockService;

    @Autowired
    JsonParamMockService jsonParamMockService;

    @Autowired
    MockServletResponse mockServletResponse;


    /**
     * 请求处理
     * @param request
     * @param response
     * @throws IOException
     */
    public void actRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取路径
        String mockPath = request.getRequestURI().replaceAll("/mockx","");

        mockOperate(request,response,mockPath);

    }

    /**
     *
     * @param request
     * @param response
     * @param mockPath
     * @throws IOException
     */
    private void mockOperate(HttpServletRequest request, HttpServletResponse response, String mockPath) throws IOException {
        //workspaceId
        String workspaceId = mockPath.substring(1,12);
        //接口路径
        String path = mockPath.substring(12);
        String methodId = getMethodId(workspaceId,path,"http");


        MockQuery mockQuery = new MockQuery().setHttpId(methodId);
        //通过methodid查询所有mock
        List<Mock> mockList = mockService.findMockList(mockQuery);
        for(Mock mock:mockList){
            String mockId = mock.getId();
            int enabled = mock.getEnable();
            //启用：1, 停用：0
            if(enabled == 1){

                //获取Header状态
                boolean headerStatus = getHeaderStatus( mockId, request);

                //获取query状态
                boolean queryStatus = getQueryStatus( mockId, request);

                //获取body状态
                boolean bodyStatus = getRequestTypeStatus(mockId,request);

                //如果都匹配返回数据
                if(headerStatus&&queryStatus&&bodyStatus){
                    mockServletResponse.actResponse(mockId,response);
                }
            }
        }
    }

    //获取methodId
    public  String getMethodId(String workspaceId, String path,String protocolType){
        CategoryQuery categoryQuery = new CategoryQuery().setWorkspaceId(workspaceId);
        List<Category> categoryList = categoryService.findCategoryList(categoryQuery);
        String httpApiId = null;
        for (Category category:categoryList){

            //查询所有定义
            List<Apix> apixList = apixService.findApixList(new ApixQuery().setCategoryId(category.getId()));
            if(CollectionUtils.isEmpty(apixList)){
                continue;
            }

            //获取所有http定义
            List<Apix> httpList = apixList.stream().filter(a -> protocolType.equals(a.getProtocolType())).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(httpList)){
                continue;
            }

            //通过path找到相应的定义id
            for(Apix apix : httpList){
                //通过apix 查询对应的httpApi，用于获取path
                HttpApi httpApi = httpApiService.findHttpApi(apix.getId());

                if(path.equals(httpApi.getPath())){
                    //apix 与 定义的id相同
                    httpApiId=apix.getId();
                }
            }

        }
        return httpApiId;
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
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            servletFileUpload.setHeaderEncoding("UTF-8");
            List<FileItem> fileItems = servletFileUpload.parseRequest((RequestContext) request);

            if(CollectionUtils.isNotEmpty(fileItems)){
                for(FileItem item :fileItems){
                    //判断是否是普通类型
                    if (item.isFormField()) {
                        Map<String, String> formdatavalue = new HashMap<>();
                        String value = item.getString("utf-8");
                        formdatavalue.put(item.getFieldName(),value);
                        formdataList.add(formdatavalue);
                    }
                }
            }
        } catch (FileUploadException | UnsupportedEncodingException e) {
            throw new ApplicationException(e);
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
        return formdataList;
    }

    /**
     * 获取json
     */
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


    /**
     * 头部是否匹配成功
     * @param mockId
     * @param request
     * @return
     */
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
                    }
                }

            }
        }else {
            headerStatus=true;
        }
        return headerStatus;
    }

    /**
     * 查询参数是否匹配成功
     * @param mockId
     * @param request
     * @return
     */
    public  boolean getQueryStatus(String mockId,  HttpServletRequest request){
        Enumeration<String> urlParam = getUrlParam(request);

        boolean queryStatus = false;

        QueryParamMockQuery queryParamMockQuery = new QueryParamMockQuery().setMockId(mockId);
        List<QueryParamMock> queryParamMockList = queryParamMockService.findQueryParamMockList(queryParamMockQuery);

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
                        }
                    }
                }
            }
        }else {
            queryStatus=true;
        }
        return queryStatus;
    }

    /**
     * 判断请求体类型
     * @param mockId
     * @param request
     * @return
     * @throws IOException
     */
    public  boolean getRequestTypeStatus(String mockId, HttpServletRequest request) throws IOException {
        RequestMock requestMock = requestMockService.findRequestMock(mockId);
        String bodyType = requestMock.getBodyType();

        boolean bodyStatus = false;

        if(bodyType.equals("formdata")){
            bodyStatus = getFormStatus(mockId,request);
        }else {
            bodyStatus = getJsonStatus(mockId,request);
        }

        return bodyStatus;
    }

    /**
     * formdata 是否匹配成功
     * @param mockId
     * @param request
     * @return
     */
    public  boolean getFormStatus(String mockId, HttpServletRequest request){
        //请求参数中获取的formlist
        List<Map> formdataList = getForm(request);

        boolean bodyStatus = false;

        //数据库获取的formlist
        FormParamMockQuery formParamMockQuery = new FormParamMockQuery().setMockId(mockId);
        List<FormParamMock> formParamMockList = formParamMockService.findFormParamMockList(formParamMockQuery);

        if(CollectionUtils.isNotEmpty(formParamMockList)){
            for(FormParamMock mockForm: formParamMockList){
                String formName = mockForm.getParamName();
                String formValue = mockForm.getValue();

                if(CollectionUtils.isEmpty(formdataList)){
                    break;
                }

                for(Map<String, Object> map:formdataList){
                    for(String key : map.keySet()){
                        if(formName.equals(key)&&formValue.equals(map.get(key))){
                            bodyStatus=true;
                        }
                    }
                }

            }
        }else {
            bodyStatus=true;
        }

        return bodyStatus;
    }

    /**
     * json是否匹配成功
     * @param mockId
     * @param request
     * @return
     * @throws IOException
     */
    public  boolean getJsonStatus(String mockId, HttpServletRequest request) throws IOException {
        //请求参数中获取的json
        String jsonData = getJson(request);

        boolean bodyStatus = false;

        //数据库中获取的jsonlist
        JsonParamMockQuery jsonParamMockQuery = new JsonParamMockQuery().setMockId(mockId);
        List<JsonParamMock> jsonParamMockList = jsonParamMockService.findJsonParamMockList(jsonParamMockQuery);

        if(CollectionUtils.isNotEmpty(jsonParamMockList)){
            for(JsonParamMock jsonParamMock:jsonParamMockList){
                String jsonKey = jsonParamMock.getExp();
                String jsonValue = jsonParamMock.getValue();

                Object jsonDataValue =  JSONPath.read(jsonData,"$."+jsonKey);

                if(!jsonValue.equals(jsonDataValue)){
                    bodyStatus = false;
                    break;
                }

                bodyStatus=true;
            }
        }else {
            bodyStatus=true;
        }

        return bodyStatus;
    }

}