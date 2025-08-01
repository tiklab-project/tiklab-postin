package io.tiklab.postin.autotest.common.stepassert.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.common.stepassert.model.StepAssertCommon;
import io.tiklab.postin.autotest.common.stepassert.model.StepAssertCommonQuery;
import io.tiklab.postin.autotest.common.stepassert.service.StepAssertCommonService;
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

/**
 * 步骤断言 控制器
 */
@RestController
@RequestMapping("/stepAssertCommon")
//@Api(name = "StepAssertCommonController",desc = "公共的步骤断言")
public class StepAssertCommonController {

    private static Logger logger = LoggerFactory.getLogger(StepAssertCommonController.class);

    @Autowired
    private StepAssertCommonService stepAssertCommonService;

    @RequestMapping(path="/createStepAssertCommon",method = RequestMethod.POST)
//    @ApiMethod(name = "createStepAssertCommon",desc = "创建步骤断言")
//    @ApiParam(name = "stepAssertCommon",desc = "stepAssertCommon",required = true)
    public Result<String> createStepAssertCommon(@RequestBody @NotNull @Valid StepAssertCommon stepAssertCommon){
        String id = stepAssertCommonService.createStepAssertCommon(stepAssertCommon);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateStepAssertCommon",method = RequestMethod.POST)
//    @ApiMethod(name = "updateStepAssertCommon",desc = "修改步骤断言")
//    @ApiParam(name = "stepAssertCommon",desc = "stepAssertCommon",required = true)
    public Result<Void> updateStepAssertCommon(@RequestBody @NotNull @Valid StepAssertCommon stepAssertCommon){
        stepAssertCommonService.updateStepAssertCommon(stepAssertCommon);

        return Result.ok();
    }

    @RequestMapping(path="/deleteStepAssertCommon",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteStepAssertCommon",desc = "删除步骤断言")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteStepAssertCommon(@NotNull String id){
        stepAssertCommonService.deleteStepAssertCommon(id);

        return Result.ok();
    }

    @RequestMapping(path="/findStepAssertCommon",method = RequestMethod.POST)
//    @ApiMethod(name = "findStepAssertCommon",desc = "通过id查询")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<StepAssertCommon> findStepAssertCommon(@NotNull String id){
        StepAssertCommon stepAssertCommon = stepAssertCommonService.findStepAssertCommon(id);

        return Result.ok(stepAssertCommon);
    }


    @RequestMapping(path = "/findStepAssertCommonList",method = RequestMethod.POST)
//    @ApiMethod(name = "findStepAssertCommonList",desc = "通过查询对象查询")
//    @ApiParam(name = "stepAssertCommonQuery",desc = "stepAssertCommonQuery",required = true)
    public Result<List<StepAssertCommon>> findStepAssertCommonList(@RequestBody @Valid @NotNull StepAssertCommonQuery stepAssertCommonQuery){
            List<StepAssertCommon> stepAssertCommonList = stepAssertCommonService.findStepAssertCommonList(stepAssertCommonQuery);

        return Result.ok(stepAssertCommonList);
    }


}
