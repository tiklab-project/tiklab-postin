package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.api.http.definition.model.PreParam;
import io.tiklab.postin.api.http.definition.model.PreParamQuery;
import io.tiklab.postin.api.http.definition.service.PreParamService;
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
 * 前置 控制器
 */
@RestController
@RequestMapping("/preParam")
//@Api(name = "PreParamController",desc = "前置管理")
public class PreParamController {

    private static Logger logger = LoggerFactory.getLogger(PreParamController.class);

    @Autowired
    private PreParamService preParamService;

    @RequestMapping(path="/createPreParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createPreParam",desc = "创建前置")
//    @ApiParam(name = "preParam",desc = "前置DTO",required = true)
    public Result<String> createPreParam(@RequestBody @NotNull @Valid PreParam preParam){
        String id = preParamService.createPreParam(preParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePreParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updatePreParam",desc = "更新前置")
//    @ApiParam(name = "preParam",desc = "前置DTO",required = true)
    public Result<Void> updatePreParam(@RequestBody @NotNull @Valid PreParam preParam){
        preParamService.updatePreParam(preParam);

        return Result.ok();
    }

    @RequestMapping(path="/deletePreParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deletePreParam",desc = "根据ID删除前置")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deletePreParam(@NotNull String id){
        preParamService.deletePreParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findPreParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findPreParam",desc = "根据ID查找前置")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<PreParam> findPreParam(@NotNull String id){
        PreParam preParam = preParamService.findPreParam(id);

        return Result.ok(preParam);
    }

    @RequestMapping(path = "/findPreParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findPreParamList",desc = "根据查询参数查找前置列表")
//    @ApiParam(name = "preParamQuery",desc = "preParamQuery",required = true)
    public Result<List<PreParam>> findFormParamList(@RequestBody @Valid @NotNull PreParamQuery preParamQuery){
        List<PreParam> preParamList = preParamService.findPreParamList(preParamQuery);

        return Result.ok(preParamList);
    }



}
