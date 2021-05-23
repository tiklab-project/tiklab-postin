package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.AssertCaseDao;
import com.darthcloud.apibox.apitest.entity.AssertCasePo;
import com.darthcloud.apibox.apitest.model.AssertCase;
import com.darthcloud.apibox.apitest.model.AssertCaseQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.dsl.beans.BeanMapper;
import com.darthcloud.dsl.join.join.JoinQuery;
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
    JoinQuery joinQuery;

    @Override
    public String createAssertCase(@NotNull @Valid AssertCase assertCase) {
        AssertCasePo assertCasePo = BeanMapper.map(assertCase, AssertCasePo.class);

        return assertCaseDao.createAssertCase(assertCasePo);
    }

    @Override
    public void updateAssertCase(@NotNull @Valid AssertCase assertCase) {
        AssertCasePo assertCasePo = BeanMapper.map(assertCase, AssertCasePo.class);

        assertCaseDao.updateAssertCase(assertCasePo);
    }

    @Override
    public void deleteAssertCase(@NotNull String id) {
        assertCaseDao.deleteAssertCase(id);
    }

    @Override
    public AssertCase findOne(String id) {
        AssertCasePo assertCasePo = assertCaseDao.findAssertCase(id);

        AssertCase assertCase = BeanMapper.map(assertCasePo, AssertCase.class);
        return assertCase;
    }

    @Override
    public List<AssertCase> findList(List<String> idList) {
        List<AssertCasePo> assertCasePoList =  assertCaseDao.findAssertCaseList(idList);

        List<AssertCase> assertCaseList =  BeanMapper.mapList(assertCasePoList,AssertCase.class);
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
        List<AssertCasePo> assertCasePoList =  assertCaseDao.findAllAssertCase();

        List<AssertCase> assertCaseList =  BeanMapper.mapList(assertCasePoList,AssertCase.class);

        joinQuery.queryList(assertCaseList);
        return assertCaseList;
    }

    @Override
    public List<AssertCase> findAssertCaseList(AssertCaseQuery assertCaseQuery) {
        List<AssertCasePo> assertCasePoList = assertCaseDao.findAssertCaseList(assertCaseQuery);

        List<AssertCase> assertCaseList = BeanMapper.mapList(assertCasePoList,AssertCase.class);

        joinQuery.queryList(assertCaseList);

        return assertCaseList;
    }

    @Override
    public Pagination<AssertCase> findAssertCasePage(AssertCaseQuery assertCaseQuery) {
        Pagination<AssertCase> pg = new Pagination<>();

        Pagination<AssertCasePo>  pagination = assertCaseDao.findAssertCasePage(assertCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<AssertCase> assertCaseList = BeanMapper.mapList(pagination.getDataList(),AssertCase.class);

        joinQuery.queryList(assertCaseList);

        pg.setDataList(assertCaseList);
        return pg;
    }
}