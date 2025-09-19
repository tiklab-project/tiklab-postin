package io.tiklab.postin.autotest.http.unit.cases.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.tiklab.postin.autotest.http.unit.cases.model.AfterParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.AssertUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.FormParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.FormUrlEncodedUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.PreParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.QueryParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RawParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestBodyUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestHeaderUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.SaveToCase;
import io.tiklab.postin.autotest.test.service.TestCaseService;
import io.tiklab.postin.common.MagicValue;

@Service
public class SaveToCaseServiceImpl implements SaveToCaseService {
    private static final Logger log = LoggerFactory.getLogger(SaveToCaseServiceImpl.class);

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
    PreParamUnitService preParamUnitService;

    @Autowired
    AfterParamUnitService afterParamUnitService;

    @Autowired
    AssertUnitService assertUnitService;


    @Override
    public String saveToCase(SaveToCase saveToCase) {
        // 创建API单元用例
        String apiUnitId = apiUnitCaseService.createApiUnitCase(saveToCase.getApiUnitCase());
        if (apiUnitId == null) {
            return null;
        }

        try {
            // 保存请求头
            saveHeaders(apiUnitId, saveToCase.getHeaderList());

            // 保存查询参数
            saveQueryParams(apiUnitId, saveToCase.getQueryList());

            // 保存请求体
            saveRequestBody(apiUnitId, saveToCase);

            // 保存前置参数
            savePreParams(apiUnitId, saveToCase.getPreParamList());

            // 保存后置参数
            saveAfterParams(apiUnitId, saveToCase.getAfterParamList());

            // 保存断言
            saveAsserts(apiUnitId, saveToCase.getAssertList());

            return apiUnitId;
        } catch (Exception e) {
            // 记录错误日志
            log.error("保存用例失败，apiUnitId: {}", apiUnitId, e);
            throw new RuntimeException("保存用例失败", e);
        }
    }

    private void saveHeaders(String apiUnitId, List<RequestHeaderUnit> headerList) {
        if (headerList == null || headerList.isEmpty()) {
            return;
        }

        for (RequestHeaderUnit header : headerList) {
            header.setApiUnit(newApiUnitCase(apiUnitId));
            requestHeaderUnitService.createRequestHeader(header);
        }
    }

    private void saveQueryParams(String apiUnitId, List<QueryParamUnit> queryList) {
        if (queryList == null || queryList.isEmpty()) {
            return;
        }

        for (QueryParamUnit queryParam : queryList) {
            queryParam.setApiUnit(newApiUnitCase(apiUnitId));
            queryParamUnitService.createQueryParam(queryParam);
        }
    }

    private void saveRequestBody(String apiUnitId, SaveToCase saveToCase) {
        String bodyType = saveToCase.getBodyType();
        if (bodyType == null) {
            return;
        }

        // 更新请求体类型
        RequestBodyUnit requestBody = new RequestBodyUnit();
        // json 和 raw 类型都设置为 raw
        if (MagicValue.REQUEST_BODY_TYPE_JSON.equals(bodyType) || MagicValue.REQUEST_BODY_TYPE_RAW.equals(bodyType)) {
            requestBody.setBodyType(MagicValue.REQUEST_BODY_TYPE_RAW);
        } else {
            requestBody.setBodyType(bodyType);
        }
        requestBody.setApiUnitId(apiUnitId);
        requestBody.setId(apiUnitId);
        requestBodyUnitService.updateRequestBody(requestBody);

        // 根据不同类型保存请求体数据
        switch (bodyType) {
            case MagicValue.REQUEST_BODY_TYPE_NONE:
                // none类型不需要保存任何请求体数据
                break;

            case MagicValue.REQUEST_BODY_TYPE_FORMDATA:
                saveFormData(apiUnitId, saveToCase.getFormDataList());
                break;

            case MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED:
                saveFormUrlEncoded(apiUnitId, saveToCase.getFormUrlList());
                break;

            case MagicValue.REQUEST_BODY_TYPE_JSON:
            case MagicValue.REQUEST_BODY_TYPE_RAW:
                saveRawParam(apiUnitId, bodyType, saveToCase);
                break;

            default:
                log.warn("未知的请求体类型: {}", bodyType);
        }
    }

    private void saveFormData(String apiUnitId, List<FormParamUnit> formDataList) {
        if (formDataList == null || formDataList.isEmpty()) {
            return;
        }

        for (FormParamUnit formParam : formDataList) {
            formParam.setApiUnit(newApiUnitCase(apiUnitId));
            formParamUnitService.createFormParam(formParam);
        }
    }

    private void saveFormUrlEncoded(String apiUnitId, List<FormUrlEncodedUnit> formUrlList) {
        if (formUrlList == null || formUrlList.isEmpty()) {
            return;
        }

        for (FormUrlEncodedUnit formUrlEncoded : formUrlList) {
            formUrlEncoded.setApiUnit(newApiUnitCase(apiUnitId));
            formUrlencodedUnitService.createFormUrlencoded(formUrlEncoded);
        }
    }

    private void saveRawParam(String apiUnitId, String bodyType, SaveToCase saveToCase) {
        RawParamUnit rawParam = rawParamUnitService.findRawParam(apiUnitId);
        if (rawParam == null) {
            rawParam = new RawParamUnit();
            rawParam.setApiUnitId(apiUnitId);
            rawParam.setId(apiUnitId);
        }

        if (MagicValue.REQUEST_BODY_TYPE_JSON.equals(bodyType)) {
            rawParam.setRaw(saveToCase.getJson());
            rawParam.setType(MagicValue.MEDIA_TYPE_JSON);
        } else {
            RawParamUnit rawParamUnit = saveToCase.getRawParam();
            String type = rawParamUnit.getType();
            if (type != null && !type.isEmpty()) {
                if(type.equals(MagicValue.MEDIA_TYPE_JSON)){
                    rawParam.setRaw(rawParamUnit.getRaw());
                    rawParam.setType(MagicValue.MEDIA_TYPE_JSON);
                }else {
                    rawParam.setRaw(rawParamUnit.getRaw());
                    rawParam.setType(MagicValue.MEDIA_TYPE_RAW);
                }
            }
        }

        rawParamUnitService.updateRawParam(rawParam);
    }

    private void savePreParams(String apiUnitId, List<PreParamUnit> preParamList) {
        if (preParamList == null || preParamList.isEmpty()) {
            return;
        }

        for (PreParamUnit preParam : preParamList) {
            preParam.setApiUnitId(apiUnitId);
            preParamUnitService.createPreParamUnit(preParam);
        }
    }

    private void saveAfterParams(String apiUnitId, List<AfterParamUnit> afterParamList) {
        if (afterParamList == null || afterParamList.isEmpty()) {
            return;
        }

        for (AfterParamUnit afterParam : afterParamList) {
            afterParam.setApiUnitId(apiUnitId);
            afterParamUnitService.createAfterParamUnit(afterParam);
        }
    }

    private void saveAsserts(String apiUnitId, List<AssertUnit> assertList) {
        if (assertList == null || assertList.isEmpty()) {
            return;
        }

        for (AssertUnit assertUnit : assertList) {
            assertUnit.setApiUnit(newApiUnitCase(apiUnitId));
            assertUnitService.createAssertCase(assertUnit);
        }
    }

    private ApiUnitCase newApiUnitCase(String apiUnitId) {
        return new ApiUnitCase().setId(apiUnitId);
    }
}
