package io.tiklab.postin.support.environment.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.support.environment.model.EnvVariable;
import io.tiklab.postin.support.environment.model.EnvVariableQuery;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 环境中变量 服务接口
*/
@JoinProvider(model = EnvVariable.class)
public interface EnvVariableService {

    /**
    * 创建环境中变量
    * @param envVariable
    * @return
    */
    String createEnvVariable(@NotNull @Valid EnvVariable envVariable);

    /**
    * 更新环境中变量
    * @param envVariable
    */
    void updateEnvVariable(@NotNull @Valid EnvVariable envVariable);

    /**
    * 删除环境中变量
    * @param id
    */
    void deleteEnvVariable(@NotNull String id);

    /**
     * 通过workspaceId删除
     * @param workspaceId
     */
    void deleteAllEnvVariable(@NotNull String workspaceId);


    @FindOne
    EnvVariable findOne(@NotNull String id);

    @FindList
    List<EnvVariable> findList(List<String> idList);

    /**
    * 查找环境中变量
    * @param id
    * @return
    */
    EnvVariable findEnvVariable(@NotNull String id);

    /**
    * 查找所有环境中变量
    * @return
    */
    List<EnvVariable> findAllEnvVariable();

    /**
    * 查询列表环境中变量
    * @param envVariableQuery
    * @return
    */
    List<EnvVariable> findEnvVariableList(EnvVariableQuery envVariableQuery);

    /**
    * 按分页查询环境中变量
    * @param envVariableQuery
    * @return
    */
    Pagination<EnvVariable> findEnvVariablePage(EnvVariableQuery envVariableQuery);

    /**
     * 批量创建变量
     */
    void batchCreateVariable(JSONObject jsonObject);

    /**
     * 获取环境变量
     * @param envId
     * @return
     */
    JSONObject getVariable (String envId);
}