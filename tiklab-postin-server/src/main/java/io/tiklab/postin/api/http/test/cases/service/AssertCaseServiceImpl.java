package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.postin.api.http.test.cases.dao.AssertCaseDao;
import io.tiklab.postin.api.http.test.cases.entity.AssertCasesEntity;
import io.tiklab.postin.api.http.test.cases.model.AssertCases;
import io.tiklab.postin.api.http.test.cases.model.AssertCaseQuery;

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
public class AssertCaseServiceImpl implements AssertCaseService {

    @Autowired
    AssertCaseDao assertCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAssertCase(@NotNull @Valid AssertCases assertCases) {
        AssertCasesEntity assertCasesEntity = BeanMapper.map(assertCases, AssertCasesEntity.class);

        return assertCaseDao.createAssertCase(assertCasesEntity);
    }

    @Override
    public void updateAssertCase(@NotNull @Valid AssertCases assertCases) {
        AssertCasesEntity assertCasesEntity = BeanMapper.map(assertCases, AssertCasesEntity.class);

        assertCaseDao.updateAssertCase(assertCasesEntity);
    }

    @Override
    public void deleteAssertCase(@NotNull String id) {
        assertCaseDao.deleteAssertCase(id);
    }

    @Override
    public AssertCases findOne(String id) {
        AssertCasesEntity assertCasesEntity = assertCaseDao.findAssertCase(id);

        AssertCases assertCases = BeanMapper.map(assertCasesEntity, AssertCases.class);
        return assertCases;
    }

    @Override
    public List<AssertCases> findList(List<String> idList) {
        List<AssertCasesEntity> assertCasesEntityList =  assertCaseDao.findAssertCaseList(idList);

        List<AssertCases> assertCasesList =  BeanMapper.mapList(assertCasesEntityList, AssertCases.class);
        return assertCasesList;
    }

    @Override
    public AssertCases findAssertCase(@NotNull String id) {
        AssertCases assertCases = findOne(id);

        joinTemplate.joinQuery(assertCases);
        return assertCases;
    }

    @Override
    public List<AssertCases> findAllAssertCase() {
        List<AssertCasesEntity> assertCasesEntityList =  assertCaseDao.findAllAssertCase();

        List<AssertCases> assertCasesList =  BeanMapper.mapList(assertCasesEntityList, AssertCases.class);

        joinTemplate.joinQuery(assertCasesList);
        return assertCasesList;
    }

    @Override
    public List<AssertCases> findAssertCaseList(AssertCaseQuery assertCaseQuery) {
        List<AssertCasesEntity> assertCasesEntityList = assertCaseDao.findAssertCaseList(assertCaseQuery);

        List<AssertCases> assertCasesList = BeanMapper.mapList(assertCasesEntityList, AssertCases.class);

        joinTemplate.joinQuery(assertCasesList);

        return assertCasesList;
    }

    @Override
    public Pagination<AssertCases> findAssertCasePage(AssertCaseQuery assertCaseQuery) {

        Pagination<AssertCasesEntity>  pagination = assertCaseDao.findAssertCasePage(assertCaseQuery);

        List<AssertCases> assertCasesList = BeanMapper.mapList(pagination.getDataList(), AssertCases.class);

        joinTemplate.joinQuery(assertCasesList);


        return PaginationBuilder.build(pagination, assertCasesList);
    }
}