package io.tiklab.postin.autotest.testplan.quartz.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzTimePlan;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzTimePlanQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 定时任务时间 服务接口
*/
@JoinProvider(model = QuartzTimePlan.class)
public interface QuartzTimePlanService {

    /**
    * 创建定时任务时间
    * @param quartzTimePlan
    * @return
    */
    String createQuartzTimePlan(@NotNull @Valid QuartzTimePlan quartzTimePlan);

    /**
    * 更新定时任务时间
    * @param quartzTimePlan
    */
    void updateQuartzTimePlan(@NotNull @Valid QuartzTimePlan quartzTimePlan);

    /**
    * 删除定时任务时间
    * @param id
    */
    void deleteQuartzTimePlan(@NotNull String id);

    /**
     * 删除所有定时时间3
     * @param quartzPlanId
     */
    void deleteAllQuartzTimePlan(@NotNull String quartzPlanId,String testPlanId);

    @FindOne
    QuartzTimePlan findOne(@NotNull String id);


    @FindList
    List<QuartzTimePlan> findList(List<String> idList);

    /**
    * 根据id查找定时任务时间
    * @param id
    * @return
    */

    QuartzTimePlan findQuartzTimePlan(@NotNull String id);

    /**
     * 根据定时任务id查找时间
     * @param quartzPlanId
     * @return
     */
    QuartzTimePlan findQuartzTimePlanByQuartzPlanId(@NotNull String quartzPlanId);



    /**
    * 查找所有定时任务时间
    * @return
    */
    @FindAll
    List<QuartzTimePlan> findAllQuartzTimePlan();

    /**
    * 根据查询参数查询定时任务时间列表
    * @param quartzTimePlanQuery
    * @return
    */
    List<QuartzTimePlan> findQuartzTimePlanList(QuartzTimePlanQuery quartzTimePlanQuery);

    /**
    * 根据查询参数按分页查询定时任务时间
    * @param quartzTimePlanQuery
    * @return
    */
    Pagination<QuartzTimePlan> findQuartzTimePlanPage(QuartzTimePlanQuery quartzTimePlanQuery);

}