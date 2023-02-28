package net.tiklab.postin.support.imexport.common;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.postin.api.apix.model.Apix;
import net.tiklab.postin.api.apix.service.ApixService;
import net.tiklab.postin.api.http.definition.model.*;
import net.tiklab.postin.api.http.definition.service.*;
import net.tiklab.postin.category.model.Category;
import net.tiklab.postin.category.model.CategoryQuery;
import net.tiklab.postin.category.service.CategoryService;
import net.tiklab.postin.support.imexport.model.*;
import net.tiklab.postin.workspace.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 公共的导入
 */
@Component
public class FunctionImport {

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
    JsonParamService jsonParamService;

    @Autowired
    RawParamService rawParamService;

    @Autowired
    ApiResponseService apiResponseService;

    @Autowired
    JsonResponseService jsonResponseService;

    /**
     * 获取导入文件数据
     */
    public JSONObject getJsonData(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuffer sb = new StringBuffer("");
        String temp = null;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        String jsonString = sb.toString();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return jsonObject;
    }

    /**
     * 覆盖
     */
    public void coverCategory(String workspaceId, String categoryId, String categoryName){
        //把之前的删除
        delCategory(workspaceId,categoryId);

        //添加新导入的
        addCategroy(workspaceId,categoryId,categoryName);

    }


    /**
     * 遍历是否有相同的categoryId，有就删除
     * @param workspaceId
     * @param categoryId
     */
    private void delCategory(String workspaceId,String categoryId){
        CategoryQuery categoryQuery = new CategoryQuery().setWorkspaceId(workspaceId);
        List<Category> categoryListTree = categoryService.findCategoryListTree(categoryQuery);

        for(Category category:categoryListTree){
            String categoryDataId = category.getId();
            if(categoryDataId.equals(categoryId)){
                categoryService.deleteCategory(categoryId);
            }
        }
    }

    /**
     * 导入分组
     * @param workspaceId
     * @param categoryId
     * @param categoryName
     */
    private void addCategroy(String workspaceId, String categoryId, String categoryName){
        Workspace workspace = new Workspace();
        workspace.setId(workspaceId);

        Category category = new Category();

        category.setId(categoryId);
        category.setName(categoryName);
        category.setWorkspace(workspace);

        categoryService.createCategory(category);
    }

    /**
     * 导入 接口
     * @param ApixVo
     */
    public void addMethod(ApixImportVo ApixVo){
        Category categoryID = new Category();
        categoryID.setId(ApixVo.getCategoryId());

        Apix apix = new Apix();

        apix.setCategory(categoryID);


        apixService.createApix(apix);


//        httpApi.setCategory(categoryID);
//        httpApi.setId(methodVo.getMethodId());
//        httpApi.setName(methodVo.getName());
//        httpApi.setRequestType(methodVo.getRequestType());
//        httpApi.setPath(methodVo.getPath());
//        httpApi.setDesc(methodVo.getDesc());

//        httpApiService.createHttpApi(httpApi);
    }

    /**
     * 导入请求头
     * @param hVo
     */
    public void addHeader(HeaderParamImportVo hVo){
        HttpApi httpApi = new HttpApi();
        httpApi.setId(hVo.getMethodId());

        RequestHeader requestHeader = new RequestHeader();

        requestHeader.setHttp(httpApi);
        requestHeader.setHeaderName(hVo.getName());
        requestHeader.setValue(hVo.getValue());
        requestHeader.setDesc(hVo.getDesc());

        requestHeaderService.createRequestHeader(requestHeader);
    }

    /**
     * 导入查询参数
     * @param qVo
     */
    public void addQuery(QueryParamImportVo qVo){
        HttpApi httpApi = new HttpApi();
        httpApi.setId(qVo.getMethodId());

        QueryParam queryParam = new QueryParam();

        queryParam.setHttp(httpApi);
        queryParam.setParamName(qVo.getName());
        queryParam.setValue(qVo.getValue());
        queryParam.setDesc(qVo.getDesc());

        queryParamService.createQueryParam(queryParam);
    }

