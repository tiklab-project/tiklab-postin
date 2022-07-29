package com.tiklab.postlink.apimock.http.controller;

import com.tiklab.postlink.annotation.Api;
import com.tiklab.postlink.apimock.http.model.Mock;
import com.tiklab.postlink.apimock.http.model.MockQuery;
import com.tiklab.postlink.apimock.http.service.MockService;
import com.tiklab.core.page.Pagination;
import com.tiklab.core.Result;
import com.tiklab.postlink.annotation.ApiMethod;
import com.tiklab.postlink.annotation.ApiParam;
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
@RequestMapping("/mock")
@Api(name = "MockController",desc = "接口Mock管理")
public class MockController {

    private static Logger logger = LoggerFactory.getLogger(MockController.class);

    @Autowired
    private MockService mockService;

    @RequestMapping(path="/createMock",method = RequestMethod.POST)
    @ApiMethod(name = "createMock",desc = "创建mock")
    @ApiParam(name = "mock",desc = "MockDto",required = true)
    public Result<String> createMock(@RequestBody @NotNull @Valid Mock mock){
        String id = mockService.createMock(mock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateMock",desc = "更新mock")
    @ApiParam(name = "mock",desc = "MockDto",required = true)
    public Result<Void> updateMock(@RequestBody @NotNull @Valid Mock mock){
        mockService.updateMock(mock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteMock",desc = "根据ID删除mock")
    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteMock(@NotNull String id){
        mockService.deleteMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findMock",method = RequestMethod.POST)
    @ApiMethod(name = "findMock",desc = "根据ID查找mock")
    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Mock> findMock(@NotNull String id){
        Mock mock = mockService.findMock(id);

        return Result.ok(mock);
    }

    @RequestMapping(path="/findAllMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllMock",desc = "查找所有mock")
    public Result<List<Mock>> findAllMock(){
        List<Mock> mockList = mockService.findAllMock();

        return Result.ok(mockList);
    }


    @RequestMapping(path = "/findMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findMockList",desc = "根据查询条件查找mock列表")
    @ApiParam(name = "mockQuery",desc = "查询条件",required = true)
    public Result<List<Mock>> findMockList(@RequestBody @Valid @NotNull MockQuery mockQuery){
        List<Mock> mockList = mockService.findMockList(mockQuery);

        return Result.ok(mockList);
    }


    @RequestMapping(path = "/findMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findMockPage",desc = "根据查询条件按分页查找mock列表")
    @ApiParam(name = "mockQuery",desc = "查询条件",required = true)
    public Result<Pagination<Mock>> findMockPage(@RequestBody @Valid @NotNull MockQuery mockQuery){
        Pagination<Mock> pagination = mockService.findMockPage(mockQuery);

        return Result.ok(pagination);
    }

}
