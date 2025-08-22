package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.OperateScriptDao;
import io.tiklab.postin.api.http.definition.entity.OperateScriptEntity;
import io.tiklab.postin.api.http.definition.model.OperateScript;
import io.tiklab.postin.api.http.definition.model.OperateScriptQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 后置操作 服务
 */
@Service
public class OperateScriptServiceImpl implements OperateScriptService {

    @Autowired
    OperateScriptDao operateScriptDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createOperateScript(@NotNull @Valid OperateScript operateScript) {
        OperateScriptEntity operateScriptEntity = BeanMapper.map(operateScript, OperateScriptEntity.class);
        String id = operateScriptDao.createOperateScript(operateScriptEntity);
        return id;
    }

    @Override
    public void updateOperateScript(@NotNull @Valid OperateScript operateScript) {
        OperateScriptEntity operateScriptEntity = BeanMapper.map(operateScript, OperateScriptEntity.class);

        operateScriptDao.updateOperateScript(operateScriptEntity);
    }

    @Override
    public void deleteOperateScript(@NotNull String id) {
        operateScriptDao.deleteOperateScript(id);
    }

    @Override
    public void deleteAllOperateScript(String operationId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(OperateScriptEntity.class)
                .eq("operationId", operationId)
                .get();
        operateScriptDao.deleteOperateScriptList(deleteCondition);
    }

    @Override
    public OperateScript findOperateScript(@NotNull String id) {
        OperateScriptEntity operateScriptEntity = operateScriptDao.findOperateScript(id);
        OperateScript operateScript = BeanMapper.map(operateScriptEntity, OperateScript.class);

        return operateScript;
    }

    @Override
    public List<OperateScript> findAllOperateScript() {
        List<OperateScriptEntity> operateScriptEntityList =  operateScriptDao.findAllOperateScript();

        List<OperateScript> operateScriptList = BeanMapper.mapList(operateScriptEntityList, OperateScript.class);

        return operateScriptList;
    }

    @Override
    public List<OperateScript> findOperateScriptList(OperateScriptQuery operateScriptQuery) {
        List<OperateScriptEntity> operateScriptEntityList = operateScriptDao.findOperateScriptList(operateScriptQuery);

        List<OperateScript> operateScriptList = BeanMapper.mapList(operateScriptEntityList, OperateScript.class);

        return operateScriptList;
    }

    @Override
    public Pagination<OperateScript> findOperateScriptPage(OperateScriptQuery operateScriptQuery) {

        Pagination<OperateScriptEntity>  pagination = operateScriptDao.findOperateScriptPage(operateScriptQuery);

        List<OperateScript> operateScriptList = BeanMapper.mapList(pagination.getDataList(), OperateScript.class);

        return PaginationBuilder.build(pagination, operateScriptList);
    }
}