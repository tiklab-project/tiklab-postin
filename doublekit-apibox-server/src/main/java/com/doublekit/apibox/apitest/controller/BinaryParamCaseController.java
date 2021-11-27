package com.doublekit.apibox.apitest.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apitest.model.BinaryParamCase;
import com.doublekit.apibox.apitest.model.BinaryParamCaseQuery;
import com.doublekit.apibox.apitest.service.BinaryParamCaseService;
import com.doublekit.common.page.Pagination;
import com.doublekit.common.Result;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * BinaryParamCaseController
 */
@RestController
@RequestMapping("/binaryParamCase")
@Api(name = "BinaryParamCaseController",desc = "BinaryParamCaseController")
public class BinaryParamCaseController {

    private static Logger logger = LoggerFactory.getLogger(BinaryParamCaseController.class);

    @Autowired
    private BinaryParamCaseService binaryParamCaseService;

    @RequestMapping(path="/createBinaryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "createBinaryParamCase",desc = "createBinaryParamCase")
    @ApiParam(name = "binaryParamCase",desc = "binaryParamCase",required = true)
    public Result<String> createBinaryParamCase(@RequestBody @NotNull @Valid BinaryParamCase binaryParamCase){
        String id = binaryParamCaseService.createBinaryParamCase(binaryParamCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateBinaryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateBinaryParamCase",desc = "updateBinaryParamCase")
    @ApiParam(name = "binaryParamCase",desc = "binaryParamCase",required = true)
    public Result<Void> updateBinaryParamCase(@RequestBody @NotNull @Valid BinaryParamCase binaryParamCase){
        binaryParamCaseService.updateBinaryParamCase(binaryParamCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteBinaryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteBinaryParamCase",desc = "deleteBinaryParamCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteBinaryParamCase(@NotNull String id){
        binaryParamCaseService.deleteBinaryParamCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findBinaryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "findBinaryParamCase",desc = "findBinaryParamCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<BinaryParamCase> findBinaryParamCase(@NotNull String id){
        BinaryParamCase binaryParamCase = binaryParamCaseService.findBinaryParamCase(id);

        return Result.ok(binaryParamCase);
    }

    @RequestMapping(path="/findAllBinaryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllBinaryParamCase",desc = "findAllBinaryParamCase")
    public Result<List<BinaryParamCase>> findAllBinaryParamCase(){
        List<BinaryParamCase> binaryParamCaseList = binaryParamCaseService.findAllBinaryParamCase();

        return Result.ok(binaryParamCaseList);
    }

    @RequestMapping(path = "/findBinaryParamCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findBinaryParamCaseList",desc = "findBinaryParamCaseList")
    @ApiParam(name = "binaryParamCaseQuery",desc = "binaryParamCaseQuery",required = true)
    public Result<List<BinaryParamCase>> findBinaryParamCaseList(@RequestBody @Valid @NotNull BinaryParamCaseQuery binaryParamCaseQuery){
        List<BinaryParamCase> binaryParamCaseList = binaryParamCaseService.findBinaryParamCaseList(binaryParamCaseQuery);

        return Result.ok(binaryParamCaseList);
    }

    @RequestMapping(path = "/findBinaryParamCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findBinaryParamCasePage",desc = "findBinaryParamCasePage")
    @ApiParam(name = "binaryParamCaseQuery",desc = "binaryParamCaseQuery",required = true)
    public Result<Pagination<BinaryParamCase>> findBinaryParamCasePage(@RequestBody @Valid @NotNull BinaryParamCaseQuery binaryParamCaseQuery){
        Pagination<BinaryParamCase> pagination = binaryParamCaseService.findBinaryParamCasePage(binaryParamCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/findBinaryParamCaseByte",method = RequestMethod.POST)
    @ApiMethod(name = "findBinaryParamCaseByte",desc = "findBinaryParamCaseByte")
    @ApiParam(name = "binaryParamCaseQuery",desc = "binaryParamCaseQuery",required = true)
    public Result<BinaryParamCase> findBinaryParamCaseByte(@RequestBody @Valid @NotNull BinaryParamCaseQuery binaryParamCaseQuery){

        String a = binaryParamCaseService.findBinaryParamCaseByte(binaryParamCaseQuery);

        return Result.ok(a);
    }

}
