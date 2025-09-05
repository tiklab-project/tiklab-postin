package io.tiklab.postin.autotest.common.forstep.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.common.forstep.model.ForStep;
import io.tiklab.postin.autotest.common.forstep.model.ForStepQuery;
import io.tiklab.postin.autotest.common.forstep.service.ForStepService;
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
 * for循环 控制器
 */
@RestController
@RequestMapping("/forStep")
//@Api(name = "ForStepController",desc = "for循环管理")
public class ForStepController {

    private static Logger logger = LoggerFactory.getLogger(ForStepController.class);

    @Autowired
    private ForStepService forStepService;

    @RequestMapping(path="/createForStep",method = RequestMethod.POST)
//    @ApiMethod(name = "createForStep",desc = "创建for循环")
//    @ApiParam(name = "forStep",desc = "forStep",required = true)
    public Result<String> createForStep(@RequestBody @NotNull @Valid ForStep forStep){
        String id = forStepService.createForStep(forStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateForStep",method = RequestMethod.POST)
//    @ApiMethod(name = "updateForStep",desc = "修改for循环")
//    @ApiParam(name = "forStep",desc = "forStep",required = true)
    public Result<Void> updateForStep(@RequestBody @NotNull @Valid ForStep forStep){
        forStepService.updateForStep(forStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteForStep",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteForStep",desc = "删除for循环")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteForStep(@NotNull String id){
        forStepService.deleteForStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findForStep",method = RequestMethod.POST)
//    @ApiMethod(name = "findForStep",desc = "通过id查询")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ForStep> findForStep(@NotNull String id){
        ForStep forStep = forStepService.findForStep(id);

        return Result.ok(forStep);
    }


    @RequestMapping(path = "/findForStepList",method = RequestMethod.POST)
//    @ApiMethod(name = "findForStepList",desc = "通过查询对象查询")
//    @ApiParam(name = "forStepQuery",desc = "forStepQuery",required = true)
    public Result<List<ForStep>> findForStepList(@RequestBody @Valid @NotNull ForStepQuery forStepQuery){
        List<ForStep> forStepList = forStepService.findForStepList(forStepQuery);

        return Result.ok(forStepList);
    }


}
