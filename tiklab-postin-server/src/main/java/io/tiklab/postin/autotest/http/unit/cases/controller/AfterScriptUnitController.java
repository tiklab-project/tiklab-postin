package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterScriptUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterScriptUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.AfterScriptUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 后置脚本 控制器
 */
@RestController
@RequestMapping("/afterScriptUnit")
//@Api(name = "AfterScriptController",desc = "接口用例步骤后置脚本管理")
public class AfterScriptUnitController {

    private static Logger logger = LoggerFactory.getLogger(AfterScriptUnitController.class);

    @Autowired
    private AfterScriptUnitService afterScriptUnitService;

    @RequestMapping(path="/createAfterScript",method = RequestMethod.POST)
//    @ApiMethod(name = "createAfterScript",desc = "创建后置脚本")
//    @ApiParam(name = "afterScriptUnit",desc = "afterScript",required = true)
    public Result<String> createAfterScript(@RequestBody @NotNull @Valid AfterScriptUnit afterScriptUnit){
        String id = afterScriptUnitService.createAfterScript(afterScriptUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAfterScript",method = RequestMethod.POST)
//    @ApiMethod(name = "updateAfterScript",desc = "更新后置脚本")
//    @ApiParam(name = "afterScriptUnit",desc = "afterScript",required = true)
    public Result<Void> updateAfterScript(@RequestBody @NotNull @Valid AfterScriptUnit afterScriptUnit){
        afterScriptUnitService.updateAfterScript(afterScriptUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAfterScript",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteAfterScript",desc = "删除后置脚本")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAfterScript(@NotNull String id){
        afterScriptUnitService.deleteAfterScript(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAfterScript",method = RequestMethod.POST)
//    @ApiMethod(name = "findAfterScript",desc = "根据id查找后置脚本")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AfterScriptUnit> findAfterScript(@NotNull String id){
        AfterScriptUnit afterScriptUnit = afterScriptUnitService.findAfterScript(id);

        return Result.ok(afterScriptUnit);
    }

    @RequestMapping(path="/findAllAfterScript",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllAfterScript",desc = "查找所有后置脚本")
    public Result<List<AfterScriptUnit>> findAllAfterScript(){
        List<AfterScriptUnit> afterScriptUnitList = afterScriptUnitService.findAllAfterScript();

        return Result.ok(afterScriptUnitList);
    }

    @RequestMapping(path = "/findAfterScriptList",method = RequestMethod.POST)
//    @ApiMethod(name = "findAfterScriptList",desc = "通过查询参数查询后置脚本列表")
//    @ApiParam(name = "afterScriptUnitQuery",desc = "afterScriptQuery",required = true)
    public Result<List<AfterScriptUnit>> findAfterScriptList(@RequestBody @Valid @NotNull AfterScriptUnitQuery afterScriptUnitQuery){
        List<AfterScriptUnit> afterScriptUnitList = afterScriptUnitService.findAfterScriptList(afterScriptUnitQuery);

        return Result.ok(afterScriptUnitList);
    }

    @RequestMapping(path = "/findAfterScriptPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findAfterScriptPage",desc = "通过查询参数按分页查询后置脚本")
//    @ApiParam(name = "afterScriptUnitQuery",desc = "afterScriptQuery",required = true)
    public Result<Pagination<AfterScriptUnit>> findAfterScriptPage(@RequestBody @Valid @NotNull AfterScriptUnitQuery afterScriptUnitQuery){
        Pagination<AfterScriptUnit> pagination = afterScriptUnitService.findAfterScriptPage(afterScriptUnitQuery);

        return Result.ok(pagination);
    }

}
