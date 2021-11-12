package com.doublekit.apibox.apidef.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apidef.model.PreScript;
import com.doublekit.apibox.apidef.model.PreScriptQuery;
import com.doublekit.apibox.apidef.service.PreScriptService;
import com.doublekit.common.page.Pagination;
import com.doublekit.common.Result;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/preScript")
@Api(name = "PreScriptController",desc = "接口请求前置脚本管理")
public class PreScriptController {

    private static Logger logger = LoggerFactory.getLogger(PreScriptController.class);

    @Autowired
    private PreScriptService preScriptService;

    @RequestMapping(path="/createPreScript",method = RequestMethod.POST)
    @ApiMethod(name = "createPreScript",desc = "createPreScript")
    @ApiParam(name = "preScript",desc = "preScript",required = true)
    public Result<String> createPreScript(@RequestBody @NotNull @Valid PreScript preScript){
        String id = preScriptService.createPreScript(preScript);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePreScript",method = RequestMethod.POST)
    @ApiMethod(name = "updatePreScript",desc = "updatePreScript")
    @ApiParam(name = "preScript",desc = "preScript",required = true)
    public Result<Void> updatePreScript(@RequestBody @NotNull @Valid PreScript preScript){
        preScriptService.updatePreScript(preScript);

        return Result.ok();
    }

    @RequestMapping(path="/deletePreScript",method = RequestMethod.POST)
    @ApiMethod(name = "deletePreScript",desc = "deletePreScript")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deletePreScript(@NotNull String id){
        preScriptService.deletePreScript(id);

        return Result.ok();
    }

    @RequestMapping(path="/findPreScript",method = RequestMethod.POST)
    @ApiMethod(name = "findPreScript",desc = "findPreScript")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<PreScript> findPreScript(@NotNull String id){
        PreScript preScript = preScriptService.findPreScript(id);

        return Result.ok(preScript);
    }

    @RequestMapping(path="/findAllPreScript",method = RequestMethod.POST)
    @ApiMethod(name = "findAllPreScript",desc = "findAllPreScript")
    public Result<List<PreScript>> findAllPreScript(){
        List<PreScript> preScriptList = preScriptService.findAllPreScript();

        return Result.ok(preScriptList);
    }


    @RequestMapping(path = "/findPreScriptList",method = RequestMethod.POST)
    @ApiMethod(name = "findPreScriptList",desc = "findPreScriptList")
    @ApiParam(name = "preScriptQuery",desc = "preScriptQuery",required = true)
    public Result<List<PreScript>> findPreScriptList(@RequestBody @Valid @NotNull PreScriptQuery preScriptQuery){
        List<PreScript> preScriptList = preScriptService.findPreScriptList(preScriptQuery);

        return Result.ok(preScriptList);
    }


    @RequestMapping(path = "/findPreScriptPage",method = RequestMethod.POST)
    @ApiMethod(name = "findPreScriptPage",desc = "findPreScriptPage")
    @ApiParam(name = "preScriptQuery",desc = "preScriptQuery",required = true)
    public Result<Pagination<PreScript>> findPreScriptPage(@RequestBody @Valid @NotNull PreScriptQuery preScriptQuery){
        Pagination<PreScript> pagination = preScriptService.findPreScriptPage(preScriptQuery);

        return Result.ok(pagination);
    }

}
