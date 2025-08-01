package io.tiklab.postin.autotest.common.ifjudgment.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgment;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentQuery;
import io.tiklab.postin.autotest.common.ifjudgment.service.IfJudgmentService;
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
 * if条件 控制器
 */
@RestController
@RequestMapping("/ifJudgment")
//@Api(name = "IfJudgmentController",desc = "if条件管理")
public class IfJudgmentController {

    private static Logger logger = LoggerFactory.getLogger(IfJudgmentController.class);

    @Autowired
    private IfJudgmentService ifJudgmentService;

    @RequestMapping(path="/createIfJudgment",method = RequestMethod.POST)
//    @ApiMethod(name = "createIfJudgment",desc = "创建if条件")
//    @ApiParam(name = "ifJudgment",desc = "ifJudgment",required = true)
    public Result<String> createIfJudgment(@RequestBody @NotNull @Valid IfJudgment ifJudgment){
        String id = ifJudgmentService.createIfJudgment(ifJudgment);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateIfJudgment",method = RequestMethod.POST)
//    @ApiMethod(name = "updateIfJudgment",desc = "修改if条件")
//    @ApiParam(name = "ifJudgment",desc = "ifJudgment",required = true)
    public Result<Void> updateIfJudgment(@RequestBody @NotNull @Valid IfJudgment ifJudgment){
        ifJudgmentService.updateIfJudgment(ifJudgment);

        return Result.ok();
    }

    @RequestMapping(path="/deleteIfJudgment",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteIfJudgment",desc = "删除if条件")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteIfJudgment(@NotNull String id){
        ifJudgmentService.deleteIfJudgment(id);

        return Result.ok();
    }

    @RequestMapping(path="/findIfJudgment",method = RequestMethod.POST)
//    @ApiMethod(name = "findIfJudgment",desc = "通过id查询")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<IfJudgment> findIfJudgment(@NotNull String id){
        IfJudgment ifJudgment = ifJudgmentService.findIfJudgment(id);

        return Result.ok(ifJudgment);
    }


    @RequestMapping(path = "/findIfJudgmentList",method = RequestMethod.POST)
//    @ApiMethod(name = "findIfJudgmentList",desc = "通过查询对象查询")
//    @ApiParam(name = "ifJudgmentQuery",desc = "ifJudgmentQuery",required = true)
    public Result<List<IfJudgment>> findIfJudgmentList(@RequestBody @Valid @NotNull IfJudgmentQuery ifJudgmentQuery){
        List<IfJudgment> ifJudgmentList = ifJudgmentService.findIfJudgmentList(ifJudgmentQuery);

        return Result.ok(ifJudgmentList);
    }


}
