package com.doublekit.apibox.imexport.type;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.doublekit.apibox.apidef.model.*;
import com.doublekit.apibox.apidef.service.*;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.category.model.CategoryQuery;
import com.doublekit.apibox.category.service.CategoryService;
import com.doublekit.apibox.imexport.common.FunctionImport;
import com.doublekit.apibox.imexport.utils.Md5;
import com.doublekit.apibox.workspace.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class PostmanImport {

    @Autowired
    FunctionImport functionImport;

    @Autowired
    CategoryService categoryService;

    @Autowired
    MethodService methodService;

    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    RequestBodyService requestBodyService;

    @Autowired
    FormParamService formParamService;

    @Autowired
    FormUrlencodedService formUrlencodedService;

    @Autowired
    RawParamService rawParamService;

    public void importData( String workspaceId, InputStream stream) throws IOException {
        JSONObject jsonObject =functionImport.getJsonData(stream);

        //解析
        JSONObject info = jsonObject.getJSONObject("info");
        String categoryName = info.getString("name");
        String categoryId = info.getString("_postman_id");

        //如果有已存在的先删除
        coverCategory(categoryId,workspaceId);

        //再添加新导入的
        addCategroy(workspaceId,categoryId,categoryName);


        JSONArray item = jsonObject.getJSONArray("item");
        for (int i =0 ;i<item.toArray().length;i++){
            //获取当前对象
            JSONObject obj = item.getJSONObject(i);

            //获取request对象
            JSONObject request = obj.getJSONObject("request");
            //获取request中的对象url
            JSONObject url = request.getJSONObject("url");
            //获取request中的对象body
            JSONObject body = request.getJSONObject("body");
            //获取request中的对象path
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
            methodService.createMethod(methodEx);

            //添加header
            if(request.containsKey("header")){
                JSONArray header = request.getJSONArray("header");
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

            //添加query参数
            if(url.containsKey("query")){
                JSONArray query = url.getJSONArray("query");
                for(int qi = 0;qi<query.toArray().length;qi++){
                    JSONObject queryObj = query.getJSONObject(qi);

                    String queryName = queryObj.getString("key");
                    String queryValue = queryObj.getString("value");
                    String queryDesc = queryObj.getString("description");
                    MethodEx queryMethod = new MethodEx();
                    queryMethod.setId(methodId);

                    QueryParam queryParam = new QueryParam();
                    queryParam.setParamName(queryName);
                    queryParam.setValue(queryValue);
                    queryParam.setDesc(queryDesc);
                    queryParam.setMethod(queryMethod);
                    queryParamService.createQueryParam(queryParam);
                }
            }

            if(request.containsKey("body")){
                //添加requestBody
                if(body.containsKey("mode")){
                    String requestBody = body.getString("mode");
                    MethodEx requestBodyMethod = new MethodEx();
                    requestBodyMethod.setId(methodId);

                    RequestBodyEx requestBodyEx = new RequestBodyEx();
                    requestBodyEx.setId(methodId);
                    requestBodyEx.setBodyType(requestBody);
                    requestBodyEx.setMethod(requestBodyMethod);
                    requestBodyService.createRequestBody(requestBodyEx);
                }

                //添加formdata
                if(body.containsKey("formdata")){
                    JSONArray formParam = body.getJSONArray("formdata");
                    for(int fi = 0; fi<formParam.toArray().length;fi++){
                        JSONObject formParamObj = formParam.getJSONObject(fi);

                        String formParamName = formParamObj.getString("key");
                        String formParamValue = formParamObj.getString("value");
                        String formParamType = formParamObj.getString("type");
                        String formParamDesc = formParamObj.getString("description");

                        MethodEx formParamMethod = new MethodEx();
                        formParamMethod.setId(methodId);
                        FormParam formParams = new FormParam();
                        formParams.setParamName(formParamName);
                        formParams.setValue(formParamValue);
                        formParams.setDesc(formParamDesc);
                        formParams.setDataType(formParamType);
                        formParams.setMethod(formParamMethod);
                        formParamService.createFormParam(formParams);
                    }
                }

                //添加formurlencoded
                if(body.containsKey("urlencoded")){
                    JSONArray urlencoded = body.getJSONArray("urlencoded");
                    for(int urlencodedi = 0;urlencodedi<urlencoded.toArray().length;urlencodedi++){
                        JSONObject urlObj = urlencoded.getJSONObject(urlencodedi);

                        String urlencodedName = urlObj.getString("key");
                        String urlencodedValue = urlObj.getString("value");
                        String urlencodedType = urlObj.getString("type");
                        String urlencodedDesc = urlObj.getString("description");
                        MethodEx urlencodedMethod = new MethodEx();
                        urlencodedMethod.setId(methodId);

                        FormUrlencoded formUrlencoded = new FormUrlencoded();
                        formUrlencoded.setParamName(urlencodedName);
                        formUrlencoded.setDataType(urlencodedType);
                        formUrlencoded.setValue(urlencodedValue);
                        formUrlencoded.setDesc(urlencodedDesc);
                        formUrlencodedService.createFormUrlencoded(formUrlencoded);
                    }
                }

                //添加raw: json,html,xml,javascript
                if(body.containsKey("raw")){
                    JSONObject options = body.getJSONObject("options");
                    JSONObject raw = options.getJSONObject("raw");
                    String rawType = raw.getString("language");
                    String rawData = body.getString("raw");
                    MethodEx rawMethod = new MethodEx();
                    rawMethod.setId(methodId);

                    RawParam rawParam = new RawParam();
                    rawParam.setRaw(rawData);
                    rawParam.setType(rawType);
                    rawParam.setId(methodId);
                    rawParam.setMethod(rawMethod);
                    rawParamService.createRawParam(rawParam);
                }
            }
        }

    }


    public void coverCategory(String categoryId, String workspaceId){
        List<Category> categoryListTree = categoryService.findCategoryListTree(new CategoryQuery().setWorkspaceId(workspaceId));
        for(Category category:categoryListTree){
            String categoryDataId = category.getId();
            if(categoryDataId.equals(categoryId)){
                categoryService.deleteCategory(categoryId);
            }
        }
    }

    public void addCategroy(String workspaceId, String categoryId, String categoryName){
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        Workspace workspace = new Workspace();
        workspace.setId(workspaceId);
        category.setWorkspace(workspace);
        categoryService.createCategory(category);
    }
}
