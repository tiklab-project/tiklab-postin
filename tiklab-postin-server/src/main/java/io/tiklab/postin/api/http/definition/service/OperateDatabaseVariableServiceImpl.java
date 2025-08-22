package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;

import io.tiklab.postin.api.http.definition.dao.OperateDatabaseVariableDao;
import io.tiklab.postin.api.http.definition.entity.OperateDatabaseVariableEntity;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseVariable;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseVariableQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据库操作中的变量 服务
 */
@Service
public class OperateDatabaseVariableServiceImpl implements OperateDatabaseVariableService {

    @Autowired
    OperateDatabaseVariableDao operateDatabaseVariableDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    AuthApiKeyService authApiKeyService;

    @Autowired
    AuthBearerService authBearerService;

    @Override
    public String createOperateDatabaseVariable(@NotNull @Valid OperateDatabaseVariable operateDatabaseVariable) {
        OperateDatabaseVariableEntity operateDatabaseVariableEntity = BeanMapper.map(operateDatabaseVariable, OperateDatabaseVariableEntity.class);
        String id = operateDatabaseVariableDao.createOperateDatabaseVariable(operateDatabaseVariableEntity);

        return id;
    }

    @Override
    public void updateOperateDatabaseVariable(@NotNull @Valid OperateDatabaseVariable operateDatabaseVariable) {
        OperateDatabaseVariableEntity operateDatabaseVariableEntity = BeanMapper.map(operateDatabaseVariable, OperateDatabaseVariableEntity.class);

        operateDatabaseVariableDao.updateOperateDatabaseVariable(operateDatabaseVariableEntity);
    }

    @Override
    public void deleteOperateDatabaseVariable(@NotNull String id) {
        operateDatabaseVariableDao.deleteOperateDatabaseVariable(id);
    }

    @Override
    public void deleteAllOperateDatabaseVariable(String operationId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(OperateDatabaseVariableEntity.class)
                .eq("operationId", operationId)
                .get();
        operateDatabaseVariableDao.deleteOperateDatabaseVariableList(deleteCondition);
    }

    @Override
    public OperateDatabaseVariable findOperateDatabaseVariable(@NotNull String id) {
        OperateDatabaseVariableEntity operateDatabaseVariableEntity = operateDatabaseVariableDao.findOperateDatabaseVariable(id);
        OperateDatabaseVariable operateDatabaseVariable = BeanMapper.map(operateDatabaseVariableEntity, OperateDatabaseVariable.class);
        return operateDatabaseVariable;
    }

    @Override
    public List<OperateDatabaseVariable> findAllOperateDatabaseVariable() {
        List<OperateDatabaseVariableEntity> operateDatabaseVariableEntityList =  operateDatabaseVariableDao.findAllOperateDatabaseVariable();

        List<OperateDatabaseVariable> operateDatabaseVariableList = BeanMapper.mapList(operateDatabaseVariableEntityList, OperateDatabaseVariable.class);

        return operateDatabaseVariableList;
    }

    @Override
    public List<OperateDatabaseVariable> findOperateDatabaseVariableList(OperateDatabaseVariableQuery operateDatabaseVariableQuery) {
        List<OperateDatabaseVariableEntity> operateDatabaseVariableEntityList = operateDatabaseVariableDao.findOperateDatabaseVariableList(operateDatabaseVariableQuery);

        List<OperateDatabaseVariable> operateDatabaseVariableList = BeanMapper.mapList(operateDatabaseVariableEntityList, OperateDatabaseVariable.class);

        return operateDatabaseVariableList;
    }

    @Override
    public Pagination<OperateDatabaseVariable> findOperateDatabaseVariablePage(OperateDatabaseVariableQuery operateDatabaseVariableQuery) {

        Pagination<OperateDatabaseVariableEntity>  pagination = operateDatabaseVariableDao.findOperateDatabaseVariablePage(operateDatabaseVariableQuery);

        List<OperateDatabaseVariable> operateDatabaseVariableList = BeanMapper.mapList(pagination.getDataList(), OperateDatabaseVariable.class);

        return PaginationBuilder.build(pagination, operateDatabaseVariableList);
    }
}