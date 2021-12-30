package com.doublekit.apibox.sysmgr.apistatus.service;

import com.doublekit.apibox.sysmgr.apistatus.dao.ApiStatusDao;
import com.doublekit.apibox.sysmgr.apistatus.entity.ApiStatusEntity;
import com.doublekit.apibox.sysmgr.apistatus.model.ApiStatus;
import com.doublekit.apibox.sysmgr.apistatus.model.ApiStatusQuery;

import com.doublekit.common.page.Pagination;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiStatusServiceImpl
*/
@Service
public class ApiStatusServiceImpl implements ApiStatusService {

    @Autowired
    ApiStatusDao apiStatusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiStatus(@NotNull @Valid ApiStatus apiStatus) {
        ApiStatusEntity apiStatusEntity = BeanMapper.map(apiStatus, ApiStatusEntity.class);

        return apiStatusDao.createApiStatus(apiStatusEntity);
    }

    @Override
    public void updateApiStatus(@NotNull @Valid ApiStatus apiStatus) {
        ApiStatusEntity apiStatusEntity = BeanMapper.map(apiStatus, ApiStatusEntity.class);

        apiStatusDao.updateApiStatus(apiStatusEntity);
    }

    @Override
    public void deleteApiStatus(@NotNull String id) {
        apiStatusDao.deleteApiStatus(id);
    }

    @Override
    public ApiStatus findOne(String id) {
        ApiStatusEntity apiStatusEntity = apiStatusDao.findApiStatus(id);

        ApiStatus apiStatus = BeanMapper.map(apiStatusEntity, ApiStatus.class);
        return apiStatus;
    }

    @Override
    public List<ApiStatus> findList(List<String> idList) {
        List<ApiStatusEntity> apiStatusEntityList =  apiStatusDao.findApiStatusList(idList);

        List<ApiStatus> apiStatusList =  BeanMapper.mapList(apiStatusEntityList,ApiStatus.class);
        return apiStatusList;
    }

    @Override
    public ApiStatus findApiStatus(@NotNull String id) {
        ApiStatus apiStatus = findOne(id);

        joinTemplate.joinQuery(apiStatus);
        return apiStatus;
    }

    @Override
    public List<ApiStatus> findAllApiStatus() {
        List<ApiStatusEntity> apiStatusEntityList =  apiStatusDao.findAllApiStatus();

        List<ApiStatus> apiStatusList =  BeanMapper.mapList(apiStatusEntityList,ApiStatus.class);

        joinTemplate.joinQuery(apiStatusList);
        return apiStatusList;
    }

    @Override
    public List<ApiStatus> findApiStatusList(ApiStatusQuery apiStatusQuery) {
        List<ApiStatusEntity> apiStatusEntityList = apiStatusDao.findApiStatusList(apiStatusQuery);

        List<ApiStatus> apiStatusList = BeanMapper.mapList(apiStatusEntityList,ApiStatus.class);

        joinTemplate.joinQuery(apiStatusList);

        return apiStatusList;
    }

    @Override
    public Pagination<ApiStatus> findApiStatusPage(ApiStatusQuery apiStatusQuery) {
        Pagination<ApiStatusEntity>  pagination = apiStatusDao.findApiStatusPage(apiStatusQuery);

        List<ApiStatus> apiStatusList = BeanMapper.mapList(pagination.getDataList(),ApiStatus.class);

        joinTemplate.joinQuery(apiStatusList);

        return PaginationBuilder.build(pagination,apiStatusList);
    }
}