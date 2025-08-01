package io.tiklab.postin.autotest.http.perf.cases.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfStep;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfStepQuery;
import io.tiklab.postin.autotest.http.perf.cases.service.ApiPerfStepService;
import io.tiklab.postin.autotest.test.model.TestCase;
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
 * 接口性能下场景步骤 控制器
 */
@RestController
@RequestMapping("/apiPerfStep")
//@Api(name = "ApiPerfStepController",desc = "ApiPerfStepController")
public class ApiPerfStepController {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfStepController.class);

    @Autowired
    private ApiPerfStepService apiPerfStepService;

    @RequestMapping(path="/createApiPerfStep",method = RequestMethod.POST)
//    @ApiMethod(name = "createApiPerfStep",desc = "新增场景步骤")
//    @ApiParam(name = "apiPerfStep",desc = "apiPerfStep",required = true)
    public Result<String> createApiPerfStep(@RequestBody @NotNull @Valid ApiPerfStep apiPerfStep){
        String id = apiPerfStepService.createApiPerfStep(apiPerfStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiPerfStep",method = RequestMethod.POST)
//    @ApiMethod(name = "updateApiPerfStep",desc = "更新场景步骤")
//    @ApiParam(name = "apiPerfStep",desc = "apiPerfStep",required = true)
    public Result<Void> updateApiPerfStep(@RequestBody @NotNull @Valid ApiPerfStep apiPerfStep){
        apiPerfStepService.updateApiPerfStep(apiPerfStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiPerfStep",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteApiPerfStep",desc = "删除场景步骤")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiPerfStep(@NotNull String id){
        apiPerfStepService.deleteApiPerfStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiPerfStep",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfStep",desc = "通过id查询场景步骤")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiPerfStep> findApiPerfStep(@NotNull String id){
        ApiPerfStep apiPerfStep = apiPerfStepService.findApiPerfStep(id);

        return Result.ok(apiPerfStep);
    }

    @RequestMapping(path="/findAllApiPerfStep",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllApiPerfStep",desc = "查询所有场景步骤")
    public Result<List<ApiPerfStep>> findAllApiPerfStep(){
        List<ApiPerfStep> apiPerfStepList = apiPerfStepService.findAllApiPerfStep();

        return Result.ok(apiPerfStepList);
    }

    @RequestMapping(path = "/findApiPerfStepList",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfStepList",desc = "查询列表接口性能场景步骤")
//    @ApiParam(name = "apiPerfStepQuery",desc = "apiPerfStepQuery",required = true)
    public Result<List<ApiPerfStep>> findApiPerfStepList(@RequestBody @Valid @NotNull ApiPerfStepQuery apiPerfStepQuery){
        List<ApiPerfStep> apiPerfStepList = apiPerfStepService.findApiPerfStepList(apiPerfStepQuery);

        return Result.ok(apiPerfStepList);
    }

    @RequestMapping(path = "/findApiPerfStepPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfStepPage",desc = "按分页查询接口性能场景步骤")
//    @ApiParam(name = "apiPerfStepQuery",desc = "apiPerfStepQuery",required = true)
    public Result<Pagination<ApiPerfStep>> findApiPerfStepPage(@RequestBody @Valid @NotNull ApiPerfStepQuery apiPerfStepQuery){
        Pagination<ApiPerfStep> pagination = apiPerfStepService.findApiPerfStepPage(apiPerfStepQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path="/bindApiScene",method = RequestMethod.POST)
//    @ApiMethod(name = "bindApiScene",desc = "绑定场景步骤")
//    @ApiParam(name = "apiPerfStep",desc = "apiPerfStep",required = true)
    public Result<String> bindApiScene(@RequestBody @NotNull @Valid List<ApiPerfStep> apiPerfStep){
          apiPerfStepService.bindApiScene(apiPerfStep);

        return Result.ok();
    }

    @RequestMapping(path = "/findApiPerfStepWillBindCasePage",method = RequestMethod.POST)
    public Result<Pagination<TestCase>> findApiPerfStepWillBindCasePage(@RequestBody @Valid @NotNull ApiPerfStepQuery apiPerfStepQuery){
        Pagination<TestCase> pagination = apiPerfStepService.findApiPerfStepWillBindCasePage(apiPerfStepQuery);

        return Result.ok(pagination);
    }
}
