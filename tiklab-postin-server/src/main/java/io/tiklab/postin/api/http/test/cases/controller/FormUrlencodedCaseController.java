package io.tiklab.postin.api.http.test.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.test.cases.model.FormUrlencodedCase;
import io.tiklab.postin.api.http.test.cases.model.FormUrlencodedCaseQuery;
import io.tiklab.postin.api.http.test.cases.service.FormUrlencodedCaseService;
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
 * FormUrlencodedCaseController
 */
@RestController
@RequestMapping("/formUrlencodedCase")
@Api(name = "FormUrlencodedCaseController",desc = "接口用例-x-www-formUrlencoded参数管理")
public class FormUrlencodedCaseController {

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedCaseController.class);

    @Autowired
    private FormUrlencodedCaseService formUrlencodedCaseService;

    @RequestMapping(path="/createFormUrlencodedCase",method = RequestMethod.POST)
    @ApiMethod(name = "createFormUrlencodedCase",desc = "createFormUrlencodedCase")
    @ApiParam(name = "formUrlencodedCase",desc = "formUrlencodedCase",required = true)
    public Result<String> createFormUrlencodedCase(@RequestBody @NotNull @Valid FormUrlencodedCase formUrlencodedCase){
        String id = formUrlencodedCaseService.createFormUrlencodedCase(formUrlencodedCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormUrlencodedCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateFormUrlencodedCase",desc = "updateFormUrlencodedCase")
    @ApiParam(name = "formUrlencodedCase",desc = "formUrlencodedCase",required = true)
    public Result<Void> updateFormUrlencodedCase(@RequestBody @NotNull @Valid FormUrlencodedCase formUrlencodedCase){
        formUrlencodedCaseService.updateFormUrlencodedCase(formUrlencodedCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormUrlencodedCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteFormUrlencodedCase",desc = "deleteFormUrlencodedCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormUrlencodedCase(@NotNull String id){
        formUrlencodedCaseService.deleteFormUrlencodedCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormUrlencodedCase",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencodedCase",desc = "findFormUrlencodedCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormUrlencodedCase> findFormUrlencodedCase(@NotNull String id){
        FormUrlencodedCase formUrlencodedCase = formUrlencodedCaseService.findFormUrlencodedCase(id);

        return Result.ok(formUrlencodedCase);
    }

    @RequestMapping(path="/findAllFormUrlencodedCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllFormUrlencodedCase",desc = "findAllFormUrlencodedCase")
    public Result<List<FormUrlencodedCase>> findAllFormUrlencodedCase(){
        List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findAllFormUrlencodedCase();

        return Result.ok(formUrlencodedCaseList);
    }

    @RequestMapping(path = "/findFormUrlencodedCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencodedCaseList",desc = "findFormUrlencodedCaseList")
    @ApiParam(name = "formUrlencodedCaseQuery",desc = "formUrlencodedCaseQuery",required = true)
    public Result<List<FormUrlencodedCase>> findFormUrlencodedCaseList(@RequestBody @Valid @NotNull FormUrlencodedCaseQuery formUrlencodedCaseQuery){
        List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findFormUrlencodedCaseList(formUrlencodedCaseQuery);

        return Result.ok(formUrlencodedCaseList);
    }

    @RequestMapping(path = "/findFormUrlencodedCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencodedCasePage",desc = "findFormUrlencodedCasePage")
    @ApiParam(name = "formUrlencodedCaseQuery",desc = "formUrlencodedCaseQuery",required = true)
    public Result<Pagination<FormUrlencodedCase>> findFormUrlencodedCasePage(@RequestBody @Valid @NotNull FormUrlencodedCaseQuery formUrlencodedCaseQuery){
        Pagination<FormUrlencodedCase> pagination = formUrlencodedCaseService.findFormUrlencodedCasePage(formUrlencodedCaseQuery);

        return Result.ok(pagination);
    }

}
