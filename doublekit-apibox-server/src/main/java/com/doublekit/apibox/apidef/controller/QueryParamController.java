package com.doublekit.apibox.apidef.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apidef.model.QueryParam;
import com.doublekit.apibox.apidef.model.QueryParamQuery;
import com.doublekit.apibox.apidef.service.QueryParamService;
import com.doublekit.common.Pagination;
import com.doublekit.common.Result;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
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
@RequestMapping("/queryParam")
@Api(name = "QueryParamController",desc = "查询参数管理")
public class QueryParamController {

    private static Logger logger = LoggerFactory.getLogger(QueryParamController.class);

    @Autowired
    private QueryParamService queryParamService;

    @RequestMapping(path="/createQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "createQueryParam",desc = "创建查询参数")
    @ApiParam(name = "queryParam",desc = "查询参数DTO",required = true)
    public Result<String> createQueryParam(@RequestBody @NotNull @Valid QueryParam queryParam){
        String id = queryParamService.createQueryParam(queryParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateQueryParam",desc = "更新查询参数")
    @ApiParam(name = "queryParam",desc = "查询参数DTO",required = true)
    public Result<Void> updateQueryParam(@RequestBody @NotNull @Valid QueryParam queryParam){
        queryParamService.updateQueryParam(queryParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteQueryParam",desc = "根据ID删除查询参数")
    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteQueryParam(@NotNull String id){
        queryParamService.deleteQueryParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParam",desc = "根据ID查找查询参数")
    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<QueryParam> findQueryParam(@NotNull String id){
        QueryParam queryParam = queryParamService.findQueryParam(id);

        return Result.ok(queryParam);
    }

    @RequestMapping(path="/findAllQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllQueryParam",desc = "查找所有查询参数列表")
    public Result<List<QueryParam>> findAllQueryParam(){
        List<QueryParam> queryParamList = queryParamService.findAllQueryParam();

        return Result.ok(queryParamList);
    }


    @RequestMapping(path = "/findQueryParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamList",desc = "根据查询对象查找查询参数列表")
    @ApiParam(name = "queryParamQuery",desc = "查找条件",required = true)
    public Result<List<QueryParam>> findQueryParamList(@RequestBody @Valid @NotNull QueryParamQuery queryParamQuery){
        List<QueryParam> queryParamList = queryParamService.findQueryParamList(queryParamQuery);

        return Result.ok(queryParamList);
    }


    @RequestMapping(path = "/findQueryParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamPage",desc = "根据查询对象按分页查找查询参数列表")
    @ApiParam(name = "queryParamQuery",desc = "查找条件",required = true)
    public Result<Pagination<QueryParam>> findQueryParamPage(@RequestBody @Valid @NotNull QueryParamQuery queryParamQuery){
        Pagination<QueryParam> pagination = queryParamService.findQueryParamPage(queryParamQuery);

        return Result.ok(pagination);
    }

}
