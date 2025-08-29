package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.model.OperateDatabase;
import io.tiklab.postin.api.http.definition.model.OperateScript;
import io.tiklab.postin.api.http.definition.service.OperateDatabaseService;
import io.tiklab.postin.api.http.definition.service.OperateScriptService;
import io.tiklab.postin.autotest.http.unit.cases.dao.AfterParamUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.AfterParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterParamUnitQuery;
import io.tiklab.postin.common.MagicValue;
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
public class AfterParamUnitServiceImpl implements AfterParamUnitService {

    @Autowired
    AfterParamUnitDao afterParamUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    OperateDatabaseService operateDatabaseService;

    @Autowired
    OperateScriptService operateScriptService;

    @Override
    public String createAfterParamUnit(@NotNull @Valid AfterParamUnit afterParamUnit) {
        AfterParamUnitEntity afterParamUnitEntity = BeanMapper.map(afterParamUnit, AfterParamUnitEntity.class);
        //获取最大排序
        int sort = afterParamUnitDao.bigSort(afterParamUnit.getApiUnitId());
        afterParamUnitEntity.setSort(1+sort);

        String id = afterParamUnitDao.createAfterParamUnit(afterParamUnitEntity);

        switch (afterParamUnit.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                OperateDatabase operateDatabase = new OperateDatabase();
                if(afterParamUnit.getOperateDatabase()!=null){
                    operateDatabase = afterParamUnit.getOperateDatabase();
                    operateDatabase.setOperationId(id);
                    operateDatabase.setId(id);
                }else {
                    operateDatabase.setOperationId(id);
                    operateDatabase.setId(id);
                    operateDatabase.setSqlText("");
                    operateDatabase.setIsConsolePrint(0);
                }
                operateDatabaseService.createOperateDatabase(operateDatabase);
                break;
            case MagicValue.OPERATION_TYPE_SCRIPT:
                OperateScript operateScript = new OperateScript();
                if(afterParamUnit.getOperateScript()!=null){
                    operateScript = afterParamUnit.getOperateScript();
                    operateScript.setOperationId(id);
                    operateScript.setId(id);
                }else {
                    operateScript.setOperationId(id);
                    operateScript.setId(id);
                    operateScript.setScriptText("");
                }
                operateScriptService.createOperateScript(operateScript);
                break;
            default:
        }
        return id;
    }

    @Override
    public void updateAfterParamUnit(@NotNull @Valid AfterParamUnit afterParamUnit) {
        AfterParamUnitEntity afterParamUnitEntity = BeanMapper.map(afterParamUnit, AfterParamUnitEntity.class);
        afterParamUnitDao.updateAfterParamUnit(afterParamUnitEntity);

        switch (afterParamUnit.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                OperateDatabase operateDatabase = afterParamUnit.getOperateDatabase();
                operateDatabaseService.updateOperateDatabase(operateDatabase);
                break;
            case MagicValue.OPERATION_TYPE_SCRIPT:
                OperateScript operateScript = afterParamUnit.getOperateScript();
                operateScriptService.updateOperateScript(operateScript);
                break;
            default:
        }
    }

    @Override
    public void deleteAfterParamUnit(@NotNull String id) {
        AfterParamUnit afterParamUnit = findAfterParamUnit(id);

        switch (afterParamUnit.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                operateDatabaseService.deleteOperateDatabase(id);
                break;
            case MagicValue.OPERATION_TYPE_SCRIPT:
                operateScriptService.deleteOperateScript(id);
                break;
            default:
        }


        afterParamUnitDao.deleteAfterParamUnit(id);
    }

    @Override
    public void deleteAllAfterParamUnit(String id) {
//        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AfterParamUnitEntity.class)
//                .eq("apiUnitId", id)
//                .get();
//        afterParamUnitDao.deleteAfterParamUnitList(deleteCondition);
        AfterParamUnitQuery afterParamUnitQuery = new AfterParamUnitQuery();
        afterParamUnitQuery.setApiUnitId(id);
        List<AfterParamUnit> afterParamUnitList = findAfterParamUnitList(afterParamUnitQuery);
        if(afterParamUnitList==null) return;
        for(AfterParamUnit afterParamUnit:afterParamUnitList){
            deleteAfterParamUnit(afterParamUnit.getId());
        }
    }

    @Override
    public AfterParamUnit findAfterParamUnit(@NotNull String id) {
        AfterParamUnitEntity afterParamUnitEntity = afterParamUnitDao.findAfterParamUnit(id);
        AfterParamUnit afterParamUnit = BeanMapper.map(afterParamUnitEntity, AfterParamUnit.class);

        return afterParamUnit;
    }

    @Override
    public List<AfterParamUnit> findAllAfterParamUnit() {
        List<AfterParamUnitEntity> afterParamUnitEntityList =  afterParamUnitDao.findAllAfterParamUnit();

        List<AfterParamUnit> afterParamUnitList = BeanMapper.mapList(afterParamUnitEntityList, AfterParamUnit.class);

        return afterParamUnitList;
    }

    @Override
    public List<AfterParamUnit> findAfterParamUnitList(AfterParamUnitQuery afterParamQuery) {
        List<AfterParamUnitEntity> afterParamEntityList = afterParamUnitDao.findAfterParamUnitList(afterParamQuery);

        List<AfterParamUnit> afterParamList = BeanMapper.mapList(afterParamEntityList, AfterParamUnit.class);

        if(afterParamList!=null){
            for(AfterParamUnit afterParam:afterParamList){
                switch (afterParam.getType()){
                    case MagicValue.OPERATION_TYPE_DATABASE:
                        afterParam.setOperateDatabase(operateDatabaseService.findOperateDatabase(afterParam.getId()));
                        break;
                    case MagicValue.OPERATION_TYPE_SCRIPT:
                        afterParam.setOperateScript(operateScriptService.findOperateScript(afterParam.getId()));
                        break;
                    default:
                }
            }
        }

        return afterParamList;
    }

    @Override
    public Pagination<AfterParamUnit> findAfterParamUnitPage(AfterParamUnitQuery afterParamUnitQuery) {

        Pagination<AfterParamUnitEntity>  pagination = afterParamUnitDao.findAfterParamUnitPage(afterParamUnitQuery);

        List<AfterParamUnit> afterParamUnitList = BeanMapper.mapList(pagination.getDataList(), AfterParamUnit.class);

        return PaginationBuilder.build(pagination, afterParamUnitList);
    }
}