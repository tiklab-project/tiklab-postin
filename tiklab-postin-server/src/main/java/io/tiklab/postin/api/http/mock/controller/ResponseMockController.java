package io.tiklab.postin.api.http.mock.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.mock.model.ResponseMock;
import io.tiklab.postin.api.http.mock.model.ResponseMockQuery;
import io.tiklab.postin.api.http.mock.service.ResponseMockService;
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
 * mock
 * 响应 控制器
 */
@RestController
@RequestMapping("/responseMock")
@Api(name = "ResponseMockController",desc = "接口mock-响应管理")
public class ResponseMockController {

    private static Logger logger = LoggerFactory.getLogger(ResponseMockController.class);

    @Autowired
    private ResponseMockService responseMockService;

    @RequestMapping(path="/createResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseMock",desc = "创建响应管理")
    @ApiParam(name = "responseMock",desc = "responseMock",required = true)
    public Result<String> createResponseMock(@RequestBody @NotNull @Valid ResponseMock responseMock){
        String id = responseMockService.createResponseMock(responseMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseMock",desc = "更新响应管理")
    @ApiParam(name = "responseMock",desc = "responseMock",required = true)
    public Result<Void> updateResponseMock(@RequestBody @NotNull @Valid ResponseMock responseMock){
        responseMockService.updateResponseMock(responseMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseMock",desc = "删除响应管理")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseMock(@NotNull String id){
        responseMockService.deleteResponseMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseMock",desc = "通过id查找响应管理")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseMock> findResponseMock(@NotNull String id){
        ResponseMock responseMock = responseMockService.findResponseMock(id);

        return Result.ok(responseMock);
    }

    @RequestMapping(path="/findAllResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseMock",desc = "查找所有响应管理")
    public Result<List<ResponseMock>> findAllResponseMock(){
        List<ResponseMock> responseMockList = responseMockService.findAllResponseMock();

        return Result.ok(responseMockList);
    }


    @RequestMapping(path = "/findResponseMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseMockList",desc = "根据查询参数查找响应管理")
    @ApiParam(name = "responseMockQuery",desc = "responseMockQuery",required = true)
    public Result<List<ResponseMock>> findResponseMockList(@RequestBody @Valid @NotNull ResponseMockQuery responseMockQuery){
        List<ResponseMock> responseMockList = responseMockService.findResponseMockList(responseMockQuery);

        return Result.ok(responseMockList);
    }


    @RequestMapping(path = "/findResponseMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseMockPage",desc = "根据查询参数按分页查找响应管理")
    @ApiParam(name = "responseMockQuery",desc = "responseMockQuery",required = true)
    public Result<Pagination<ResponseMock>> findResponseMockPage(@RequestBody @Valid @NotNull ResponseMockQuery responseMockQuery){
        Pagination<ResponseMock> pagination = responseMockService.findResponseMockPage(responseMockQuery);

        return Result.ok(pagination);
    }

}
