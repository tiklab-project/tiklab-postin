package io.tiklab.postin.support.docletreport.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.postin.api.http.definition.service.*;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.support.docletreport.model.ApiReport;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
public class DocletReportServicelmpl implements DocletReportService {

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


    @Override
    public String categoryReport(Category category) {
        String name = category.getName();
        String categoryId = getIdByMd5(name);

        //如果查询不到分组，新建分组
        Category isExistCategory = categoryService.findCategory(categoryId);
        if(isExistCategory==null){
            category.setId(categoryId);
            categoryService.createCategory(category);
        }

        return categoryId;
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

        return null;
    }

    private void createApi(ApiReport apiReport){
        String apiId = apiReport.getApiId();

        HttpApi httpApi = apiReport.getHttpApi();
        httpApi.setId(apiId);
        httpApiService.createHttpApi(httpApi);

        ApiRequest request = apiReport.getRequest();
        request.setId(apiId);
        request.setHttpId(apiId);
        apiRequestService.updateApiRequest(apiReport.getRequest());

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
    }

    private void updateApi(ApiReport apiReport) {
        String apiId = apiReport.getApiId();

        HttpApi httpApi = apiReport.getHttpApi();
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



    /**
     * 使用md5 获取id
     */
    private  String getIdByMd5(String data){
        String id = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md5.digest(data.getBytes("UTF-8"));

            String hashString = Hex.encodeHexString(hashBytes);
            id = hashString.substring(0, 12);

            if(id.length() < 12) {
                id = "00000000000".substring(0, 12 - id.length()) + id;
            }
        }catch (Exception e){
            throw new ApplicationException("通过MD5获取ID失败");
        }

        return id;
    }


}
