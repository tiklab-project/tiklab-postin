package com.darthcloud.apibox.apidef.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apidef.model.AfterScript;
import com.darthcloud.apibox.apidef.model.AfterScriptQuery;
import com.darthcloud.apibox.apidef.service.AfterScriptService;
import com.darthcloud.common.Pagination;
import com.darthcloud.common.Result;
import com.darthcloud.apibox.annotation.ApiMethod;
import com.darthcloud.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.darthcloud.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/afterScript")
@Api(name = "AfterScriptController",desc = "AfterScriptController")
public class AfterScriptController {

    private static Logger logger = LoggerFactory.getLogger(AfterScriptController.class);

    @Autowired
    private AfterScriptService afterScriptService;

    @RequestMapping(path="/createAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "createAfterScript",desc = "createAfterScript")
    @ApiParam(name = "afterScript",desc = "afterScript",required = true)
    public Result<String> createAfterScript(@RequestBody @NotNull @Valid AfterScript afterScript){
        String id = afterScriptService.createAfterScript(afterScript);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "updateAfterScript",desc = "updateAfterScript")
    @ApiParam(name = "afterScript",desc = "afterScript",required = true)
    public Result<Void> updateAfterScript(@RequestBody @NotNull @Valid AfterScript afterScript){
        afterScriptService.updateAfterScript(afterScript);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAfterScript",desc = "deleteAfterScript")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAfterScript(@NotNull String id){
        afterScriptService.deleteAfterScript(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "findAfterScript",desc = "findAfterScript")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AfterScript> findAfterScript(@NotNull String id){
        AfterScript afterScript = afterScriptService.findAfterScript(id);

        return Result.ok(afterScript);
    }

    @RequestMapping(path="/findAllAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAfterScript",desc = "findAllAfterScript")
    public Result<List<AfterScript>> findAllAfterScript(){
        List<AfterScript> afterScriptList = afterScriptService.findAllAfterScript();

        return Result.ok(afterScriptList);
    }

    @Validator
    @RequestMapping(path = "/findAfterScriptList",method = RequestMethod.POST)
    @ApiMethod(name = "findAfterScriptList",desc = "findAfterScriptList")
    @ApiParam(name = "afterScriptQuery",desc = "afterScriptQuery",required = true)
    public Result<List<AfterScript>> findAfterScriptList(@RequestBody @Valid @NotNull AfterScriptQuery afterScriptQuery){
        List<AfterScript> afterScriptList = afterScriptService.findAfterScriptList(afterScriptQuery);

        return Result.ok(afterScriptList);
    }

    @Validator
    @RequestMapping(path = "/findAfterScriptPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAfterScriptPage",desc = "findAfterScriptPage")
    @ApiParam(name = "afterScriptQuery",desc = "afterScriptQuery",required = true)
    public Result<Pagination<List<AfterScript>>> findAfterScriptPage(@RequestBody @Valid @NotNull AfterScriptQuery afterScriptQuery){
        Pagination<List<AfterScript>> pagination = afterScriptService.findAfterScriptPage(afterScriptQuery);

        return Result.ok(pagination);
    }

}
