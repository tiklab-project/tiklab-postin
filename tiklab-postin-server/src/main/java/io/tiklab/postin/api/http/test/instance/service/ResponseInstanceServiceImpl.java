package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.dao.ResponseInstanceDao;
import io.tiklab.postin.api.http.test.instance.entity.ResponseInstancesEntity;
import io.tiklab.postin.api.http.test.instance.model.ResponseInstance;
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
    public String createResponseInstance(@NotNull @Valid ResponseInstance responseInstance) {
        ResponseInstancesEntity responseInstancesEntity = BeanMapper.map(responseInstance, ResponseInstancesEntity.class);

        return responseInstanceDao.createResponseInstance(responseInstancesEntity);
    }

    @Override
    public void updateResponseInstance(@NotNull @Valid ResponseInstance responseInstance) {
        ResponseInstancesEntity responseInstancesEntity = BeanMapper.map(responseInstance, ResponseInstancesEntity.class);

        responseInstanceDao.updateResponseInstance(responseInstancesEntity);
    }

    @Override
    public void deleteResponseInstance(@NotNull String id) {
        responseInstanceDao.deleteResponseInstance(id);
    }

    @Override
    public ResponseInstance findOne(String id) {
        ResponseInstancesEntity responseInstancesEntity = responseInstanceDao.findResponseInstance(id);

        ResponseInstance responseInstance = BeanMapper.map(responseInstancesEntity, ResponseInstance.class);
        return responseInstance;
    }

    @Override
    public List<ResponseInstance> findList(List<String> idList) {
        List<ResponseInstancesEntity> responseInstancesEntityList =  responseInstanceDao.findResponseInstanceList(idList);

        List<ResponseInstance> responseInstanceList =  BeanMapper.mapList(responseInstancesEntityList, ResponseInstance.class);
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
        List<ResponseInstancesEntity> responseInstancesEntityList =  responseInstanceDao.findAllResponseInstance();

        List<ResponseInstance> responseInstanceList =  BeanMapper.mapList(responseInstancesEntityList, ResponseInstance.class);

        joinTemplate.joinQuery(responseInstanceList);
        return responseInstanceList;
    }

    @Override
    public List<ResponseInstance> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        List<ResponseInstancesEntity> responseInstancesEntityList = responseInstanceDao.findResponseInstanceList(responseInstanceQuery);

        List<ResponseInstance> responseInstanceList = BeanMapper.mapList(responseInstancesEntityList, ResponseInstance.class);

        joinTemplate.joinQuery(responseInstanceList);

        return responseInstanceList;
    }

    @Override
    public Pagination<ResponseInstance> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) {

        Pagination<ResponseInstancesEntity>  pagination = responseInstanceDao.findResponseInstancePage(responseInstanceQuery);

        List<ResponseInstance> responseInstanceList = BeanMapper.mapList(pagination.getDataList(), ResponseInstance.class);

        joinTemplate.joinQuery(responseInstanceList);

        return PaginationBuilder.build(pagination, responseInstanceList);
    }
}