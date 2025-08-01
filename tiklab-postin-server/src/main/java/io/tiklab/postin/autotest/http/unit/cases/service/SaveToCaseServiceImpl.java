package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.postin.autotest.http.unit.cases.model.*;
import io.tiklab.postin.autotest.test.service.TestCaseService;
import io.tiklab.postin.common.MagicValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveToCaseServiceImpl implements SaveToCaseService {

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    RequestHeaderUnitService requestHeaderUnitService;

    @Autowired
    QueryParamUnitService queryParamUnitService;

    @Autowired
    RequestBodyUnitService requestBodyUnitService;

    @Autowired
    FormParamUnitService formParamUnitService;

    @Autowired
    FormUrlencodedUnitService formUrlencodedUnitService;

    @Autowired
    JsonParamUnitService jsonParamUnitService;

    @Autowired
    RawParamUnitService rawParamUnitService;

    @Autowired
    AssertUnitService assertUnitService;


    @Override
    public String saveToCase(SaveToCase saveToCase) {

        String apiUnitId = apiUnitCaseService.createApiUnitCase(saveToCase.getApiUnitCase());

        if(apiUnitId != null&&saveToCase.getHeaderList()!= null){
            for(RequestHeaderUnit header: saveToCase.getHeaderList()){
                header.setApiUnit(new ApiUnitCase().setId(apiUnitId));
                requestHeaderUnitService.createRequestHeader(header);
            }
        }

        if(apiUnitId != null&&saveToCase.getQueryList()!= null){
            for(QueryParamUnit queryParamUnit: saveToCase.getQueryList()){
                queryParamUnit.setApiUnit(new ApiUnitCase().setId(apiUnitId));
                queryParamUnitService.createQueryParam(queryParamUnit);
            }
        }

        String bodyType = saveToCase.getBodyType();
        if(bodyType!=null) {
            RequestBodyUnit requestBody = new RequestBodyUnit();
            requestBody.setBodyType("raw");
            requestBody.setApiUnitId(apiUnitId);
            requestBody.setId(apiUnitId);
            requestBodyUnitService.updateRequestBody(requestBody);

            switch (bodyType){
                case MagicValue.REQUEST_BODY_TYPE_FORMDATA:
                    for(FormParamUnit formParamUnit: saveToCase.getFormDataList()){
                        formParamUnit.setApiUnit(new ApiUnitCase().setId(apiUnitId));
                        formParamUnitService.createFormParam(formParamUnit);
                    }
                    break;
                case MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED:
                    for(FormUrlEncodedUnit formUrlEncodedUnit: saveToCase.getFormUrlList()){
                        formUrlEncodedUnit.setApiUnit(new ApiUnitCase().setId(apiUnitId));
                        formUrlencodedUnitService.createFormUrlencoded(formUrlEncodedUnit);
                    }
                    break;
                case MagicValue.REQUEST_BODY_TYPE_JSON:
//                    JsonParamUnit jsonParam = jsonParamUnitService.findJsonParam(apiUnitId);
//                    jsonParam.setSchemaText(saveToCase.getJson());
//                    jsonParamUnitService.updateJsonParam(jsonParam);
//                    break;
                case MagicValue.REQUEST_BODY_TYPE_RAW:
                    RawParamUnit rawParam = rawParamUnitService.findRawParam(apiUnitId);
                    if(rawParam == null){
                        rawParam = new RawParamUnit();
                        rawParam.setApiUnitId(apiUnitId);
                        rawParam.setId(apiUnitId);
                    }
                    if(bodyType.equals(MagicValue.REQUEST_BODY_TYPE_JSON)){
                        rawParam.setRaw(saveToCase.getJson());
                        rawParam.setType("application/json");
                    }else {
                        rawParam.setRaw(saveToCase.getRaw());
                        rawParam.setType("text/plain");
                    }
                    rawParamUnitService.updateRawParam(rawParam);
                    break;
                default:
                    break;

            }

        }

        if(apiUnitId != null&&saveToCase.getAssertList()!= null){
            for(AssertUnit assertUnit: saveToCase.getAssertList()){
                assertUnit.setApiUnit(new ApiUnitCase().setId(apiUnitId));
                assertUnitService.createAssertCase(assertUnit);
            }
        }

        return apiUnitId;
    }
}
