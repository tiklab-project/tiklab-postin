package com.darthcloud.apibox.apidef.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apidef.model.FormParam;
import com.darthcloud.apibox.apidef.model.FormParamQuery;
import com.darthcloud.apibox.apidef.service.FormParamService;
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
@RequestMapping("/formParam")
@Api(name = "FormParamController",desc = "form参数管理")
public class FormParamController {

    private static Logger logger = LoggerFactory.getLogger(FormParamController.class);

    @Autowired
    private FormParamService formParamService;

    @RequestMapping(path="/createFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "createFormParam",desc = "createFormParam")
    @ApiParam(name = "formParam",desc = "formParam",required = true)
    public Result<String> createFormParam(@RequestBody @NotNull @Valid FormParam formParam){
        String id = formParamService.createFormParam(formParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateFormParam",desc = "updateFormParam")
    @ApiParam(name = "formParam",desc = "formParam",required = true)
    public Result<Void> updateFormParam(@RequestBody @NotNull @Valid FormParam formParam){
        formParamService.updateFormParam(formParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteFormParam",desc = "deleteFormParam")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormParam(@NotNull String id){
        formParamService.deleteFormParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParam",desc = "findFormParam")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormParam> findFormParam(@NotNull String id){
        FormParam formParam = formParamService.findFormParam(id);

        return Result.ok(formParam);
    }

    @RequestMapping(path="/findAllFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllFormParam",desc = "findAllFormParam")
    public Result<List<FormParam>> findAllFormParam(){
        List<FormParam> formParamList = formParamService.findAllFormParam();

        return Result.ok(formParamList);
    }

    @Validator
    @RequestMapping(path = "/findFormParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamList",desc = "findFormParamList")
    @ApiParam(name = "formParamQuery",desc = "formParamQuery",required = true)
    public Result<List<FormParam>> findFormParamList(@RequestBody @Valid @NotNull FormParamQuery formParamQuery){
        List<FormParam> formParamList = formParamService.findFormParamList(formParamQuery);

        return Result.ok(formParamList);
    }

    @Validator
    @RequestMapping(path = "/findFormParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamPage",desc = "findFormParamPage")
    @ApiParam(name = "formParamQuery",desc = "formParamQuery",required = true)
    public Result<Pagination<List<FormParam>>> findFormParamPage(@RequestBody @Valid @NotNull FormParamQuery formParamQuery){
        Pagination<List<FormParam>> pagination = formParamService.findFormParamPage(formParamQuery);

        return Result.ok(pagination);
    }

}
