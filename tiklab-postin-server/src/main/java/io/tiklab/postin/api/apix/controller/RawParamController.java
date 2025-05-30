package io.tiklab.postin.api.apix.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.apix.model.RawParam;
import io.tiklab.postin.api.apix.model.RawParamQuery;
import io.tiklab.postin.api.apix.service.RawParamService;
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
 * http协议 定义
 * raw 控制器
 */
@RestController
@RequestMapping("/rawParam")
//@Api(name = "RawParamController",desc = "Raw(自定义文本)参数管理")
public class RawParamController {

    private static Logger logger = LoggerFactory.getLogger(RawParamController.class);

    @Autowired
    private RawParamService rawParamService;

    @RequestMapping(path="/createRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createRawParam",desc = "创建raw参数")
//    @ApiParam(name = "rawParam",desc = "rawParam",required = true)
    public Result<String> createRawParam(@RequestBody @NotNull @Valid RawParam rawParam){
        String id = rawParamService.createRawParam(rawParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateRawParam",desc = "更新raw 参数")
//    @ApiParam(name = "rawParam",desc = "rawParam",required = true)
    public Result<Void> updateRawParam(@RequestBody @NotNull @Valid RawParam rawParam){
        rawParamService.updateRawParam(rawParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteRawParam",desc = "删除raw参数")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawParam(@NotNull String id){
        rawParamService.deleteRawParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawParam",desc = "根据id查找raw")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawParam> findRawParam(@NotNull String id){
        RawParam rawParam = rawParamService.findRawParam(id);

        return Result.ok(rawParam);
    }

    @RequestMapping(path="/findAllRawParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllRawParam",desc = "查找所有raw")
    public Result<List<RawParam>> findAllRawParam(){
        List<RawParam> rawParamList = rawParamService.findAllRawParam();

        return Result.ok(rawParamList);
    }


    @RequestMapping(path = "/findRawParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawParamList",desc = "根据查询参数查找raw列表")
//    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<List<RawParam>> findRawParamList(@RequestBody @Valid @NotNull RawParamQuery rawParamQuery){
        List<RawParam> rawParamList = rawParamService.findRawParamList(rawParamQuery);

        return Result.ok(rawParamList);
    }


    @RequestMapping(path = "/findRawParamPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawParamPage",desc = "根据查询参数按分页查找raw列表")
//    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<Pagination<RawParam>> findRawParamPage(@RequestBody @Valid @NotNull RawParamQuery rawParamQuery){
        Pagination<RawParam> pagination = rawParamService.findRawParamPage(rawParamQuery);

        return Result.ok(pagination);
    }

}
