package io.tiklab.postin.support.imexport.service;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.apix.model.*;
import io.tiklab.postin.api.apix.service.ApiRequestService;
import io.tiklab.postin.api.apix.service.QueryParamService;
import io.tiklab.postin.api.apix.service.RawParamService;
import io.tiklab.postin.api.apix.service.RequestHeaderService;
import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.postin.api.http.definition.service.*;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.model.CategoryQuery;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.support.apistatus.model.ApiStatus;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.List;


/**
 * 导出 服务
 */
@Service
public class ExportServiceImpl implements ExportService {

    private Configuration configuration;
    private Template template;
    private Map<String, Object> dataModel;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    CategoryService categoryService;

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
    ResponseHeaderService responseHeaderService;




    public ExportServiceImpl() {
        // 初始化 FreeMarker 配置
        configuration = new Configuration(Configuration.VERSION_2_3_25);
        configuration.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "templates");
        configuration.setDefaultEncoding("UTF-8");

        try {
            // 加载模板
            InputStream ips = this.getClass().getResourceAsStream("/templates/exportHtml.ftl");
            BufferedReader tplReader = new BufferedReader(new InputStreamReader(ips));

            template = new Template("exportHtml.ftl", tplReader, configuration);
        } catch (IOException e) {
            throw new ApplicationException("Error while loading template", e);
        }

    }

    @Override
    public String generateHtml(String workspaceId) {
        // 渲染模板并生成 HTML 内容
        StringWriter writer = new StringWriter();
        try {
            String data = allJson(workspaceId);

            dataModel = new HashMap<>();
            dataModel.put("jsonData", data);
            template.process(dataModel, writer);
        } catch (TemplateException | IOException e) {
            throw new ApplicationException("Error while generating HTML", e);
        }

        return writer.toString();
    }


    /**
     * 总的json
     * @param workspaceId
     * @return
     */
    @Override
    public String allJson(String workspaceId){

        JSONObject allJson = new JSONObject();

        allJson.put("projectInfo",projectJson(workspaceId));
        JSONArray list = apiGroupList(workspaceId);
        allJson.put("apiGroupList",list);

        return allJson.toString();
    }


    /**
     * 获取空间项目的json
     */
    private JSONObject projectJson(String workspaceId){

        JSONObject workspaceJson = new JSONObject();

        Workspace workspace = workspaceService.findWorkspace(workspaceId);
        if(workspace!=null){
            workspaceJson.put("projectName",workspace.getWorkspaceName());
            workspaceJson.put("projectDesc",workspace.getDesc());
            workspaceJson.put("projectId",workspace.getId());
            workspaceJson.put("projectIcon",workspace.getIconUrl());
        }

        return workspaceJson;
    }

    /**
     * 分组json
     * @param workspaceId
     * @return
     */
    private JSONArray apiGroupList(String workspaceId){
        List<Category> categoryListTree = categoryService.findCategoryListTree(new CategoryQuery().setWorkspaceId(workspaceId));

        JSONArray list = loopArr(categoryListTree);

        return list;
    }


    /**
     * 递归分组
     * @param categoryListTree
     * @return
     */
    private JSONArray loopArr(List<Category> categoryListTree){
        JSONArray apiGroupList = new JSONArray();

        if(categoryListTree!=null&&categoryListTree.size()>0){
            for(Category category:categoryListTree){
                JSONObject categoryNode = new JSONObject();
                categoryNode.put("id",category.getId() );
                categoryNode.put("name", category.getName());

                if(category.getNodeList()!=null&category.getNodeList().size()>0){
                    JSONArray apiList = new JSONArray();
                    for(Apix apix:category.getNodeList()){
                        JSONObject apiJson = new JSONObject();
                        apiJson.put("id",apix.getId());
                        apiJson.put("name", apix.getName());
                        apiJson.put("protocolType", apix.getProtocolType());
                        apiJson.put("updateTime", apix.getUpdateTime());
                        apiJson.put("status",statusJson(apix.getStatus()));
                        apiJson.put("path",apix.getPath());

                        if("http".equals(apix.getProtocolType())){
                            HttpApi httpApi = httpApiService.findHttpApi(apix.getId());
                            apiJson.put("methodType", httpApi.getMethodType());
                            apiJson.put("request",requestJson(httpApi));
                            apiJson.put("response",responseJson(httpApi.getId()));
                        }

                        apiList.add(apiJson);
                    }

                    categoryNode.put("nodeList", apiList);
                }

                if(category.getChildren()!=null&&category.getChildren().size()>0){
                    categoryNode.put("children", loopArr(category.getChildren()));
                }

                apiGroupList.add(categoryNode);
            }


        }

        return apiGroupList;
    }

    /**
     * 状态转换
     */
    private JSONObject statusJson(ApiStatus status) {
        JSONObject statusJson = new JSONObject();
        statusJson.put("id", status.getId());
        statusJson.put("color", status.getColor());
        statusJson.put("name", status.getName());
        statusJson.put("type", status.getType());

        return statusJson;
    }

    /**
     * 请求部分
     */
    private JSONObject requestJson(HttpApi httpApi){

        ApiRequest apiRequest = httpApi.getRequest();

        //获取数据
        JSONObject json = new JSONObject();
        json.put("id",apiRequest.getId());
        json.put("header",headerJson(httpApi));
        json.put("query",queryJson(httpApi));
        json.put("body",bodyJson(httpApi));
        json.put("preScript",apiRequest.getPreScript());
        json.put("afterScript",apiRequest.getAfterScript());

        return json;
    }

    /**
     * header
     */
    private JSONArray headerJson(HttpApi httpApi){
        JSONArray headerArr = new JSONArray();

        if(httpApi.getHeaderList()!=null&&httpApi.getHeaderList().size()>0){
            for(RequestHeader requestHeader : httpApi.getHeaderList()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",requestHeader.getHeaderName());
                jsonObject.put("value",requestHeader.getValue());
                jsonObject.put("id",requestHeader.getId());
                jsonObject.put("desc",requestHeader.getDesc());
                jsonObject.put("required",requestHeader.getRequired());
                jsonObject.put("sort",requestHeader.getSort());

                headerArr.add(jsonObject);
            }
        }

        return headerArr;
    }

    /**
     * query
     */
    private JSONArray queryJson(HttpApi httpApi){
        JSONArray queryArr = new JSONArray();

        if(httpApi.getQueryList()!=null&&httpApi.getQueryList().size()>0){
            for(QueryParam queryParam : httpApi.getQueryList()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",queryParam.getParamName());
                jsonObject.put("value",queryParam.getValue());
                jsonObject.put("id",queryParam.getId());
                jsonObject.put("desc",queryParam.getDesc());
                jsonObject.put("required",queryParam.getRequired());
                jsonObject.put("sort",queryParam.getSort());

                queryArr.add(jsonObject);
            }
        }

        return queryArr;
    }


    /**
     * 请求体的信息
     */
    private JSONObject bodyJson(HttpApi httpApi){

        String bodyType = httpApi.getRequest().getBodyType();

        JSONObject json = new JSONObject();
        json.put("bodyType",bodyType);

        switch (bodyType){
            case "formdata":
                json.put("formdata",formData(httpApi.getFormList()));
                break;
            case "formUrlencoded":
                json.put("formUrlencoded",formUrl(httpApi.getUrlencodedList()));
                break;
            case "raw":
                json.put("raw",rawData(httpApi.getRawParam()));
                break;
        }

        return json;
    }

    /**
     * form
     */
    private JSONArray formData(List<FormParam> formList){
        JSONArray formArr = new JSONArray();

        if(formList!=null&&formList.size()>0){
            for(FormParam formParam:formList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",formParam.getParamName());
                jsonObject.put("value",formParam.getValue());
                jsonObject.put("id",formParam.getId());
                jsonObject.put("desc",formParam.getDesc());
                jsonObject.put("dataType",formParam.getDataType());
                jsonObject.put("required",formParam.getRequired());
                jsonObject.put("sort",formParam.getSort());

                formArr.add(jsonObject);
            }
        }

        return formArr;
    }

    /**
     * formUrlencoded
     */
    private Object formUrl(List<FormUrlencoded> formUrlList) {
        JSONArray formUrlJson = new JSONArray();

        if(formUrlList!=null&&formUrlList.size()>0){
            for(FormUrlencoded formUrlencoded:formUrlList){
                JSONObject json = new JSONObject();
                json.put("name",formUrlencoded.getParamName());
                json.put("value",formUrlencoded.getValue());
                json.put("id",formUrlencoded.getId());
                json.put("desc",formUrlencoded.getDesc());
                json.put("dataType",formUrlencoded.getDataType());
                json.put("required",formUrlencoded.getRequired());
                json.put("sort",formUrlencoded.getSort());

                formUrlJson.add(json);
            }
        }

        return formUrlJson;
    }

    /**
     * raw
     */
    private Object rawData(RawParam rawParam) {
        JSONObject json = new JSONObject();

        json.put("raw",rawParam.getRaw());
        json.put("rawType",rawParam.getType());
        json.put("id",rawParam.getId());

        return json;
    }

    /**
     * response
     */

    private JSONObject responseJson(String httpId){
        JSONObject json = new JSONObject();

        json.put("header",responseHeaderList(httpId));
        json.put("result",responseResultList(httpId));

        return json;
    }


    private JSONArray responseResultList(String httpId){

        List<ApiResponse> apiResponseList = apiResponseService.findApiResponseList(new ApiResponseQuery().setHttpId(httpId));

        JSONArray array = new JSONArray();
        if(apiResponseList!=null&&apiResponseList.size()>0){

            for(ApiResponse apiResponse:apiResponseList){
                JSONObject json = new JSONObject();
                json.put("id",apiResponse.getId());
                json.put("name",apiResponse.getName());
                json.put("httpCode",apiResponse.getHttpCode());
                json.put("dataType",apiResponse.getDataType());
                if(Objects.equals(apiResponse.getDataType(),"json")){
                    json.put("jsonText",apiResponse.getJsonText());
                }else {
                    json.put("rawText",apiResponse.getRawText());
                }

                array.add(json);
            }
        }

        return array;
    }

    /**
     * 响应头
     */
    private JSONArray responseHeaderList(String httpId){
        List<ResponseHeader> responseHeaderList = responseHeaderService.findResponseHeaderList(new ResponseHeaderQuery().setHttpId(httpId));

        JSONArray array = new JSONArray();
        if(responseHeaderList!=null&&responseHeaderList.size()>0){
            for (ResponseHeader responseHeader :responseHeaderList){
                JSONObject json = new JSONObject();

                json.put("id", responseHeader.getId());
                json.put("name", responseHeader.getHeaderName());
                json.put("value", responseHeader.getValue());
                json.put("required", responseHeader.getRequired());
                json.put("desc", responseHeader.getDesc());
                json.put("sort", responseHeader.getSort());

                array.add(json);
            }
        }

        return array;
    }


}
