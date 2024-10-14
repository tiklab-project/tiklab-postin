package io.tiklab.postin.api.http.test.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.api.http.test.cases.model.RequestCase;
import io.tiklab.postin.api.http.test.cases.model.RequestCaseQuery;
import io.tiklab.postin.api.http.test.cases.service.RequestCaseService;
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
 * RequestCaseController
 */
@RestController
@RequestMapping("/requestCase")
@Api(name = "RequestCaseController",desc = "RequestCaseController")
public class RequestCaseController {

    private static Logger logger = LoggerFactory.getLogger(RequestCaseController.class);

    @Autowired
    private RequestCaseService requestCaseService;

    @RequestMapping(path="/createRequestCase",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestCase",desc = "createRequestCase")
    @ApiParam(name = "requestCase",desc = "requestCase",required = true)
    public Result<String> createRequestCase(@RequestBody @NotNull @Valid RequestCase requestCase){
        String id = requestCaseService.createRequestCase(requestCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestCase",desc = "updateRequestCase")
    @ApiParam(name = "requestCase",desc = "requestCase",required = true)
    public Result<Void> updateRequestCase(@RequestBody @NotNull @Valid RequestCase requestCase){
        requestCaseService.updateRequestCase(requestCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestCase",desc = "deleteRequestCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestCase(@NotNull String id){
        requestCaseService.deleteRequestCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestCase",desc = "findRequestCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestCase> findRequestCase(@NotNull String id){
        RequestCase requestCase = requestCaseService.findRequestCase(id);

        return Result.ok(requestCase);
    }

    @RequestMapping(path="/findAllRequestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestCase",desc = "findAllRequestCase")
    public Result<List<RequestCase>> findAllRequestCase(){
        List<RequestCase> requestCaseList = requestCaseService.findAllRequestCase();

        return Result.ok(requestCaseList);
    }

    @RequestMapping(path = "/findRequestCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestCaseList",desc = "findRequestCaseList")
    @ApiParam(name = "requestCaseQuery",desc = "requestCaseQuery",required = true)
    public Result<List<RequestCase>> findRequestCaseList(@RequestBody @Valid @NotNull RequestCaseQuery requestCaseQuery){
        List<RequestCase> requestCaseList = requestCaseService.findRequestCaseList(requestCaseQuery);

        return Result.ok(requestCaseList);
    }

    @RequestMapping(path = "/findRequestCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestCasePage",desc = "findRequestCasePage")
    @ApiParam(name = "requestCaseQuery",desc = "requestCaseQuery",required = true)
    public Result<Pagination<RequestCase>> findRequestCasePage(@RequestBody @Valid @NotNull RequestCaseQuery requestCaseQuery){
        Pagination<RequestCase> pagination = requestCaseService.findRequestCasePage(requestCaseQuery);

        return Result.ok(pagination);
    }

}
