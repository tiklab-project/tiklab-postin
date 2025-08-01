package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.unit.cases.dao.AfterScriptUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.AfterScriptUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterScriptUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterScriptUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 后置脚本 服务
*/
@Service
public class AfterScriptUnitUnitServiceImpl implements AfterScriptUnitService {

    @Autowired
    AfterScriptUnitDao afterScriptUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAfterScript(@NotNull @Valid AfterScriptUnit afterScriptUnit) {
        AfterScriptUnitEntity afterScriptUnitEntity = BeanMapper.map(afterScriptUnit, AfterScriptUnitEntity.class);

        return afterScriptUnitDao.createAfterScript(afterScriptUnitEntity);
    }

    @Override
    public void updateAfterScript(@NotNull @Valid AfterScriptUnit afterScriptUnit) {
        AfterScriptUnitEntity afterScriptUnitEntity = BeanMapper.map(afterScriptUnit, AfterScriptUnitEntity.class);

        afterScriptUnitDao.updateAfterScript(afterScriptUnitEntity);
    }

    @Override
    public void deleteAfterScript(@NotNull String id) {
        afterScriptUnitDao.deleteAfterScript(id);
    }

    @Override
    public AfterScriptUnit findOne(String id) {
        AfterScriptUnitEntity afterScriptUnitEntity = afterScriptUnitDao.findAfterScript(id);

        AfterScriptUnit afterScriptUnit = BeanMapper.map(afterScriptUnitEntity, AfterScriptUnit.class);
        return afterScriptUnit;
    }

    @Override
    public List<AfterScriptUnit> findList(List<String> idList) {
        List<AfterScriptUnitEntity> afterScriptUnitEntityList =  afterScriptUnitDao.findAfterScriptList(idList);

        List<AfterScriptUnit> afterScriptUnitList =  BeanMapper.mapList(afterScriptUnitEntityList, AfterScriptUnit.class);
        return afterScriptUnitList;
    }

    @Override
    public AfterScriptUnit findAfterScript(@NotNull String id) {
        AfterScriptUnit afterScriptUnit = findOne(id);

        joinTemplate.joinQuery(afterScriptUnit);
        return afterScriptUnit;
    }

    @Override
    public List<AfterScriptUnit> findAllAfterScript() {
        List<AfterScriptUnitEntity> afterScriptUnitEntityList =  afterScriptUnitDao.findAllAfterScript();

        List<AfterScriptUnit> afterScriptUnitList =  BeanMapper.mapList(afterScriptUnitEntityList, AfterScriptUnit.class);

        joinTemplate.joinQuery(afterScriptUnitList);
        return afterScriptUnitList;
    }

    @Override
    public List<AfterScriptUnit> findAfterScriptList(AfterScriptUnitQuery afterScriptUnitQuery) {
        List<AfterScriptUnitEntity> afterScriptUnitEntityList = afterScriptUnitDao.findAfterScriptList(afterScriptUnitQuery);

        List<AfterScriptUnit> afterScriptUnitList = BeanMapper.mapList(afterScriptUnitEntityList, AfterScriptUnit.class);

        joinTemplate.joinQuery(afterScriptUnitList);

        return afterScriptUnitList;
    }

    @Override
    public Pagination<AfterScriptUnit> findAfterScriptPage(AfterScriptUnitQuery afterScriptUnitQuery) {

        Pagination<AfterScriptUnitEntity>  pagination = afterScriptUnitDao.findAfterScriptPage(afterScriptUnitQuery);

        List<AfterScriptUnit> afterScriptUnitList = BeanMapper.mapList(pagination.getDataList(), AfterScriptUnit.class);

        joinTemplate.joinQuery(afterScriptUnitList);

        return PaginationBuilder.build(pagination, afterScriptUnitList);
    }
}