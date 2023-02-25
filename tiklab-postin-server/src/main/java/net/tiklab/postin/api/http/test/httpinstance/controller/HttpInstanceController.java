package net.tiklab.postin.api.http.test.httpinstance.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.api.http.test.httpinstance.model.HttpInstance;
import net.tiklab.postin.api.http.test.httpinstance.model.HttpInstanceQuery;
import net.tiklab.postin.api.http.test.httpinstance.service.TestInstanceService;
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
@RequestMapping("/testInstance")
@Api(name = "TestInstanceController",desc = "测试实例管理")
public class HttpInstanceController {

    private static Logger logger = LoggerFactory.getLogger(HttpInstanceController.class);

    @Autowired
    private TestInstanceService testInstanceService;

    @RequestMapping(path="/createTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createTestInstance",desc = "createTestInstance")
    @ApiParam(name = "testInstance",desc = "testInstance",required = true)
    public Result<String> createTestInstance(@RequestBody @NotNull @Valid HttpInstance httpInstance){
        String id = testInstanceService.createTestInstanceWithNest(httpInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestInstance",desc = "updateTestInstance")
    @ApiParam(name = "testInstance",desc = "testInstance",required = true)
    public Result<Void> updateTestInstance(@RequestBody @NotNull @Valid HttpInstance httpInstance){
        testInstanceService.updateTestInstance(httpInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestInstance",desc = "deleteTestInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestInstance(@NotNull String id){
        testInstanceService.deleteTestInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAllTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAllTestInstance",desc = "deleteAllTestInstance")
    @ApiParam(name = "userId",desc = "userId",required = true)
    public Result<Void> deleteAllTestInstance(@NotNull String userId){
        testInstanceService.deleteAllTestInstance(userId);

        return Result.ok();
    }

    @RequestMapping(path="/findTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findTestInstance",desc = "findTestInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<HttpInstance> findTestInstance(@NotNull String id){
        HttpInstance httpInstance = testInstanceService.findTestInstanceWithNest(id);

        return Result.ok(httpInstance);
    }

    @RequestMapping(path="/findAllTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestInstance",desc = "findAllTestInstance")
    public Result<List<HttpInstance>> findAllTestInstance(){
        List<HttpInstance> httpInstanceList = testInstanceService.findAllTestInstance();

        return Result.ok(httpInstanceList);
    }


    @RequestMapping(path = "/findTestInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestInstanceList",desc = "findTestInstanceList")
    @ApiParam(name = "testInstanceQuery",desc = "testInstanceQuery",required = true)
    public Result<List<HttpInstance>> findTestInstanceList(@RequestBody @Valid @NotNull HttpInstanceQuery httpInstanceQuery){
        List<HttpInstance> httpInstanceList = testInstanceService.findTestInstanceList(httpInstanceQuery);

        return Result.ok(httpInstanceList);
    }


    @RequestMapping(path = "/findTestInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestInstancePage",desc = "findTestInstancePage")
    @ApiParam(name = "testInstanceQuery",desc = "testInstanceQuery",required = true)
    public Result<Pagination<HttpInstance>> findTestInstancePage(@RequestBody @Valid @NotNull HttpInstanceQuery httpInstanceQuery){
        Pagination<HttpInstance> pagination = testInstanceService.findTestInstancePage(httpInstanceQuery);

        return Result.ok(pagination);
    }

}
