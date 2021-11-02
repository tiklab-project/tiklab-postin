package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.RequestInstanceDao;
import com.doublekit.apibox.apitest.entity.RequestInstanceEntity;
import com.doublekit.apibox.apitest.model.RequestInstance;
import com.doublekit.apibox.apitest.model.RequestInstanceQuery;

import com.doublekit.apibox.apitest.dao.RequestInstanceDao;
import com.doublekit.apibox.apitest.model.RequestInstance;
import com.doublekit.apibox.apitest.model.RequestInstanceQuery;
import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class RequestInstanceServiceImpl implements RequestInstanceService {

    @Autowired
    RequestInstanceDao requestInstanceDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createRequestInstance(@NotNull @Valid RequestInstance requestInstance) {
        RequestInstanceEntity requestInstanceEntity = BeanMapper.map(requestInstance, RequestInstanceEntity.class);

        return requestInstanceDao.createRequestInstance(requestInstanceEntity);
    }

    @Override
    public void updateRequestInstance(@NotNull @Valid RequestInstance requestInstance) {
        RequestInstanceEntity requestInstanceEntity = BeanMapper.map(requestInstance, RequestInstanceEntity.class);

        requestInstanceDao.updateRequestInstance(requestInstanceEntity);
    }

    @Override
    public void deleteRequestInstance(@NotNull String id) {
        requestInstanceDao.deleteRequestInstance(id);
    }

    @Override
    public RequestInstance findOne(String id) {
        RequestInstanceEntity requestInstanceEntity = requestInstanceDao.findRequestInstance(id);

        RequestInstance requestInstance = BeanMapper.map(requestInstanceEntity, RequestInstance.class);
        return requestInstance;
    }

    @Override
    public List<RequestInstance> findList(List<String> idList) {
        List<RequestInstanceEntity> requestInstanceEntityList =  requestInstanceDao.findRequestInstanceList(idList);

        List<RequestInstance> requestInstanceList =  BeanMapper.mapList(requestInstanceEntityList,RequestInstance.class);
        return requestInstanceList;
    }

    @Override
    public RequestInstance findRequestInstance(@NotNull String id) {
        RequestInstance requestInstance = findOne(id);

        joinQuery.queryOne(requestInstance);
        return requestInstance;
    }

    @Override
    public List<RequestInstance> findAllRequestInstance() {
        List<RequestInstanceEntity> requestInstanceEntityList =  requestInstanceDao.findAllRequestInstance();

        List<RequestInstance> requestInstanceList =  BeanMapper.mapList(requestInstanceEntityList,RequestInstance.class);

        joinQuery.queryList(requestInstanceList);
        return requestInstanceList;
    }

    @Override
    public List<RequestInstance> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        List<RequestInstanceEntity> requestInstanceEntityList = requestInstanceDao.findRequestInstanceList(requestInstanceQuery);

        List<RequestInstance> requestInstanceList = BeanMapper.mapList(requestInstanceEntityList,RequestInstance.class);

        joinQuery.queryList(requestInstanceList);

        return requestInstanceList;
    }

    @Override
    public Pagination<RequestInstance> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {
        Pagination<RequestInstance> pg = new Pagination<>();

        Pagination<RequestInstanceEntity>  pagination = requestInstanceDao.findRequestInstancePage(requestInstanceQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestInstance> requestInstanceList = BeanMapper.mapList(pagination.getDataList(),RequestInstance.class);

        joinQuery.queryList(requestInstanceList);

        pg.setDataList(requestInstanceList);
        return pg;
    }
}