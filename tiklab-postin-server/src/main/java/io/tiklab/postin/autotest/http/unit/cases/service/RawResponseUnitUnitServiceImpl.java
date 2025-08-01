package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.unit.cases.dao.RawResponseUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.RawResponseUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.RawResponseUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RawResponseUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应中raw 服务
*/
@Service
public class RawResponseUnitUnitServiceImpl implements RawResponseUnitService {

    @Autowired
    RawResponseUnitDao rawResponseUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRawResponse(@NotNull @Valid RawResponseUnit rawResponseUnit) {
        RawResponseUnitEntity rawResponseUnitEntity = BeanMapper.map(rawResponseUnit, RawResponseUnitEntity.class);

        return rawResponseUnitDao.createRawResponse(rawResponseUnitEntity);
    }

    @Override
    public void updateRawResponse(@NotNull @Valid RawResponseUnit rawResponseUnit) {
        RawResponseUnitEntity rawResponseUnitEntity = BeanMapper.map(rawResponseUnit, RawResponseUnitEntity.class);

        rawResponseUnitDao.updateRawResponse(rawResponseUnitEntity);
    }

    @Override
    public void deleteRawResponse(@NotNull String id) {
        rawResponseUnitDao.deleteRawResponse(id);
    }

    @Override
    public RawResponseUnit findOne(String id) {
        RawResponseUnitEntity rawResponseUnitEntity = rawResponseUnitDao.findRawResponse(id);

        RawResponseUnit rawResponseUnit = BeanMapper.map(rawResponseUnitEntity, RawResponseUnit.class);
        return rawResponseUnit;
    }

    @Override
    public List<RawResponseUnit> findList(List<String> idList) {
        List<RawResponseUnitEntity> rawResponseUnitEntityList =  rawResponseUnitDao.findRawResponseList(idList);

        List<RawResponseUnit> rawResponseUnitList =  BeanMapper.mapList(rawResponseUnitEntityList, RawResponseUnit.class);
        return rawResponseUnitList;
    }

    @Override
    public RawResponseUnit findRawResponse(@NotNull String id) {
        RawResponseUnit rawResponseUnit = findOne(id);

        joinTemplate.joinQuery(rawResponseUnit);
        return rawResponseUnit;
    }

    @Override
    public List<RawResponseUnit> findAllRawResponse() {
        List<RawResponseUnitEntity> rawResponseUnitEntityList =  rawResponseUnitDao.findAllRawResponse();

        List<RawResponseUnit> rawResponseUnitList =  BeanMapper.mapList(rawResponseUnitEntityList, RawResponseUnit.class);

        joinTemplate.joinQuery(rawResponseUnitList);
        return rawResponseUnitList;
    }

    @Override
    public List<RawResponseUnit> findRawResponseList(RawResponseUnitQuery rawResponseUnitQuery) {
        List<RawResponseUnitEntity> rawResponseUnitEntityList = rawResponseUnitDao.findRawResponseList(rawResponseUnitQuery);

        List<RawResponseUnit> rawResponseUnitList = BeanMapper.mapList(rawResponseUnitEntityList, RawResponseUnit.class);

        joinTemplate.joinQuery(rawResponseUnitList);

        return rawResponseUnitList;
    }

    @Override
    public Pagination<RawResponseUnit> findRawResponsePage(RawResponseUnitQuery rawResponseUnitQuery) {

        Pagination<RawResponseUnitEntity>  pagination = rawResponseUnitDao.findRawResponsePage(rawResponseUnitQuery);

        List<RawResponseUnit> rawResponseUnitList = BeanMapper.mapList(pagination.getDataList(), RawResponseUnit.class);

        joinTemplate.joinQuery(rawResponseUnitList);

        return PaginationBuilder.build(pagination, rawResponseUnitList);
    }
}