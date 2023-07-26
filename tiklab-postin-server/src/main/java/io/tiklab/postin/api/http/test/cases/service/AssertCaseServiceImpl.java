package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.postin.api.http.test.cases.dao.AssertCaseDao;
import io.tiklab.postin.api.http.test.cases.entity.AssertCasesEntity;
import io.tiklab.postin.api.http.test.cases.model.AssertCase;
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
    public String createAssertCase(@NotNull @Valid AssertCase assertCase) {
        AssertCasesEntity assertCasesEntity = BeanMapper.map(assertCase, AssertCasesEntity.class);

        return assertCaseDao.createAssertCase(assertCasesEntity);
    }

    @Override
    public void updateAssertCase(@NotNull @Valid AssertCase assertCase) {
        AssertCasesEntity assertCasesEntity = BeanMapper.map(assertCase, AssertCasesEntity.class);

        assertCaseDao.updateAssertCase(assertCasesEntity);
    }

    @Override
    public void deleteAssertCase(@NotNull String id) {
        assertCaseDao.deleteAssertCase(id);
    }

    @Override
    public AssertCase findOne(String id) {
        AssertCasesEntity assertCasesEntity = assertCaseDao.findAssertCase(id);

        AssertCase assertCase = BeanMapper.map(assertCasesEntity, AssertCase.class);
        return assertCase;
    }

    @Override
    public List<AssertCase> findList(List<String> idList) {
        List<AssertCasesEntity> assertCasesEntityList =  assertCaseDao.findAssertCaseList(idList);

        List<AssertCase> assertCaseList =  BeanMapper.mapList(assertCasesEntityList, AssertCase.class);
        return assertCaseList;
    }

    @Override
    public AssertCase findAssertCase(@NotNull String id) {
        AssertCase assertCase = findOne(id);

        joinTemplate.joinQuery(assertCase);
        return assertCase;
    }

    @Override
    public List<AssertCase> findAllAssertCase() {
        List<AssertCasesEntity> assertCasesEntityList =  assertCaseDao.findAllAssertCase();

        List<AssertCase> assertCaseList =  BeanMapper.mapList(assertCasesEntityList, AssertCase.class);

        joinTemplate.joinQuery(assertCaseList);
        return assertCaseList;
    }

    @Override
    public List<AssertCase> findAssertCaseList(AssertCaseQuery assertCaseQuery) {
        List<AssertCasesEntity> assertCasesEntityList = assertCaseDao.findAssertCaseList(assertCaseQuery);

        List<AssertCase> assertCaseList = BeanMapper.mapList(assertCasesEntityList, AssertCase.class);

        joinTemplate.joinQuery(assertCaseList);

        return assertCaseList;
    }

    @Override
    public Pagination<AssertCase> findAssertCasePage(AssertCaseQuery assertCaseQuery) {

        Pagination<AssertCasesEntity>  pagination = assertCaseDao.findAssertCasePage(assertCaseQuery);

        List<AssertCase> assertCaseList = BeanMapper.mapList(pagination.getDataList(), AssertCase.class);

        joinTemplate.joinQuery(assertCaseList);


        return PaginationBuilder.build(pagination, assertCaseList);
    }
}