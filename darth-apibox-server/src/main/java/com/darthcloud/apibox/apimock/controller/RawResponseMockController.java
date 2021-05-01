package com.darthcloud.apibox.apimock.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apimock.model.RawResponseMock;
import com.darthcloud.apibox.apimock.model.RawResponseMockQuery;
import com.darthcloud.apibox.apimock.service.RawResponseMockService;
import com.darthcloud.common.Pagination;
import com.darthcloud.common.Result;
import com.darthcloud.apibox.annotation.ApiMethod;
import com.darthcloud.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.darthcloud.web.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/rawResponseMock")
@Api(name = "RawResponseMockController",desc = "接口mock-Raw(自定义文本)响应管理")
public class RawResponseMockController {

    private static Logger logger = LoggerFactory.getLogger(RawResponseMockController.class);

    @Autowired
    private RawResponseMockService rawResponseMockService;

    @RequestMapping(path="/createRawResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "createRawResponseMock",desc = "createRawResponseMock")
    @ApiParam(name = "rawResponseMock",desc = "rawResponseMock",required = true)
    public Result<String> createRawResponseMock(@RequestBody @NotNull @Valid RawResponseMock rawResponseMock){
        String id = rawResponseMockService.createRawResponseMock(rawResponseMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateRawResponseMock",desc = "updateRawResponseMock")
    @ApiParam(name = "rawResponseMock",desc = "rawResponseMock",required = true)
    public Result<Void> updateRawResponseMock(@RequestBody @NotNull @Valid RawResponseMock rawResponseMock){
        rawResponseMockService.updateRawResponseMock(rawResponseMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRawResponseMock",desc = "deleteRawResponseMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawResponseMock(@NotNull String id){
        rawResponseMockService.deleteRawResponseMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "findRawResponseMock",desc = "findRawResponseMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawResponseMock> findRawResponseMock(@NotNull String id){
        RawResponseMock rawResponseMock = rawResponseMockService.findRawResponseMock(id);

        return Result.ok(rawResponseMock);
    }

    @RequestMapping(path="/findAllRawResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRawResponseMock",desc = "findAllRawResponseMock")
    public Result<List<RawResponseMock>> findAllRawResponseMock(){
        List<RawResponseMock> rawResponseMockList = rawResponseMockService.findAllRawResponseMock();

        return Result.ok(rawResponseMockList);
    }

    @Validator
    @RequestMapping(path = "/findRawResponseMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findRawResponseMockList",desc = "findRawResponseMockList")
    @ApiParam(name = "rawResponseMockQuery",desc = "rawResponseMockQuery",required = true)
    public Result<List<RawResponseMock>> findRawResponseMockList(@RequestBody @Valid @NotNull RawResponseMockQuery rawResponseMockQuery){
        List<RawResponseMock> rawResponseMockList = rawResponseMockService.findRawResponseMockList(rawResponseMockQuery);

        return Result.ok(rawResponseMockList);
    }

    @Validator
    @RequestMapping(path = "/findRawResponseMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRawResponseMockPage",desc = "findRawResponseMockPage")
    @ApiParam(name = "rawResponseMockQuery",desc = "rawResponseMockQuery",required = true)
    public Result<Pagination<RawResponseMock>> findRawResponseMockPage(@RequestBody @Valid @NotNull RawResponseMockQuery rawResponseMockQuery){
        Pagination<RawResponseMock> pagination = rawResponseMockService.findRawResponseMockPage(rawResponseMockQuery);

        return Result.ok(pagination);
    }

}
