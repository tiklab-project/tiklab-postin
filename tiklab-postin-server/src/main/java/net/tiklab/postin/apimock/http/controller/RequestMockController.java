package net.tiklab.postin.apimock.http.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.apimock.http.model.RequestMock;
import net.tiklab.postin.apimock.http.model.RequestMockQuery;
import net.tiklab.postin.apimock.http.service.RequestMockService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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

@RestController
@RequestMapping("/requestMock")
@Api(name = "RequestMockController",desc = "接口mock-请求体管理")
public class RequestMockController {

    private static Logger logger = LoggerFactory.getLogger(RequestMockController.class);

    @Autowired
    private RequestMockService requestMockService;

    @RequestMapping(path="/createRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestMock",desc = "createRequestMock")
    @ApiParam(name = "RequestMock",desc = "RequestMock",required = true)
    public Result<String> createRequestMock(@RequestBody @NotNull @Valid RequestMock requestMock){
        String id = requestMockService.createRequestMock(requestMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestMock",desc = "updateRequestMock")
    @ApiParam(name = "RequestMock",desc = "RequestMock",required = true)
    public Result<Void> updateRequestMock(@RequestBody @NotNull @Valid RequestMock requestMock){
        requestMockService.updateRequestMock(requestMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestMock",desc = "deleteRequestMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestMock(@NotNull String id){
        requestMockService.deleteRequestMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestMock",desc = "findRequestMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestMock> findRequestMock(@NotNull String id){
        RequestMock requestMock = requestMockService.findRequestMock(id);

        return Result.ok(requestMock);
    }

    @RequestMapping(path="/findAllRequestMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestMock",desc = "findAllRequestMock")
    public Result<List<RequestMock>> findAllRequestMock(){
        List<RequestMock> requestMockList = requestMockService.findAllRequestMock();

        return Result.ok(requestMockList);
    }


    @RequestMapping(path = "/findRequestMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestMockList",desc = "findRequestMockList")
    @ApiParam(name = "RequestMockQuery",desc = "RequestMockQuery",required = true)
    public Result<List<RequestMock>> findRequestMockList(@RequestBody @Valid @NotNull RequestMockQuery requestMockQuery){
        List<RequestMock> requestMockList = requestMockService.findRequestMockList(requestMockQuery);

        return Result.ok(requestMockList);
    }


    @RequestMapping(path = "/findRequestMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestMockPage",desc = "findRequestMockPage")
    @ApiParam(name = "RequestMockQuery",desc = "RequestMockQuery",required = true)
    public Result<Pagination<RequestMock>> findRequestMockPage(@RequestBody @Valid @NotNull RequestMockQuery requestMockQuery){
        Pagination<RequestMock> pagination = requestMockService.findRequestMockPage(requestMockQuery);

        return Result.ok(pagination);
    }

}
