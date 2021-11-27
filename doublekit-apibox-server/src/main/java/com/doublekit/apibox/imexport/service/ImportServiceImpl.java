package com.doublekit.apibox.imexport.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.doublekit.apibox.apidef.model.FormParam;
import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apidef.model.QueryParam;
import com.doublekit.apibox.apidef.model.RequestHeader;
import com.doublekit.apibox.apidef.service.FormParamService;
import com.doublekit.apibox.apidef.service.MethodService;
import com.doublekit.apibox.apidef.service.QueryParamService;
import com.doublekit.apibox.apidef.service.RequestHeaderService;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.category.service.CategoryService;
import com.doublekit.apibox.imexport.utils.Md5;
import com.doublekit.apibox.workspace.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;

@Service
public class ImportServiceImpl implements ImportService{

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MethodService methodService;

    @Autowired
    private RequestHeaderService requestHeaderService;

    @Autowired
    private QueryParamService queryParamService;

    @Autowired
    private FormParamService formParamService;

    @Override
    public String importData(String type, String workspaceId, InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        StringBuffer sb = new StringBuffer("");
        String temp = null;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        String jsonString = sb.toString();
        //把字符串转换成json
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        //添加分组
        JSONObject info = jsonObject.getJSONObject("info");
        String categoryName = info.getString("name");
        String categoryId = info.getString("_postman_id");

        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        Workspace workspace = new Workspace();
        workspace.setId(workspaceId);
        category.setWorkspace(workspace);
        categoryService.createCategory(category);


        JSONArray item = jsonObject.getJSONArray("item");
        for (int i =0 ;i<item.toArray().length;i++){
            //获取当前对象
            JSONObject obj = item.getJSONObject(i);

            //获取request对象
            JSONObject request = obj.getJSONObject("request");

            //获取url
            JSONObject url = request.getJSONObject("url");

            //获取path
            JSONArray urlPath = url.getJSONArray("path");
            String path= new String();
            for(int j=0; j<urlPath.toArray().length;j++){
                path=path+"/"+ urlPath.getString(j);
            }


            //根据path Md5加密，获取id
            String methodId = Md5.getMD5String(path);
            String methodName = obj.getString("name");
            String requestType = request.getString("method");
            Category category1 = new Category();
            category1.setId(categoryId);

            MethodEx methodEx = new MethodEx();
            methodEx.setId(methodId);
            methodEx.setName(methodName);
            methodEx.setRequestType(requestType);
            methodEx.setPath(path);
            methodEx.setCategory(category1);

            //添加接口
            methodService.createMethod(methodEx);

            JSONArray header = request.getJSONArray("header");
            if(header.toArray().length>0){
                for(int hi=0;hi<header.toArray().length;hi++){
                    JSONObject headerObj = header.getJSONObject(hi);

                    String headerName = headerObj.getString("key");
                    String headerDesc = headerObj.getString("description");
                    String headerValue = headerObj.getString("value");
                    MethodEx headerMethod = new MethodEx();
                    headerMethod.setId(methodId);

                    RequestHeader requestHeader = new RequestHeader();
                    requestHeader.setHeaderName(headerName);
                    requestHeader.setDesc(headerDesc);
                    requestHeader.setValue(headerValue);
                    requestHeader.setMethod(headerMethod);
                    requestHeaderService.createRequestHeader(requestHeader);
                }
            }

            JSONArray query = url.getJSONArray("query");
            if(query.toArray().length>0){
                for(int qi = 0;qi<query.toArray().length;qi++){
                    JSONObject queryObj = query.getJSONObject(qi);
//
                    String queryName = queryObj.getString("key");
                    String queryValue = queryObj.getString("value");
                    String queryDesc = queryObj.getString("description");
                    MethodEx queryMethod = new MethodEx();
                    queryMethod.setId(methodId);
//
                    QueryParam queryParam = new QueryParam();
                    queryParam.setParamName(queryName);
                    queryParam.setValue(queryValue);
                    queryParam.setDesc(queryDesc);
                    queryParam.setMethod(queryMethod);
                    queryParamService.createQueryParam(queryParam);
                }
            }

            JSONObject body = request.getJSONObject("body");

            JSONArray formdata = body.getJSONArray("formdata");
            if(formdata.toArray().length>0){
                for(int fi = 0; fi<formdata.toArray().length;fi++){
                    JSONObject formdataObj = query.getJSONObject(fi);

                    String formdataName = formdataObj.getString("key");
                    String formdataValue = formdataObj.getString("value");
                    String formdataType = formdataObj.getString("type");
                    String formdataDesc = formdataObj.getString("description");
                    MethodEx formdataMethod = new MethodEx();
                    formdataMethod.setId(methodId);

                    FormParam formParam = new FormParam();
                    formParam.setParamName(formdataName);
                    formParam.setValue(formdataValue);
                    formParam.setDesc(formdataDesc);
                    formParam.setDataType(formdataType);
                    formParam.setMethod(formdataMethod);
                    formParamService.createFormParam(formParam);




                }
            }


            System.out.println(methodId);
        }



        return null;
    }

}
