package io.tiklab.postin.autotest.common.forstep.service;

import io.tiklab.postin.autotest.common.forstep.model.ForStep;
import io.tiklab.postin.autotest.common.forstep.model.ForStepQuery;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* for循环 服务接口
*/
@JoinProvider(model = ForStep.class)
public interface ForStepService {

    /**
    * 创建for循环
    * @param forStep
    * @return
    */
    String createForStep(@NotNull @Valid ForStep forStep);

    /**
    * 更新for循环
    * @param forStep
    */
    void updateForStep(@NotNull @Valid ForStep forStep);

    /**
    * 删除for循环
    * @param id
    */
    void deleteForStep(@NotNull String id);

    @FindOne
    ForStep findOne(@NotNull String id);

    /**
    * 根据id查找for循环
    * @param id
    * @return
    */
    ForStep findForStep(@NotNull String id);

    /**
    * 根据查询参数查询for循环列表
    * @param forStepQuery
    * @return
    */
    List<ForStep> findForStepList(ForStepQuery forStepQuery);

}