package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.AssertInstanceDao;
import com.doublekit.apibox.apitest.entity.AssertInstanceEntity;
import com.doublekit.apibox.apitest.model.AssertInstance;
import com.doublekit.apibox.apitest.model.AssertInstanceQuery;

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
public class AssertInstanceServiceImpl implements AssertInstanceService {

    @Autowired
    AssertInstanceDao assertInstanceDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createAssertInstance(@NotNull @Valid AssertInstance assertInstance) {
        AssertInstanceEntity assertInstanceEntity = BeanMapper.map(assertInstance, AssertInstanceEntity.class);

        return assertInstanceDao.createAssertInstance(assertInstanceEntity);
    }

    @Override
    public void updateAssertInstance(@NotNull @Valid AssertInstance assertInstance) {
        AssertInstanceEntity assertInstanceEntity = BeanMapper.map(assertInstance, AssertInstanceEntity.class);

        assertInstanceDao.updateAssertInstance(assertInstanceEntity);
    }

    @Override
    public void deleteAssertInstance(@NotNull String id) {
        assertInstanceDao.deleteAssertInstance(id);
    }

    @Override
    public AssertInstance findOne(String id) {
        AssertInstanceEntity assertInstanceEntity = assertInstanceDao.findAssertInstance(id);

        AssertInstance assertInstance = BeanMapper.map(assertInstanceEntity, AssertInstance.class);
        return assertInstance;
    }

    @Override
    public List<AssertInstance> findList(List<String> idList) {
        List<AssertInstanceEntity> assertInstanceEntityList =  assertInstanceDao.findAssertInstanceList(idList);

        List<AssertInstance> assertInstanceList =  BeanMapper.mapList(assertInstanceEntityList,AssertInstance.class);
        return assertInstanceList;
    }

    @Override
    public AssertInstance findAssertInstance(@NotNull String id) {
        AssertInstance assertInstance = findOne(id);

        joinQuery.queryOne(assertInstance);
        return assertInstance;
    }

    @Override
    public List<AssertInstance> findAllAssertInstance() {
        List<AssertInstanceEntity> assertInstanceEntityList =  assertInstanceDao.findAllAssertInstance();

        List<AssertInstance> assertInstanceList =  BeanMapper.mapList(assertInstanceEntityList,AssertInstance.class);

        joinQuery.queryList(assertInstanceList);
        return assertInstanceList;
    }

    @Override
    public List<AssertInstance> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        List<AssertInstanceEntity> assertInstanceEntityList = assertInstanceDao.findAssertInstanceList(assertInstanceQuery);

        List<AssertInstance> assertInstanceList = BeanMapper.mapList(assertInstanceEntityList,AssertInstance.class);

        joinQuery.queryList(assertInstanceList);

        return assertInstanceList;
    }

    @Override
    public Pagination<AssertInstance> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) {
        Pagination<AssertInstance> pg = new Pagination<>();

        Pagination<AssertInstanceEntity>  pagination = assertInstanceDao.findAssertInstancePage(assertInstanceQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<AssertInstance> assertInstanceList = BeanMapper.mapList(pagination.getDataList(),AssertInstance.class);

        joinQuery.queryList(assertInstanceList);

        pg.setDataList(assertInstanceList);
        return pg;
    }
}