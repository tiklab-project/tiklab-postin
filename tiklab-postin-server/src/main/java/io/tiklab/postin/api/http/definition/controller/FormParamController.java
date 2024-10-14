package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.definition.model.FormParam;
import io.tiklab.postin.api.http.definition.model.FormParamQuery;
import io.tiklab.postin.api.http.definition.service.FormParamService;
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
 * 定义
 * http协议
 * formdata 控制器
 */
@RestController
@RequestMapping("/formParam")
@Api(name = "FormParamController",desc = "form参数管理")
public class FormParamController {

    private static Logger logger = LoggerFactory.getLogger(FormParamController.class);

    @Autowired
    private FormParamService formParamService;

    @RequestMapping(path="/createFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "createFormParam",desc = "创建form-data参数")
    @ApiParam(name = "formParam",desc = "formParam",required = true)
    public Result<String> createFormParam(@RequestBody @NotNull @Valid FormParam formParam){
        String id = formParamService.createFormParam(formParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateFormParam",desc = "更新form-data参数")
    @ApiParam(name = "formParam",desc = "formParam",required = true)
    public Result<Void> updateFormParam(@RequestBody @NotNull @Valid FormParam formParam){
        formParamService.updateFormParam(formParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteFormParam",desc = "删除form-data参数")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormParam(@NotNull String id){
        formParamService.deleteFormParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParam",desc = "根据id查找form-data参数")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormParam> findFormParam(@NotNull String id){
        FormParam formParam = formParamService.findFormParam(id);

        return Result.ok(formParam);
    }

    @RequestMapping(path="/findAllFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllFormParam",desc = "查找所有form-data参数")
    public Result<List<FormParam>> findAllFormParam(){
        List<FormParam> formParamList = formParamService.findAllFormParam();

        return Result.ok(formParamList);
    }


    @RequestMapping(path = "/findFormParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamList",desc = "根据查询参数查找form-data参数")
    @ApiParam(name = "formParamQuery",desc = "formParamQuery",required = true)
    public Result<List<FormParam>> findFormParamList(@RequestBody @Valid @NotNull FormParamQuery formParamQuery){
        List<FormParam> formParamList = formParamService.findFormParamList(formParamQuery);

        return Result.ok(formParamList);
    }


    @RequestMapping(path = "/findFormParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamPage",desc = "根据查询参数按分页查找form-data参数")
    @ApiParam(name = "formParamQuery",desc = "formParamQuery",required = true)
    public Result<Pagination<FormParam>> findFormParamPage(@RequestBody @Valid @NotNull FormParamQuery formParamQuery){
        Pagination<FormParam> pagination = formParamService.findFormParamPage(formParamQuery);

        return Result.ok(pagination);
    }

}
