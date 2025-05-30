package io.tiklab.postin.api.http.test.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.test.cases.model.RawParamCase;
import io.tiklab.postin.api.http.test.cases.model.RawParamCaseQuery;
import io.tiklab.postin.api.http.test.cases.service.RawParamCaseService;
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
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/rawParamCase")
//@Api(name = "RawParamCaseController",desc = "接口用例-raw参数管理")
public class RawParamCaseController {

    private static Logger logger = LoggerFactory.getLogger(RawParamCaseController.class);

    @Autowired
    private RawParamCaseService rawParamCaseService;

    @RequestMapping(path="/createRawParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "createRawParamCase",desc = "createRawParamCase")
//    @ApiParam(name = "rawParamCase",desc = "rawParamCase",required = true)
    public Result<String> createRawParamCase(@RequestBody @NotNull @Valid RawParamCase rawParamCase){
        String id = rawParamCaseService.createRawParamCase(rawParamCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "updateRawParamCase",desc = "updateRawParamCase")
//    @ApiParam(name = "rawParamCase",desc = "rawParamCase",required = true)
    public Result<Void> updateRawParamCase(@RequestBody @NotNull @Valid RawParamCase rawParamCase){
        rawParamCaseService.updateRawParamCase(rawParamCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteRawParamCase",desc = "deleteRawParamCase")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawParamCase(@NotNull String id){
        rawParamCaseService.deleteRawParamCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawParamCase",desc = "findRawParamCase")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawParamCase> findRawParamCase(@NotNull String id){
        RawParamCase rawParamCase = rawParamCaseService.findRawParamCase(id);

        return Result.ok(rawParamCase);
    }

    @RequestMapping(path="/findAllRawParamCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllRawParamCase",desc = "findAllRawParamCase")
    public Result<List<RawParamCase>> findAllRawParamCase(){
        List<RawParamCase> rawParamCaseList = rawParamCaseService.findAllRawParamCase();

        return Result.ok(rawParamCaseList);
    }


    @RequestMapping(path = "/findRawParamCaseList",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawParamCaseList",desc = "findRawParamCaseList")
//    @ApiParam(name = "rawParamCaseQuery",desc = "rawParamCaseQuery",required = true)
    public Result<List<RawParamCase>> findRawParamCaseList(@RequestBody @Valid @NotNull RawParamCaseQuery rawParamCaseQuery){
        List<RawParamCase> rawParamCaseList = rawParamCaseService.findRawParamCaseList(rawParamCaseQuery);

        return Result.ok(rawParamCaseList);
    }


    @RequestMapping(path = "/findRawParamCasePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawParamCasePage",desc = "findRawParamCasePage")
//    @ApiParam(name = "rawParamCaseQuery",desc = "rawParamCaseQuery",required = true)
    public Result<Pagination<RawParamCase>> findRawParamCasePage(@RequestBody @Valid @NotNull RawParamCaseQuery rawParamCaseQuery){
        Pagination<RawParamCase> pagination = rawParamCaseService.findRawParamCasePage(rawParamCaseQuery);

        return Result.ok(pagination);
    }

}
