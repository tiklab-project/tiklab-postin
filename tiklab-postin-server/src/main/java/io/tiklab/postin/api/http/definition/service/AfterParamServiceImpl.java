package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.AfterParamDao;
import io.tiklab.postin.api.http.definition.entity.AfterParamEntity;
import io.tiklab.postin.api.http.definition.model.*;
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
public class AfterParamServiceImpl implements AfterParamService {

    @Autowired
    AfterParamDao afterParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    OperateDatabaseService operateDatabaseService;

    @Autowired
    OperateScriptService operateScriptService;

    @Override
    public String createAfterParam(@NotNull @Valid AfterParam afterParam) {
        AfterParamEntity afterParamEntity = BeanMapper.map(afterParam, AfterParamEntity.class);
        //获取最大排序
        int sort = afterParamDao.bigSort(afterParam.getApiId());
        afterParamEntity.setSort(1+sort);

        String id = afterParamDao.createAfterParam(afterParamEntity);

        switch (afterParam.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                OperateDatabase operateDatabase = new OperateDatabase();
                if(afterParam.getOperateDatabase()!=null){
                    operateDatabase = afterParam.getOperateDatabase();
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
                if(afterParam.getOperateScript()!=null){
                    operateScript = afterParam.getOperateScript();
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
    public void updateAfterParam(@NotNull @Valid AfterParam afterParam) {
        AfterParamEntity afterParamEntity = BeanMapper.map(afterParam, AfterParamEntity.class);
        afterParamDao.updateAfterParam(afterParamEntity);

        switch (afterParam.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                OperateDatabase operateDatabase = afterParam.getOperateDatabase();
                operateDatabaseService.updateOperateDatabase(operateDatabase);
                break;
            case MagicValue.OPERATION_TYPE_SCRIPT:
                OperateScript operateScript = afterParam.getOperateScript();
                operateScriptService.updateOperateScript(operateScript);
                break;
            default:
        }
    }

    @Override
    public void deleteAfterParam(@NotNull String id) {
        AfterParam afterParam = findAfterParam(id);
        String apiId = afterParam.getApiId();
        int deletedSort = afterParam.getSort();

        switch (afterParam.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                operateDatabaseService.deleteOperateDatabase(id);
                break;
            case MagicValue.OPERATION_TYPE_SCRIPT:
                operateScriptService.deleteOperateScript(id);
                break;
            default:
        }

        afterParamDao.deleteAfterParam(id);
        
        // 删除后更新排序：将排序值大于被删除项的所有记录的排序值减1
        afterParamDao.updateSortAfterDelete(apiId, deletedSort);
    }

    @Override
    public void deleteAllAfterParam(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AfterParamEntity.class)
                .eq("apiId", id)
                .get();
        afterParamDao.deleteAfterParamList(deleteCondition);
    }

    @Override
    public AfterParam findAfterParam(@NotNull String id) {
        AfterParamEntity afterParamEntity = afterParamDao.findAfterParam(id);
        AfterParam afterParam = BeanMapper.map(afterParamEntity, AfterParam.class);

        return afterParam;
    }

    @Override
    public List<AfterParam> findAllAfterParam() {
        List<AfterParamEntity> afterParamEntityList =  afterParamDao.findAllAfterParam();

        List<AfterParam> afterParamList = BeanMapper.mapList(afterParamEntityList, AfterParam.class);

        return afterParamList;
    }

    @Override
    public List<AfterParam> findAfterParamList(AfterParamQuery afterParamQuery) {
        List<AfterParamEntity> afterParamEntityList = afterParamDao.findAfterParamList(afterParamQuery);

        List<AfterParam> afterParamList = BeanMapper.mapList(afterParamEntityList, AfterParam.class);

        if(afterParamList!=null){
            for(AfterParam afterParam:afterParamList){
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
    public Pagination<AfterParam> findAfterParamPage(AfterParamQuery afterParamQuery) {

        Pagination<AfterParamEntity>  pagination = afterParamDao.findAfterParamPage(afterParamQuery);

        List<AfterParam> afterParamList = BeanMapper.mapList(pagination.getDataList(), AfterParam.class);

        return PaginationBuilder.build(pagination, afterParamList);
    }

    @Override
    public void updateAfterParamSort(@NotNull String id, @NotNull Integer newSort) {
        AfterParam afterParam = findAfterParam(id);
        if (afterParam == null) {
            return;
        }
        
        String apiId = afterParam.getApiId();
        int oldSort = afterParam.getSort();
        
        // 如果排序值没有变化，直接返回
        if (oldSort == newSort) {
            return;
        }
        
        // 先更新其他记录的排序
        afterParamDao.updateSortAfterDrag(apiId, oldSort, newSort);
        
        // 再更新当前记录的排序
        AfterParamEntity afterParamEntity = new AfterParamEntity();
        afterParamEntity.setId(id);
        afterParamEntity.setSort(newSort);
        afterParamDao.updateAfterParam(afterParamEntity);
    }
}