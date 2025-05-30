package io.tiklab.postin.api.http.test.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.test.cases.model.FormParamCase;
import io.tiklab.postin.api.http.test.cases.model.FormParamCaseQuery;
import io.tiklab.postin.api.http.test.cases.service.FormParamCaseService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
@RequestMapping("/formParamCase")
//@Api(name = "FormParamCaseController",desc = "接口用例-form参数管理")
public class FormParamCaseController {

    private static Logger logger = LoggerFactory.getLogger(FormParamCaseController.class);

    @Autowired
    private FormParamCaseService formParamCaseService;

    @RequestMapping(path="/createFormParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "createFormParamCase",desc = "createFormParamCase")
//    @ApiParam(name = "formParamCase",desc = "formParamCase",required = true)
    public Result<String> createFormParamCase(@RequestBody @NotNull @Valid FormParamCase formParamCase){
        String id = formParamCaseService.createFormParamCase(formParamCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "updateFormParamCase",desc = "updateFormParamCase")
//    @ApiParam(name = "formParamCase",desc = "formParamCase",required = true)
    public Result<Void> updateFormParamCase(@RequestBody @NotNull @Valid FormParamCase formParamCase){
        formParamCaseService.updateFormParamCase(formParamCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteFormParamCase",desc = "deleteFormParamCase")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormParamCase(@NotNull String id){
        formParamCaseService.deleteFormParamCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findFormParamCase",desc = "findFormParamCase")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormParamCase> findFormParamCase(@NotNull String id){
        FormParamCase formParamCase = formParamCaseService.findFormParamCase(id);

        return Result.ok(formParamCase);
    }

    @RequestMapping(path="/findAllFormParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllFormParamCase",desc = "findAllFormParamCase")
    public Result<List<FormParamCase>> findAllFormParamCase(){
        List<FormParamCase> formParamCaseList = formParamCaseService.findAllFormParamCase();

        return Result.ok(formParamCaseList);
    }


    @RequestMapping(path = "/findFormParamCaseList",method = RequestMethod.POST)
//    @ApiMethod(name = "findFormParamCaseList",desc = "findFormParamCaseList")
//    @ApiParam(name = "formParamCaseQuery",desc = "formParamCaseQuery",required = true)
    public Result<List<FormParamCase>> findFormParamCaseList(@RequestBody @Valid @NotNull FormParamCaseQuery formParamCaseQuery){
        List<FormParamCase> formParamCaseList = formParamCaseService.findFormParamCaseList(formParamCaseQuery);

        return Result.ok(formParamCaseList);
    }


    @RequestMapping(path = "/findFormParamCasePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findFormParamCasePage",desc = "findFormParamCasePage")
//    @ApiParam(name = "formParamCaseQuery",desc = "formParamCaseQuery",required = true)
    public Result<Pagination<FormParamCase>> findFormParamCasePage(@RequestBody @Valid @NotNull FormParamCaseQuery formParamCaseQuery){
        Pagination<FormParamCase> pagination = formParamCaseService.findFormParamCasePage(formParamCaseQuery);

        return Result.ok(pagination);
    }

}
