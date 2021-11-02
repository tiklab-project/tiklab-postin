package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.AssertCaseDao;
import com.doublekit.apibox.apitest.entity.AssertCaseEntity;
import com.doublekit.apibox.apitest.model.AssertCase;
import com.doublekit.apibox.apitest.model.AssertCaseQuery;

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
public class AssertCaseServiceImpl implements AssertCaseService {

    @Autowired
    AssertCaseDao assertCaseDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createAssertCase(@NotNull @Valid AssertCase assertCase) {
        AssertCaseEntity assertCaseEntity = BeanMapper.map(assertCase, AssertCaseEntity.class);

        return assertCaseDao.createAssertCase(assertCaseEntity);
    }

    @Override
    public void updateAssertCase(@NotNull @Valid AssertCase assertCase) {
        AssertCaseEntity assertCaseEntity = BeanMapper.map(assertCase, AssertCaseEntity.class);

        assertCaseDao.updateAssertCase(assertCaseEntity);
    }

    @Override
    public void deleteAssertCase(@NotNull String id) {
        assertCaseDao.deleteAssertCase(id);
    }

    @Override
    public AssertCase findOne(String id) {
        AssertCaseEntity assertCaseEntity = assertCaseDao.findAssertCase(id);

        AssertCase assertCase = BeanMapper.map(assertCaseEntity, AssertCase.class);
        return assertCase;
    }

    @Override
    public List<AssertCase> findList(List<String> idList) {
        List<AssertCaseEntity> assertCaseEntityList =  assertCaseDao.findAssertCaseList(idList);

        List<AssertCase> assertCaseList =  BeanMapper.mapList(assertCaseEntityList,AssertCase.class);
        return assertCaseList;
    }

    @Override
    public AssertCase findAssertCase(@NotNull String id) {
        AssertCase assertCase = findOne(id);

        joinQuery.queryOne(assertCase);
        return assertCase;
    }

    @Override
    public List<AssertCase> findAllAssertCase() {
        List<AssertCaseEntity> assertCaseEntityList =  assertCaseDao.findAllAssertCase();

        List<AssertCase> assertCaseList =  BeanMapper.mapList(assertCaseEntityList,AssertCase.class);

        joinQuery.queryList(assertCaseList);
        return assertCaseList;
    }

    @Override
    public List<AssertCase> findAssertCaseList(AssertCaseQuery assertCaseQuery) {
        List<AssertCaseEntity> assertCaseEntityList = assertCaseDao.findAssertCaseList(assertCaseQuery);

        List<AssertCase> assertCaseList = BeanMapper.mapList(assertCaseEntityList,AssertCase.class);

        joinQuery.queryList(assertCaseList);

        return assertCaseList;
    }

    @Override
    public Pagination<AssertCase> findAssertCasePage(AssertCaseQuery assertCaseQuery) {
        Pagination<AssertCase> pg = new Pagination<>();

        Pagination<AssertCaseEntity>  pagination = assertCaseDao.findAssertCasePage(assertCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<AssertCase> assertCaseList = BeanMapper.mapList(pagination.getDataList(),AssertCase.class);

        joinQuery.queryList(assertCaseList);

        pg.setDataList(assertCaseList);
        return pg;
    }
}