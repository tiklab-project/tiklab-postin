package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.entity.AssertInstancesEntity;
import io.tiklab.postin.api.http.test.instance.dao.AssertInstanceDao;


import io.tiklab.postin.api.http.test.instance.model.AssertInstances;
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
    public String createAssertInstance(@NotNull @Valid AssertInstances assertInstances) {
        AssertInstancesEntity assertInstancesEntity = BeanMapper.map(assertInstances, AssertInstancesEntity.class);

        return assertInstanceDao.createAssertInstance(assertInstancesEntity);
    }

    @Override
    public void updateAssertInstance(@NotNull @Valid AssertInstances assertInstances) {
        AssertInstancesEntity assertInstancesEntity = BeanMapper.map(assertInstances, AssertInstancesEntity.class);

        assertInstanceDao.updateAssertInstance(assertInstancesEntity);
    }

    @Override
    public void deleteAssertInstance(@NotNull String id) {
        assertInstanceDao.deleteAssertInstance(id);
    }

    @Override
    public AssertInstances findOne(String id) {
        AssertInstancesEntity assertInstancesEntity = assertInstanceDao.findAssertInstance(id);

        AssertInstances assertInstances = BeanMapper.map(assertInstancesEntity, AssertInstances.class);
        return assertInstances;
    }

    @Override
    public List<AssertInstances> findList(List<String> idList) {
        List<AssertInstancesEntity> assertInstancesEntityList =  assertInstanceDao.findAssertInstanceList(idList);

        List<AssertInstances> assertInstancesList =  BeanMapper.mapList(assertInstancesEntityList, AssertInstances.class);
        return assertInstancesList;
    }

    @Override
    public AssertInstances findAssertInstance(@NotNull String id) {
        AssertInstances assertInstances = findOne(id);

        joinTemplate.joinQuery(assertInstances);
        return assertInstances;
    }

    @Override
    public List<AssertInstances> findAllAssertInstance() {
        List<AssertInstancesEntity> assertInstancesEntityList =  assertInstanceDao.findAllAssertInstance();

        List<AssertInstances> assertInstancesList =  BeanMapper.mapList(assertInstancesEntityList, AssertInstances.class);

        joinTemplate.joinQuery(assertInstancesList);
        return assertInstancesList;
    }

    @Override
    public List<AssertInstances> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        List<AssertInstancesEntity> assertInstancesEntityList = assertInstanceDao.findAssertInstanceList(assertInstanceQuery);

        List<AssertInstances> assertInstancesList = BeanMapper.mapList(assertInstancesEntityList, AssertInstances.class);

        joinTemplate.joinQuery(assertInstancesList);

        return assertInstancesList;
    }

    @Override
    public Pagination<AssertInstances> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) {

        Pagination<AssertInstancesEntity>  pagination = assertInstanceDao.findAssertInstancePage(assertInstanceQuery);

        List<AssertInstances> assertInstancesList = BeanMapper.mapList(pagination.getDataList(), AssertInstances.class);

        joinTemplate.joinQuery(assertInstancesList);

        return PaginationBuilder.build(pagination, assertInstancesList);
    }
}