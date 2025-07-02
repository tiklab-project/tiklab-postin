package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.PathParamDao;
import io.tiklab.postin.api.http.definition.entity.PathParamEntity;
import io.tiklab.postin.api.http.definition.model.PathParam;
import io.tiklab.postin.api.http.definition.model.PathParamQuery;
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
public class PathParamServiceImpl implements PathParamService {

    @Autowired
    PathParamDao pathParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createPathParam(@NotNull @Valid PathParam pathParam) {
        PathParamEntity pathParamEntity = BeanMapper.map(pathParam, PathParamEntity.class);

        return pathParamDao.createPathParam(pathParamEntity);
    }

    @Override
    public void updatePathParam(@NotNull @Valid PathParam pathParam) {
        PathParamEntity pathParamEntity = BeanMapper.map(pathParam, PathParamEntity.class);

        pathParamDao.updatePathParam(pathParamEntity);
    }

    @Override
    public void deletePathParam(@NotNull String id) {
        pathParamDao.deletePathParam(id);
    }

    @Override
    public void deleteAllPathParam(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(PathParamEntity.class)
                .eq("apiId", id)
                .get();
        pathParamDao.deletePathParamList(deleteCondition);
    }

    @Override
    public PathParam findPathParam(@NotNull String id) {
        PathParamEntity pathParamEntity = pathParamDao.findPathParam(id);

        PathParam pathParam = BeanMapper.map(pathParamEntity, PathParam.class);

        return pathParam;
    }

    @Override
    public List<PathParam> findAllPathParam() {
        List<PathParamEntity> pathParamEntityList =  pathParamDao.findAllPathParam();

        List<PathParam> pathParamList = BeanMapper.mapList(pathParamEntityList, PathParam.class);

        return pathParamList;
    }

    @Override
    public List<PathParam> findPathParamList(PathParamQuery pathParamQuery) {
        List<PathParamEntity> pathParamEntityList = pathParamDao.findPathParamList(pathParamQuery);

        List<PathParam> pathParamList = BeanMapper.mapList(pathParamEntityList, PathParam.class);

        return pathParamList;
    }

    @Override
    public Pagination<PathParam> findPathParamPage(PathParamQuery pathParamQuery) {

        Pagination<PathParamEntity>  pagination = pathParamDao.findPathParamPage(pathParamQuery);

        List<PathParam> pathParamList = BeanMapper.mapList(pagination.getDataList(), PathParam.class);

        return PaginationBuilder.build(pagination, pathParamList);
    }
}