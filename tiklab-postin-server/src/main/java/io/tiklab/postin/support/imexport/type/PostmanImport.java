package io.tiklab.postin.support.imexport.type;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.postin.api.http.definition.service.*;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.support.imexport.common.FunctionImport;
import io.tiklab.postin.workspace.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * postman 导入
 */
@Component
public class PostmanImport {

    @Autowired
    FunctionImport functionImport;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApixService apixService;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    ApiRequestService apiRequestService;

    @Autowired
    FormParamService formParamService;

    @Autowired
    FormUrlencodedService formUrlencodedService;


    @Autowired
    RawParamService rawParamService;

    @Autowired
    ApiResponseService apiResponseService;

    @Autowired
    JsonResponseService jsonResponseService;


    private String workspaceIds;

    private String categoryId;

    /**
     * postman解析
     */
    public void analysisPostmanData( String workspaceId, InputStream stream) {
        workspaceIds =workspaceId;
        try{
            JSONObject jsonObject =functionImport.getJsonData(stream);

            //解析相应的字段
            JSONObject info = jsonObject.getJSONObject("info");
            String categoryName = info.getString("name");
//            String categoryId = info.getString("_postman_id").substring(0,12);

            //导入分组
            Workspace workspace = new Workspace();
            workspace.setId(workspaceId);
            Category category = new Category();

            category.setName(categoryName);
            category.setWorkspace(workspace);

            categoryId = categoryService.createCategory(category);


            analysisData(jsonObject,categoryId);
        }catch (Exception e){
            throw new ApplicationException("解析失败",e);
        }

    }

    /**
     * 获取path
     */
    private String getPath(JSONArray urlPath){
        StringBuilder path= new StringBuilder();
        for(int j=0; j<urlPath.size();j++){
            path.append("/").append(urlPath.getString(j));
        }
        return path.toString();
    }

    /**
     *  解析数据
     */
    private void analysisData(JSONObject jsonObject, String categoryId){
        JSONArray item = jsonObject.getJSONArray("item");
        for (int i =0 ;i<item.toArray().length;i++){
            //获取当前对象
            JSONObject obj = item.getJSONObject(i);

            loopItem(obj,categoryId);
        }
    }

    private void loopItem(JSONObject objItem,String categoryId){
        //名称
        String name = objItem.getString("name");

        if(objItem.containsKey("request")){
            JSONObject request = objItem.getJSONObject("request");
            //请求方式 get、post、put、delete等
            String method = request.getString("method");

            //描述
            String desc = null;
            if(request.containsKey("description")){
                desc = request.getString("description");
            }

            //添加接口返回id
            String httpApiId = "";

            //请求地址
            String path = null;
            Object url = request.get("url");
            if(url instanceof String){
                path = request.getString("url");

                //添加接口返回id
                httpApiId = addApi(categoryId, name, path,method,desc);
            }else {
                JSONObject urlObject = request.getJSONObject("url");
                JSONArray pathArray =urlObject.getJSONArray("path");
                path = getPath(pathArray);

                //添加接口返回id
                httpApiId = addApi(categoryId, name, path,method,desc);

                //添加query
                if(urlObject.containsKey("query")){
                    JSONArray query = urlObject.getJSONArray("query");
                    addQuery(query,httpApiId);
                }
            }

            //添加header
            if(request.containsKey("header")){
                addHeader(request, httpApiId);
            }

            //添加body
            addRequestBody(request, httpApiId);
        }

        //如果有子目录
        if(objItem.containsKey("item")){
            //先创建子目录
            Category category = new Category();
            category.setName(name);

            Category parent = new Category();
            parent.setId(categoryId);
            category.setParent(parent);
            Workspace workspaceModel = new Workspace();
            workspaceModel.setId(workspaceIds);
            category.setWorkspace(workspaceModel);
            String categoryChildId = categoryService.createCategory(category);

            //遍历子目录下的每一项
            JSONArray itemList = objItem.getJSONArray("item");
            for(int i = 0;i<itemList.toArray().length;i++){
                JSONObject childItem = itemList.getJSONObject(i);

                loopItem( childItem,categoryChildId);
            }
        }
    }


    /**
     * 添加接口
     */
    private  String addApi(String categoryId,String name,String path,String method,String desc){

        HttpApi httpApi = new HttpApi();
        httpApi.setPath(path);
        httpApi.setMethodType(method.toLowerCase());

        Apix apix = new Apix();
        apix.setName(name);
        apix.setDesc(desc);
        apix.setWorkspaceId(workspaceIds);
        Category category = new Category();
        category.setId(categoryId);
        apix.setCategory(category);

        httpApi.setApix(apix);

        String httpApiId = httpApiService.createHttpApi(httpApi);

        return httpApiId;
    }

    /**
     * 解析请求头 header
     */
    private void addHeader(JSONObject request, String methodId){
        if(request.containsKey("header")){
            JSONArray header = request.getJSONArray("header");
            for(int hi=0;hi<header.toArray().length;hi++){
                JSONObject headerObj = header.getJSONObject(hi);

                RequestHeader requestHeader = new RequestHeader();

                requestHeader.setHttp(new HttpApi().setId(methodId));
                requestHeader.setHeaderName(headerObj.getString("key"));
                requestHeader.setValue(headerObj.getString("value"));
                requestHeader.setRequired(1);
                requestHeader.setDesc(headerObj.getString("description"));

                requestHeaderService.createRequestHeader(requestHeader);
            }
        }
    }

