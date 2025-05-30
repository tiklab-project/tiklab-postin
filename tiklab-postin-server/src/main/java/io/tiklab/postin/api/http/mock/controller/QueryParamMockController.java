package io.tiklab.postin.api.http.mock.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.mock.model.QueryParamMock;
import io.tiklab.postin.api.http.mock.model.QueryParamMockQuery;
import io.tiklab.postin.api.http.mock.service.QueryParamMockService;
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
 * query查询参数 控制器
 */
@RestController
@RequestMapping("/queryParamMock")
//@Api(name = "QueryParamMockController",desc = "接口mock-查询参数管理")
public class QueryParamMockController {

    private static Logger logger = LoggerFactory.getLogger(QueryParamMockController.class);

    @Autowired
    private QueryParamMockService queryParamMockService;

    @RequestMapping(path="/createQueryParamMock",method = RequestMethod.POST)
//    @ApiMethod(name = "createQueryParamMock",desc = "创建query参数")
//    @ApiParam(name = "queryParamMock",desc = "queryParamMock",required = true)
    public Result<String> createQueryParamMock(@RequestBody @NotNull @Valid QueryParamMock queryParamMock){
        String id = queryParamMockService.createQueryParamMock(queryParamMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQueryParamMock",method = RequestMethod.POST)
//    @ApiMethod(name = "updateQueryParamMock",desc = "更新query参数")
//    @ApiParam(name = "queryParamMock",desc = "queryParamMock",required = true)
    public Result<Void> updateQueryParamMock(@RequestBody @NotNull @Valid QueryParamMock queryParamMock){
        queryParamMockService.updateQueryParamMock(queryParamMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteQueryParamMock",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteQueryParamMock",desc = "删除query参数")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteQueryParamMock(@NotNull String id){
        queryParamMockService.deleteQueryParamMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findQueryParamMock",method = RequestMethod.POST)
//    @ApiMethod(name = "findQueryParamMock",desc = "通过Id查询query参数")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<QueryParamMock> findQueryParamMock(@NotNull String id){
        QueryParamMock queryParamMock = queryParamMockService.findQueryParamMock(id);

        return Result.ok(queryParamMock);
    }

    @RequestMapping(path="/findAllQueryParamMock",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllQueryParamMock",desc = "查找所有query参数")
    public Result<List<QueryParamMock>> findAllQueryParamMock(){
        List<QueryParamMock> queryParamMockList = queryParamMockService.findAllQueryParamMock();

        return Result.ok(queryParamMockList);
    }


    @RequestMapping(path = "/findQueryParamMockList",method = RequestMethod.POST)
//    @ApiMethod(name = "findQueryParamMockList",desc = "通过查询参数查找query列表")
//    @ApiParam(name = "queryParamMockQuery",desc = "queryParamMockQuery",required = true)
    public Result<List<QueryParamMock>> findQueryParamMockList(@RequestBody @Valid @NotNull QueryParamMockQuery queryParamMockQuery){
        List<QueryParamMock> queryParamMockList = queryParamMockService.findQueryParamMockList(queryParamMockQuery);

        return Result.ok(queryParamMockList);
    }


    @RequestMapping(path = "/findQueryParamMockPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findQueryParamMockPage",desc = "根据查询参数按分页查找query列表")
//    @ApiParam(name = "queryParamMockQuery",desc = "queryParamMockQuery",required = true)
    public Result<Pagination<QueryParamMock>> findQueryParamMockPage(@RequestBody @Valid @NotNull QueryParamMockQuery queryParamMockQuery){
        Pagination<QueryParamMock> pagination = queryParamMockService.findQueryParamMockPage(queryParamMockQuery);

        return Result.ok(pagination);
    }

}