    /**
     * 导入请求体
     * @param requestBody
     * @param methodId
     */
    public void addBody(String requestBody,String methodId){
        ApiRequest apiRequest = new ApiRequest();

        apiRequest.setId(methodId);
        apiRequest.setBodyType(requestBody);
        apiRequest.setHttpId(methodId);

        apiRequestService.createApiRequest(apiRequest);
    }

    /**
     * 导入formdata
     * @param fvo
     */
    public void addFormData(FormDataImportVo fvo){
        HttpApi httpApi = new HttpApi();
        httpApi.setId(fvo.getMethodId());

        FormParam formParams = new FormParam();

        formParams.setHttp(httpApi);
        formParams.setParamName(fvo.getName());
        formParams.setValue(fvo.getValue());
        formParams.setDesc(fvo.getDesc());
        formParams.setDataType(fvo.getType());
        formParams.setRequired(fvo.getRequired());

        formParamService.createFormParam(formParams);
    }

    /**
     * 导入formurl
     * @param fUVo
     */
    public void addFormUrlencoded(FormUrlencodedImportVo fUVo){
        HttpApi httpApi = new HttpApi();
        httpApi.setId(fUVo.getMethodId());

        FormUrlencoded formUrlencoded = new FormUrlencoded();

        formUrlencoded.setHttp(httpApi);
        formUrlencoded.setParamName(fUVo.getName());
        formUrlencoded.setValue(fUVo.getValue());
        formUrlencoded.setRequired(fUVo.getRequired());
        formUrlencoded.setDataType(fUVo.getDataType());
        formUrlencoded.setDesc(fUVo.getDesc());

        formUrlencodedService.createFormUrlencoded(formUrlencoded);
    }

    /**
     * 导入json
     * @param jVo
     * @return
     */
    public String addJsonParam(JsonParamImportVo jVo){
        HttpApi httpApi = new HttpApi();
        httpApi.setId(jVo.getMethodId());

        JsonParam jsonParam = new JsonParam();

        jsonParam.setParamName(jVo.getName());
        jsonParam.setValue(jVo.getValue());
        jsonParam.setDataType(jVo.getDataType());
        jsonParam.setRequired(jVo.getRequired());
        jsonParam.setDesc(jVo.getDesc());
        jsonParam.setHttp(httpApi);
        jsonParam.setParent(jVo.getParentId());

        String parentid = jsonParamService.createJsonParam(jsonParam);

        return parentid;
    }

    /**
     * 导入raw
     * @param methodId
     * @param rawType
     * @param rawData
     */
    public void addRaw(String methodId, String rawType, String rawData){
        HttpApi rawMethod = new HttpApi();
        rawMethod.setId(methodId);

        RawParam rawParam = new RawParam();

        rawParam.setRaw(rawData);
        rawParam.setType(rawType);
        rawParam.setId(methodId);
        rawParam.setHttp(rawMethod);

        rawParamService.createRawParam(rawParam);
    }

    /**
     * 导入响应体
     * @param methodId
     */
    public void addResponseBody(String methodId){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setId(methodId);
        apiResponse.setHttpId(methodId);

        apiResponseService.createApiResponse(apiResponse);
    }

    /**
     * 导入响应体json
     * @param jRVo
     * @return
     */
    public String addResponseJson(JsonResponseImportVo jRVo){
        HttpApi httpApi = new HttpApi();
        httpApi.setId(jRVo.getMethodId());

        JsonResponse jsonResponse = new JsonResponse();

        jsonResponse.setHttp(httpApi);
        jsonResponse.setParent(jRVo.getParentId());
        jsonResponse.setPropertyName(jRVo.getName());
        jsonResponse.setValue(jRVo.getValue());
        jsonResponse.setDataType(jRVo.getDataType());
        jsonResponse.setRequired(jRVo.getRequired());
        jsonResponse.setDesc(jRVo.getDesc());

        String parentid = jsonResponseService.createJsonResponse(jsonResponse);
        return parentid;
    }


}
