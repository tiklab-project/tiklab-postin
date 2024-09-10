package io.thoughtware.postin.api.http.definition.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.postin.api.http.definition.dao.PathParamDao;
import io.thoughtware.postin.api.http.definition.entity.PathParamEntity;
import io.thoughtware.postin.api.http.definition.model.PathParam;
import io.thoughtware.postin.api.http.definition.model.PathParamQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
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

        joinTemplate.joinQuery(pathParam);

        return pathParam;
    }

    @Override
    public List<PathParam> findAllPathParam() {
        List<PathParamEntity> pathParamEntityList =  pathParamDao.findAllPathParam();

        List<PathParam> pathParamList = BeanMapper.mapList(pathParamEntityList, PathParam.class);

        joinTemplate.joinQuery(pathParamList);

        return pathParamList;
    }

    @Override
    public List<PathParam> findPathParamList(PathParamQuery pathParamQuery) {
        List<PathParamEntity> pathParamEntityList = pathParamDao.findPathParamList(pathParamQuery);

        List<PathParam> pathParamList = BeanMapper.mapList(pathParamEntityList, PathParam.class);

        joinTemplate.joinQuery(pathParamList);

        return pathParamList;
    }

    @Override
    public Pagination<PathParam> findPathParamPage(PathParamQuery pathParamQuery) {

        Pagination<PathParamEntity>  pagination = pathParamDao.findPathParamPage(pathParamQuery);

        List<PathParam> pathParamList = BeanMapper.mapList(pagination.getDataList(), PathParam.class);

        joinTemplate.joinQuery(pathParamList);

        return PaginationBuilder.build(pagination, pathParamList);
    }
}