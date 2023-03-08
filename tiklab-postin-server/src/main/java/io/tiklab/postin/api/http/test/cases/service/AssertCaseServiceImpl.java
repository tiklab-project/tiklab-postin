package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.postin.api.http.test.cases.dao.AssertCaseDao;
import io.tiklab.postin.api.http.test.cases.entity.AssertCaseEntity;
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

        joinTemplate.joinQuery(assertCase);
        return assertCase;
    }

    @Override
    public List<AssertCase> findAllAssertCase() {
        List<AssertCaseEntity> assertCaseEntityList =  assertCaseDao.findAllAssertCase();

        List<AssertCase> assertCaseList =  BeanMapper.mapList(assertCaseEntityList,AssertCase.class);

        joinTemplate.joinQuery(assertCaseList);
        return assertCaseList;
    }

    @Override
    public List<AssertCase> findAssertCaseList(AssertCaseQuery assertCaseQuery) {
        List<AssertCaseEntity> assertCaseEntityList = assertCaseDao.findAssertCaseList(assertCaseQuery);

        List<AssertCase> assertCaseList = BeanMapper.mapList(assertCaseEntityList,AssertCase.class);

        joinTemplate.joinQuery(assertCaseList);

        return assertCaseList;
    }

    @Override
    public Pagination<AssertCase> findAssertCasePage(AssertCaseQuery assertCaseQuery) {

        Pagination<AssertCaseEntity>  pagination = assertCaseDao.findAssertCasePage(assertCaseQuery);

        List<AssertCase> assertCaseList = BeanMapper.mapList(pagination.getDataList(),AssertCase.class);

        joinTemplate.joinQuery(assertCaseList);


        return PaginationBuilder.build(pagination,assertCaseList);
    }
}