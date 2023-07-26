package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.dao.RequestInstanceDao;
import io.tiklab.postin.api.http.test.instance.entity.RequestInstancesEntity;
import io.tiklab.postin.api.http.test.instance.model.RequestInstance;
import io.tiklab.postin.api.http.test.instance.model.RequestInstanceQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
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
        RequestInstancesEntity requestInstancesEntity = BeanMapper.map(requestInstance, RequestInstancesEntity.class);

        return requestInstanceDao.createRequestInstance(requestInstancesEntity);
    }

    @Override
    public void updateRequestInstance(@NotNull @Valid RequestInstance requestInstance) {
        RequestInstancesEntity requestInstancesEntity = BeanMapper.map(requestInstance, RequestInstancesEntity.class);

        requestInstanceDao.updateRequestInstance(requestInstancesEntity);
    }

    @Override
    public void deleteRequestInstance(@NotNull String id) {
        requestInstanceDao.deleteRequestInstance(id);
    }

    @Override
    public RequestInstance findOne(String id) {
        RequestInstancesEntity requestInstancesEntity = requestInstanceDao.findRequestInstance(id);

        RequestInstance requestInstance = BeanMapper.map(requestInstancesEntity, RequestInstance.class);
        return requestInstance;
    }

    @Override
    public List<RequestInstance> findList(List<String> idList) {
        List<RequestInstancesEntity> requestInstancesEntityList =  requestInstanceDao.findRequestInstanceList(idList);

        List<RequestInstance> requestInstanceList =  BeanMapper.mapList(requestInstancesEntityList, RequestInstance.class);
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
        List<RequestInstancesEntity> requestInstancesEntityList =  requestInstanceDao.findAllRequestInstance();

        List<RequestInstance> requestInstanceList =  BeanMapper.mapList(requestInstancesEntityList, RequestInstance.class);

        joinTemplate.joinQuery(requestInstanceList);
        return requestInstanceList;
    }

    @Override
    public List<RequestInstance> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        List<RequestInstancesEntity> requestInstancesEntityList = requestInstanceDao.findRequestInstanceList(requestInstanceQuery);

        List<RequestInstance> requestInstanceList = BeanMapper.mapList(requestInstancesEntityList, RequestInstance.class);

        joinTemplate.joinQuery(requestInstanceList);

        return requestInstanceList;
    }

    @Override
    public Pagination<RequestInstance> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {

        Pagination<RequestInstancesEntity>  pagination = requestInstanceDao.findRequestInstancePage(requestInstanceQuery);

        List<RequestInstance> requestInstanceList = BeanMapper.mapList(pagination.getDataList(), RequestInstance.class);

        joinTemplate.joinQuery(requestInstanceList);

        return PaginationBuilder.build(pagination, requestInstanceList);
    }
}