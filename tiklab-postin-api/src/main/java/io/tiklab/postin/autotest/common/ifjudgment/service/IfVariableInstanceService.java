package io.tiklab.postin.autotest.common.ifjudgment.service;

import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariableInstance;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariableInstanceQuery;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* if判断 服务接口
*/
@JoinProvider(model = IfVariableInstance.class)
public interface IfVariableInstanceService {

    /**
    * 创建if判断
    * @param ifVariableInstance
    * @return
    */
    String createIfVariableInstance(@NotNull @Valid IfVariableInstance ifVariableInstance);

    /**
    * 更新if判断
    * @param ifVariableInstance
    */
    void updateIfVariableInstance(@NotNull @Valid IfVariableInstance ifVariableInstance);

    /**
    * 删除if判断
    * @param id
    */
    void deleteIfVariableInstance(@NotNull String id);

    void deleteAllIfVariableInstance(String stepInstanceId);

    @FindOne
    IfVariableInstance findOne(@NotNull String id);

    /**
    * 根据id查找if判断
    * @param id
    * @return
    */
    IfVariableInstance findIfVariableInstance(@NotNull String id);

    /**
    * 根据查询参数查询if判断列表
    * @param ifVariableInstanceQuery
    * @return
    */
    List<IfVariableInstance> findIfVariableInstanceList(IfVariableInstanceQuery ifVariableInstanceQuery);


}