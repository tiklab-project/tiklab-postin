package io.tiklab.postin.autotest.http.perf.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfTestData;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfTestDataQuery;
import io.tiklab.postin.autotest.http.perf.cases.service.ApiPerfTestDataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 接口性能测试数据 控制器
 */
@RestController
@RequestMapping("/apiPerfTestData")
//@Api(name = "ApiPerfTestDataController",desc = "api性能测试数据管理")
public class ApiPerfTestDataController {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfTestDataController.class);

    @Autowired
    ApiPerfTestDataService apiPerfTestDataService;

    @RequestMapping(path = "/createApiPerfTestData", method = RequestMethod.POST)
//    @ApiMethod(name = "createApiPerfTestData", desc = "创建性能测试数据")
//    @ApiParam(name = "apiPerfTestData", desc = "apiPerfTestData", required = true)
    public Result<String> createApiPerfTestData(@RequestBody @NotNull @Valid ApiPerfTestData apiPerfTestData) {
        String id = apiPerfTestDataService.createApiPerfTestData(apiPerfTestData);

        return Result.ok(id);
    }

    @RequestMapping(path = "/updateApiPerfTestData", method = RequestMethod.POST)
//    @ApiMethod(name = "updateApiPerfTestData", desc = "修改性能测试数据")
//    @ApiParam(name = "apiPerfTestData", desc = "apiPerfTestData", required = true)
    public Result<Void> updateApiPerfTestData(@RequestBody @NotNull @Valid ApiPerfTestData apiPerfTestData) {
        apiPerfTestDataService.updateApiPerfTestData(apiPerfTestData);

        return Result.ok();
    }

    @RequestMapping(path = "/deleteApiPerfTestData", method = RequestMethod.POST)
//    @ApiMethod(name = "deleteApiPerfTestData", desc = "删除性能测试数据")
//    @ApiParam(name = "id", desc = "id", required = true)
    public Result<Void> deleteApiPerfTestData(@NotNull String id) {
        apiPerfTestDataService.deleteApiPerfTestData(id);

        return Result.ok();
    }

    @RequestMapping(path = "/findApiPerfTestData", method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfTestData", desc = "通过id查询性能测试数据")
//    @ApiParam(name = "id", desc = "id", required = true)
    public Result<ApiPerfTestData> findApiPerfTestData(@NotNull String id) {
        ApiPerfTestData apiPerfTestData = apiPerfTestDataService.findApiPerfTestData(id);

        return Result.ok(apiPerfTestData);
    }

    @RequestMapping(path = "/findApiPerfTestDataByCaseId", method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfTestDataByCaseId", desc = "通过caseId查询测试数据")
//    @ApiParam(name = "caseId", desc = "caseId", required = true)
    public Result<ApiPerfTestData> findApiPerfTestDataByCaseId(@NotNull String caseId) {
        ApiPerfTestData apiPerfTestData = apiPerfTestDataService.findApiPerfTestDataByCaseId(caseId);

        return Result.ok(apiPerfTestData);
    }


    @RequestMapping(path = "/findAllApiPerfTestData", method = RequestMethod.POST)
//    @ApiMethod(name = "findAllApiPerfTestData", desc = "查询所有性能测试数据")
    public Result<List<ApiPerfTestData>> findAllApiPerfTestData() {
        List<ApiPerfTestData> apiPerfTestDataList = apiPerfTestDataService.findAllApiPerfTestData();

        return Result.ok(apiPerfTestDataList);
    }

    @RequestMapping(path = "/findApiPerfTestDataList", method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfTestDataList", desc = "通过查询对象查询性能测试数据")
//    @ApiParam(name = "apiPerfTestDataQuery", desc = "apiPerfTestDataQuery", required = true)
    public Result<List<ApiPerfTestData>> findApiPerfTestDataList(@RequestBody @Valid @NotNull ApiPerfTestDataQuery apiPerfTestDataQuery) {
        List<ApiPerfTestData> apiPerfTestDataList = apiPerfTestDataService.findApiPerfTestDataList(apiPerfTestDataQuery);

        return Result.ok(apiPerfTestDataList);
    }

    @RequestMapping(path = "/findApiPerfTestDataPage", method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfTestDataPage", desc = "通过查询对象分页查询性能测试数据")
//    @ApiParam(name = "apiPerfTestDataQuery", desc = "apiPerfTestDataQuery", required = true)
    public Result<Pagination<ApiPerfTestData>> findApiPerfTestDataPage(@RequestBody @Valid @NotNull ApiPerfTestDataQuery apiPerfTestDataQuery) {
        Pagination<ApiPerfTestData> pagination = apiPerfTestDataService.findApiPerfTestDataPage(apiPerfTestDataQuery);

        return Result.ok(pagination);
    }
    


}
