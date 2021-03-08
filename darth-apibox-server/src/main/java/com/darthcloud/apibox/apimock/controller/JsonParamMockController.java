package com.darthcloud.apibox.apimock.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apimock.model.JsonParamMock;
import com.darthcloud.apibox.apimock.model.JsonParamMockQuery;
import com.darthcloud.apibox.apimock.service.JsonParamMockService;
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
@RequestMapping("/jsonParamMock")
@Api(name = "JsonParamMockController",desc = "接口mock-json参数管理")
public class JsonParamMockController {

    private static Logger logger = LoggerFactory.getLogger(JsonParamMockController.class);

    @Autowired
    private JsonParamMockService jsonParamMockService;

    @RequestMapping(path="/createJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "createJsonParamMock",desc = "createJsonParamMock")
    @ApiParam(name = "jsonParamMock",desc = "jsonParamMock",required = true)
    public Result<String> createJsonParamMock(@RequestBody @NotNull @Valid JsonParamMock jsonParamMock){
        String id = jsonParamMockService.createJsonParamMock(jsonParamMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonParamMock",desc = "updateJsonParamMock")
    @ApiParam(name = "jsonParamMock",desc = "jsonParamMock",required = true)
    public Result<Void> updateJsonParamMock(@RequestBody @NotNull @Valid JsonParamMock jsonParamMock){
        jsonParamMockService.updateJsonParamMock(jsonParamMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteJsonParamMock",desc = "deleteJsonParamMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteJsonParamMock(@NotNull String id){
        jsonParamMockService.deleteJsonParamMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamMock",desc = "findJsonParamMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<JsonParamMock> findJsonParamMock(@NotNull String id){
        JsonParamMock jsonParamMock = jsonParamMockService.findJsonParamMock(id);

        return Result.ok(jsonParamMock);
    }

    @RequestMapping(path="/findAllJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonParamMock",desc = "findAllJsonParamMock")
    public Result<List<JsonParamMock>> findAllJsonParamMock(){
        List<JsonParamMock> jsonParamMockList = jsonParamMockService.findAllJsonParamMock();

        return Result.ok(jsonParamMockList);
    }

    @Validator
    @RequestMapping(path = "/findJsonParamMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamMockList",desc = "findJsonParamMockList")
    @ApiParam(name = "jsonParamMockQuery",desc = "jsonParamMockQuery",required = true)
    public Result<List<JsonParamMock>> findJsonParamMockList(@RequestBody @Valid @NotNull JsonParamMockQuery jsonParamMockQuery){
        List<JsonParamMock> jsonParamMockList = jsonParamMockService.findJsonParamMockList(jsonParamMockQuery);

        return Result.ok(jsonParamMockList);
    }

    @Validator
    @RequestMapping(path = "/findJsonParamMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamMockPage",desc = "findJsonParamMockPage")
    @ApiParam(name = "jsonParamMockQuery",desc = "jsonParamMockQuery",required = true)
    public Result<Pagination<List<JsonParamMock>>> findJsonParamMockPage(@RequestBody @Valid @NotNull JsonParamMockQuery jsonParamMockQuery){
        Pagination<List<JsonParamMock>> pagination = jsonParamMockService.findJsonParamMockPage(jsonParamMockQuery);

        return Result.ok(pagination);
    }

}
