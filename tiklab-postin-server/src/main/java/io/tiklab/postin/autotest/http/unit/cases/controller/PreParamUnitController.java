package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.unit.cases.model.PreParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.PreParamUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.PreParamUnitService;
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
 * 前置 控制器
 */
@RestController
@RequestMapping("/preParamUnit")
//@Api(name = "PreParamUnitController",desc = "前置管理")
public class PreParamUnitController {

    private static Logger logger = LoggerFactory.getLogger(PreParamUnitController.class);

    @Autowired
    private PreParamUnitService preParamUnitService;

    @RequestMapping(path="/createPreParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "createPreParamUnit",desc = "创建前置")
//    @ApiParamUnit(name = "preParamUnit",desc = "前置DTO",required = true)
    public Result<String> createPreParamUnit(@RequestBody @NotNull @Valid PreParamUnit preParamUnit){
        String id = preParamUnitService.createPreParamUnit(preParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePreParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "updatePreParamUnit",desc = "更新前置")
//    @ApiParamUnit(name = "preParamUnit",desc = "前置DTO",required = true)
    public Result<Void> updatePreParamUnit(@RequestBody @NotNull @Valid PreParamUnit preParamUnit){
        preParamUnitService.updatePreParamUnit(preParamUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deletePreParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "deletePreParamUnit",desc = "根据ID删除前置")
//    @ApiParamUnit(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deletePreParamUnit(@NotNull String id){
        preParamUnitService.deletePreParamUnit(id);

        return Result.ok();
    }

    @RequestMapping(path="/findPreParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "findPreParamUnit",desc = "根据ID查找前置")
//    @ApiParamUnit(name = "id",desc = "唯一标识",required = true)
    public Result<PreParamUnit> findPreParamUnit(@NotNull String id){
        PreParamUnit preParamUnit = preParamUnitService.findPreParamUnit(id);

        return Result.ok(preParamUnit);
    }

    @RequestMapping(path = "/findPreParamUnitList",method = RequestMethod.POST)
//    @ApiMethod(name = "findPreParamUnitList",desc = "根据查询参数查找前置列表")
//    @ApiParamUnit(name = "preParamUnitQuery",desc = "preParamUnitQuery",required = true)
    public Result<List<PreParamUnit>> findFormParamUnitList(@RequestBody @Valid @NotNull PreParamUnitQuery preParamUnitQuery){
        List<PreParamUnit> preParamUnitList = preParamUnitService.findPreParamUnitList(preParamUnitQuery);

        return Result.ok(preParamUnitList);
    }

    @RequestMapping(path = "/updatePreParamUnitSort",method = RequestMethod.POST)
    public Result<Void> updatePreParamUnitSort( @NotNull String id,  @NotNull Integer newSort){
        preParamUnitService.updatePreParamUnitSort(id, newSort);
        return Result.ok();
    }


}
