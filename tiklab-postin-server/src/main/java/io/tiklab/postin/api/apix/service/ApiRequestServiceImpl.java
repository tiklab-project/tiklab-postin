package io.tiklab.postin.api.apix.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.postin.api.apix.dao.ApiRequestDao;
import io.tiklab.postin.api.apix.entity.ApiRequestEntity;

import io.tiklab.postin.api.apix.model.ApiRequest;
import io.tiklab.postin.api.apix.model.ApiRequestQuery;

import io.tiklab.postin.api.apix.model.JsonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 定义
 * http协议
 * 请求区服务
 */
@Service
public class ApiRequestServiceImpl implements ApiRequestService {

    @Autowired
    ApiRequestDao apiRequestDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    JsonParamService jsonParamService;

    @Override
    public String createApiRequest(@NotNull @Valid ApiRequest apiRequest) {
        ApiRequestEntity apiRequestEntity = BeanMapper.map(apiRequest, ApiRequestEntity.class);

        return apiRequestDao.createApiRequest(apiRequestEntity);
    }

    @Override
    public void updateApiRequest(@NotNull @Valid ApiRequest apiRequest) {
        ApiRequestEntity apiRequestEntity = BeanMapper.map(apiRequest, ApiRequestEntity.class);

        if("json".equals(apiRequest.getBodyType())){
            //切换请求体，如果是json，没有找到，就会自动生成一个。
            String apiId = apiRequest.getApiId();
            JsonParam isExsit = jsonParamService.findJsonParam(apiId);
            if(isExsit==null){
                JsonParam jsonParam = new JsonParam();
                jsonParam.setId(apiId);
                jsonParam.setApiId(apiId);
                jsonParam.setJsonText("{\"type\": \"object\",\"properties\": {}}");
                jsonParamService.createJsonParam(jsonParam);
            }
        }

        apiRequestDao.updateApiRequest(apiRequestEntity);
    }

    @Override
    public void deleteApiRequest(@NotNull String id) {
        apiRequestDao.deleteApiRequest(id);
    }

    @Override
    public ApiRequest findOne(String id) {
        ApiRequestEntity apiRequestEntity = apiRequestDao.findApiRequest(id);

        ApiRequest apiRequest = BeanMapper.map(apiRequestEntity, ApiRequest.class);
        return apiRequest;
    }

    @Override
    public List<ApiRequest> findList(List<String> idList) {
        List<ApiRequestEntity> apiRequestEntityList =  apiRequestDao.findApiRequestList(idList);

        List<ApiRequest> apiRequestList =  BeanMapper.mapList(apiRequestEntityList,ApiRequest.class);
        return apiRequestList;
    }

    @Override
    public ApiRequest findApiRequest(@NotNull String id) {
        ApiRequest apiRequest = findOne(id);

        joinTemplate.joinQuery(apiRequest);

        return apiRequest;
    }

    @Override
    public List<ApiRequest> findAllApiRequest() {
        List<ApiRequestEntity> apiRequestEntityList =  apiRequestDao.findAllApiRequest();

        List<ApiRequest> apiRequestList =  BeanMapper.mapList(apiRequestEntityList,ApiRequest.class);

        joinTemplate.joinQuery(apiRequestList);

        return apiRequestList;
    }

    @Override
    public List<ApiRequest> findApiRequestList(ApiRequestQuery apiRequestQuery) {
        List<ApiRequestEntity> apiRequestEntityList = apiRequestDao.findApiRequestList(apiRequestQuery);

        List<ApiRequest> apiRequestList = BeanMapper.mapList(apiRequestEntityList,ApiRequest.class);

        joinTemplate.joinQuery(apiRequestList);

        return apiRequestList;
    }

    @Override
    public Pagination<ApiRequest> findApiRequestPage(ApiRequestQuery apiRequestQuery) {
        Pagination<ApiRequestEntity>  pagination = apiRequestDao.findApiRequestPage(apiRequestQuery);

        List<ApiRequest> apiRequestList = BeanMapper.mapList(pagination.getDataList(),ApiRequest.class);

        joinTemplate.joinQuery(apiRequestList);

        return PaginationBuilder.build(pagination,apiRequestList);
    }
}