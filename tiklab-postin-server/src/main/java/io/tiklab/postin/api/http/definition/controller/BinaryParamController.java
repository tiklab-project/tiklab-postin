package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.definition.model.BinaryParam;
import io.tiklab.postin.api.http.definition.model.BinaryParamQuery;
import io.tiklab.postin.api.http.definition.service.BinaryParamService;
import io.tiklab.postin.api.http.test.cases.model.BinaryParamCase;
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
 * binary参数控制器
 */
@RestController
@RequestMapping("/binaryParam")
//@Api(name = "BinaryParamController",desc = "binary参数管理")
public class BinaryParamController {

    private static Logger logger = LoggerFactory.getLogger(BinaryParamController.class);

    @Autowired
    private BinaryParamService binaryParamService;

    @RequestMapping(path="/createBinaryParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createBinaryParam",desc = "createBinaryParam")
//    @ApiParam(name = "binaryParam",desc = "binaryParam",required = true)
    public Result<String> createBinaryParam(@RequestBody @NotNull @Valid BinaryParam binaryParam){
        String id = binaryParamService.createBinaryParam(binaryParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateBinaryParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateBinaryParam",desc = "updateBinaryParam")
//    @ApiParam(name = "binaryParam",desc = "binaryParam",required = true)
    public Result<Void> updateBinaryParam(@RequestBody @NotNull @Valid BinaryParam binaryParam){
        binaryParamService.updateBinaryParam(binaryParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteBinaryParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteBinaryParam",desc = "deleteBinaryParam")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteBinaryParam(@NotNull String id){
        binaryParamService.deleteBinaryParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findBinaryParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findBinaryParam",desc = "findBinaryParam")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<BinaryParam> findBinaryParam(@NotNull String id){
        BinaryParam binaryParam = binaryParamService.findBinaryParam(id);

        return Result.ok(binaryParam);
    }

    @RequestMapping(path="/findAllBinaryParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllBinaryParam",desc = "findAllBinaryParam")
    public Result<List<BinaryParam>> findAllBinaryParam(){
        List<BinaryParam> binaryParamList = binaryParamService.findAllBinaryParam();

        return Result.ok(binaryParamList);
    }

    @RequestMapping(path = "/findBinaryParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findBinaryParamList",desc = "findBinaryParamList")
//    @ApiParam(name = "binaryParamQuery",desc = "binaryParamQuery",required = true)
    public Result<List<BinaryParam>> findBinaryParamList(@RequestBody @Valid @NotNull BinaryParamQuery binaryParamQuery){
        List<BinaryParam> binaryParamList = binaryParamService.findBinaryParamList(binaryParamQuery);

        return Result.ok(binaryParamList);
    }

    @RequestMapping(path = "/findBinaryParamPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findBinaryParamPage",desc = "findBinaryParamPage")
//    @ApiParam(name = "binaryParamQuery",desc = "binaryParamQuery",required = true)
    public Result<Pagination<BinaryParam>> findBinaryParamPage(@RequestBody @Valid @NotNull BinaryParamQuery binaryParamQuery){
        Pagination<BinaryParam> pagination = binaryParamService.findBinaryParamPage(binaryParamQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/findBinaryParamByte",method = RequestMethod.POST)
//    @ApiMethod(name = "findBinaryParamByte",desc = "findBinaryParamCaseByte")
//    @ApiParam(name = "binaryParamQuery",desc = "binaryParamQuery",required = true)
    public Result<BinaryParamCase> findBinaryParamCaseByte(@RequestBody @Valid @NotNull BinaryParamQuery binaryParamQuery){

        String a = binaryParamService.findBinaryParamByte(binaryParamQuery);

        return Result.ok(a);
    }




}
