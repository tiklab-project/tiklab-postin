package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.unit.cases.dao.AssertCaseUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.AssertCaseUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AssertUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AssertUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 断言 服务
*/
@Service
public class AssertCaseUnitServiceImpl implements AssertUnitService {

    @Autowired
    AssertCaseUnitDao assertCaseUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAssertCase(@NotNull @Valid AssertUnit assertUnit) {
        AssertCaseUnitEntity assertCaseUnitEntity = BeanMapper.map(assertUnit, AssertCaseUnitEntity.class);

        return assertCaseUnitDao.createAssertCase(assertCaseUnitEntity);
    }

    @Override
    public void updateAssertCase(@NotNull @Valid AssertUnit assertUnit) {
        AssertCaseUnitEntity assertCaseUnitEntity = BeanMapper.map(assertUnit, AssertCaseUnitEntity.class);

        assertCaseUnitDao.updateAssertCase(assertCaseUnitEntity);
    }

    @Override
    public void deleteAssertCase(@NotNull String id) {
        assertCaseUnitDao.deleteAssertCase(id);
    }

    @Override
    public void deleteAllAssertCase( String caseId) {
        AssertUnitQuery assertUnitQuery = new AssertUnitQuery();
        assertUnitQuery.setApiUnitId(caseId);
        List<AssertUnit> assertCaseList = findAssertCaseList(assertUnitQuery);
        for (AssertUnit assertCase : assertCaseList) {
            assertCaseUnitDao.deleteAssertCase(assertCase.getId());
        }

    }

    @Override
    public AssertUnit findOne(String id) {
        AssertCaseUnitEntity assertCaseUnitEntity = assertCaseUnitDao.findAssertCase(id);

        AssertUnit assertUnit = BeanMapper.map(assertCaseUnitEntity, AssertUnit.class);
        return assertUnit;
    }

    @Override
    public List<AssertUnit> findList(List<String> idList) {
        List<AssertCaseUnitEntity> assertCaseUnitEntityList =  assertCaseUnitDao.findAssertCaseList(idList);

        List<AssertUnit> assertUnitList =  BeanMapper.mapList(assertCaseUnitEntityList, AssertUnit.class);
        return assertUnitList;
    }

    @Override
    public AssertUnit findAssertCase(@NotNull String id) {
        AssertUnit assertUnit = findOne(id);

        joinTemplate.joinQuery(assertUnit);
        return assertUnit;
    }

    @Override
    public List<AssertUnit> findAllAssertCase() {
        List<AssertCaseUnitEntity> assertCaseUnitEntityList =  assertCaseUnitDao.findAllAssertCase();

        List<AssertUnit> assertUnitList =  BeanMapper.mapList(assertCaseUnitEntityList, AssertUnit.class);

        joinTemplate.joinQuery(assertUnitList);
        return assertUnitList;
    }

    @Override
    public List<AssertUnit> findAssertCaseList(AssertUnitQuery assertUnitQuery) {
        List<AssertCaseUnitEntity> assertCaseUnitEntityList = assertCaseUnitDao.findAssertCaseList(assertUnitQuery);

        List<AssertUnit> assertUnitList = BeanMapper.mapList(assertCaseUnitEntityList, AssertUnit.class);

        joinTemplate.joinQuery(assertUnitList);

        return assertUnitList;
    }

    @Override
    public Pagination<AssertUnit> findAssertCasePage(AssertUnitQuery assertUnitQuery) {

        Pagination<AssertCaseUnitEntity>  pagination = assertCaseUnitDao.findAssertCasePage(assertUnitQuery);

        List<AssertUnit> assertUnitList = BeanMapper.mapList(pagination.getDataList(), AssertUnit.class);

        joinTemplate.joinQuery(assertUnitList);

        return PaginationBuilder.build(pagination, assertUnitList);

    }
}