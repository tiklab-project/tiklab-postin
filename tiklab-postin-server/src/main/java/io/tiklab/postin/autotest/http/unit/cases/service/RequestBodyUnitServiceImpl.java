package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.postin.autotest.http.unit.cases.dao.RequestBodyUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.RequestBodyUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RawParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestBodyUnit;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestBodyUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求体 服务
*/
@Service
public class RequestBodyUnitServiceImpl implements RequestBodyUnitService {

    @Autowired
    RequestBodyUnitDao requestBodyUnitDao;

    @Autowired
    JsonParamUnitService jsonParamUnitService;

    @Autowired
    RawParamUnitService rawParamUnitService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestBody(@NotNull @Valid RequestBodyUnit requestBodyUnit) {
        RequestBodyUnitEntity requestBodyUnitEntity = BeanMapper.map(requestBodyUnit, RequestBodyUnitEntity.class);

        return requestBodyUnitDao.createRequestBody(requestBodyUnitEntity);
    }

    @Override
    public void updateRequestBody(@NotNull @Valid RequestBodyUnit requestBodyUnit) {
        RequestBodyUnitEntity requestBodyUnitEntity = BeanMapper.map(requestBodyUnit, RequestBodyUnitEntity.class);

        if("json".equals(requestBodyUnit.getBodyType())){
            //切换请求体，如果是json，没有找到，就会自动生成一个。
            String apiUnitId = requestBodyUnit.getApiUnitId();
            JsonParamUnit isExsit = jsonParamUnitService.findJsonParam(apiUnitId);
            if(isExsit==null){
                JsonParamUnit jsonParamUnit = new JsonParamUnit();
                jsonParamUnit.setId(apiUnitId);
                jsonParamUnit.setApiUnitId(apiUnitId);
                jsonParamUnit.setSchemaText("{\"type\": \"object\",\"properties\": {}}");
                jsonParamUnitService.createJsonParam(jsonParamUnit);
            }
        }

        if("raw".equals(requestBodyUnit.getBodyType())){
            String apiUnitId = requestBodyUnit.getApiUnitId();
            RawParamUnit rawParamUnitIsExist = rawParamUnitService.findRawParam(apiUnitId);
            if(rawParamUnitIsExist ==null){
                RawParamUnit rawParamUnit = new RawParamUnit();
                rawParamUnit.setApiUnitId(apiUnitId);
                rawParamUnit.setId(apiUnitId);
                rawParamUnit.setType("application/json");
                rawParamUnit.setRaw("");
                rawParamUnitService.createRawParam(rawParamUnit);
            }
        }


        requestBodyUnitDao.updateRequestBody(requestBodyUnitEntity);
    }

    @Override
    public void deleteRequestBody(@NotNull String id) {
        requestBodyUnitDao.deleteRequestBody(id);
    }

    @Override
    public RequestBodyUnit findOne(String id) {
        RequestBodyUnitEntity requestBodyUnitEntity = requestBodyUnitDao.findRequestBody(id);

        RequestBodyUnit requestBodyUnit = BeanMapper.map(requestBodyUnitEntity, RequestBodyUnit.class);
        return requestBodyUnit;
    }

    @Override
    public List<RequestBodyUnit> findList(List<String> idList) {
        List<RequestBodyUnitEntity> requestBodyUnitEntityList =  requestBodyUnitDao.findRequestBodyList(idList);

        List<RequestBodyUnit> requestBodyUnitList =  BeanMapper.mapList(requestBodyUnitEntityList, RequestBodyUnit.class);
        return requestBodyUnitList;
    }

    @Override
    public RequestBodyUnit findRequestBody(@NotNull String id) {
        RequestBodyUnit requestBodyUnit = findOne(id);

        joinTemplate.joinQuery(requestBodyUnit);
        return requestBodyUnit;
    }

    @Override
    public List<RequestBodyUnit> findAllRequestBody() {
        List<RequestBodyUnitEntity> requestBodyUnitEntityList =  requestBodyUnitDao.findAllRequestBody();

        List<RequestBodyUnit> requestBodyUnitList =  BeanMapper.mapList(requestBodyUnitEntityList, RequestBodyUnit.class);

        joinTemplate.joinQuery(requestBodyUnitList);
        return requestBodyUnitList;
    }

    @Override
    public List<RequestBodyUnit> findRequestBodyList(RequestBodyUnitQuery requestBodyUnitQuery) {
        List<RequestBodyUnitEntity> requestBodyUnitEntityList = requestBodyUnitDao.findRequestBodyList(requestBodyUnitQuery);

        List<RequestBodyUnit> requestBodyUnitList = BeanMapper.mapList(requestBodyUnitEntityList, RequestBodyUnit.class);

        joinTemplate.joinQuery(requestBodyUnitList);

        return requestBodyUnitList;
    }

    @Override
    public Pagination<RequestBodyUnit> findRequestBodyPage(RequestBodyUnitQuery requestBodyUnitQuery) {

        Pagination<RequestBodyUnitEntity>  pagination = requestBodyUnitDao.findRequestBodyPage(requestBodyUnitQuery);

        List<RequestBodyUnit> requestBodyUnitList = BeanMapper.mapList(pagination.getDataList(), RequestBodyUnit.class);

        joinTemplate.joinQuery(requestBodyUnitList);

        return PaginationBuilder.build(pagination, requestBodyUnitList);
    }


}