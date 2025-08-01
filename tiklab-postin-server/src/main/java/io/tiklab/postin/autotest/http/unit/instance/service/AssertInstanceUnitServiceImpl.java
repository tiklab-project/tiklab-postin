package io.tiklab.postin.autotest.http.unit.instance.service;

import io.tiklab.postin.autotest.http.unit.instance.dao.AssertInstanceUnitDao;
import io.tiklab.postin.autotest.http.unit.instance.entity.AssertInstanceUnitEntity;
import io.tiklab.postin.autotest.http.unit.instance.model.AssertInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.AssertInstanceUnitQuery;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 断言实例 服务
*/
@Service
public class AssertInstanceUnitServiceImpl implements AssertInstanceUnitService {

    @Autowired
    AssertInstanceUnitDao assertInstanceUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAssertInstance(@NotNull @Valid AssertInstanceUnit assertInstanceUnit) {
        AssertInstanceUnitEntity assertInstanceUnitEntity = BeanMapper.map(assertInstanceUnit, AssertInstanceUnitEntity.class);

        return assertInstanceUnitDao.createAssertInstance(assertInstanceUnitEntity);
    }

    @Override
    public void updateAssertInstance(@NotNull @Valid AssertInstanceUnit assertInstanceUnit) {
        AssertInstanceUnitEntity assertInstanceUnitEntity = BeanMapper.map(assertInstanceUnit, AssertInstanceUnitEntity.class);

        assertInstanceUnitDao.updateAssertInstance(assertInstanceUnitEntity);
    }

    @Override
    public void deleteAssertInstance(@NotNull String id) {
        assertInstanceUnitDao.deleteAssertInstance(id);
    }



    @Override
    public AssertInstanceUnit findOne(String id) {
        AssertInstanceUnitEntity assertInstanceUnitEntity = assertInstanceUnitDao.findAssertInstance(id);

        AssertInstanceUnit assertInstanceUnit = BeanMapper.map(assertInstanceUnitEntity, AssertInstanceUnit.class);
        return assertInstanceUnit;
    }

    @Override
    public List<AssertInstanceUnit> findList(List<String> idList) {
        List<AssertInstanceUnitEntity> assertInstanceUnitEntityList =  assertInstanceUnitDao.findAssertInstanceList(idList);

        List<AssertInstanceUnit> assertInstanceUnitList =  BeanMapper.mapList(assertInstanceUnitEntityList, AssertInstanceUnit.class);
        return assertInstanceUnitList;
    }

    @Override
    public AssertInstanceUnit findAssertInstance(@NotNull String id) {
        AssertInstanceUnit assertInstanceUnit = findOne(id);

        joinTemplate.joinQuery(assertInstanceUnit);
        return assertInstanceUnit;
    }

    @Override
    public List<AssertInstanceUnit> findAllAssertInstance() {
        List<AssertInstanceUnitEntity> assertInstanceUnitEntityList =  assertInstanceUnitDao.findAllAssertInstance();

        List<AssertInstanceUnit> assertInstanceUnitList =  BeanMapper.mapList(assertInstanceUnitEntityList, AssertInstanceUnit.class);

        joinTemplate.joinQuery(assertInstanceUnitList);
        return assertInstanceUnitList;
    }

    @Override
    public List<AssertInstanceUnit> findAssertInstanceList(AssertInstanceUnitQuery assertInstanceUnitQuery) {
        List<AssertInstanceUnitEntity> assertInstanceUnitEntityList = assertInstanceUnitDao.findAssertInstanceList(assertInstanceUnitQuery);

        List<AssertInstanceUnit> assertInstanceUnitList = BeanMapper.mapList(assertInstanceUnitEntityList, AssertInstanceUnit.class);

        joinTemplate.joinQuery(assertInstanceUnitList);

        return assertInstanceUnitList;
    }

    @Override
    public Pagination<AssertInstanceUnit> findAssertInstancePage(AssertInstanceUnitQuery assertInstanceUnitQuery) {

        Pagination<AssertInstanceUnitEntity>  pagination = assertInstanceUnitDao.findAssertInstancePage(assertInstanceUnitQuery);

        List<AssertInstanceUnit> assertInstanceUnitList = BeanMapper.mapList(pagination.getDataList(), AssertInstanceUnit.class);

        joinTemplate.joinQuery(assertInstanceUnitList);

        return PaginationBuilder.build(pagination, assertInstanceUnitList);
    }
}