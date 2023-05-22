package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.definition.model.RawParams;
import io.tiklab.postin.api.http.definition.model.RawParamQuery;
import io.tiklab.postin.api.http.definition.service.RawParamService;
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
@Api(name = "RawParamController",desc = "Raw(自定义文本)参数管理")
public class RawParamController {

    private static Logger logger = LoggerFactory.getLogger(RawParamController.class);

    @Autowired
    private RawParamService rawParamService;

    @RequestMapping(path="/createRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "createRawParam",desc = "创建raw参数")
    @ApiParam(name = "rawParam",desc = "rawParam",required = true)
    public Result<String> createRawParam(@RequestBody @NotNull @Valid RawParams rawParams){
        String id = rawParamService.createRawParam(rawParams);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateRawParam",desc = "更新raw 参数")
    @ApiParam(name = "rawParam",desc = "rawParam",required = true)
    public Result<Void> updateRawParam(@RequestBody @NotNull @Valid RawParams rawParams){
        rawParamService.updateRawParam(rawParams);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRawParam",desc = "删除raw参数")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawParam(@NotNull String id){
        rawParamService.deleteRawParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParam",desc = "根据id查找raw")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawParams> findRawParam(@NotNull String id){
        RawParams rawParams = rawParamService.findRawParam(id);

        return Result.ok(rawParams);
    }

    @RequestMapping(path="/findAllRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRawParam",desc = "查找所有raw")
    public Result<List<RawParams>> findAllRawParam(){
        List<RawParams> rawParamsList = rawParamService.findAllRawParam();

        return Result.ok(rawParamsList);
    }


    @RequestMapping(path = "/findRawParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamList",desc = "根据查询参数查找raw列表")
    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<List<RawParams>> findRawParamList(@RequestBody @Valid @NotNull RawParamQuery rawParamQuery){
        List<RawParams> rawParamsList = rawParamService.findRawParamList(rawParamQuery);

        return Result.ok(rawParamsList);
    }


    @RequestMapping(path = "/findRawParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamPage",desc = "根据查询参数按分页查找raw列表")
    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<Pagination<RawParams>> findRawParamPage(@RequestBody @Valid @NotNull RawParamQuery rawParamQuery){
        Pagination<RawParams> pagination = rawParamService.findRawParamPage(rawParamQuery);

        return Result.ok(pagination);
    }

}
