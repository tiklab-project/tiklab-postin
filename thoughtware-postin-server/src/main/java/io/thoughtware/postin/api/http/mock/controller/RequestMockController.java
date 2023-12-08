package io.thoughtware.postin.api.http.mock.controller;

import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.api.http.mock.model.RequestMock;
import io.thoughtware.postin.api.http.mock.model.RequestMockQuery;
import io.thoughtware.postin.api.http.mock.service.RequestMockService;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * 请求体 控制器
 */
@RestController
@RequestMapping("/requestMock")
@Api(name = "RequestMockController",desc = "接口mock-请求体管理")
public class RequestMockController {

    private static Logger logger = LoggerFactory.getLogger(RequestMockController.class);

    @Autowired
    private RequestMockService requestMockService;

    @RequestMapping(path="/createRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestMock",desc = "创建请求体")
    @ApiParam(name = "requestMock",desc = "requestMock",required = true)
    public Result<String> createRequestMock(@RequestBody @NotNull @Valid RequestMock requestMock){
        String id = requestMockService.createRequestMock(requestMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestMock",desc = "更新请求体")
    @ApiParam(name = "requestMock",desc = "requestMock",required = true)
    public Result<Void> updateRequestMock(@RequestBody @NotNull @Valid RequestMock requestMock){
        requestMockService.updateRequestMock(requestMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestMock",desc = "删除请求体")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestMock(@NotNull String id){
        requestMockService.deleteRequestMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestMock",desc = "通过id查询请求体")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestMock> findRequestMock(@NotNull String id){
        RequestMock requestMock = requestMockService.findRequestMock(id);

        return Result.ok(requestMock);
    }

    @RequestMapping(path="/findAllRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestMock",desc = "查询所有请求体")
    public Result<List<RequestMock>> findAllRequestMock(){
        List<RequestMock> requestMockList = requestMockService.findAllRequestMock();

        return Result.ok(requestMockList);
    }


    @RequestMapping(path = "/findRequestMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestMockList",desc = "根据查询参数查找请求体")
    @ApiParam(name = "requestMockQuery",desc = "requestMockQuery",required = true)
    public Result<List<RequestMock>> findRequestMockList(@RequestBody @Valid @NotNull RequestMockQuery requestMockQuery){
        List<RequestMock> requestMockList = requestMockService.findRequestMockList(requestMockQuery);

        return Result.ok(requestMockList);
    }


    @RequestMapping(path = "/findRequestMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestMockPage",desc = "根据查询参数按分页查找请求体")
    @ApiParam(name = "requestMockQuery",desc = "requestMockQuery",required = true)
    public Result<Pagination<RequestMock>> findRequestMockPage(@RequestBody @Valid @NotNull RequestMockQuery requestMockQuery){
        Pagination<RequestMock> pagination = requestMockService.findRequestMockPage(requestMockQuery);

        return Result.ok(pagination);
    }

}
