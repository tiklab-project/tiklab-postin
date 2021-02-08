package com.darthcloud.apibox.api.apimock.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.api.apimock.model.ResponseHeaderMock;
import com.darthcloud.apibox.api.apimock.model.ResponseHeaderMockQuery;
import com.darthcloud.apibox.api.apimock.service.ResponseHeaderMockService;
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
@RequestMapping("/responseHeaderMock")
@Api(name = "ResponseHeaderMockController",desc = "响应头部mock管理")
public class ResponseHeaderMockController {

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderMockController.class);

    @Autowired
    private ResponseHeaderMockService responseHeaderMockService;

    @RequestMapping(path="/createResponseHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseHeaderMock",desc = "createResponseHeaderMock")
    @ApiParam(name = "responseHeaderMock",desc = "responseHeaderMock",required = true)
    public Result<String> createResponseHeaderMock(@RequestBody @NotNull @Valid ResponseHeaderMock responseHeaderMock){
        String id = responseHeaderMockService.createResponseHeaderMock(responseHeaderMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseHeaderMock",desc = "updateResponseHeaderMock")
    @ApiParam(name = "responseHeaderMock",desc = "responseHeaderMock",required = true)
    public Result<Void> updateResponseHeaderMock(@RequestBody @NotNull @Valid ResponseHeaderMock responseHeaderMock){
        responseHeaderMockService.updateResponseHeaderMock(responseHeaderMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseHeaderMock",desc = "deleteResponseHeaderMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseHeaderMock(@NotNull String id){
        responseHeaderMockService.deleteResponseHeaderMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderMock",desc = "findResponseHeaderMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseHeaderMock> findResponseHeaderMock(@NotNull String id){
        ResponseHeaderMock responseHeaderMock = responseHeaderMockService.findResponseHeaderMock(id);

        return Result.ok(responseHeaderMock);
    }

    @RequestMapping(path="/findAllResponseHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseHeaderMock",desc = "findAllResponseHeaderMock")
    public Result<List<ResponseHeaderMock>> findAllResponseHeaderMock(){
        List<ResponseHeaderMock> responseHeaderMockList = responseHeaderMockService.findAllResponseHeaderMock();

        return Result.ok(responseHeaderMockList);
    }

    @Validator
    @RequestMapping(path = "/findResponseHeaderMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderMockList",desc = "findResponseHeaderMockList")
    @ApiParam(name = "responseHeaderMockQuery",desc = "responseHeaderMockQuery",required = true)
    public Result<List<ResponseHeaderMock>> findResponseHeaderMockList(@RequestBody @Valid @NotNull ResponseHeaderMockQuery responseHeaderMockQuery){
        List<ResponseHeaderMock> responseHeaderMockList = responseHeaderMockService.findResponseHeaderMockList(responseHeaderMockQuery);

        return Result.ok(responseHeaderMockList);
    }

    @Validator
    @RequestMapping(path = "/findResponseHeaderMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderMockPage",desc = "findResponseHeaderMockPage")
    @ApiParam(name = "responseHeaderMockQuery",desc = "responseHeaderMockQuery",required = true)
    public Result<Pagination<List<ResponseHeaderMock>>> findResponseHeaderMockPage(@RequestBody @Valid @NotNull ResponseHeaderMockQuery responseHeaderMockQuery){
        Pagination<List<ResponseHeaderMock>> pagination = responseHeaderMockService.findResponseHeaderMockPage(responseHeaderMockQuery);

        return Result.ok(pagination);
    }

}
