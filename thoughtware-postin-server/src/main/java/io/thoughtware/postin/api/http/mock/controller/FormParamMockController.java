package io.thoughtware.postin.api.http.mock.controller;

import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.api.http.mock.model.FormParamMock;
import io.thoughtware.postin.api.http.mock.model.FormParamMockQuery;
import io.thoughtware.postin.api.http.mock.service.FormParamMockService;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * mock
 * formdata 控制器
 */
@RestController
@RequestMapping("/formParamMock")
@Api(name = "FormParamMockController",desc = "接口mock-Form参数管理")
public class FormParamMockController {

    private static Logger logger = LoggerFactory.getLogger(FormParamMockController.class);

    @Autowired
    private FormParamMockService formParamMockService;

    @RequestMapping(path="/createFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "createFormParamMock",desc = "创建form-data")
    @ApiParam(name = "formParamMock",desc = "formParamMock",required = true)
    public Result<String> createFormParamMock(@RequestBody @NotNull @Valid FormParamMock formParamMock){
        String id = formParamMockService.createFormParamMock(formParamMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateFormParamMock",desc = "更新form-data")
    @ApiParam(name = "formParamMock",desc = "formParamMock",required = true)
    public Result<Void> updateFormParamMock(@RequestBody @NotNull @Valid FormParamMock formParamMock){
        formParamMockService.updateFormParamMock(formParamMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteFormParamMock",desc = "删除form-data")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormParamMock(@NotNull String id){
        formParamMockService.deleteFormParamMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamMock",desc = "根据id查找form-data")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormParamMock> findFormParamMock(@NotNull String id){
        FormParamMock formParamMock = formParamMockService.findFormParamMock(id);

        return Result.ok(formParamMock);
    }

    @RequestMapping(path="/findAllFormParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllFormParamMock",desc = "查找所有form-data")
    public Result<List<FormParamMock>> findAllFormParamMock(){
        List<FormParamMock> formParamMockList = formParamMockService.findAllFormParamMock();

        return Result.ok(formParamMockList);
    }


    @RequestMapping(path = "/findFormParamMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamMockList",desc = "根据查询参数查找form-data")
    @ApiParam(name = "formParamMockQuery",desc = "formParamMockQuery",required = true)
    public Result<List<FormParamMock>> findFormParamMockList(@RequestBody @Valid @NotNull FormParamMockQuery formParamMockQuery){
        List<FormParamMock> formParamMockList = formParamMockService.findFormParamMockList(formParamMockQuery);

        return Result.ok(formParamMockList);
    }


    @RequestMapping(path = "/findFormParamMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamMockPage",desc = "根据查询参数按分页查找form-data")
    @ApiParam(name = "formParamMockQuery",desc = "formParamMockQuery",required = true)
    public Result<Pagination<FormParamMock>> findFormParamMockPage(@RequestBody @Valid @NotNull FormParamMockQuery formParamMockQuery){
        Pagination<FormParamMock> pagination = formParamMockService.findFormParamMockPage(formParamMockQuery);

        return Result.ok(pagination);
    }

}
