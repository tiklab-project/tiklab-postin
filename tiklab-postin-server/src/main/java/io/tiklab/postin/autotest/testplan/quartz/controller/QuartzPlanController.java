package io.tiklab.postin.autotest.testplan.quartz.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzPlan;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzPlanQuery;
import io.tiklab.postin.autotest.testplan.quartz.service.QuartzPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/quartzPlan")
//@Api(name = "QuartzPlanController",desc = "定时任务")
public class QuartzPlanController {

    private static Logger logger = LoggerFactory.getLogger(QuartzPlanController.class);

    @Autowired
    private QuartzPlanService quartzPlanService;


    @RequestMapping(path="/createQuartzPlan",method = RequestMethod.POST)
//    @ApiMethod(name = "createQuartzPlan",desc = "创建测试i计划")
//    @ApiParam(name = "quartzPlan",desc = "quartzPlan",required = true)
    public Result<String> createQuartzPlan(@RequestBody @NotNull @Valid QuartzPlan quartzPlan){
        String id = quartzPlanService.createQuartzPlan(quartzPlan);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateQuartzPlan",method = RequestMethod.POST)
//    @ApiMethod(name = "updateQuartzPlan",desc = "修改定时任务")
//    @ApiParam(name = "quartzPlan",desc = "quartzPlan",required = true)
    public Result<Void> updateQuartzPlan(@RequestBody @NotNull @Valid QuartzPlan quartzPlan){
        quartzPlanService.updateQuartzPlan(quartzPlan);

        return Result.ok();
    }


    @RequestMapping(path="/updateQuartzPlanState",method = RequestMethod.POST)
//    @ApiMethod(name = "updateQuartzPlanState",desc = "修改定时任务")
//    @ApiParam(name = "quartzPlan",desc = "quartzPlan",required = true)
    public Result<Void> updateQuartzPlanState(@RequestBody @NotNull @Valid QuartzPlan quartzPlan){
        quartzPlanService.updateQuartzPlanState(quartzPlan);

        return Result.ok();
    }


    @RequestMapping(path="/deleteQuartzPlan",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteQuartzPlan",desc = "通过id删除定时任务")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteQuartzPlan(@NotNull String id){
        quartzPlanService.deleteQuartzPlan(id);

        return Result.ok();
    }
    
    @RequestMapping(path="/findQuartzPlan",method = RequestMethod.POST)
//    @ApiMethod(name = "findQuartzPlan",desc = "通过id查询定时任务")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<QuartzPlan> findQuartzPlan(@NotNull String id){
        QuartzPlan quartzPlan = quartzPlanService.findQuartzPlan(id);

        return Result.ok(quartzPlan);
    }

    @RequestMapping(path="/findAllQuartzPlan",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllQuartzPlan",desc = "查询所有定时任务")
    public Result<List<QuartzPlan>> findAllQuartzPlan(){
        List<QuartzPlan> quartzPlanList = quartzPlanService.findAllQuartzPlan();

        return Result.ok(quartzPlanList);
    }

    @RequestMapping(path = "/findQuartzPlanList",method = RequestMethod.POST)
//    @ApiMethod(name = "findQuartzPlanList",desc = "通过查询对象查询定时任务")
//    @ApiParam(name = "quartzPlanQuery",desc = "quartzPlanQuery",required = true)
    public Result<List<QuartzPlan>> findQuartzPlanList(@RequestBody @Valid @NotNull QuartzPlanQuery quartzPlanQuery){
        List<QuartzPlan> quartzPlanList = quartzPlanService.findQuartzPlanList(quartzPlanQuery);

        return Result.ok(quartzPlanList);
    }

    @RequestMapping(path = "/findQuartzPlanPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findQuartzPlanPage",desc = "通过查询对象分页查询定时任务")
//    @ApiParam(name = "quartzPlanQuery",desc = "quartzPlanQuery",required = true)
    public Result<Pagination<QuartzPlan>> findQuartzPlanPage(@RequestBody @Valid @NotNull QuartzPlanQuery quartzPlanQuery){
        Pagination<QuartzPlan> pagination = quartzPlanService.findQuartzPlanPage(quartzPlanQuery);

        return Result.ok(pagination);
    }

}
