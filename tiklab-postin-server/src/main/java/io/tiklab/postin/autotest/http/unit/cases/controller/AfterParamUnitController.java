package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterParamUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.AfterParamUnitService;
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
 * 后置 控制器
 */
@RestController
@RequestMapping("/afterParamUnit")
//@Api(name = "AfterParamUnitController",desc = "后置管理")
public class AfterParamUnitController {

    private static Logger logger = LoggerFactory.getLogger(AfterParamUnitController.class);

    @Autowired
    private AfterParamUnitService afterParamUnitService;

    @RequestMapping(path="/createAfterParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "createAfterParamUnit",desc = "创建后置")
//    @ApiParamUnit(name = "afterParamUnit",desc = "后置DTO",required = true)
    public Result<String> createAfterParamUnit(@RequestBody @NotNull @Valid AfterParamUnit afterParamUnit){
        String id = afterParamUnitService.createAfterParamUnit(afterParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAfterParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "updateAfterParamUnit",desc = "更新后置")
//    @ApiParamUnit(name = "afterParamUnit",desc = "后置DTO",required = true)
    public Result<Void> updateAfterParamUnit(@RequestBody @NotNull @Valid AfterParamUnit afterParamUnit){
        afterParamUnitService.updateAfterParamUnit(afterParamUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAfterParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteAfterParamUnit",desc = "根据ID删除后置")
//    @ApiParamUnit(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteAfterParamUnit(@NotNull String id){
        afterParamUnitService.deleteAfterParamUnit(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAfterParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "findAfterParamUnit",desc = "根据ID查找后置")
//    @ApiParamUnit(name = "id",desc = "唯一标识",required = true)
    public Result<AfterParamUnit> findAfterParamUnit(@NotNull String id){
        AfterParamUnit afterParamUnit = afterParamUnitService.findAfterParamUnit(id);

        return Result.ok(afterParamUnit);
    }

    @RequestMapping(path = "/findAfterParamUnitList",method = RequestMethod.POST)
//    @ApiMethod(name = "findAfterParamUnitList",desc = "根据查询参数查找后置列表")
//    @ApiParamUnit(name = "afterParamUnitQuery",desc = "afterParamUnitQuery",required = true)
    public Result<List<AfterParamUnit>> findFormParamUnitList(@RequestBody @Valid @NotNull AfterParamUnitQuery afterParamUnitQuery){
        List<AfterParamUnit> afterParamUnitList = afterParamUnitService.findAfterParamUnitList(afterParamUnitQuery);

        return Result.ok(afterParamUnitList);
    }



}
