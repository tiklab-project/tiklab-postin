package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCaseQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.ApiUnitCaseService;
import io.tiklab.postin.autotest.test.model.TestCaseQuery;
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
 * 接口单元用例 控制器
 */
@RestController
@RequestMapping("/apiUnitCase")
//@Api(name = "ApiUnitCaseController",desc = "api单元测试用例管理")
public class ApiUnitCaseController {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitCaseController.class);

    @Autowired
    private ApiUnitCaseService apiUnitCaseService;

    @RequestMapping(path="/createApiUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "createApiUnitCase",desc = "创建接口单元用例")
//    @ApiParam(name = "apiUnitCase",desc = "apiUnitCase",required = true)
    public Result<String> createApiUnitCase(@RequestBody @NotNull @Valid ApiUnitCase apiUnitCase){
        String id = apiUnitCaseService.createApiUnitCase(apiUnitCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "updateApiUnitCase",desc = "更新接口单元")
//    @ApiParam(name = "apiUnitCase",desc = "apiUnitCase",required = true)
    public Result<Void> updateApiUnitCase(@RequestBody @NotNull @Valid ApiUnitCase apiUnitCase){
        apiUnitCaseService.updateApiUnitCase(apiUnitCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteApiUnitCase",desc = "删除接口单元")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiUnitCase(@NotNull String id){
        apiUnitCaseService.deleteApiUnitCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/isApiUnitBind",method = RequestMethod.POST)
//    @ApiMethod(name = "isApiUnitBind",desc = "判断接口单元用例是否被绑定")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Boolean> isApiUnitBind(@NotNull String id){
        Boolean apiUnitBind = apiUnitCaseService.isApiUnitBind(id);

        return Result.ok(apiUnitBind);
    }



    @RequestMapping(path="/findApiUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiUnitCase",desc = "通过id查找接口单元")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiUnitCase> findApiUnitCase(@NotNull String id){
        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(id);

        return Result.ok(apiUnitCase);
    }

    @RequestMapping(path="/findAllApiUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllApiUnitCase",desc = "查找所有接口单元")
    public Result<List<ApiUnitCase>> findAllApiUnitCase(){
        List<ApiUnitCase> apiUnitCaseList = apiUnitCaseService.findAllApiUnitCase();

        return Result.ok(apiUnitCaseList);
    }

    @RequestMapping(path = "/findApiUnitCaseList",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiUnitCaseList",desc = "根据查询参数查询接口单元列表")
//    @ApiParam(name = "apiUnitCaseQuery",desc = "apiUnitCaseQuery",required = true)
    public Result<List<ApiUnitCase>> findApiUnitCaseList(@RequestBody @Valid @NotNull ApiUnitCaseQuery apiUnitCaseQuery ){
        List<ApiUnitCase> apiUnitCaseList = apiUnitCaseService.findApiUnitCaseList(apiUnitCaseQuery);

        return Result.ok(apiUnitCaseList);
    }


    @RequestMapping(path = "/findApiUnitCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitCasePage",desc = "根据查询参数按分页查询接口单元")
//    @ApiParam(name = "apiUnitCaseQuery",desc = "apiUnitCaseQuery",required = true)
    public Result<Pagination<ApiUnitCase>> findApiUnitCasePage(@RequestBody @Valid @NotNull ApiUnitCaseQuery apiUnitCaseQuery){
        Pagination<ApiUnitCase> pagination = apiUnitCaseService.findApiUnitCasePage(apiUnitCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findApiUnitCaseListByTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitCaseListByTestCase",desc = "通过testCaseQuery查询接口单元")
//    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<ApiUnitCase>> findApiUnitCaseListByTestCase(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery ){
        List<ApiUnitCase> apiUnitCaseList = apiUnitCaseService.findApiUnitCaseListByTestCase(testCaseQuery);

        return Result.ok(apiUnitCaseList);
    }



}
