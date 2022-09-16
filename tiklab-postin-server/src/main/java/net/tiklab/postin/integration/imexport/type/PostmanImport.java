package net.tiklab.postin.integration.imexport.type;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.tiklab.postin.integration.imexport.common.FunctionImport;
import net.tiklab.postin.integration.imexport.model.*;
import net.tiklab.postin.integration.imexport.model.*;
import net.tiklab.postin.integration.imexport.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class PostmanImport {

    @Autowired
    FunctionImport functionImport;

    //postman解析
    public void analysisPostmanData( String workspaceId, InputStream stream) throws IOException {

        JSONObject jsonObject =functionImport.getJsonData(stream);

        //解析相应的字段
        JSONObject info = jsonObject.getJSONObject("info");
        String categoryName = info.getString("name");
        String categoryId = info.getString("_postman_id");

        //如果已经导入过了，覆盖之前的导入数据
        functionImport.coverCategory(workspaceId,categoryId,categoryName);

        analysisData(jsonObject,categoryId);
    }

    //获取path
    private String getPath(JSONArray urlPath){
        String path= new String();
        for(int j=0; j<urlPath.size();j++){
            path=path+"/"+ urlPath.getString(j);
        }
        return path;
    }

    //解析数据
    private void analysisData(JSONObject jsonObject, String categoryId){
        JSONArray item = jsonObject.getJSONArray("item");
        for (int i =0 ;i<item.toArray().length;i++){
            //获取当前对象
            JSONObject obj = item.getJSONObject(i);

            JSONObject request = obj.getJSONObject("request");//获取request对象
            JSONObject url = request.getJSONObject("url");//获取request中的对象url
            String path = getPath(url.getJSONArray("path")); //获取request中的对象path
            String methodId = Md5.getMD5String(path+"postman");//根据path Md5加密，获取methodId。后面加postman,防止与其他导入类型id相同

            //1.解析的method数据添加到数据库
            analysisMethod(obj,categoryId,methodId);

            //2.解析header数据添加到数据库
            analysisHeader(request,methodId);

            //3.解析query数据添加到数据库
            analysisQuery(url,methodId);

            //4.解析body数据添加到数据库
            analysisBody(request,methodId);
        }
    }

    //操作method
    private void analysisMethod( JSONObject obj, String categoryId, String methodId){
        JSONObject request = obj.getJSONObject("request");
        JSONObject url = request.getJSONObject("url");

        ApixImportVo apixImportVo = new ApixImportVo();

        apixImportVo.setMethodId(methodId);
        apixImportVo.setCategoryId(categoryId);
        apixImportVo.setName(obj.getString("name"));
        apixImportVo.setRequestType(request.getString("method"));
        apixImportVo.setPath(getPath(url.getJSONArray("path")));

        functionImport.addMethod(apixImportVo);
    }

    //header
    private void analysisHeader(JSONObject request, String methodId){
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

    //query参数
    private void analysisQuery(JSONObject url, String methodId){
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

    //requestBody
    private void analysisBody(JSONObject request,  String methodId){
        //获取request中的对象body
        JSONObject body = request.getJSONObject("body");
        if(request.containsKey("body")){
            String requestBody = transferBodyType(body.getString("mode"));
            functionImport.addBody(requestBody,methodId);

            switch (requestBody){
                case "formdata":
                    analysisFormData(body,methodId);
                    break;
                case "raw":
                    analysisRaw(body,methodId);
                    break;
                case "formUrlencoded":
                    analysisFormUrlencoded(body,methodId);
                    break;
            }
        }else {
            functionImport.addBody("none",methodId);
        }
    }

    //formdata
    private void analysisFormData(JSONObject body, String methodId){
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
            }
        }
    }

    //formurlencoded
    private void analysisFormUrlencoded(JSONObject body, String methodId){
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

    //raw: json,html,xml,javascript
    private void analysisRaw(JSONObject body, String methodId){
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
