package io.thoughtware.postin.api.http.test.instance.service;

import io.thoughtware.postin.api.http.test.instance.dao.RequestInstanceDao;
import io.thoughtware.postin.api.http.test.instance.entity.RequestInstanceEntity;
import io.thoughtware.postin.api.http.test.instance.model.RequestInstance;
import io.thoughtware.postin.api.http.test.instance.model.RequestInstanceQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class RequestInstanceServiceImpl implements RequestInstanceService {

    @Autowired
    RequestInstanceDao requestInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        List<RequestInstance> requestInstanceList =  BeanMapper.mapList(requestInstanceEntityList, RequestInstance.class);
        return requestInstanceList;
    }

    @Override
    public RequestInstance findRequestInstance(@NotNull String id) {
        RequestInstance requestInstance = findOne(id);

        joinTemplate.joinQuery(requestInstance);
        return requestInstance;
    }

    @Override
    public List<RequestInstance> findAllRequestInstance() {
        List<RequestInstanceEntity> requestInstanceEntityList =  requestInstanceDao.findAllRequestInstance();

        List<RequestInstance> requestInstanceList =  BeanMapper.mapList(requestInstanceEntityList, RequestInstance.class);

        joinTemplate.joinQuery(requestInstanceList);
        return requestInstanceList;
    }

    @Override
    public List<RequestInstance> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        List<RequestInstanceEntity> requestInstanceEntityList = requestInstanceDao.findRequestInstanceList(requestInstanceQuery);

        List<RequestInstance> requestInstanceList = BeanMapper.mapList(requestInstanceEntityList, RequestInstance.class);

        joinTemplate.joinQuery(requestInstanceList);

        return requestInstanceList;
    }

    @Override
    public Pagination<RequestInstance> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {

        Pagination<RequestInstanceEntity>  pagination = requestInstanceDao.findRequestInstancePage(requestInstanceQuery);

        List<RequestInstance> requestInstanceList = BeanMapper.mapList(pagination.getDataList(), RequestInstance.class);

        joinTemplate.joinQuery(requestInstanceList);

        return PaginationBuilder.build(pagination, requestInstanceList);
    }
}