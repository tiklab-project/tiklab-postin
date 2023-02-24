package net.tiklab.postin.apidef.http.definition.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.postin.apidef.http.definition.dao.ApiRequestDao;
import net.tiklab.postin.apidef.http.definition.entity.ApiRequestEntity;
import net.tiklab.postin.apidef.http.definition.model.ApiRequest;
import net.tiklab.postin.apidef.http.definition.model.ApiRequestQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
* ApiRequestServiceImpl
*/
@Service
public class ApiRequestServiceImpl implements ApiRequestService {

    @Autowired
    ApiRequestDao apiRequestDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiRequest(@NotNull @Valid ApiRequest apiRequest) {
        ApiRequestEntity apiRequestEntity = BeanMapper.map(apiRequest, ApiRequestEntity.class);

        return apiRequestDao.createApiRequest(apiRequestEntity);
    }

    @Override
    public void updateApiRequest(@NotNull @Valid ApiRequest apiRequest) {
        ApiRequestEntity apiRequestEntity = BeanMapper.map(apiRequest, ApiRequestEntity.class);

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