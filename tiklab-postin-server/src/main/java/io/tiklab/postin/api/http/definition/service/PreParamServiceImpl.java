package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.PreParamDao;
import io.tiklab.postin.api.http.definition.entity.PreParamEntity;
import io.tiklab.postin.api.http.definition.model.OperateDatabase;
import io.tiklab.postin.api.http.definition.model.OperateScript;
import io.tiklab.postin.api.http.definition.model.PreParam;
import io.tiklab.postin.api.http.definition.model.PreParamQuery;
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
public class PreParamServiceImpl implements PreParamService {

    @Autowired
    PreParamDao preParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    OperateDatabaseService operateDatabaseService;

    @Autowired
    OperateScriptService operateScriptService;


    @Override
    public String createPreParam(@NotNull @Valid PreParam preParam) {
        PreParamEntity preParamEntity = BeanMapper.map(preParam, PreParamEntity.class);
        //获取最大排序
        int sort = preParamDao.bigSort(preParam.getApiId());
        preParamEntity.setSort(1+sort);

        String id = preParamDao.createPreParam(preParamEntity);


        switch (preParam.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                OperateDatabase operateDatabase = new OperateDatabase();
                if(preParam.getOperateDatabase()!=null){
                    operateDatabase = preParam.getOperateDatabase();
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
                if(preParam.getOperateScript()!=null){
                    operateScript = preParam.getOperateScript();
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
    public void updatePreParam(@NotNull @Valid PreParam preParam) {
        PreParamEntity preParamEntity = BeanMapper.map(preParam, PreParamEntity.class);
        preParamDao.updatePreParam(preParamEntity);

        switch (preParam.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                OperateDatabase operateDatabase = preParam.getOperateDatabase();
                operateDatabaseService.updateOperateDatabase(operateDatabase);
                break;
            case MagicValue.OPERATION_TYPE_SCRIPT:
                OperateScript operateScript = preParam.getOperateScript();
                operateScriptService.updateOperateScript(operateScript);
                break;
            default:
        }

    }

    @Override
    public void deletePreParam(@NotNull String id) {
        PreParam preParam = findPreParam(id);

        switch (preParam.getType()){
            case MagicValue.OPERATION_TYPE_DATABASE:
                operateDatabaseService.deleteOperateDatabase(id);
                break;
            case MagicValue.OPERATION_TYPE_SCRIPT:
                operateScriptService.deleteOperateScript(id);
                break;
            default:
        }

        preParamDao.deletePreParam(id);
    }

    @Override
    public void deleteAllPreParam(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(PreParamEntity.class)
                .eq("apiId", id)
                .get();
        preParamDao.deletePreParamList(deleteCondition);
    }

    @Override
    public PreParam findPreParam(@NotNull String id) {
        PreParamEntity preParamEntity = preParamDao.findPreParam(id);

        return BeanMapper.map(preParamEntity, PreParam.class);
    }

    @Override
    public List<PreParam> findAllPreParam() {
        List<PreParamEntity> preParamEntityList =  preParamDao.findAllPreParam();

        List<PreParam> preParamList = BeanMapper.mapList(preParamEntityList, PreParam.class);

        return preParamList;
    }

    @Override
    public List<PreParam> findPreParamList(PreParamQuery preParamQuery) {
        List<PreParamEntity> preParamEntityList = preParamDao.findPreParamList(preParamQuery);

        List<PreParam> preParamList = BeanMapper.mapList(preParamEntityList, PreParam.class);

        if(preParamList!=null){
            for(PreParam preParam:preParamList){
                switch (preParam.getType()){
                    case MagicValue.OPERATION_TYPE_DATABASE:
                        preParam.setOperateDatabase(operateDatabaseService.findOperateDatabase(preParam.getId()));
                        break;
                    case MagicValue.OPERATION_TYPE_SCRIPT:
                        preParam.setOperateScript(operateScriptService.findOperateScript(preParam.getId()));
                        break;
                    default:
                }
            }
        }


        return preParamList;
    }

    @Override
    public Pagination<PreParam> findPreParamPage(PreParamQuery preParamQuery) {

        Pagination<PreParamEntity>  pagination = preParamDao.findPreParamPage(preParamQuery);

        List<PreParam> preParamList = BeanMapper.mapList(pagination.getDataList(), PreParam.class);

        return PaginationBuilder.build(pagination, preParamList);
    }
}