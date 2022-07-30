package com.tiklab.postin.apimock.http.controller;

import com.tiklab.core.Result;
import com.tiklab.core.page.Pagination;
import com.tiklab.postin.annotation.Api;
import com.tiklab.postin.apimock.http.model.QueryParamMock;
import com.tiklab.postin.apimock.http.model.QueryParamMockQuery;
import com.tiklab.postin.apimock.http.service.QueryParamMockService;
import com.tiklab.postin.annotation.ApiMethod;
import com.tiklab.postin.annotation.ApiParam;
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
@RequestMapping("/queryParamMock")
@Api(name = "QueryParamMockController",desc = "接口mock-查询参数管理")
public class QueryParamMockController {

    private static Logger logger = LoggerFactory.getLogger(QueryParamMockController.class);

    @Autowired
    private QueryParamMockService queryParamMockService;

    @RequestMapping(path="/createQueryParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "createQueryParamMock",desc = "createQueryParamMock")
    @ApiParam(name = "queryParamMock",desc = "queryParamMock",required = true)
    public Result<String> createQueryParamMock(@RequestBody @NotNull @Valid QueryParamMock queryParamMock){
        String id = queryParamMockService.createQueryParamMock(queryParamMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQueryParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateQueryParamMock",desc = "updateQueryParamMock")
    @ApiParam(name = "queryParamMock",desc = "queryParamMock",required = true)
    public Result<Void> updateQueryParamMock(@RequestBody @NotNull @Valid QueryParamMock queryParamMock){
        queryParamMockService.updateQueryParamMock(queryParamMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteQueryParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteQueryParamMock",desc = "deleteQueryParamMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteQueryParamMock(@NotNull String id){
        queryParamMockService.deleteQueryParamMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findQueryParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamMock",desc = "findQueryParamMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<QueryParamMock> findQueryParamMock(@NotNull String id){
        QueryParamMock queryParamMock = queryParamMockService.findQueryParamMock(id);

        return Result.ok(queryParamMock);
    }

    @RequestMapping(path="/findAllQueryParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllQueryParamMock",desc = "findAllQueryParamMock")
    public Result<List<QueryParamMock>> findAllQueryParamMock(){
        List<QueryParamMock> queryParamMockList = queryParamMockService.findAllQueryParamMock();

        return Result.ok(queryParamMockList);
    }


    @RequestMapping(path = "/findQueryParamMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamMockList",desc = "findQueryParamMockList")
    @ApiParam(name = "queryParamMockQuery",desc = "queryParamMockQuery",required = true)
    public Result<List<QueryParamMock>> findQueryParamMockList(@RequestBody @Valid @NotNull QueryParamMockQuery queryParamMockQuery){
        List<QueryParamMock> queryParamMockList = queryParamMockService.findQueryParamMockList(queryParamMockQuery);

        return Result.ok(queryParamMockList);
    }


    @RequestMapping(path = "/findQueryParamMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamMockPage",desc = "findQueryParamMockPage")
    @ApiParam(name = "queryParamMockQuery",desc = "queryParamMockQuery",required = true)
    public Result<Pagination<QueryParamMock>> findQueryParamMockPage(@RequestBody @Valid @NotNull QueryParamMockQuery queryParamMockQuery){
        Pagination<QueryParamMock> pagination = queryParamMockService.findQueryParamMockPage(queryParamMockQuery);

        return Result.ok(pagination);
    }

}
