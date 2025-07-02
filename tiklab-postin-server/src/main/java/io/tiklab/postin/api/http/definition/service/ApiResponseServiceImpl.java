package io.tiklab.postin.api.http.definition.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.apix.entity.RequestHeaderEntity;
import io.tiklab.postin.api.http.definition.dao.ApiResponseDao;
import io.tiklab.postin.api.http.definition.entity.ApiResponseEntity;
import io.tiklab.postin.api.http.definition.model.ApiResponse;
import io.tiklab.postin.api.http.definition.model.ApiResponseQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * 定义
 * http协议
 * 响应区服务
*/
@Service
public class ApiResponseServiceImpl implements ApiResponseService {

    @Autowired
    ApiResponseDao apiResponseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiResponse(@NotNull @Valid ApiResponse apiResponse) {
        ApiResponseEntity apiResponseEntity = BeanMapper.map(apiResponse, ApiResponseEntity.class);
        apiResponseEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return apiResponseDao.createApiResponse(apiResponseEntity);
    }

    @Override
    public void updateApiResponse(@NotNull @Valid ApiResponse apiResponse) {
        ApiResponseEntity apiResponseEntity = BeanMapper.map(apiResponse, ApiResponseEntity.class);

        apiResponseDao.updateApiResponse(apiResponseEntity);
    }

    @Override
    public void deleteApiResponse(@NotNull String id) {
        apiResponseDao.deleteApiResponse(id);
    }

    @Override
    public void deleteAllApiResponse(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(ApiResponseEntity.class)
                .eq("httpId", id)
                .get();
        apiResponseDao.deleteApiResponseList(deleteCondition);
    }

    @Override
    public ApiResponse findApiResponse(@NotNull String id) {
        ApiResponseEntity apiResponseEntity = apiResponseDao.findApiResponse(id);

        ApiResponse apiResponse = BeanMapper.map(apiResponseEntity, ApiResponse.class);

        return apiResponse;
    }

    @Override
    public List<ApiResponse> findAllApiResponse() {
        List<ApiResponseEntity> apiResponseEntityList =  apiResponseDao.findAllApiResponse();

        List<ApiResponse> apiResponseList =  BeanMapper.mapList(apiResponseEntityList, ApiResponse.class);

        return apiResponseList;
    }

    @Override
    public List<ApiResponse> findApiResponseList(ApiResponseQuery apiResponseQuery) {
        List<ApiResponseEntity> apiResponseEntityList = apiResponseDao.findApiResponseList(apiResponseQuery);

        List<ApiResponse> apiResponseList = BeanMapper.mapList(apiResponseEntityList, ApiResponse.class);

        return apiResponseList;
    }

    @Override
    public Pagination<ApiResponse> findApiResponsePage(ApiResponseQuery apiResponseQuery) {

        Pagination<ApiResponseEntity>  pagination = apiResponseDao.findApiResponsePage(apiResponseQuery);

        List<ApiResponse> apiResponseList = BeanMapper.mapList(pagination.getDataList(), ApiResponse.class);


        return PaginationBuilder.build(pagination, apiResponseList);
    }
}