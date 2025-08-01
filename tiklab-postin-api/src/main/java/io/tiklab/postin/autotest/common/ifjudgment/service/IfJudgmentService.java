package io.tiklab.postin.autotest.common.ifjudgment.service;

import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgment;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentQuery;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* if判断 服务接口
*/
@JoinProvider(model = IfJudgment.class)
public interface IfJudgmentService {

    /**
    * 创建if判断
    * @param ifJudgment
    * @return
    */
    String createIfJudgment(@NotNull @Valid IfJudgment ifJudgment);

    /**
    * 更新if判断
    * @param ifJudgment
    */
    void updateIfJudgment(@NotNull @Valid IfJudgment ifJudgment);

    /**
    * 删除if判断
    * @param id
    */
    void deleteIfJudgment(@NotNull String id);

    @FindOne
    IfJudgment findOne(@NotNull String id);

    /**
    * 根据id查找if判断
    * @param id
    * @return
    */
    IfJudgment findIfJudgment(@NotNull String id);

    /**
    * 根据查询参数查询if判断列表
    * @param ifJudgmentQuery
    * @return
    */
    List<IfJudgment> findIfJudgmentList(IfJudgmentQuery ifJudgmentQuery);

    /**
     * 根据id查找if判断,带下级变量列表
     */
    IfJudgment findIfAddVariable(@NotNull String id);


}