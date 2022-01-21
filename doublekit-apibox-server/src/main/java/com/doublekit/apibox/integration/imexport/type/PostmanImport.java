package com.doublekit.apibox.integration.imexport.type;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.doublekit.apibox.apidef.model.*;
import com.doublekit.apibox.integration.imexport.common.FunctionImport;
import com.doublekit.apibox.integration.imexport.model.*;
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

        //解析相应的字段
        JSONObject info = jsonObject.getJSONObject("info");
        String categoryName = info.getString("name");
        String categoryId = info.getString("_postman_id");

        //如果已经导入过了，覆盖之前的导入数据
        functionImport.coverCategory(workspaceId,categoryId,categoryName);

        methodAnalysis(jsonObject,categoryId);
    }

    //获取path
    private String actPath(JSONArray urlPath){
        String path= new String();
        for(int j=0; j<urlPath.size();j++){
            path=path+"/"+ urlPath.getString(j);
        }
        return path;
    }

    private void methodAnalysis(JSONObject jsonObject, String categoryId){
        JSONArray item = jsonObject.getJSONArray("item");
        for (int i =0 ;i<item.toArray().length;i++){
            //获取当前对象
            JSONObject obj = item.getJSONObject(i);
            //获取request对象
            JSONObject request = obj.getJSONObject("request");
            //获取request中的对象url
            JSONObject url = request.getJSONObject("url");
            //获取request中的对象path
            String path = actPath(url.getJSONArray("path"));
            //根据path Md5加密，获取id
            String methodId = Md5.getMD5String(path+"postman");//后面加postman,防止与其他导入类型id相同

            actMethod(obj,categoryId,methodId);

            actHeader(request,methodId);

            actQuery(url,methodId);

            actBody(request,methodId);
        }
    }

    //操作method
    private void actMethod( JSONObject obj, String categoryId, String methodId){
        JSONObject request = obj.getJSONObject("request");
        JSONObject url = request.getJSONObject("url");

        MethodImportVo methodImportVo = new MethodImportVo();

        methodImportVo.setMethodId(methodId);
        methodImportVo.setCategoryId(categoryId);
        methodImportVo.setName(obj.getString("name"));
        methodImportVo.setRequestType(request.getString("method"));
        methodImportVo.setPath(actPath(url.getJSONArray("path")));

        functionImport.addMethod(methodImportVo);
    }

    //添加header
    private void actHeader(JSONObject request, String methodId){
        if(request.containsKey("header")){
            JSONArray header = request.getJSONArray("header");
            for(int hi=0;hi<header.toArray().length;hi++){
                JSONObject headerObj = header.getJSONObject(hi);

                HeaderParamImportVo hVo = new HeaderParamImportVo();
                hVo.setMethodId(methodId);
                hVo.setName(headerObj.getString("key"));
                hVo.setValue(headerObj.getString("value"));
                hVo.setDesc(headerObj.getString("description"));

                functionImport.addHeader(hVo);
            }
        }
    }

    //添加query参数
    private void actQuery(JSONObject url, String methodId){
        if(url.containsKey("query")){
            JSONArray query = url.getJSONArray("query");
            for(int qi = 0;qi<query.toArray().length;qi++){
                JSONObject queryObj = query.getJSONObject(qi);

                QueryParamImportVo qVo = new QueryParamImportVo();
                qVo.setMethodId(methodId);
                qVo.setName(queryObj.getString("key"));
                qVo.setValue(queryObj.getString("value"));
                qVo.setDesc(queryObj.getString("description"));

                functionImport.addQuery(qVo);
            }
        }
    }

    //添加requestBody
    private void actBody(JSONObject request,  String methodId){
        //获取request中的对象body
        JSONObject body = request.getJSONObject("body");
        if(request.containsKey("body")){
            String requestBody = transferBodyType(body.getString("mode"));
            functionImport.addBody(requestBody,methodId);

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
            functionImport.addBody("none",methodId);
        }
    }

    //添加formdata
    private void actFormData(JSONObject body, String methodId){
        if(body.containsKey("formdata")){
            JSONArray formParam = body.getJSONArray("formdata");
            for(int fi = 0; fi<formParam.toArray().length;fi++){
                JSONObject formParamObj = formParam.getJSONObject(fi);

                FormDataImportVo formData = new FormDataImportVo();
                formData.setName(formParamObj.getString("key"));
                formData.setValue(formParamObj.getString("value"));
                formData.setType(formParamObj.getString("type"));
                formData.setDesc(formParamObj.getString("description"));
                formData.setRequired(0);
                formData.setMethodId(methodId);

                functionImport.addFormData(formData);
//                String formParamName = formParamObj.getString("key");
//                String formParamValue = formParamObj.getString("value");
//                String formParamType = formParamObj.getString("type");
//                String formParamDesc = formParamObj.getString("description");
//                int required = 0;//postman没有是否必须，就全部设为不必须

//                functionImport.actFormData(methodId,formParamName,formParamValue,formParamType,formParamDesc,required);
            }
        }
    }

    //添加formurlencoded
    private void actFormUrlencoded(JSONObject body, String methodId){
        if(body.containsKey("urlencoded")){
            JSONArray urlencoded = body.getJSONArray("urlencoded");
            for(int urlencodedi = 0;urlencodedi<urlencoded.toArray().length;urlencodedi++){
                JSONObject urlObj = urlencoded.getJSONObject(urlencodedi);

                FormUrlencodedImportVo fUVo = new FormUrlencodedImportVo();
                fUVo.setMethodId(methodId);
                fUVo.setName(urlObj.getString("key"));
                fUVo.setValue(urlObj.getString("value"));
                fUVo.setDataType(urlObj.getString("type"));
                fUVo.setRequired(0);//postman没有是否必须，就全部设为不必须
                fUVo.setDesc(urlObj.getString("description"));

                functionImport.addFormUrlencoded(fUVo);
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

            functionImport.addRaw(methodId,rawType,rawData);
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
