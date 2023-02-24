package net.tiklab.postin.apidef.http.test.httpcase.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.apidef.http.test.httpcase.model.HttpTestcase;
import net.tiklab.postin.apidef.http.test.httpcase.model.HttpTestcaseQuery;
import net.tiklab.postin.apidef.http.test.httpcase.service.HttpTestcaseService;
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

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/testcase")
@Api(name = "TestcaseController",desc = "接口用例管理")
public class HttpTestcaseController {

    private static Logger logger = LoggerFactory.getLogger(HttpTestcaseController.class);

    @Autowired
    private HttpTestcaseService httpTestcaseService;

    @RequestMapping(path="/createTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "createTestcase",desc = "创建测试用例")
    @ApiParam(name = "testcase",desc = "testcase",required = true)
    public Result<String> createTestcase(@RequestBody @NotNull @Valid HttpTestcase httpTestcase){
        String id = httpTestcaseService.createTestcase(httpTestcase);

        return Result.ok(id);
    }

    @RequestMapping(path="/createTestcaseWithNest",method = RequestMethod.POST)
    @ApiMethod(name = "createTestcaseWithNest",desc = "创建测试用例，级联保存从、子表数据")
    @ApiParam(name = "testcase",desc = "testcase",required = true)
    public Result<String> createTestcaseWithNest(@RequestBody @NotNull @Valid HttpTestcase httpTestcase){
        String id = httpTestcaseService.createTestcaseWithNest(httpTestcase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestcase",desc = "updateTestcase")
    @ApiParam(name = "testcase",desc = "testcase",required = true)
    public Result<Void> updateTestcase(@RequestBody @NotNull @Valid HttpTestcase httpTestcase){
        httpTestcaseService.updateTestcase(httpTestcase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestcase",desc = "deleteTestcase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestcase(@NotNull String id){
        httpTestcaseService.deleteTestcase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "findTestcase",desc = "findTestcase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<HttpTestcase> findTestcase(@NotNull String id){
        HttpTestcase httpTestcase = httpTestcaseService.findTestcase(id);

        return Result.ok(httpTestcase);
    }

    @RequestMapping(path="/findAllTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestcase",desc = "findAllTestcase")
    public Result<List<HttpTestcase>> findAllTestcase(){
        List<HttpTestcase> httpTestcaseList = httpTestcaseService.findAllTestcase();

        return Result.ok(httpTestcaseList);
    }


    @RequestMapping(path = "/findTestcaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestcaseList",desc = "findTestcaseList")
    @ApiParam(name = "testcaseQuery",desc = "testcaseQuery",required = true)
    public Result<List<HttpTestcase>> findTestcaseList(@RequestBody @Valid @NotNull HttpTestcaseQuery httpTestcaseQuery){
        List<HttpTestcase> httpTestcaseList = httpTestcaseService.findTestcaseList(httpTestcaseQuery);

        return Result.ok(httpTestcaseList);
    }


    @RequestMapping(path = "/findTestcasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestcasePage",desc = "findTestcasePage")
    @ApiParam(name = "testcaseQuery",desc = "testcaseQuery",required = true)
    public Result<Pagination<HttpTestcase>> findTestcasePage(@RequestBody @Valid @NotNull HttpTestcaseQuery httpTestcaseQuery){
        Pagination<HttpTestcase> pagination = httpTestcaseService.findTestcasePage(httpTestcaseQuery);

        return Result.ok(pagination);
    }

}
