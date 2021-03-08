package com.darthcloud.apibox.apimock.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apimock.model.JsonResponseMock;
import com.darthcloud.apibox.apimock.model.JsonResponseMockQuery;
import com.darthcloud.apibox.apimock.service.JsonResponseMockService;
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
import com.darthcloud.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/jsonResponseMock")
@Api(name = "JsonResponseMockController",desc = "接口mock-Json响应管理")
public class JsonResponseMockController {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseMockController.class);

    @Autowired
    private JsonResponseMockService jsonResponseMockService;

    @RequestMapping(path="/createJsonResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "createJsonResponseMock",desc = "createJsonResponseMock")
    @ApiParam(name = "jsonResponseMock",desc = "jsonResponseMock",required = true)
    public Result<String> createJsonResponseMock(@RequestBody @NotNull @Valid JsonResponseMock jsonResponseMock){
        String id = jsonResponseMockService.createJsonResponseMock(jsonResponseMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonResponseMock",desc = "updateJsonResponseMock")
    @ApiParam(name = "jsonResponseMock",desc = "jsonResponseMock",required = true)
    public Result<Void> updateJsonResponseMock(@RequestBody @NotNull @Valid JsonResponseMock jsonResponseMock){
        jsonResponseMockService.updateJsonResponseMock(jsonResponseMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteJsonResponseMock",desc = "deleteJsonResponseMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteJsonResponseMock(@NotNull String id){
        jsonResponseMockService.deleteJsonResponseMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonResponseMock",desc = "findJsonResponseMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<JsonResponseMock> findJsonResponseMock(@NotNull String id){
        JsonResponseMock jsonResponseMock = jsonResponseMockService.findJsonResponseMock(id);

        return Result.ok(jsonResponseMock);
    }

    @RequestMapping(path="/findAllJsonResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonResponseMock",desc = "findAllJsonResponseMock")
    public Result<List<JsonResponseMock>> findAllJsonResponseMock(){
        List<JsonResponseMock> jsonResponseMockList = jsonResponseMockService.findAllJsonResponseMock();

        return Result.ok(jsonResponseMockList);
    }

    @Validator
    @RequestMapping(path = "/findJsonResponseMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonResponseMockList",desc = "findJsonResponseMockList")
    @ApiParam(name = "jsonResponseMockQuery",desc = "jsonResponseMockQuery",required = true)
    public Result<List<JsonResponseMock>> findJsonResponseMockList(@RequestBody @Valid @NotNull JsonResponseMockQuery jsonResponseMockQuery){
        List<JsonResponseMock> jsonResponseMockList = jsonResponseMockService.findJsonResponseMockList(jsonResponseMockQuery);

        return Result.ok(jsonResponseMockList);
    }

    @Validator
    @RequestMapping(path = "/findJsonResponseMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonResponseMockPage",desc = "findJsonResponseMockPage")
    @ApiParam(name = "jsonResponseMockQuery",desc = "jsonResponseMockQuery",required = true)
    public Result<Pagination<List<JsonResponseMock>>> findJsonResponseMockPage(@RequestBody @Valid @NotNull JsonResponseMockQuery jsonResponseMockQuery){
        Pagination<List<JsonResponseMock>> pagination = jsonResponseMockService.findJsonResponseMockPage(jsonResponseMockQuery);

        return Result.ok(pagination);
    }

}
