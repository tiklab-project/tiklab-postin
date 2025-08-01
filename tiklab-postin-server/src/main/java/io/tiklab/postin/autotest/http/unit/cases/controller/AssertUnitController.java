package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.unit.cases.model.AssertUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AssertUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.AssertUnitService;
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
 * 断言 控住器
 */
@RestController
@RequestMapping("/assertParamUnit")
//@Api(name = "AssertCaseController",desc = "接口用例步骤断言管理")
public class AssertUnitController {

    private static Logger logger = LoggerFactory.getLogger(AssertUnitController.class);

    @Autowired
    private AssertUnitService assertCaseService;

    @RequestMapping(path="/createAssertParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createAssertCase",desc = "创建断言")
//    @ApiParam(name = "assertUnit",desc = "assertCase",required = true)
    public Result<String> createAssertCase(@RequestBody @NotNull @Valid AssertUnit assertUnit){
        String id = assertCaseService.createAssertCase(assertUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAssertParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateAssertCase",desc = "更新断言")
//    @ApiParam(name = "assertUnit",desc = "assertCase",required = true)
    public Result<Void> updateAssertCase(@RequestBody @NotNull @Valid AssertUnit assertUnit){
        assertCaseService.updateAssertCase(assertUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAssertParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteAssertCase",desc = "删除断言")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAssertCase(@NotNull String id){
        assertCaseService.deleteAssertCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAssertParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAssertCase",desc = "通过id查找断言")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AssertUnit> findAssertCase(@NotNull String id){
        AssertUnit assertUnit = assertCaseService.findAssertCase(id);

        return Result.ok(assertUnit);
    }

    @RequestMapping(path="/findAllAssertParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllAssertCase",desc = "查找所有断言")
    public Result<List<AssertUnit>> findAllAssertCase(){
        List<AssertUnit> assertUnitList = assertCaseService.findAllAssertCase();

        return Result.ok(assertUnitList);
    }

    @RequestMapping(path = "/findAssertParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findAssertCaseList",desc = "查询断言列表")
//    @ApiParam(name = "assertUnitQuery",desc = "assertCaseQuery",required = true)
    public Result<List<AssertUnit>> findAssertCaseList(@RequestBody @Valid @NotNull AssertUnitQuery assertUnitQuery){
        List<AssertUnit> assertUnitList = assertCaseService.findAssertCaseList(assertUnitQuery);

        return Result.ok(assertUnitList);
    }

    @RequestMapping(path = "/findAssertParamPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findAssertCasePage",desc = "按分页查询断言")
//    @ApiParam(name = "assertUnitQuery",desc = "assertCaseQuery",required = true)
    public Result<Pagination<AssertUnit>> findAssertCasePage(@RequestBody @Valid @NotNull AssertUnitQuery assertUnitQuery){
        Pagination<AssertUnit> pagination = assertCaseService.findAssertCasePage(assertUnitQuery);

        return Result.ok(pagination);
    }

}
