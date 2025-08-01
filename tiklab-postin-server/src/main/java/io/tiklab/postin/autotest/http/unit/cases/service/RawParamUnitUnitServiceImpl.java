package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.PaginationBuilder;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.dao.RawParamUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.RawParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.RawParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RawParamUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* raw类型 服务
*/
@Service
public class RawParamUnitUnitServiceImpl implements RawParamUnitService {

    @Autowired
    RawParamUnitDao rawParamUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRawParam(@NotNull @Valid RawParamUnit rawParamUnit) {
        RawParamUnitEntity rawParamUnitEntity = BeanMapper.map(rawParamUnit, RawParamUnitEntity.class);

        return rawParamUnitDao.createRawParam(rawParamUnitEntity);
    }

    @Override
    public void updateRawParam(@NotNull @Valid RawParamUnit rawParamUnit) {
        RawParamUnitEntity rawParamUnitEntity = BeanMapper.map(rawParamUnit, RawParamUnitEntity.class);

        rawParamUnitDao.updateRawParam(rawParamUnitEntity);
    }

    @Override
    public void deleteRawParam(@NotNull String id) {
        rawParamUnitDao.deleteRawParam(id);
    }

    @Override
    public void deleteAllRawParam(String caseId) {
        RawParamUnitQuery rawParamUnitQuery = new RawParamUnitQuery();
        rawParamUnitQuery.setApiUnitId(caseId);
        List<RawParamUnit> rawParamList = findRawParamList(rawParamUnitQuery);
        for(RawParamUnit rawParamUnit: rawParamList){
            rawParamUnitDao.deleteRawParam(rawParamUnit.getId());
        }

    }


    @Override
    public RawParamUnit findOne(String id) {
        RawParamUnitEntity rawParamUnitEntity = rawParamUnitDao.findRawParam(id);

        RawParamUnit rawParamUnit = BeanMapper.map(rawParamUnitEntity, RawParamUnit.class);
        return rawParamUnit;
    }

    @Override
    public List<RawParamUnit> findList(List<String> idList) {
        List<RawParamUnitEntity> rawParamUnitEntityList =  rawParamUnitDao.findRawParamList(idList);

        List<RawParamUnit> rawParamUnitList =  BeanMapper.mapList(rawParamUnitEntityList, RawParamUnit.class);
        return rawParamUnitList;
    }

    @Override
    public RawParamUnit findRawParam(@NotNull String id) {
        RawParamUnit rawParamUnit = findOne(id);

        joinTemplate.joinQuery(rawParamUnit);
        return rawParamUnit;
    }

    @Override
    public List<RawParamUnit> findAllRawParam() {
        List<RawParamUnitEntity> rawParamUnitEntityList =  rawParamUnitDao.findAllRawParam();

        List<RawParamUnit> rawParamUnitList =  BeanMapper.mapList(rawParamUnitEntityList, RawParamUnit.class);

        joinTemplate.joinQuery(rawParamUnitList);
        return rawParamUnitList;
    }

    @Override
    public List<RawParamUnit> findRawParamList(RawParamUnitQuery rawParamUnitQuery) {
        List<RawParamUnitEntity> rawParamUnitEntityList = rawParamUnitDao.findRawParamList(rawParamUnitQuery);

        List<RawParamUnit> rawParamUnitList = BeanMapper.mapList(rawParamUnitEntityList, RawParamUnit.class);

        joinTemplate.joinQuery(rawParamUnitList);

        return rawParamUnitList;
    }

    @Override
    public Pagination<RawParamUnit> findRawParamPage(RawParamUnitQuery rawParamUnitQuery) {

        Pagination<RawParamUnitEntity>  pagination = rawParamUnitDao.findRawParamPage(rawParamUnitQuery);

        List<RawParamUnit> rawParamUnitList = BeanMapper.mapList(pagination.getDataList(), RawParamUnit.class);

        joinTemplate.joinQuery(rawParamUnitList);


        return PaginationBuilder.build(pagination, rawParamUnitList);
    }
}