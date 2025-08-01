package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.autotest.http.unit.cases.model.RawParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RawParamUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.RawParamUnitService;
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
 * raw类型 控制器
 */
@RestController
@RequestMapping("/rawParamUnit")
//@Api(name = "RawParamController",desc = "接口用例步骤RawParamController")
public class RawParamUnitController {

    private static Logger logger = LoggerFactory.getLogger(RawParamUnitController.class);

    @Autowired
    private RawParamUnitService rawParamUnitService;

    @RequestMapping(path="/createRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createRawParam",desc = "创建raw")
//    @ApiParam(name = "rawParamUnit",desc = "rawParam",required = true)
    public Result<String> createRawParam(@RequestBody @NotNull @Valid RawParamUnit rawParamUnit){
        String id = rawParamUnitService.createRawParam(rawParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateRawParam",desc = "更新raw")
//    @ApiParam(name = "rawParamUnit",desc = "rawParam",required = true)
    public Result<Void> updateRawParam(@RequestBody @NotNull @Valid RawParamUnit rawParamUnit){
        rawParamUnitService.updateRawParam(rawParamUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteRawParam",desc = "删除raw")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawParam(@NotNull String id){
        rawParamUnitService.deleteRawParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawParam",desc = "根据id查找raw")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawParamUnit> findRawParam(@NotNull String id){
        RawParamUnit rawParamUnit = rawParamUnitService.findRawParam(id);

        return Result.ok(rawParamUnit);
    }

    @RequestMapping(path="/findAllRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllRawParam",desc = "查找所有raw")
    public Result<List<RawParamUnit>> findAllRawParam(){
        List<RawParamUnit> rawParamUnitList = rawParamUnitService.findAllRawParam();

        return Result.ok(rawParamUnitList);
    }

    @RequestMapping(path = "/findRawParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamList",desc = "根据查询参数查询raw列表")
//    @ApiParam(name = "rawParamUnitQuery",desc = "rawParamQuery",required = true)
    public Result<List<RawParamUnit>> findRawParamList(@RequestBody @Valid @NotNull RawParamUnitQuery rawParamUnitQuery){
        List<RawParamUnit> rawParamUnitList = rawParamUnitService.findRawParamList(rawParamUnitQuery);

        return Result.ok(rawParamUnitList);
    }

    @RequestMapping(path = "/findRawParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamPage",desc = "根据查询参数按分页查询raw")
//    @ApiParam(name = "rawParamUnitQuery",desc = "rawParamQuery",required = true)
    public Result<Pagination<RawParamUnit>> findRawParamPage(@RequestBody @Valid @NotNull RawParamUnitQuery rawParamUnitQuery){
        Pagination<RawParamUnit> pagination = rawParamUnitService.findRawParamPage(rawParamUnitQuery);

        return Result.ok(pagination);
    }

}
