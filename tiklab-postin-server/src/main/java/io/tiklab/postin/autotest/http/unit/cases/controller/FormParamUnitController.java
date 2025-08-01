package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.autotest.http.unit.cases.model.FormParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.FormParamUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.FormParamUnitService;
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
 * form-data 控制器
 */
@RestController
@RequestMapping("/formParamUnit")
//@Api(name = "FormParamController",desc = "接口用例步骤form 参数管理")
public class FormParamUnitController {

    private static Logger logger = LoggerFactory.getLogger(FormParamUnitController.class);

    @Autowired
    private FormParamUnitService formParamUnitService;

    @RequestMapping(path="/createFormParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createFormParam",desc = "创建form-data")
//    @ApiParam(name = "formParamUnit",desc = "formParam",required = true)
    public Result<String> createFormParam(@RequestBody @NotNull @Valid FormParamUnit formParamUnit){
        String id = formParamUnitService.createFormParam(formParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateFormParam",desc = "更新form-data")
//    @ApiParam(name = "formParamUnit",desc = "formParam",required = true)
    public Result<Void> updateFormParam(@RequestBody @NotNull @Valid FormParamUnit formParamUnit){
        formParamUnitService.updateFormParam(formParamUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteFormParam",desc = "删除form-data")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormParam(@NotNull String id){
        formParamUnitService.deleteFormParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findFormParam",desc = "根据id查找form-data")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormParamUnit> findFormParam(@NotNull String id){
        FormParamUnit formParamUnit = formParamUnitService.findFormParam(id);

        return Result.ok(formParamUnit);
    }

    @RequestMapping(path="/findAllFormParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllFormParam",desc = "查找所有form-data")
    public Result<List<FormParamUnit>> findAllFormParam(){
        List<FormParamUnit> formParamUnitList = formParamUnitService.findAllFormParam();

        return Result.ok(formParamUnitList);
    }

    @RequestMapping(path = "/findFormParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findFormParamList",desc = "根据查询参数查询form-data列表")
//    @ApiParam(name = "formParamUnitQuery",desc = "formParamQuery",required = true)
    public Result<List<FormParamUnit>> findFormParamList(@RequestBody @Valid @NotNull FormParamUnitQuery formParamUnitQuery){
        List<FormParamUnit> formParamUnitList = formParamUnitService.findFormParamList(formParamUnitQuery);

        return Result.ok(formParamUnitList);
    }

    @RequestMapping(path = "/findFormParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamPage",desc = "根据查询参数按分页查询form-data")
//    @ApiParam(name = "formParamUnitQuery",desc = "formParamQuery",required = true)
    public Result<Pagination<FormParamUnit>> findFormParamPage(@RequestBody @Valid @NotNull FormParamUnitQuery formParamUnitQuery){
        Pagination<FormParamUnit> pagination = formParamUnitService.findFormParamPage(formParamUnitQuery);

        return Result.ok(pagination);
    }

}
