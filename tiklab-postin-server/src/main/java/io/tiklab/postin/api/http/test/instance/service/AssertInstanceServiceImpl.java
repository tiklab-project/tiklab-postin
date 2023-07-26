package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.entity.AssertInstancesEntity;
import io.tiklab.postin.api.http.test.instance.dao.AssertInstanceDao;


import io.tiklab.postin.api.http.test.instance.model.AssertInstance;
import io.tiklab.postin.api.http.test.instance.model.AssertInstanceQuery;
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
public class AssertInstanceServiceImpl implements AssertInstanceService {

    @Autowired
    AssertInstanceDao assertInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAssertInstance(@NotNull @Valid AssertInstance assertInstance) {
        AssertInstancesEntity assertInstancesEntity = BeanMapper.map(assertInstance, AssertInstancesEntity.class);

        return assertInstanceDao.createAssertInstance(assertInstancesEntity);
    }

    @Override
    public void updateAssertInstance(@NotNull @Valid AssertInstance assertInstance) {
        AssertInstancesEntity assertInstancesEntity = BeanMapper.map(assertInstance, AssertInstancesEntity.class);

        assertInstanceDao.updateAssertInstance(assertInstancesEntity);
    }

    @Override
    public void deleteAssertInstance(@NotNull String id) {
        assertInstanceDao.deleteAssertInstance(id);
    }

    @Override
    public AssertInstance findOne(String id) {
        AssertInstancesEntity assertInstancesEntity = assertInstanceDao.findAssertInstance(id);

        AssertInstance assertInstance = BeanMapper.map(assertInstancesEntity, AssertInstance.class);
        return assertInstance;
    }

    @Override
    public List<AssertInstance> findList(List<String> idList) {
        List<AssertInstancesEntity> assertInstancesEntityList =  assertInstanceDao.findAssertInstanceList(idList);

        List<AssertInstance> assertInstanceList =  BeanMapper.mapList(assertInstancesEntityList, AssertInstance.class);
        return assertInstanceList;
    }

    @Override
    public AssertInstance findAssertInstance(@NotNull String id) {
        AssertInstance assertInstance = findOne(id);

        joinTemplate.joinQuery(assertInstance);
        return assertInstance;
    }

    @Override
    public List<AssertInstance> findAllAssertInstance() {
        List<AssertInstancesEntity> assertInstancesEntityList =  assertInstanceDao.findAllAssertInstance();

        List<AssertInstance> assertInstanceList =  BeanMapper.mapList(assertInstancesEntityList, AssertInstance.class);

        joinTemplate.joinQuery(assertInstanceList);
        return assertInstanceList;
    }

    @Override
    public List<AssertInstance> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        List<AssertInstancesEntity> assertInstancesEntityList = assertInstanceDao.findAssertInstanceList(assertInstanceQuery);

        List<AssertInstance> assertInstanceList = BeanMapper.mapList(assertInstancesEntityList, AssertInstance.class);

        joinTemplate.joinQuery(assertInstanceList);

        return assertInstanceList;
    }

    @Override
    public Pagination<AssertInstance> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) {

        Pagination<AssertInstancesEntity>  pagination = assertInstanceDao.findAssertInstancePage(assertInstanceQuery);

        List<AssertInstance> assertInstanceList = BeanMapper.mapList(pagination.getDataList(), AssertInstance.class);

        joinTemplate.joinQuery(assertInstanceList);

        return PaginationBuilder.build(pagination, assertInstanceList);
    }
}