package io.tiklab.postin.support.docletreport.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.postin.api.http.definition.service.*;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.support.docletreport.model.ApiReport;
import io.tiklab.postin.support.docletreport.model.ModuleReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DocletReportServicelmpl implements DocletReportService {
    private static Logger logger = LoggerFactory.getLogger(DocletReportServicelmpl.class);


    @Autowired
    CategoryService categoryService;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    ApiRequestService apiRequestService;

    @Autowired
    FormParamService formParamService;

    @Autowired
    FormUrlencodedService formUrlencodedService
            ;
    @Autowired
    RawParamService rawParamService;

    @Autowired
    ApiResponseService apiResponseService;

    @Override
    public String categoryReport(Category category) {
        String id = category.getId();
        //如果查询不到分组，新建分组
        Category isExistCategory = categoryService.findCategory(id);
        if(isExistCategory==null){
            category.setId(id);
            categoryService.createCategory(category);
        }

        return id;
    }

    @Override
    public String apiReport(ApiReport apiReport) {
        String apiId = apiReport.getApiId();

        HttpApi isExistApi = httpApiService.findOne(apiId);
        if(isExistApi==null){
            createApi(apiReport);
        }else {
            updateApi(apiReport);
        }

        return apiId;
    }

    @Override
    public String moduleReport(ModuleReport moduleReport) {

        try {
            //创建分组
            categoryReport(moduleReport.getCategory());
        }catch (Exception e){
            return "category create error";
        }

        JSONObject jsonObject = new JSONObject();

        int add=0;
        int update=0;
        int error = 0;
        if(moduleReport.getModuleMethodList()==null){
            jsonObject.put("add",add);
            jsonObject.put("update",update);
            jsonObject.put("error",error);

            return jsonObject.toJSONString();
        }

        //创建接口
        for (ApiReport apiReportData: moduleReport.getModuleMethodList()){
            try {
                String apiId = apiReportData.getApiId();
                HttpApi isExistApi = httpApiService.findOne(apiId);
                if(isExistApi==null){
                    createApi(apiReportData);
                    ++add;
                }else {
                    updateApi(apiReportData);
                    ++update;
                }
            }catch (Exception e){
                logger.info("Error --- report api error : {}",e);
                ++error;
            }
        }

        jsonObject.put("add",add);
        jsonObject.put("update",update);
        jsonObject.put("error",error);

        return jsonObject.toJSONString();
    }

    private void createApi(ApiReport apiReport){
        String apiId = apiReport.getApiId();

        //创建基础
        HttpApi httpApi = apiReport.getApiBase();
        httpApi.setId(apiId);
        httpApiService.createHttpApi(httpApi);

        //请求信息
        ApiRequest request = apiReport.getRequest();
        request.setId(apiId);
        request.setHttpId(apiId);
        apiRequestService.updateApiRequest(apiReport.getRequest());

        //请求体
        String bodyType = apiReport.getRequest().getBodyType();
        if(bodyType!=null){
            switch (bodyType){
                case "formdata":
                    for (FormParam formParam : apiReport.getFormList()) {
                        formParam.setHttp(httpApi);
                        formParamService.createFormParam(formParam);
                    }
                    break;
                case "formUrlencoded":
                    for (FormUrlencoded formUrlencoded : apiReport.getFormUrlList()) {
                        formUrlencoded.setHttp(httpApi);
                        formUrlencodedService.createFormUrlencoded(formUrlencoded);
                    }
                case "json":
                case "raw":
                    RawParam raw = apiReport.getRaw();
                    raw.setHttp(httpApi);
                    rawParamService.createRawParam(raw);
                    break;
                default:
                    break;
            }
        }

        //响应
        List<ApiResponse> apiResponseList = apiResponseService.findApiResponseList(new ApiResponseQuery().setHttpId(apiId));
        for (ApiResponse apiResponse : apiResponseList) {
            apiResponseService.deleteApiResponse(apiResponse.getId());
        }

        ApiResponse response = apiReport.getResponse();
        response.setHttpId(apiId);
        apiResponseService.createApiResponse(response);
    }

    private void updateApi(ApiReport apiReport) {
        String apiId = apiReport.getApiId();

        HttpApi httpApi = apiReport.getApiBase();
        httpApi.setId(apiId);
        httpApi.getApix().setId(apiId);
        httpApiService.updateHttpApi(httpApi);

        ApiRequest request = apiReport.getRequest();
        request.setId(apiId);
        request.setHttpId(apiId);
        apiRequestService.updateApiRequest(apiReport.getRequest());

        String bodyType = apiReport.getRequest().getBodyType();
        if(bodyType!=null){
            switch (bodyType){
                case "formdata":
                    //先把所有的都删除
                    List<FormParam> formParamList = formParamService.findFormParamList(new FormParamQuery().setHttpId(apiReport.getApiId()));
                    for(FormParam formParam:formParamList){
                        formParamService.deleteFormParam(formParam.getId());
                    }

                    //在新建
                    for (FormParam formParam : apiReport.getFormList()) {
                        formParam.setHttp(httpApi);
                        formParamService.createFormParam(formParam);
                    }
                    break;
                case "formUrlencoded":
                    List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findFormUrlencodedList(new FormUrlencodedQuery().setHttpId(apiReport.getApiId()));
                    for (FormUrlencoded formUrlencoded:formUrlencodedList){
                        formUrlencodedService.deleteFormUrlencoded(formUrlencoded.getId());
                    }

                    for (FormUrlencoded formUrlencoded : apiReport.getFormUrlList()) {
                        formUrlencoded.setHttp(httpApi);
                        formUrlencodedService.createFormUrlencoded(formUrlencoded);
                    }
                case "json":
                case "raw":
                    RawParam raw = apiReport.getRaw();
                    raw.setHttp(httpApi);
                    rawParamService.updateRawParam(raw);
                    break;
                default:
                    break;
            }
        }
    }



}