    /**
     * 创建query参数
     */
    private void addQuery(JSONArray query, String methodId){
            for(int qi = 0;qi<query.toArray().length;qi++){
                JSONObject queryObj = query.getJSONObject(qi);

                QueryParam queryParam = new QueryParam();
                queryParam.setHttp(new HttpApi().setId(methodId));
                queryParam.setParamName(queryObj.getString("key"));
                queryParam.setValue(queryObj.getString("value"));
                queryParam.setRequired(1);
                queryParam.setDesc(queryObj.getString("description"));

                queryParamService.createQueryParam(queryParam);
            }
    }

    /**
     * 解析请求体
     * @param request
     * @param methodId
     */
    private void addRequestBody(JSONObject request,  String methodId){
        //获取request中的对象body
        if(request.containsKey("body")){
            JSONObject body = request.getJSONObject("body");
            String requestBody = transferBodyType(body.getString("mode"));
            addRequest(requestBody,methodId);

            switch (requestBody){
                case "formdata":
                    addFormData(body,methodId);
                    break;
                case "formUrlencoded":
                    addFormUrlencoded(body,methodId);
                    break;
                case "raw":
                    addRaw(body,methodId);
                    break;
            }
        }else {
            addRequest("none",methodId);
        }
    }

    /**
     * 导入请求参数
     * @param requestBody
     * @param methodId
     */
    public void addRequest(String requestBody,String methodId){
        ApiRequest apiRequest = new ApiRequest();

        apiRequest.setId(methodId);
        apiRequest.setBodyType(requestBody);
        apiRequest.setHttpId(methodId);

        apiRequestService.updateApiRequest(apiRequest);
    }


    /**
     * 解析formdata
     * @param body
     * @param methodId
     */
    private void addFormData(JSONObject body, String methodId){
        if(body.containsKey("formdata")){
            JSONArray formParam = body.getJSONArray("formdata");
            for(int fi = 0; fi<formParam.toArray().length;fi++){
                JSONObject formParamObj = formParam.getJSONObject(fi);

                FormParam formParams = new FormParam();

                formParams.setHttp(new HttpApi().setId(methodId));
                formParams.setParamName(formParamObj.getString("key"));
                formParams.setValue(formParamObj.getString("value"));
                formParams.setDataType(formParamObj.getString("type"));
                formParams.setDesc(formParamObj.getString("description"));
                formParams.setRequired(0);

                formParamService.createFormParam(formParams);
            }
        }
    }

    /**
     * 解析formurl
     * @param body
     * @param methodId
     */
    private void addFormUrlencoded(JSONObject body, String methodId){
        if(body.containsKey("urlencoded")){
            JSONArray urlencoded = body.getJSONArray("urlencoded");
            for(int urlencodedi = 0;urlencodedi<urlencoded.toArray().length;urlencodedi++){
                JSONObject urlObj = urlencoded.getJSONObject(urlencodedi);

                FormUrlencoded formUrlencoded = new FormUrlencoded();
                formUrlencoded.setHttp(new HttpApi().setId(methodId));
                formUrlencoded.setParamName(urlObj.getString("key"));
                formUrlencoded.setValue(urlObj.getString("value"));
                formUrlencoded.setDataType("string");
                formUrlencoded.setRequired(1);//postman没有是否必须，就全部设为必须
                formUrlencoded.setDesc(urlObj.getString("description"));

                formUrlencodedService.createFormUrlencoded(formUrlencoded);
            }
        }
    }

    /**
     *  解析 raw: json,html,xml,javascript
     */
    private void addRaw(JSONObject body, String methodId){
        if(body.containsKey("raw")){

            String rawType = "";
            if(body.containsKey("options")){
                JSONObject options = body.getJSONObject("options");
                JSONObject raw = options.getJSONObject("raw");
                String rawTypeString = raw.getString("language");
                rawType = transferRawType(rawTypeString);
            }else {
                rawType = "text/xml";
            }

            String rawData = body.getString("raw");

            RawParam rawParam = new RawParam();
            rawParam.setRaw(rawData);
            rawParam.setType(rawType);
            rawParam.setId(methodId);
            rawParam.setHttp(new HttpApi().setId(methodId));

            rawParamService.createRawParam(rawParam);
        }
    }


    /**
     * 转换请求体类型
     */
    private String transferBodyType(String bodyType){
        switch (bodyType){
            case "raw":
                return "raw";
            case "formdata":
                return "formdata";
            case "urlencoded":
                return "formUrlencoded";
            case "file":
                return "none";
        }
        return "none";
    }


    /**
     *  解析 raw: json,html,xml,javascript
     */
    private String transferRawType(String rawType){
        return switch (rawType) {
            case "json" -> "application/json";
            case "html" -> "text/html";
            case "xml" -> "text/xml";
            case "javascript" -> "application/javascript";
            default -> "text/plain";
        };
    }



}
