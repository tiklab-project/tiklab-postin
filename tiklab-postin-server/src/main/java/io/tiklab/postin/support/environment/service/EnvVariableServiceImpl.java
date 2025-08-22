package io.tiklab.postin.support.environment.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.support.environment.dao.EnvVariableDao;
import io.tiklab.postin.support.environment.entity.EnvVariableEntity;
import io.tiklab.postin.support.environment.model.EnvVariable;
import io.tiklab.postin.support.environment.model.EnvVariableQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 环境中变量 服务
*/
@Service
public class EnvVariableServiceImpl implements EnvVariableService {

    @Autowired
    EnvVariableDao envVariableDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createEnvVariable(@NotNull @Valid EnvVariable envVariable) {
        EnvVariableEntity envVariableEntity = BeanMapper.map(envVariable, EnvVariableEntity.class);

        return envVariableDao.createEnvVariable(envVariableEntity);
    }

    @Override
    public void updateEnvVariable(@NotNull @Valid EnvVariable envVariable) {
        EnvVariableEntity envVariableEntity = BeanMapper.map(envVariable, EnvVariableEntity.class);

        envVariableDao.updateEnvVariable(envVariableEntity);
    }

    @Override
    public void deleteEnvVariable(@NotNull String id) {
        envVariableDao.deleteEnvVariable(id);
    }

    @Override
    public void deleteAllEnvVariable(String workspaceId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(EnvVariableEntity.class)
                .eq("envId", workspaceId)
                .get();
        envVariableDao.deleteEnvVariable(deleteCondition);
    }

    @Override
    public EnvVariable findOne(String id) {
        EnvVariableEntity envVariableEntity = envVariableDao.findEnvVariable(id);

        EnvVariable envVariable = BeanMapper.map(envVariableEntity, EnvVariable.class);
        return envVariable;
    }

    @Override
    public List<EnvVariable> findList(List<String> idList) {
        List<EnvVariableEntity> envVariableEntityList =  envVariableDao.findEnvVariableList(idList);

        List<EnvVariable> envVariableList =  BeanMapper.mapList(envVariableEntityList,EnvVariable.class);
        return envVariableList;
    }

    @Override
    public EnvVariable findEnvVariable(@NotNull String id) {
        EnvVariable envVariable = findOne(id);

        return envVariable;
    }

    @Override
    public List<EnvVariable> findAllEnvVariable() {
        List<EnvVariableEntity> envVariableEntityList =  envVariableDao.findAllEnvVariable();

        List<EnvVariable> envVariableList =  BeanMapper.mapList(envVariableEntityList,EnvVariable.class);

        return envVariableList;
    }

    @Override
    public List<EnvVariable> findEnvVariableList(EnvVariableQuery envVariableQuery) {
        List<EnvVariableEntity> envVariableEntityList = envVariableDao.findEnvVariableList(envVariableQuery);

        List<EnvVariable> envVariableList = BeanMapper.mapList(envVariableEntityList,EnvVariable.class);


        return envVariableList;
    }

    @Override
    public Pagination<EnvVariable> findEnvVariablePage(EnvVariableQuery envVariableQuery) {

        Pagination<EnvVariableEntity>  pagination = envVariableDao.findEnvVariablePage(envVariableQuery);

        List<EnvVariable> envVariableList = BeanMapper.mapList(pagination.getDataList(),EnvVariable.class);


        return PaginationBuilder.build(pagination,envVariableList);
    }

    @Override
    public void batchCreateVariable(JSONObject jsonObject) {
        String envId = jsonObject.getString("envId");
        String workspaceId = jsonObject.getString("workspaceId");
        JSONObject variables = jsonObject.getJSONObject("variables");

        if (variables == null || variables.isEmpty()) {
            return;
        }

        // 查询 environment 变量
        Map<String, EnvVariable> envVariableMap = new HashMap<>();
        if (envId != null) {
            EnvVariableQuery envQuery = new EnvVariableQuery();
            envQuery.setEnvId(envId);
            List<EnvVariable> envVariableList = findEnvVariableList(envQuery);
            envVariableList.forEach(v -> envVariableMap.put(v.getName(), v));
        }

        // 查询 project 变量
        Map<String, EnvVariable> workspaceVariableMap = new HashMap<>();
        if (workspaceId != null) {
            EnvVariableQuery wsQuery = new EnvVariableQuery();
            wsQuery.setWorkspaceId(workspaceId);
            List<EnvVariable> workspaceVariableList = findEnvVariableList(wsQuery);
            workspaceVariableList.forEach(v -> workspaceVariableMap.put(v.getName(), v));
        }

        // 遍历传入的 variables，执行 upsert
        for (String key : variables.keySet()) {
            JSONObject varObj = variables.getJSONObject(key);
            String type = varObj.getString("type");
            String value = varObj.getString("value");

            if ("environment".equalsIgnoreCase(type) && envId != null) {
                upsertVariable(envVariableMap, key, value, "environment", envId, workspaceId);
            } else if ("project".equalsIgnoreCase(type) && workspaceId != null) {
                upsertVariable(workspaceVariableMap, key, value, "project", envId, workspaceId);
            }

        }
    }

    /**
     * 如果存在则更新，不存在则创建
     */
    private void upsertVariable(Map<String, EnvVariable> existingMap,
                                String key, String value,
                                String type, String envId, String workspaceId) {
        EnvVariable variable = existingMap.get(key);
        if (variable != null) {
            variable.setValue(value);
            updateEnvVariable(variable);
        } else {
            EnvVariable newVar = new EnvVariable();
            newVar.setName(key);
            newVar.setValue(value);

            if ("project".equalsIgnoreCase(type)) {
                newVar.setWorkspaceId(workspaceId);
            } else if ("environment".equalsIgnoreCase(type)) {
                newVar.setEnvId(envId);
            }

            createEnvVariable(newVar);
        }
    }



    /**
     * 获取变量
     */
    @Override
    public JSONObject getVariable (String envId){
        JSONObject variableJson = new JSONObject();

        EnvVariableQuery variableQuery = new EnvVariableQuery();
        variableQuery.setEnvId(envId);
        List<EnvVariable> variableList = findEnvVariableList(variableQuery);
        if(variableList!=null&& !variableList.isEmpty()){
            for(EnvVariable variable:variableList){
                variableJson.put(variable.getName(),variable.getValue());
            }
        }

        return variableJson;
    }

}