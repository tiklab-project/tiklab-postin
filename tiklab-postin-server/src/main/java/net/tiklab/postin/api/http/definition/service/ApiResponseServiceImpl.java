package net.tiklab.postin.api.http.definition.service;

import net.tiklab.postin.api.http.definition.dao.ApiResponseDao;
import net.tiklab.postin.api.http.definition.entity.ApiResponseEntity;
import net.tiklab.postin.api.http.definition.model.ApiResponse;
import net.tiklab.postin.api.http.definition.model.ApiResponseQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 用户服务业务处理
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
    public ApiResponse findApiResponse(@NotNull String id) {
        ApiResponseEntity apiResponseEntity = apiResponseDao.findApiResponse(id);

        ApiResponse apiResponse = BeanMapper.map(apiResponseEntity, ApiResponse.class);

        joinTemplate.joinQuery(apiResponse);

        return apiResponse;
    }

    @Override
    public List<ApiResponse> findAllApiResponse() {
        List<ApiResponseEntity> apiResponseEntityList =  apiResponseDao.findAllApiResponse();

        List<ApiResponse> apiResponseList =  BeanMapper.mapList(apiResponseEntityList, ApiResponse.class);

        joinTemplate.joinQuery(apiResponseList);

        return apiResponseList;
    }

    @Override
    public List<ApiResponse> findApiResponseList(ApiResponseQuery apiResponseQuery) {
        List<ApiResponseEntity> apiResponseEntityList = apiResponseDao.findApiResponseList(apiResponseQuery);

        List<ApiResponse> apiResponseList = BeanMapper.mapList(apiResponseEntityList, ApiResponse.class);

        joinTemplate.joinQuery(apiResponseList);

        return apiResponseList;
    }

    @Override
    public Pagination<ApiResponse> findApiResponsePage(ApiResponseQuery apiResponseQuery) {

        Pagination<ApiResponseEntity>  pagination = apiResponseDao.findApiResponsePage(apiResponseQuery);

        List<ApiResponse> apiResponseList = BeanMapper.mapList(pagination.getDataList(), ApiResponse.class);

        joinTemplate.joinQuery(apiResponseList);

        return PaginationBuilder.build(pagination, apiResponseList);
    }
}