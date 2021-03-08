package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.AssertInstanceDao;
import com.darthcloud.apibox.apitest.entity.AssertInstancePo;
import com.darthcloud.apibox.apitest.model.AssertInstance;
import com.darthcloud.apibox.apitest.model.AssertInstanceQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
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
    JoinQuery joinQuery;

    @Override
    public String createAssertInstance(@NotNull @Valid AssertInstance assertInstance) {
        AssertInstancePo assertInstancePo = BeanMapper.map(assertInstance, AssertInstancePo.class);

        return assertInstanceDao.createAssertInstance(assertInstancePo);
    }

    @Override
    public void updateAssertInstance(@NotNull @Valid AssertInstance assertInstance) {
        AssertInstancePo assertInstancePo = BeanMapper.map(assertInstance, AssertInstancePo.class);

        assertInstanceDao.updateAssertInstance(assertInstancePo);
    }

    @Override
    public void deleteAssertInstance(@NotNull String id) {
        assertInstanceDao.deleteAssertInstance(id);
    }

    @Override
    public AssertInstance findOne(String id) {
        AssertInstancePo assertInstancePo = assertInstanceDao.findAssertInstance(id);

        AssertInstance assertInstance = BeanMapper.map(assertInstancePo, AssertInstance.class);
        return assertInstance;
    }

    @Override
    public List<AssertInstance> findList(List<String> idList) {
        List<AssertInstancePo> assertInstancePoList =  assertInstanceDao.findAssertInstanceList(idList);

        List<AssertInstance> assertInstanceList =  BeanMapper.mapList(assertInstancePoList,AssertInstance.class);
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
        List<AssertInstancePo> assertInstancePoList =  assertInstanceDao.findAllAssertInstance();

        List<AssertInstance> assertInstanceList =  BeanMapper.mapList(assertInstancePoList,AssertInstance.class);

        joinQuery.queryList(assertInstanceList);
        return assertInstanceList;
    }

    @Override
    public List<AssertInstance> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        List<AssertInstancePo> assertInstancePoList = assertInstanceDao.findAssertInstanceList(assertInstanceQuery);

        List<AssertInstance> assertInstanceList = BeanMapper.mapList(assertInstancePoList,AssertInstance.class);

        joinQuery.queryList(assertInstanceList);

        return assertInstanceList;
    }

    @Override
    public Pagination<List<AssertInstance>> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) {
        Pagination<List<AssertInstance>> pg = new Pagination<>();

        Pagination<List<AssertInstancePo>>  pagination = assertInstanceDao.findAssertInstancePage(assertInstanceQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<AssertInstance> assertInstanceList = BeanMapper.mapList(pagination.getDataList(),AssertInstance.class);

        joinQuery.queryList(assertInstanceList);

        pg.setDataList(assertInstanceList);
        return pg;
    }
}