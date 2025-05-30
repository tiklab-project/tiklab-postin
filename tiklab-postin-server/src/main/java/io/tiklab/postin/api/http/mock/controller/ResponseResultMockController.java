package io.tiklab.postin.api.http.mock.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.mock.model.ResponseResultMock;
import io.tiklab.postin.api.http.mock.model.ResponseResultMockQuery;
import io.tiklab.postin.api.http.mock.service.ResponseResultMockService;
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
 * 响应结果 控制器
 */
@RestController
@RequestMapping("/responseResultMock")
//@Api(name = "ResponseResultMockController",desc = "接口mock-响应结果管理")
public class ResponseResultMockController {

    private static Logger logger = LoggerFactory.getLogger(ResponseResultMockController.class);

    @Autowired
    private ResponseResultMockService responseResultMockService;

    @RequestMapping(path="/createResponseResultMock",method = RequestMethod.POST)
//    @ApiMethod(name = "createResponseResultMock",desc = "创建响应结果")
//    @ApiParam(name = "responseResultMock",desc = "responseResultMock",required = true)
    public Result<String> createResponseResultMock(@RequestBody @NotNull @Valid ResponseResultMock responseResultMock){
        String id = responseResultMockService.createResponseResultMock(responseResultMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseResultMock",method = RequestMethod.POST)
//    @ApiMethod(name = "updateResponseResultMock",desc = "更新响应结果")
//    @ApiParam(name = "responseResultMock",desc = "responseResultMock",required = true)
    public Result<Void> updateResponseResultMock(@RequestBody @NotNull @Valid ResponseResultMock responseResultMock){
        responseResultMockService.updateResponseResultMock(responseResultMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseResultMock",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteResponseResultMock",desc = "删除响应结果")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseResultMock(@NotNull String id){
        responseResultMockService.deleteResponseResultMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseResultMock",method = RequestMethod.POST)
//    @ApiMethod(name = "findResponseResultMock",desc = "根据id查找响应结果")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseResultMock> findResponseResultMock(@NotNull String id){
        ResponseResultMock responseResultMock = responseResultMockService.findResponseResultMock(id);

        return Result.ok(responseResultMock);
    }

    @RequestMapping(path="/findAllResponseResultMock",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllResponseResultMock",desc = "查找所有响应结果")
    public Result<List<ResponseResultMock>> findAllResponseResultMock(){
        List<ResponseResultMock> responseResultMockList = responseResultMockService.findAllResponseResultMock();

        return Result.ok(responseResultMockList);
    }


    @RequestMapping(path = "/findResponseResultMockList",method = RequestMethod.POST)
//    @ApiMethod(name = "findResponseResultMockList",desc = "根据查询参数查找响应结果")
//    @ApiParam(name = "responseResultMockQuery",desc = "responseResultMockQuery",required = true)
    public Result<List<ResponseResultMock>> findResponseResultMockList(@RequestBody @Valid @NotNull ResponseResultMockQuery responseResultMockQuery){
        List<ResponseResultMock> responseResultMockList = responseResultMockService.findResponseResultMockList(responseResultMockQuery);

        return Result.ok(responseResultMockList);
    }


    @RequestMapping(path = "/findResponseResultMockPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findResponseResultMockPage",desc = "根据查询参数按分页查找响应结果")
//    @ApiParam(name = "responseResultMockQuery",desc = "responseResultMockQuery",required = true)
    public Result<Pagination<ResponseResultMock>> findResponseResultMockPage(@RequestBody @Valid @NotNull ResponseResultMockQuery responseResultMockQuery){
        Pagination<ResponseResultMock> pagination = responseResultMockService.findResponseResultMockPage(responseResultMockQuery);

        return Result.ok(pagination);
    }

}
