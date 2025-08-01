package io.tiklab.postin.autotest.http.scene.cases.controller;

import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneStep;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneStepQuery;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneStepWillBindCase;
import io.tiklab.postin.autotest.http.scene.cases.service.ApiSceneStepService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
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
 * 接口场景下步骤 控制器
 */
@RestController
@RequestMapping("/apiSceneStep")
//@Api(name = "ApiSceneStepController",desc = "接口场景下步骤管理")
public class ApiSceneStepController {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepController.class);

    @Autowired
    private ApiSceneStepService apiSceneStepService;

    @RequestMapping(path="/createApiSceneStep",method = RequestMethod.POST)
////    @ApiMethod(name = "createApiSceneStep",desc = "创建步骤")
//    @ApiParam(name = "apiSceneStep",desc = "apiSceneStep",required = true)
    public Result<String> createApiSceneStep(@RequestBody @NotNull @Valid ApiSceneStep apiSceneStep){
        String id = apiSceneStepService.createApiSceneStep(apiSceneStep);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateApiSceneStep",method = RequestMethod.POST)
////    @ApiMethod(name = "updateApiSceneStep",desc = "更新步骤")
//    @ApiParam(name = "apiSceneStep",desc = "apiSceneStep",required = true)
    public Result<Void> updateApiSceneStep(@RequestBody @NotNull @Valid ApiSceneStep apiSceneStep){
        apiSceneStepService.updateApiSceneStep(apiSceneStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiSceneStep",method = RequestMethod.POST)
////    @ApiMethod(name = "deleteApiSceneStep",desc = "删除步骤")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiSceneStep(@NotNull String id){
        apiSceneStepService.deleteApiSceneStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiSceneStep",method = RequestMethod.POST)
////    @ApiMethod(name = "findApiSceneStep",desc = "根据id查找步骤")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiSceneStep> findApiSceneStep(@NotNull String id){
        ApiSceneStep apiSceneStep = apiSceneStepService.findApiSceneStep(id);

        return Result.ok(apiSceneStep);
    }

    @RequestMapping(path="/findAllApiSceneStep",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllApiSceneStep",desc = "查找所有步骤")
    public Result<List<ApiSceneStep>> findAllApiSceneStep(){
        List<ApiSceneStep> apiSceneStepList = apiSceneStepService.findAllApiSceneStep();

        return Result.ok(apiSceneStepList);
    }

    @RequestMapping(path = "/findApiSceneStepList",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiSceneStepList",desc = "根据查询参数查找查询步骤列表")
//    @ApiParam(name = "apiSceneStepQuery",desc = "apiSceneStepQuery",required = true)
    public Result<List<ApiSceneStep>> findApiSceneStepList(@RequestBody @Valid @NotNull ApiSceneStepQuery apiSceneStepQuery){
        List<ApiSceneStep> apiSceneStepList = apiSceneStepService.findApiSceneStepList(apiSceneStepQuery);

        return Result.ok(apiSceneStepList);
    }

    @RequestMapping(path = "/findApiSceneStepPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiSceneStepPage",desc = "根据查询参数查找按分页查询步骤")
//    @ApiParam(name = "apiSceneStepQuery",desc = "apiSceneStepQuery",required = true)
    public Result<Pagination<ApiSceneStep>> findApiSceneStepPage(@RequestBody @Valid @NotNull ApiSceneStepQuery apiSceneStepQuery){
        Pagination<ApiSceneStep> pagination = apiSceneStepService.findApiSceneStepPage(apiSceneStepQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path="/bindApiUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "bindApiUnit",desc = "绑定接口单元用例")
//    @ApiParam(name = "apiSceneStep",desc = "apiSceneStep",required = true)
    public Result<String> bindApiUnit(@RequestBody @NotNull @Valid List<ApiSceneStep> apiSceneStep){
        apiSceneStepService.bindApiUnit(apiSceneStep);

        return Result.ok();
    }

    @RequestMapping(path="/findApiSceneStepWillBindCasePage",method = RequestMethod.POST)
    public Result<Pagination<ApiSceneStepWillBindCase>>  findApiSceneStepWillBindCasePage(@RequestBody @Valid @NotNull ApiSceneStepQuery apiSceneStepQuery){
        Pagination<ApiSceneStepWillBindCase> pagination = apiSceneStepService.findApiSceneStepWillBindCasePage(apiSceneStepQuery);

        return Result.ok(pagination);
    }


}
