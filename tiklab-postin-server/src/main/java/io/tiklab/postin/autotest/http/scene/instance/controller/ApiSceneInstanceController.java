package io.tiklab.postin.autotest.http.scene.instance.controller;

import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstanceQuery;
import io.tiklab.postin.autotest.http.scene.instance.service.ApiSceneInstanceService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 场景历史实例 控制器
 */
@RestController
@RequestMapping("/apiSceneInstance")
//@Api(name = "ApiSceneInstanceController",desc = "api场景测试实例管理")
public class ApiSceneInstanceController {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneInstanceController.class);

    @Autowired
    private ApiSceneInstanceService apiSceneInstanceService;

    @RequestMapping(path="/createApiSceneInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "createApiSceneInstance",desc = "创建接口场景历史实例")
//    @ApiParam(name = "apiSceneInstance",desc = "apiSceneInstance",required = true)
    public Result<String> createApiSceneInstance(@RequestBody @NotNull @Valid ApiSceneInstance apiSceneInstance){
        String id = apiSceneInstanceService.createApiSceneInstance(apiSceneInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiSceneInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "updateApiSceneInstance",desc = "更新接口场景历史实例")
//    @ApiParam(name = "apiSceneInstance",desc = "apiSceneInstance",required = true)
    public Result<Void> updateApiSceneInstance(@RequestBody @NotNull @Valid ApiSceneInstance apiSceneInstance){
        apiSceneInstanceService.updateApiSceneInstance(apiSceneInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiSceneInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteApiSceneInstance",desc = "删除接口场景历史实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiSceneInstance(@NotNull String id){
        apiSceneInstanceService.deleteApiSceneInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiSceneInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiSceneInstance",desc = "通过id查找接口场景历史实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiSceneInstance> findApiSceneInstance(@NotNull String id){
        ApiSceneInstance apiSceneInstance = apiSceneInstanceService.findApiSceneInstance(id);

        return Result.ok(apiSceneInstance);
    }

    @RequestMapping(path="/findAllApiSceneInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllApiSceneInstance",desc = "查找所有接口场景历史实例")
    public Result<List<ApiSceneInstance>> findAllApiSceneInstance(){
        List<ApiSceneInstance> allApiSceneInstance = apiSceneInstanceService.findAllApiSceneInstance();

        return Result.ok(allApiSceneInstance);
    }

    @RequestMapping(path = "/findApiSceneInstanceList",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiSceneInstanceList",desc = "根据查询参数查询接口场景历史实例列表")
//    @ApiParam(name = "apiSceneInstanceQuery",desc = "apiSceneInstanceQuery",required = true)
    public Result<List<ApiSceneInstance>> findApiSceneInstanceList(@RequestBody @Valid @NotNull ApiSceneInstanceQuery apiSceneInstanceQuery){
        List<ApiSceneInstance> apiSceneInstanceList = apiSceneInstanceService.findApiSceneInstanceList(apiSceneInstanceQuery);

        return Result.ok(apiSceneInstanceList);
    }

    @RequestMapping(path = "/findApiSceneInstancePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiSceneInstancePage",desc = "根据查询按分页查询接口场景历史实例")
//    @ApiParam(name = "apiSceneInstanceQuery",desc = "apiSceneInstanceQuery",required = true)
    public Result<Pagination<ApiSceneInstance>> findApiSceneInstancePage(@RequestBody @Valid @NotNull ApiSceneInstanceQuery apiSceneInstanceQuery){
        Pagination<ApiSceneInstance> pagination = apiSceneInstanceService.findApiSceneInstancePage(apiSceneInstanceQuery);

        return Result.ok(pagination);
    }




}
