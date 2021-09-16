package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.RequestInstanceDao;
import com.doublekit.apibox.apitest.entity.RequestInstancePo;
import com.doublekit.apibox.apitest.model.RequestInstance;
import com.doublekit.apibox.apitest.model.RequestInstanceQuery;

import com.doublekit.apibox.apitest.dao.RequestInstanceDao;
import com.doublekit.apibox.apitest.model.RequestInstance;
import com.doublekit.apibox.apitest.model.RequestInstanceQuery;
import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
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
    JoinQuery joinQuery;

    @Override
    public String createRequestInstance(@NotNull @Valid RequestInstance requestInstance) {
        RequestInstancePo requestInstancePo = BeanMapper.map(requestInstance, RequestInstancePo.class);

        return requestInstanceDao.createRequestInstance(requestInstancePo);
    }

    @Override
    public void updateRequestInstance(@NotNull @Valid RequestInstance requestInstance) {
        RequestInstancePo requestInstancePo = BeanMapper.map(requestInstance, RequestInstancePo.class);

        requestInstanceDao.updateRequestInstance(requestInstancePo);
    }

    @Override
    public void deleteRequestInstance(@NotNull String id) {
        requestInstanceDao.deleteRequestInstance(id);
    }

    @Override
    public RequestInstance findOne(String id) {
        RequestInstancePo requestInstancePo = requestInstanceDao.findRequestInstance(id);

        RequestInstance requestInstance = BeanMapper.map(requestInstancePo, RequestInstance.class);
        return requestInstance;
    }

    @Override
    public List<RequestInstance> findList(List<String> idList) {
        List<RequestInstancePo> requestInstancePoList =  requestInstanceDao.findRequestInstanceList(idList);

        List<RequestInstance> requestInstanceList =  BeanMapper.mapList(requestInstancePoList,RequestInstance.class);
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
        List<RequestInstancePo> requestInstancePoList =  requestInstanceDao.findAllRequestInstance();

        List<RequestInstance> requestInstanceList =  BeanMapper.mapList(requestInstancePoList,RequestInstance.class);

        joinQuery.queryList(requestInstanceList);
        return requestInstanceList;
    }

    @Override
    public List<RequestInstance> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        List<RequestInstancePo> requestInstancePoList = requestInstanceDao.findRequestInstanceList(requestInstanceQuery);

        List<RequestInstance> requestInstanceList = BeanMapper.mapList(requestInstancePoList,RequestInstance.class);

        joinQuery.queryList(requestInstanceList);

        return requestInstanceList;
    }

    @Override
    public Pagination<RequestInstance> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {
        Pagination<RequestInstance> pg = new Pagination<>();

        Pagination<RequestInstancePo>  pagination = requestInstanceDao.findRequestInstancePage(requestInstanceQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestInstance> requestInstanceList = BeanMapper.mapList(pagination.getDataList(),RequestInstance.class);

        joinQuery.queryList(requestInstanceList);

        pg.setDataList(requestInstanceList);
        return pg;
    }
}