package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.api.http.definition.model.OperateDatabase;
import io.tiklab.postin.api.http.definition.model.OperateScript;
import io.tiklab.postin.api.http.definition.service.OperateDatabaseService;
import io.tiklab.postin.api.http.definition.service.OperateScriptService;
import io.tiklab.postin.autotest.http.unit.cases.dao.PreParamUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.PreParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.PreParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.PreParamUnitQuery;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 前置操作 服务
 */
@Service
public class PreParamUnitServiceImpl implements PreParamUnitService {

    @Autowired
    PreParamUnitDao preParamUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    OperateDatabaseService operateDatabaseService;

    @Autowired
    OperateScriptService operateScriptService;


    @Override
    public String createPreParamUnit(@NotNull @Valid PreParamUnit preParamUnit) {
        PreParamUnitEntity preParamUnitEntity = BeanMapper.map(preParamUnit, PreParamUnitEntity.class);
        //获取最大排序
        int sort = preParamUnitDao.bigSort(preParamUnit.getApiUnitId());
        preParamUnitEntity.setSort(1+sort);

        String id = preParamUnitDao.createPreParamUnit(preParamUnitEntity);


        switch (preParamUnit.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                OperateDatabase operateDatabase = new OperateDatabase();
                if(preParamUnit.getOperateDatabase()!=null){
                    operateDatabase = preParamUnit.getOperateDatabase();
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
                if(preParamUnit.getOperateScript()!=null){
                    operateScript = preParamUnit.getOperateScript();
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

        return id ;
    }

    @Override
    public void updatePreParamUnit(@NotNull @Valid PreParamUnit preParamUnit) {
        PreParamUnitEntity preParamUnitEntity = BeanMapper.map(preParamUnit, PreParamUnitEntity.class);
        preParamUnitDao.updatePreParamUnit(preParamUnitEntity);

        switch (preParamUnit.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                OperateDatabase operateDatabase = preParamUnit.getOperateDatabase();
                operateDatabaseService.updateOperateDatabase(operateDatabase);
                break;
            case MagicValue.OPERATION_TYPE_SCRIPT:
                OperateScript operateScript = preParamUnit.getOperateScript();
                operateScriptService.updateOperateScript(operateScript);
                break;
            default:
        }

    }

    @Override
    public void deletePreParamUnit(@NotNull String id) {
        PreParamUnit preParamUnit = findPreParamUnit(id);
        String apiUnitId = preParamUnit.getApiUnitId();
        int deletedSort = preParamUnit.getSort();

        switch (preParamUnit.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                operateDatabaseService.deleteOperateDatabase(id);
                break;
            case MagicValue.OPERATION_TYPE_SCRIPT:
                operateScriptService.deleteOperateScript(id);
                break;
            default:
        }

        preParamUnitDao.deletePreParamUnit(id);
        
        // 删除后更新排序：将排序值大于被删除项的所有记录的排序值减1
        preParamUnitDao.updateSortAfterDelete(apiUnitId, deletedSort);
    }

    @Override
    public void deleteAllPreParamUnit(String id) {
//        DeleteCondition deleteCondition = DeleteBuilders.createDelete(PreParamUnitEntity.class)
//                .eq("apiUnitId", id)
//                .get();
//        preParamUnitDao.deletePreParamUnitList(deleteCondition);

        PreParamUnitQuery preParamUnitQuery = new PreParamUnitQuery();
        preParamUnitQuery.setApiUnitId(id);
        List<PreParamUnit> preParamUnitList = findPreParamUnitList(preParamUnitQuery);
        if(preParamUnitList==null) return;
        for(PreParamUnit preParamUnit:preParamUnitList){
            deletePreParamUnit(preParamUnit.getId());
        }
    }

    @Override
    public PreParamUnit findPreParamUnit(@NotNull String id) {
        PreParamUnitEntity preParamUnitEntity = preParamUnitDao.findPreParamUnit(id);

        return BeanMapper.map(preParamUnitEntity, PreParamUnit.class);
    }

    @Override
    public List<PreParamUnit> findAllPreParamUnit() {
        List<PreParamUnitEntity> preParamUnitEntityList =  preParamUnitDao.findAllPreParamUnit();

        List<PreParamUnit> preParamUnitList = BeanMapper.mapList(preParamUnitEntityList, PreParamUnit.class);

        return preParamUnitList;
    }

    @Override
    public List<PreParamUnit> findPreParamUnitList(PreParamUnitQuery preParamUnitQuery) {
        List<PreParamUnitEntity> preParamUnitEntityList = preParamUnitDao.findPreParamUnitList(preParamUnitQuery);

        List<PreParamUnit> preParamUnitList = BeanMapper.mapList(preParamUnitEntityList, PreParamUnit.class);

        if(preParamUnitList!=null){
            for(PreParamUnit preParamUnit:preParamUnitList){
                switch (preParamUnit.getType()){
                    case MagicValue.OPERATION_TYPE_DATABASE:
                        preParamUnit.setOperateDatabase(operateDatabaseService.findOperateDatabase(preParamUnit.getId()));
                        break;
                    case MagicValue.OPERATION_TYPE_SCRIPT:
                        preParamUnit.setOperateScript(operateScriptService.findOperateScript(preParamUnit.getId()));
                        break;
                    default:
                }
            }
        }


        return preParamUnitList;
    }

    @Override
    public Pagination<PreParamUnit> findPreParamUnitPage(PreParamUnitQuery preParamUnitQuery) {

        Pagination<PreParamUnitEntity>  pagination = preParamUnitDao.findPreParamUnitPage(preParamUnitQuery);

        List<PreParamUnit> preParamUnitList = BeanMapper.mapList(pagination.getDataList(), PreParamUnit.class);

        return PaginationBuilder.build(pagination, preParamUnitList);
    }

    @Override
    public void updatePreParamUnitSort(@NotNull String id, @NotNull Integer newSort) {
        PreParamUnit preParamUnit = findPreParamUnit(id);
        if (preParamUnit == null) {
            return;
        }
        
        String apiUnitId = preParamUnit.getApiUnitId();
        int oldSort = preParamUnit.getSort();
        
        // 如果排序值没有变化，直接返回
        if (oldSort == newSort) {
            return;
        }
        
        // 先更新其他记录的排序
        preParamUnitDao.updateSortAfterDrag(apiUnitId, oldSort, newSort);
        
        // 再更新当前记录的排序
        PreParamUnitEntity preParamUnitEntity = new PreParamUnitEntity();
        preParamUnitEntity.setId(id);
        preParamUnitEntity.setSort(newSort);
        preParamUnitDao.updatePreParamUnit(preParamUnitEntity);
    }
}