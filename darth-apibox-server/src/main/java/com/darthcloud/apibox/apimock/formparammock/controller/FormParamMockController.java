package com.darthcloud.apibox.apimock.formparammock.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apimock.formparammock.model.FormParamMock;
import com.darthcloud.apibox.apimock.formparammock.model.FormParamMockQuery;
import com.darthcloud.apibox.apimock.formparammock.service.FormParamMockService;
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
@RequestMapping("/formParamMock")
@Api(name = "FormParamMockController",desc = "FormParamMock管理")
public class FormParamMockController {

    private static Logger logger = LoggerFactory.getLogger(FormParamMockController.class);

    @Autowired
    private FormParamMockService formParamMockService;

    @RequestMapping(path="/createFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "createFormParamMock",desc = "createFormParamMock")
    @ApiParam(name = "formParamMock",desc = "formParamMock",required = true)
    public Result<String> createFormParamMock(@RequestBody @NotNull @Valid FormParamMock formParamMock){
        String id = formParamMockService.createFormParamMock(formParamMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateFormParamMock",desc = "updateFormParamMock")
    @ApiParam(name = "formParamMock",desc = "formParamMock",required = true)
    public Result<Void> updateFormParamMock(@RequestBody @NotNull @Valid FormParamMock formParamMock){
        formParamMockService.updateFormParamMock(formParamMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteFormParamMock",desc = "deleteFormParamMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormParamMock(@NotNull String id){
        formParamMockService.deleteFormParamMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamMock",desc = "findFormParamMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormParamMock> findFormParamMock(@NotNull String id){
        FormParamMock formParamMock = formParamMockService.findFormParamMock(id);

        return Result.ok(formParamMock);
    }

    @RequestMapping(path="/findAllFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllFormParamMock",desc = "findAllFormParamMock")
    public Result<List<FormParamMock>> findAllFormParamMock(){
        List<FormParamMock> formParamMockList = formParamMockService.findAllFormParamMock();

        return Result.ok(formParamMockList);
    }

    @Validator
    @RequestMapping(path = "/findFormParamMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamMockList",desc = "findFormParamMockList")
    @ApiParam(name = "formParamMockQuery",desc = "formParamMockQuery",required = true)
    public Result<List<FormParamMock>> findFormParamMockList(@RequestBody @Valid @NotNull FormParamMockQuery formParamMockQuery){
        List<FormParamMock> formParamMockList = formParamMockService.findFormParamMockList(formParamMockQuery);

        return Result.ok(formParamMockList);
    }

    @Validator
    @RequestMapping(path = "/findFormParamMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamMockPage",desc = "findFormParamMockPage")
    @ApiParam(name = "formParamMockQuery",desc = "formParamMockQuery",required = true)
    public Result<Pagination<List<FormParamMock>>> findFormParamMockPage(@RequestBody @Valid @NotNull FormParamMockQuery formParamMockQuery){
        Pagination<List<FormParamMock>> pagination = formParamMockService.findFormParamMockPage(formParamMockQuery);

        return Result.ok(pagination);
    }

}
