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
import java.util.List;

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

    /**
     * 获取变量
     */
    @Override
    public JSONObject getVariable (String envId){
        JSONObject variableJson = new JSONObject();

        EnvVariableQuery variableQuery = new EnvVariableQuery();
        variableQuery.setEnvId(envId);
        List<EnvVariable> variableList = findEnvVariableList(variableQuery);
        if(variableList!=null&&variableList.size()>0){
            for(EnvVariable variable:variableList){
                variableJson.put(variable.getName(),variable.getValue());
            }
        }

        return variableJson;
    }

}