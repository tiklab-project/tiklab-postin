package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.autotest.http.unit.cases.dao.PathParamUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.PathParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.PathParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.PathParamUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * path服务
 */
@Service
public class PathParamUnitServiceImpl implements PathParamUnitService {

    @Autowired
    PathParamUnitDao pathParamUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createPathParamUnit(@NotNull @Valid PathParamUnit pathParamUnit) {
        PathParamUnitEntity pathParamUnitEntity = BeanMapper.map(pathParamUnit, PathParamUnitEntity.class);

        return pathParamUnitDao.createPathParamUnit(pathParamUnitEntity);
    }

    @Override
    public void updatePathParamUnit(@NotNull @Valid PathParamUnit pathParamUnit) {
        PathParamUnitEntity pathParamUnitEntity = BeanMapper.map(pathParamUnit, PathParamUnitEntity.class);

        pathParamUnitDao.updatePathParamUnit(pathParamUnitEntity);
    }

    @Override
    public void deletePathParamUnit(@NotNull String id) {
        pathParamUnitDao.deletePathParamUnit(id);
    }

    @Override
    public void deleteAllPathParamUnit(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(PathParamUnitEntity.class)
                .eq("apiUnitId", id)
                .get();
        pathParamUnitDao.deletePathParamUnitList(deleteCondition);
    }

    @Override
    public PathParamUnit findPathParamUnit(@NotNull String id) {
        PathParamUnitEntity pathParamUnitEntity = pathParamUnitDao.findPathParamUnit(id);

        PathParamUnit pathParamUnit = BeanMapper.map(pathParamUnitEntity, PathParamUnit.class);

        return pathParamUnit;
    }

    @Override
    public List<PathParamUnit> findAllPathParamUnit() {
        List<PathParamUnitEntity> pathParamUnitEntityList =  pathParamUnitDao.findAllPathParamUnit();

        List<PathParamUnit> pathParamUnitList = BeanMapper.mapList(pathParamUnitEntityList, PathParamUnit.class);

        return pathParamUnitList;
    }

    @Override
    public List<PathParamUnit> findPathParamUnitList(PathParamUnitQuery pathParamUnitQuery) {
        List<PathParamUnitEntity> pathParamUnitEntityList = pathParamUnitDao.findPathParamUnitList(pathParamUnitQuery);

        List<PathParamUnit> pathParamUnitList = BeanMapper.mapList(pathParamUnitEntityList, PathParamUnit.class);

        return pathParamUnitList;
    }

    @Override
    public Pagination<PathParamUnit> findPathParamUnitPage(PathParamUnitQuery pathParamUnitQuery) {

        Pagination<PathParamUnitEntity>  pagination = pathParamUnitDao.findPathParamUnitPage(pathParamUnitQuery);

        List<PathParamUnit> pathParamUnitList = BeanMapper.mapList(pagination.getDataList(), PathParamUnit.class);

        return PaginationBuilder.build(pagination, pathParamUnitList);
    }
}