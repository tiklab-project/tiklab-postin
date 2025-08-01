package io.tiklab.postin.autotest.common.ifjudgment.service;

import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentInstance;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentInstanceQuery;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* if判断 服务接口
*/
@JoinProvider(model = IfJudgmentInstance.class)
public interface IfJudgmentInstanceService {

    /**
    * 创建if判断
    * @param ifJudgmentInstance
    * @return
    */
    String createIfJudgmentInstance(@NotNull @Valid IfJudgmentInstance ifJudgmentInstance);

    /**
    * 更新if判断
    * @param ifJudgmentInstance
    */
    void updateIfJudgmentInstance(@NotNull @Valid IfJudgmentInstance ifJudgmentInstance);

    /**
    * 删除if判断
    * @param id
    */
    void deleteIfJudgmentInstance(@NotNull String id);

    @FindOne
    IfJudgmentInstance findOne(@NotNull String id);

    /**
    * 根据id查找if判断
    * @param id
    * @return
    */
    IfJudgmentInstance findIfJudgmentInstance(@NotNull String id);

    /**
    * 根据查询参数查询if判断列表
    * @param ifJudgmentInstanceQuery
    * @return
    */
    List<IfJudgmentInstance> findIfJudgmentInstanceList(IfJudgmentInstanceQuery ifJudgmentInstanceQuery);


}