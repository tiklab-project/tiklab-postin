package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.dao.RequestInstanceDao;
import io.tiklab.postin.api.http.test.instance.entity.RequestInstancesEntity;
import io.tiklab.postin.api.http.test.instance.model.RequestInstances;
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
    public String createRequestInstance(@NotNull @Valid RequestInstances requestInstances) {
        RequestInstancesEntity requestInstancesEntity = BeanMapper.map(requestInstances, RequestInstancesEntity.class);

        return requestInstanceDao.createRequestInstance(requestInstancesEntity);
    }

    @Override
    public void updateRequestInstance(@NotNull @Valid RequestInstances requestInstances) {
        RequestInstancesEntity requestInstancesEntity = BeanMapper.map(requestInstances, RequestInstancesEntity.class);

        requestInstanceDao.updateRequestInstance(requestInstancesEntity);
    }

    @Override
    public void deleteRequestInstance(@NotNull String id) {
        requestInstanceDao.deleteRequestInstance(id);
    }

    @Override
    public RequestInstances findOne(String id) {
        RequestInstancesEntity requestInstancesEntity = requestInstanceDao.findRequestInstance(id);

        RequestInstances requestInstances = BeanMapper.map(requestInstancesEntity, RequestInstances.class);
        return requestInstances;
    }

    @Override
    public List<RequestInstances> findList(List<String> idList) {
        List<RequestInstancesEntity> requestInstancesEntityList =  requestInstanceDao.findRequestInstanceList(idList);

        List<RequestInstances> requestInstancesList =  BeanMapper.mapList(requestInstancesEntityList, RequestInstances.class);
        return requestInstancesList;
    }

    @Override
    public RequestInstances findRequestInstance(@NotNull String id) {
        RequestInstances requestInstances = findOne(id);

        joinTemplate.joinQuery(requestInstances);
        return requestInstances;
    }

    @Override
    public List<RequestInstances> findAllRequestInstance() {
        List<RequestInstancesEntity> requestInstancesEntityList =  requestInstanceDao.findAllRequestInstance();

        List<RequestInstances> requestInstancesList =  BeanMapper.mapList(requestInstancesEntityList, RequestInstances.class);

        joinTemplate.joinQuery(requestInstancesList);
        return requestInstancesList;
    }

    @Override
    public List<RequestInstances> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        List<RequestInstancesEntity> requestInstancesEntityList = requestInstanceDao.findRequestInstanceList(requestInstanceQuery);

        List<RequestInstances> requestInstancesList = BeanMapper.mapList(requestInstancesEntityList, RequestInstances.class);

        joinTemplate.joinQuery(requestInstancesList);

        return requestInstancesList;
    }

    @Override
    public Pagination<RequestInstances> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {

        Pagination<RequestInstancesEntity>  pagination = requestInstanceDao.findRequestInstancePage(requestInstanceQuery);

        List<RequestInstances> requestInstancesList = BeanMapper.mapList(pagination.getDataList(), RequestInstances.class);

        joinTemplate.joinQuery(requestInstancesList);

        return PaginationBuilder.build(pagination, requestInstancesList);
    }
}