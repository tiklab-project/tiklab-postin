package io.tiklab.postin.api.http.mock.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.mock.model.RequestHeaderMock;
import io.tiklab.postin.api.http.mock.model.RequestHeaderMockQuery;
import io.tiklab.postin.api.http.mock.service.RequestHeaderMockService;
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
 * 请求头 控制器
 */
@RestController
@RequestMapping("/requestHeaderMock")
//@Api(name = "RequestHeaderMockController",desc = "接口mock-请求头部管理")
public class RequestHeaderMockController {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderMockController.class);

    @Autowired
    private RequestHeaderMockService requestHeaderMockService;

    @RequestMapping(path="/createRequestHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "createRequestHeaderMock",desc = "创建请求头")
//    @ApiParam(name = "requestHeaderMock",desc = "requestHeaderMock",required = true)
    public Result<String> createRequestHeaderMock(@RequestBody @NotNull @Valid RequestHeaderMock requestHeaderMock){
        String id = requestHeaderMockService.createRequestHeaderMock(requestHeaderMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "updateRequestHeaderMock",desc = "更新请求头")
//    @ApiParam(name = "requestHeaderMock",desc = "requestHeaderMock",required = true)
    public Result<Void> updateRequestHeaderMock(@RequestBody @NotNull @Valid RequestHeaderMock requestHeaderMock){
        requestHeaderMockService.updateRequestHeaderMock(requestHeaderMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteRequestHeaderMock",desc = "删除请求头")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestHeaderMock(@NotNull String id){
        requestHeaderMockService.deleteRequestHeaderMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "findRequestHeaderMock",desc = "根据id查找请求头")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestHeaderMock> findRequestHeaderMock(@NotNull String id){
        RequestHeaderMock requestHeaderMock = requestHeaderMockService.findRequestHeaderMock(id);

        return Result.ok(requestHeaderMock);
    }

    @RequestMapping(path="/findAllRequestHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllRequestHeaderMock",desc = "查找所有请求头")
    public Result<List<RequestHeaderMock>> findAllRequestHeaderMock(){
        List<RequestHeaderMock> requestHeaderMockList = requestHeaderMockService.findAllRequestHeaderMock();

        return Result.ok(requestHeaderMockList);
    }


    @RequestMapping(path = "/findRequestHeaderMockList",method = RequestMethod.POST)
//    @ApiMethod(name = "findRequestHeaderMockList",desc = "根据查询参数查找请求头")
//    @ApiParam(name = "requestHeaderMockQuery",desc = "requestHeaderMockQuery",required = true)
    public Result<List<RequestHeaderMock>> findRequestHeaderMockList(@RequestBody @Valid @NotNull RequestHeaderMockQuery requestHeaderMockQuery){
        List<RequestHeaderMock> requestHeaderMockList = requestHeaderMockService.findRequestHeaderMockList(requestHeaderMockQuery);

        return Result.ok(requestHeaderMockList);
    }


    @RequestMapping(path = "/findRequestHeaderMockPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findRequestHeaderMockPage",desc = "根据查询参数按分页查找请求头")
//    @ApiParam(name = "requestHeaderMockQuery",desc = "requestHeaderMockQuery",required = true)
    public Result<Pagination<RequestHeaderMock>> findRequestHeaderMockPage(@RequestBody @Valid @NotNull RequestHeaderMockQuery requestHeaderMockQuery){
        Pagination<RequestHeaderMock> pagination = requestHeaderMockService.findRequestHeaderMockPage(requestHeaderMockQuery);

        return Result.ok(pagination);
    }

}
