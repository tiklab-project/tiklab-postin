package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.api.http.definition.model.OperateScript;
import io.tiklab.postin.api.http.definition.model.OperateScriptQuery;
import io.tiklab.postin.api.http.definition.service.OperateScriptService;
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
 * 自定义脚本 控制器
 */
@RestController
@RequestMapping("/operateScript")
//@Api(name = "OperateScriptController",desc = "自定义脚本管理")
public class OperateScriptController {

    private static Logger logger = LoggerFactory.getLogger(OperateScriptController.class);

    @Autowired
    private OperateScriptService operateScriptService;

    @RequestMapping(path="/createOperateScript",method = RequestMethod.POST)
//    @ApiMethod(name = "createOperateScript",desc = "创建自定义脚本")
//    @ApiParam(name = "operateScript",desc = "自定义脚本DTO",required = true)
    public Result<String> createOperateScript(@RequestBody @NotNull @Valid OperateScript operateScript){
        String id = operateScriptService.createOperateScript(operateScript);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateOperateScript",method = RequestMethod.POST)
//    @ApiMethod(name = "updateOperateScript",desc = "更新自定义脚本")
//    @ApiParam(name = "operateScript",desc = "自定义脚本DTO",required = true)
    public Result<Void> updateOperateScript(@RequestBody @NotNull @Valid OperateScript operateScript){
        operateScriptService.updateOperateScript(operateScript);

        return Result.ok();
    }

    @RequestMapping(path="/deleteOperateScript",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteOperateScript",desc = "根据ID删除自定义脚本")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteOperateScript(@NotNull String id){
        operateScriptService.deleteOperateScript(id);

        return Result.ok();
    }

    @RequestMapping(path="/findOperateScript",method = RequestMethod.POST)
//    @ApiMethod(name = "findOperateScript",desc = "根据ID查找自定义脚本")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<OperateScript> findOperateScript(@NotNull String id){
        OperateScript operateScript = operateScriptService.findOperateScript(id);

        return Result.ok(operateScript);
    }

    @RequestMapping(path = "/findOperateScriptList",method = RequestMethod.POST)
//    @ApiMethod(name = "findOperateScriptList",desc = "根据查询参数查找自定义脚本列表")
//    @ApiParam(name = "operateScriptQuery",desc = "operateScriptQuery",required = true)
    public Result<List<OperateScript>> findFormParamList(@RequestBody @Valid @NotNull OperateScriptQuery operateScriptQuery){
        List<OperateScript> operateScriptList = operateScriptService.findOperateScriptList(operateScriptQuery);

        return Result.ok(operateScriptList);
    }



}
