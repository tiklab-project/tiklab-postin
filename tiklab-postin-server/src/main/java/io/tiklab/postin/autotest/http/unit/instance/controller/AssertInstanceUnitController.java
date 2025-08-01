package io.tiklab.postin.autotest.http.unit.instance.controller;

import io.tiklab.postin.autotest.http.unit.instance.model.AssertInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.AssertInstanceUnitQuery;
import io.tiklab.postin.autotest.http.unit.instance.service.AssertInstanceUnitService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
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
 * 断言实例 控制器
 */
@RestController
@RequestMapping("/assertInstanceUnit")
//@Api(name = "AssertInstanceController",desc = "测试实例——断言管理")
public class AssertInstanceUnitController {

    private static Logger logger = LoggerFactory.getLogger(AssertInstanceUnitController.class);

    @Autowired
    private AssertInstanceUnitService assertInstanceUnitService;

    @RequestMapping(path="/createAssertInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "createAssertInstance",desc = "创建断言实例")
//    @ApiParam(name = "assertInstance",desc = "assertInstance",required = true)
    public Result<String> createAssertInstance(@RequestBody @NotNull @Valid AssertInstanceUnit assertInstanceUnit){
        String id = assertInstanceUnitService.createAssertInstance(assertInstanceUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAssertInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "updateAssertInstance",desc = "updateAssertInstance")
//    @ApiParam(name = "assertInstance",desc = "assertInstance",required = true)
    public Result<Void> updateAssertInstance(@RequestBody @NotNull @Valid AssertInstanceUnit assertInstanceUnit){
        assertInstanceUnitService.updateAssertInstance(assertInstanceUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAssertInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteAssertInstance",desc = "删除断言实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAssertInstance(@NotNull String id){
        assertInstanceUnitService.deleteAssertInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAssertInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAssertInstance",desc = "根据id查找断言实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AssertInstanceUnit> findAssertInstance(@NotNull String id){
        AssertInstanceUnit assertInstanceUnit = assertInstanceUnitService.findAssertInstance(id);

        return Result.ok(assertInstanceUnit);
    }

    @RequestMapping(path="/findAllAssertInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllAssertInstance",desc = "查找所有断言实例")
    public Result<List<AssertInstanceUnit>> findAllAssertInstance(){
        List<AssertInstanceUnit> assertInstanceUnitList = assertInstanceUnitService.findAllAssertInstance();

        return Result.ok(assertInstanceUnitList);
    }

    @RequestMapping(path = "/findAssertInstanceList",method = RequestMethod.POST)
//    @ApiMethod(name = "findAssertInstanceList",desc = "根据查询参数查询断言实例列表")
//    @ApiParam(name = "assertInstanceQuery",desc = "assertInstanceQuery",required = true)
    public Result<List<AssertInstanceUnit>> findAssertInstanceList(@RequestBody @Valid @NotNull AssertInstanceUnitQuery assertInstanceUnitQuery){
        List<AssertInstanceUnit> assertInstanceUnitList = assertInstanceUnitService.findAssertInstanceList(assertInstanceUnitQuery);

        return Result.ok(assertInstanceUnitList);
    }

    @RequestMapping(path = "/findAssertInstancePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findAssertInstancePage",desc = "根据查询参数按分页查询断言实例")
//    @ApiParam(name = "assertInstanceQuery",desc = "assertInstanceQuery",required = true)
    public Result<Pagination<AssertInstanceUnit>> findAssertInstancePage(@RequestBody @Valid @NotNull AssertInstanceUnitQuery assertInstanceUnitQuery){
        Pagination<AssertInstanceUnit> pagination = assertInstanceUnitService.findAssertInstancePage(assertInstanceUnitQuery);

        return Result.ok(pagination);
    }

}
