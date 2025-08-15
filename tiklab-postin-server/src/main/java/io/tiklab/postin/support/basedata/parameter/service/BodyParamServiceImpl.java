package io.tiklab.postin.support.basedata.parameter.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.support.basedata.parameter.dao.BodyParamDao;
import io.tiklab.postin.support.basedata.parameter.entity.BodyParamEntity;
import io.tiklab.postin.support.basedata.parameter.model.BodyParam;
import io.tiklab.postin.support.basedata.parameter.model.BodyParamQuery;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 基础数据中的body公共参数 (formdata/formurlencoded) 服务
*/
@Exporter
@Service
public class BodyParamServiceImpl implements BodyParamService {

    @Autowired
    BodyParamDao bodyParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createBodyParam(@NotNull @Valid BodyParam bodyParam) {
        BodyParamEntity bodyParamEntity = BeanMapper.map(bodyParam, BodyParamEntity.class);

        return bodyParamDao.createBodyParam(bodyParamEntity);
    }

    @Override
    public void updateBodyParam(@NotNull @Valid BodyParam bodyParam) {
        BodyParamEntity bodyParamEntity = BeanMapper.map(bodyParam, BodyParamEntity.class);

        bodyParamDao.updateBodyParam(bodyParamEntity);
    }

    @Override
    public void deleteBodyParam(@NotNull String id) {
        bodyParamDao.deleteBodyParam(id);
    }

    @Override
    public void deleteAllBodyParam(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(BodyParamEntity.class)
                .eq("workspaceId", id)
                .get();
        bodyParamDao.deleteBodyParamLsit(deleteCondition);
    }

    @Override
    public BodyParam findBodyParam(@NotNull String id) {
        BodyParamEntity bodyParamEntity = bodyParamDao.findBodyParam(id);

        BodyParam bodyParam = BeanMapper.map(bodyParamEntity, BodyParam.class);

        return bodyParam;
    }

    @Override
    public List<BodyParam> findAllBodyParam() {
        List<BodyParamEntity> bodyParamEntityList =  bodyParamDao.findAllBodyParam();

        List<BodyParam> bodyParamList = BeanMapper.mapList(bodyParamEntityList,BodyParam.class);

        return bodyParamList;
    }

    @Override
    public List<BodyParam> findBodyParamList(BodyParamQuery bodyParamQuery) {
        List<BodyParamEntity> bodyParamEntityList = bodyParamDao.findBodyParamList(bodyParamQuery);

        List<BodyParam> bodyParamList = BeanMapper.mapList(bodyParamEntityList,BodyParam.class);


        return bodyParamList;
    }

    @Override
    public Pagination<BodyParam> findBodyParamPage(BodyParamQuery bodyParamQuery) {

        Pagination<BodyParamEntity>  pagination = bodyParamDao.findBodyParamPage(bodyParamQuery);

        List<BodyParam> bodyParamList = BeanMapper.mapList(pagination.getDataList(),BodyParam.class);


        return PaginationBuilder.build(pagination,bodyParamList);
    }
}