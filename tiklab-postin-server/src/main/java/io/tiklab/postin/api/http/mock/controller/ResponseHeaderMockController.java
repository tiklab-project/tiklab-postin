package io.tiklab.postin.api.http.mock.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.mock.model.ResponseHeaderMock;
import io.tiklab.postin.api.http.mock.model.ResponseHeaderMockQuery;
import io.tiklab.postin.api.http.mock.service.ResponseHeaderMockService;
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
 * 响应头 控制器
 */
@RestController
@RequestMapping("/responseHeaderMock")
//@Api(name = "ResponseHeaderMockController",desc = "接口mock-响应头部管理")
public class ResponseHeaderMockController {

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderMockController.class);

    @Autowired
    private ResponseHeaderMockService responseHeaderMockService;

    @RequestMapping(path="/createResponseHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "createResponseHeaderMock",desc = "创建响应头")
//    @ApiParam(name = "responseHeaderMock",desc = "responseHeaderMock",required = true)
    public Result<String> createResponseHeaderMock(@RequestBody @NotNull @Valid ResponseHeaderMock responseHeaderMock){
        String id = responseHeaderMockService.createResponseHeaderMock(responseHeaderMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "updateResponseHeaderMock",desc = "更新响应头")
//    @ApiParam(name = "responseHeaderMock",desc = "responseHeaderMock",required = true)
    public Result<Void> updateResponseHeaderMock(@RequestBody @NotNull @Valid ResponseHeaderMock responseHeaderMock){
        responseHeaderMockService.updateResponseHeaderMock(responseHeaderMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteResponseHeaderMock",desc = "删除响应头")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseHeaderMock(@NotNull String id){
        responseHeaderMockService.deleteResponseHeaderMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "findResponseHeaderMock",desc = "根据id查询响应头")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseHeaderMock> findResponseHeaderMock(@NotNull String id){
        ResponseHeaderMock responseHeaderMock = responseHeaderMockService.findResponseHeaderMock(id);

        return Result.ok(responseHeaderMock);
    }

    @RequestMapping(path="/findAllResponseHeaderMock",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllResponseHeaderMock",desc = "查询所有响应头")
    public Result<List<ResponseHeaderMock>> findAllResponseHeaderMock(){
        List<ResponseHeaderMock> responseHeaderMockList = responseHeaderMockService.findAllResponseHeaderMock();

        return Result.ok(responseHeaderMockList);
    }


    @RequestMapping(path = "/findResponseHeaderMockList",method = RequestMethod.POST)
//    @ApiMethod(name = "findResponseHeaderMockList",desc = "根据查询参数查找响应头")
//    @ApiParam(name = "responseHeaderMockQuery",desc = "responseHeaderMockQuery",required = true)
    public Result<List<ResponseHeaderMock>> findResponseHeaderMockList(@RequestBody @Valid @NotNull ResponseHeaderMockQuery responseHeaderMockQuery){
        List<ResponseHeaderMock> responseHeaderMockList = responseHeaderMockService.findResponseHeaderMockList(responseHeaderMockQuery);

        return Result.ok(responseHeaderMockList);
    }


    @RequestMapping(path = "/findResponseHeaderMockPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findResponseHeaderMockPage",desc = "根据查询参数按分页查找响应头")
//    @ApiParam(name = "responseHeaderMockQuery",desc = "responseHeaderMockQuery",required = true)
    public Result<Pagination<ResponseHeaderMock>> findResponseHeaderMockPage(@RequestBody @Valid @NotNull ResponseHeaderMockQuery responseHeaderMockQuery){
        Pagination<ResponseHeaderMock> pagination = responseHeaderMockService.findResponseHeaderMockPage(responseHeaderMockQuery);

        return Result.ok(pagination);
    }

}
