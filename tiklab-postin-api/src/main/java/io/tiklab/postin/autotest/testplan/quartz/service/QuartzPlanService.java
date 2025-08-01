package io.tiklab.postin.autotest.testplan.quartz.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzPlan;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzPlanQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 定时任务 服务接口
*/
@JoinProvider(model = QuartzPlan.class)
public interface QuartzPlanService {

    /**
    * 创建定时任务
    * @param quartzPlan
    * @return
    */
    String createQuartzPlan(@NotNull @Valid QuartzPlan quartzPlan);

    /**
    * 更新定时任务
    * @param quartzPlan
    */
    void updateQuartzPlan(@NotNull @Valid QuartzPlan quartzPlan);

    void updateQuartzPlanState(@NotNull @Valid QuartzPlan quartzPlan);

    /**
    * 删除定时任务
    * @param id
    */
    void deleteQuartzPlan(@NotNull String id);
    void deleteAllQuartzPlan(String testPlanId);

    @FindOne
    QuartzPlan findOne(@NotNull String id);


    @FindList
    List<QuartzPlan> findList(List<String> idList);

    /**
    * 根据id查找定时任务
    * @param id
    * @return
    */

    QuartzPlan findQuartzPlan(@NotNull String id);

    /**
    * 查找所有定时任务
    * @return
    */
    @FindAll
    List<QuartzPlan> findAllQuartzPlan();

    /**
    * 根据查询参数查询定时任务列表
    * @param quartzPlanQuery
    * @return
    */
    List<QuartzPlan> findQuartzPlanList(QuartzPlanQuery quartzPlanQuery);

    /**
    * 根据查询参数按分页查询定时任务
    * @param quartzPlanQuery
    * @return
    */
    Pagination<QuartzPlan> findQuartzPlanPage(QuartzPlanQuery quartzPlanQuery);

}