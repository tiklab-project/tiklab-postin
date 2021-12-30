package com.doublekit.apibox.integration.imexport.type;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.doublekit.apibox.apidef.model.*;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.integration.imexport.common.FunctionImport;
import com.doublekit.apibox.integration.imexport.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class PostmanImport {

    @Autowired
    FunctionImport functionImport;

    //postman解析
    public void postmanAnalysisData( String workspaceId, InputStream stream) throws IOException {
        JSONObject jsonObject =functionImport.getJsonData(stream);

        //解析
        JSONObject info = jsonObject.getJSONObject("info");
        String categoryName = info.getString("name");
        String categoryId = info.getString("_postman_id");

        functionImport.coverCategory(workspaceId,categoryId,categoryName);

        JSONArray item = jsonObject.getJSONArray("item");
        action(item,categoryId);
    }

    //获取path
    private String actPath(JSONArray urlPath){
        String path= new String();
        for(int j=0; j<urlPath.size();j++){
            path=path+"/"+ urlPath.getString(j);
        }
        return path;
    }

    private void action(JSONArray item, String categoryId){
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
            String path = actPath(url.getJSONArray("path"));
            //根据path Md5加密，获取id
            String methodId = Md5.getMD5String(path);

            actMethod(path,obj,request,categoryId,methodId);

            actHeader(request,methodId);

            actQuery(url,methodId);

            actBody(request,body,methodId);
        }
    }

    //操作method
    private void actMethod(String path, JSONObject obj, JSONObject request, String categoryId, String methodId){
        String methodName = obj.getString("name");
        String requestType = request.getString("method");
        Category categoryID = new Category();
        categoryID.setId(categoryId);
        String desc = null;
        functionImport.addMethod(methodId,methodName,requestType,path,categoryID,desc);
    }

    //添加header
    private void actHeader(JSONObject request, String methodId){
        if(request.containsKey("header")){
            JSONArray header = request.getJSONArray("header");
            for(int hi=0;hi<header.toArray().length;hi++){
                JSONObject headerObj = header.getJSONObject(hi);

                String headerName = headerObj.getString("key");
                String headerDesc = headerObj.getString("description");
                String headerValue = headerObj.getString("value");
                MethodEx headerMethod = new MethodEx();
                headerMethod.setId(methodId);
                functionImport.addHeader(headerName,headerValue,headerDesc,headerMethod);

            }
        }
    }

    //添加query参数
    private void actQuery(JSONObject url, String methodId){
        if(url.containsKey("query")){
            JSONArray query = url.getJSONArray("query");
            for(int qi = 0;qi<query.toArray().length;qi++){
                JSONObject queryObj = query.getJSONObject(qi);

                String queryName = queryObj.getString("key");
                String queryValue = queryObj.getString("value");
                String queryDesc = queryObj.getString("description");
                MethodEx queryMethod = new MethodEx();
                queryMethod.setId(methodId);

                functionImport.addQuery(queryName,queryValue,queryDesc,queryMethod);

            }
        }
    }

    //添加requestBody
    private void actBody(JSONObject request, JSONObject body, String methodId){
        if(request.containsKey("body")){
            String requestBody = transferBodyType(body.getString("mode"));
            functionImport.actBody(requestBody,methodId);

            switch (requestBody){
                case "formdata":
                    actFormData(body,methodId);
                    break;
                case "raw":
                    actRaw(body,methodId);
                    break;
                case "formUrlencoded":
                    actFormUrlencoded(body,methodId);
                    break;
            }
        }else {
            functionImport.actBody("none",methodId);
        }
    }

    //添加formdata
    private void actFormData(JSONObject body, String methodId){
        if(body.containsKey("formdata")){
            JSONArray formParam = body.getJSONArray("formdata");
            for(int fi = 0; fi<formParam.toArray().length;fi++){
                JSONObject formParamObj = formParam.getJSONObject(fi);

                String formParamName = formParamObj.getString("key");
                String formParamValue = formParamObj.getString("value");
                String formParamType = formParamObj.getString("type");
                String formParamDesc = formParamObj.getString("description");
                int required = 0;//postman没有是否必须，就全部设为不必须

                functionImport.actFormData(methodId,formParamName,formParamValue,formParamType,formParamDesc,required);
            }
        }
    }

    //添加formurlencoded
    private void actFormUrlencoded(JSONObject body, String methodId){
        if(body.containsKey("urlencoded")){
            JSONArray urlencoded = body.getJSONArray("urlencoded");
            for(int urlencodedi = 0;urlencodedi<urlencoded.toArray().length;urlencodedi++){
                JSONObject urlObj = urlencoded.getJSONObject(urlencodedi);

                String name = urlObj.getString("key");
                String value = urlObj.getString("value");
                String type = urlObj.getString("type");
                String desc = urlObj.getString("description");
                int required = 0;//postman没有是否必须，就全部设为不必须
                functionImport.actFormUrlencoded(methodId,name,value,type,desc,required);
            }
        }
    }

    //添加raw: json,html,xml,javascript
    private void actRaw(JSONObject body, String methodId){
        if(body.containsKey("raw")){
            JSONObject options = body.getJSONObject("options");
            JSONObject raw = options.getJSONObject("raw");
            String rawType = raw.getString("language");
            String rawData = body.getString("raw");

            functionImport.actRaw(methodId,rawType,rawData);
        }
    }

    //转换请求体类型
    private String transferBodyType(String bodyType){
        switch (bodyType){
            case "raw":
                return "raw";
            case "formdata":
                return "formdata";
            case "urlencoded":
                return "formUrlencoded";
            case "file":
                return "binary";
        }
        return "none";
    }

}
