package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.ResponseInstanceDao;
import com.doublekit.apibox.apitest.entity.ResponseInstanceEntity;
import com.doublekit.apibox.apitest.model.ResponseInstance;
import com.doublekit.apibox.apitest.model.ResponseInstanceQuery;

import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class ResponseInstanceServiceImpl implements ResponseInstanceService {

    @Autowired
    ResponseInstanceDao responseInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseInstance(@NotNull @Valid ResponseInstance responseInstance) {
        ResponseInstanceEntity responseInstanceEntity = BeanMapper.map(responseInstance, ResponseInstanceEntity.class);

        return responseInstanceDao.createResponseInstance(responseInstanceEntity);
    }

    @Override
    public void updateResponseInstance(@NotNull @Valid ResponseInstance responseInstance) {
        ResponseInstanceEntity responseInstanceEntity = BeanMapper.map(responseInstance, ResponseInstanceEntity.class);

        responseInstanceDao.updateResponseInstance(responseInstanceEntity);
    }

    @Override
    public void deleteResponseInstance(@NotNull String id) {
        responseInstanceDao.deleteResponseInstance(id);
    }

    @Override
    public ResponseInstance findOne(String id) {
        ResponseInstanceEntity responseInstanceEntity = responseInstanceDao.findResponseInstance(id);

        ResponseInstance responseInstance = BeanMapper.map(responseInstanceEntity, ResponseInstance.class);
        return responseInstance;
    }

    @Override
    public List<ResponseInstance> findList(List<String> idList) {
        List<ResponseInstanceEntity> responseInstanceEntityList =  responseInstanceDao.findResponseInstanceList(idList);

        List<ResponseInstance> responseInstanceList =  BeanMapper.mapList(responseInstanceEntityList,ResponseInstance.class);
        return responseInstanceList;
    }

    @Override
    public ResponseInstance findResponseInstance(@NotNull String id) {
        ResponseInstance responseInstance = findOne(id);

        joinTemplate.joinQuery(responseInstance);
        return responseInstance;
    }

    @Override
    public List<ResponseInstance> findAllResponseInstance() {
        List<ResponseInstanceEntity> responseInstanceEntityList =  responseInstanceDao.findAllResponseInstance();

        List<ResponseInstance> responseInstanceList =  BeanMapper.mapList(responseInstanceEntityList,ResponseInstance.class);

        joinTemplate.joinQuery(responseInstanceList);
        return responseInstanceList;
    }

    @Override
    public List<ResponseInstance> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        List<ResponseInstanceEntity> responseInstanceEntityList = responseInstanceDao.findResponseInstanceList(responseInstanceQuery);

        List<ResponseInstance> responseInstanceList = BeanMapper.mapList(responseInstanceEntityList,ResponseInstance.class);

        joinTemplate.joinQuery(responseInstanceList);

        return responseInstanceList;
    }

    @Override
    public Pagination<ResponseInstance> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) {

        Pagination<ResponseInstanceEntity>  pagination = responseInstanceDao.findResponseInstancePage(responseInstanceQuery);

        List<ResponseInstance> responseInstanceList = BeanMapper.mapList(pagination.getDataList(),ResponseInstance.class);

        joinTemplate.joinQuery(responseInstanceList);

        return PaginationBuilder.build(pagination,responseInstanceList);
    }
}