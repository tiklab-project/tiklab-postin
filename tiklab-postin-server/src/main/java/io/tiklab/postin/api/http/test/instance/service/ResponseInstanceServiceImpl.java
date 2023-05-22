package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.dao.ResponseInstanceDao;
import io.tiklab.postin.api.http.test.instance.entity.ResponseInstancesEntity;
import io.tiklab.postin.api.http.test.instance.model.ResponseInstances;
import io.tiklab.postin.api.http.test.instance.model.ResponseInstanceQuery;
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
public class ResponseInstanceServiceImpl implements ResponseInstanceService {

    @Autowired
    ResponseInstanceDao responseInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseInstance(@NotNull @Valid ResponseInstances responseInstances) {
        ResponseInstancesEntity responseInstancesEntity = BeanMapper.map(responseInstances, ResponseInstancesEntity.class);

        return responseInstanceDao.createResponseInstance(responseInstancesEntity);
    }

    @Override
    public void updateResponseInstance(@NotNull @Valid ResponseInstances responseInstances) {
        ResponseInstancesEntity responseInstancesEntity = BeanMapper.map(responseInstances, ResponseInstancesEntity.class);

        responseInstanceDao.updateResponseInstance(responseInstancesEntity);
    }

    @Override
    public void deleteResponseInstance(@NotNull String id) {
        responseInstanceDao.deleteResponseInstance(id);
    }

    @Override
    public ResponseInstances findOne(String id) {
        ResponseInstancesEntity responseInstancesEntity = responseInstanceDao.findResponseInstance(id);

        ResponseInstances responseInstances = BeanMapper.map(responseInstancesEntity, ResponseInstances.class);
        return responseInstances;
    }

    @Override
    public List<ResponseInstances> findList(List<String> idList) {
        List<ResponseInstancesEntity> responseInstancesEntityList =  responseInstanceDao.findResponseInstanceList(idList);

        List<ResponseInstances> responseInstancesList =  BeanMapper.mapList(responseInstancesEntityList, ResponseInstances.class);
        return responseInstancesList;
    }

    @Override
    public ResponseInstances findResponseInstance(@NotNull String id) {
        ResponseInstances responseInstances = findOne(id);

        joinTemplate.joinQuery(responseInstances);
        return responseInstances;
    }

    @Override
    public List<ResponseInstances> findAllResponseInstance() {
        List<ResponseInstancesEntity> responseInstancesEntityList =  responseInstanceDao.findAllResponseInstance();

        List<ResponseInstances> responseInstancesList =  BeanMapper.mapList(responseInstancesEntityList, ResponseInstances.class);

        joinTemplate.joinQuery(responseInstancesList);
        return responseInstancesList;
    }

    @Override
    public List<ResponseInstances> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        List<ResponseInstancesEntity> responseInstancesEntityList = responseInstanceDao.findResponseInstanceList(responseInstanceQuery);

        List<ResponseInstances> responseInstancesList = BeanMapper.mapList(responseInstancesEntityList, ResponseInstances.class);

        joinTemplate.joinQuery(responseInstancesList);

        return responseInstancesList;
    }

    @Override
    public Pagination<ResponseInstances> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) {

        Pagination<ResponseInstancesEntity>  pagination = responseInstanceDao.findResponseInstancePage(responseInstanceQuery);

        List<ResponseInstances> responseInstancesList = BeanMapper.mapList(pagination.getDataList(), ResponseInstances.class);

        joinTemplate.joinQuery(responseInstancesList);

        return PaginationBuilder.build(pagination, responseInstancesList);
    }
}