package io.tiklab.postin.autotest.http.perf.cases.controller;

import io.tiklab.postin.autotest.test.model.TestCaseQuery;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfCase;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfCaseQuery;
import io.tiklab.postin.autotest.http.perf.cases.service.ApiPerfCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 接口性能 控制器
 */
@RestController
@RequestMapping("/apiPerfCase")
//@Api(name = "ApiPerfCaseController",desc = "api性能测试用例管理")
public class ApiPerfCaseController {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfCaseController.class);

    @Autowired
    ApiPerfCaseService apiPerfCaseService;

    @RequestMapping(path = "/createApiPerfCase", method = RequestMethod.POST)
//    @ApiMethod(name = "createApiPerfCase", desc = "创建性能测试")
//    @ApiParam(name = "apiPerfCase", desc = "apiPerfCase", required = true)
    public Result<String> createApiPerfCase(@RequestBody @NotNull @Valid ApiPerfCase apiPerfCase) {
        String id = apiPerfCaseService.createApiPerfCase(apiPerfCase);

        return Result.ok(id);
    }

    @RequestMapping(path = "/updateApiPerfCase", method = RequestMethod.POST)
//    @ApiMethod(name = "updateApiPerfCase", desc = "修改性能测试")
//    @ApiParam(name = "apiPerfCase", desc = "apiPerfCase", required = true)
    public Result<Void> updateApiPerfCase(@RequestBody @NotNull @Valid ApiPerfCase apiPerfCase) {
        apiPerfCaseService.updateApiPerfCase(apiPerfCase);

        return Result.ok();
    }

    @RequestMapping(path = "/deleteApiPerfCase", method = RequestMethod.POST)
//    @ApiMethod(name = "deleteApiPerfCase", desc = "删除性能测试")
//    @ApiParam(name = "id", desc = "id", required = true)
    public Result<Void> deleteApiPerfCase(@NotNull String id) {
        apiPerfCaseService.deleteApiPerfCase(id);

        return Result.ok();
    }

    @RequestMapping(path = "/findApiPerfCase", method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfCase", desc = "通过id查询性能测试")
//    @ApiParam(name = "id", desc = "id", required = true)
    public Result<ApiPerfCase> findApiPerfCase(@NotNull String id) {
        ApiPerfCase apiPerfCase = apiPerfCaseService.findApiPerfCase(id);

        return Result.ok(apiPerfCase);
    }

    @RequestMapping(path = "/findAllApiPerfCase", method = RequestMethod.POST)
//    @ApiMethod(name = "findAllApiPerfCase", desc = "查询所有性能测试")
    public Result<List<ApiPerfCase>> findAllApiPerfCase() {
        List<ApiPerfCase> apiPerfCaseList = apiPerfCaseService.findAllApiPerfCase();

        return Result.ok(apiPerfCaseList);
    }

    @RequestMapping(path = "/findApiPerfCaseList", method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfCaseList", desc = "通过查询对象查询性能测试")
//    @ApiParam(name = "apiPerfCaseQuery", desc = "apiPerfCaseQuery", required = true)
    public Result<List<ApiPerfCase>> findApiPerfCaseList(@RequestBody @Valid @NotNull ApiPerfCaseQuery apiPerfCaseQuery) {
        List<ApiPerfCase> apiPerfCaseList = apiPerfCaseService.findApiPerfCaseList(apiPerfCaseQuery);

        return Result.ok(apiPerfCaseList);
    }

    @RequestMapping(path = "/findApiPerfCasePage", method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfCasePage", desc = "通过查询对象分页查询性能测试")
//    @ApiParam(name = "apiPerfCaseQuery", desc = "apiPerfCaseQuery", required = true)
    public Result<Pagination<ApiPerfCase>> findApiPerfCasePage(@RequestBody @Valid @NotNull ApiPerfCaseQuery apiPerfCaseQuery) {
        Pagination<ApiPerfCase> pagination = apiPerfCaseService.findApiPerfCasePage(apiPerfCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findApiPerfCaseListByTestCase", method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfCaseListByTestCase", desc = "通过用例查询对象查询性能测试")
//    @ApiParam(name = "testCaseQuery", desc = "testCaseQuery", required = true)
    public Result<List<ApiPerfCase>> findApiPerfCaseListByTestCase(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery) {
        List<ApiPerfCase> apiPerfCaseList = apiPerfCaseService.findApiPerfCaseListByTestCase(testCaseQuery);

        return Result.ok(apiPerfCaseList);
    }

}
