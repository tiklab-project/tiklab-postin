package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.dao.PreScriptUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.PreScriptUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.PreScriptUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.PreScriptUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 前置脚本 服务
*/
@Service
public class PreScriptUnitUnitServiceImpl implements PreScriptUnitService {

    @Autowired
    PreScriptUnitDao preScriptUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createPreScript(@NotNull @Valid PreScriptUnit preScriptUnit) {
        PreScriptUnitEntity preScriptUnitEntity = BeanMapper.map(preScriptUnit, PreScriptUnitEntity.class);

        return preScriptUnitDao.createPreScript(preScriptUnitEntity);
    }

    @Override
    public void updatePreScript(@NotNull @Valid PreScriptUnit preScriptUnit) {
        PreScriptUnitEntity preScriptUnitEntity = BeanMapper.map(preScriptUnit, PreScriptUnitEntity.class);

        preScriptUnitDao.updatePreScript(preScriptUnitEntity);
    }

    @Override
    public void deletePreScript(@NotNull String id) {
        preScriptUnitDao.deletePreScript(id);
    }


    @Override
    public PreScriptUnit findOne(String id) {
        PreScriptUnitEntity preScriptUnitEntity = preScriptUnitDao.findPreScript(id);

        PreScriptUnit preScriptUnit = BeanMapper.map(preScriptUnitEntity, PreScriptUnit.class);
        return preScriptUnit;
    }

    @Override
    public List<PreScriptUnit> findList(List<String> idList) {
        List<PreScriptUnitEntity> preScriptUnitEntityList =  preScriptUnitDao.findPreScriptList(idList);

        List<PreScriptUnit> preScriptUnitList =  BeanMapper.mapList(preScriptUnitEntityList, PreScriptUnit.class);
        return preScriptUnitList;
    }

    @Override
    public PreScriptUnit findPreScript(@NotNull String id) {
        PreScriptUnit preScriptUnit = findOne(id);

        joinTemplate.joinQuery(preScriptUnit);
        return preScriptUnit;
    }

    @Override
    public List<PreScriptUnit> findAllPreScript() {
        List<PreScriptUnitEntity> preScriptUnitEntityList =  preScriptUnitDao.findAllPreScript();

        List<PreScriptUnit> preScriptUnitList =  BeanMapper.mapList(preScriptUnitEntityList, PreScriptUnit.class);

        joinTemplate.joinQuery(preScriptUnitList);
        return preScriptUnitList;
    }

    @Override
    public List<PreScriptUnit> findPreScriptList(PreScriptUnitQuery preScriptUnitQuery) {
        List<PreScriptUnitEntity> preScriptUnitEntityList = preScriptUnitDao.findPreScriptList(preScriptUnitQuery);

        List<PreScriptUnit> preScriptUnitList = BeanMapper.mapList(preScriptUnitEntityList, PreScriptUnit.class);

        joinTemplate.joinQuery(preScriptUnitList);

        return preScriptUnitList;
    }

    @Override
    public Pagination<PreScriptUnit> findPreScriptPage(PreScriptUnitQuery preScriptUnitQuery) {

        Pagination<PreScriptUnitEntity>  pagination = preScriptUnitDao.findPreScriptPage(preScriptUnitQuery);

        List<PreScriptUnit> preScriptUnitList = BeanMapper.mapList(pagination.getDataList(), PreScriptUnit.class);

        joinTemplate.joinQuery(preScriptUnitList);

        return PaginationBuilder.build(pagination, preScriptUnitList);
    }
}