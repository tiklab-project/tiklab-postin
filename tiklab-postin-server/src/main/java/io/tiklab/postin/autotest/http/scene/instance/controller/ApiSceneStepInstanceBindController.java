package io.tiklab.postin.autotest.http.scene.instance.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneStepInstanceBind;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneStepInstanceBindQuery;
import io.tiklab.postin.autotest.http.scene.instance.service.ApiSceneStepInstanceBindService;
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
 * 接口场景下步骤的公共实例 控制器
 */
@RestController
@RequestMapping("/apiSceneStepInstanceBind")
//@Api(name = "ApiSceneStepInstanceBindController",desc = "接口场景下步骤的公共实例管理")
public class ApiSceneStepInstanceBindController {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepInstanceBindController.class);

    @Autowired
    private ApiSceneStepInstanceBindService apiSceneStepInstanceBindService;

    @RequestMapping(path="/createApiSceneStepInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "createApiSceneStepInstanceBind",desc = "创建接口场景下步骤的公共实例")
//    @ApiParam(name = "apiSceneStepInstanceBind",desc = "apiSceneStepInstanceBind",required = true)
    public Result<String> createApiSceneStepInstanceBind(@RequestBody @NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind){
        String id = apiSceneStepInstanceBindService.createApiSceneStepInstanceBind(apiSceneStepInstanceBind);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiSceneStepInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "updateApiSceneStepInstanceBind",desc = "更新接口场景下步骤的公共实例")
//    @ApiParam(name = "apiSceneStepInstanceBind",desc = "apiSceneStepInstanceBind",required = true)
    public Result<Void> updateApiSceneStepInstanceBind(@RequestBody @NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind){
        apiSceneStepInstanceBindService.updateApiSceneStepInstanceBind(apiSceneStepInstanceBind);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiSceneStepInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteApiSceneStepInstanceBind",desc = "删除接口场景下步骤的公共实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiSceneStepInstanceBind(@NotNull String id){
        apiSceneStepInstanceBindService.deleteApiSceneStepInstanceBind(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiSceneStepInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiSceneStepInstanceBind",desc = "查找接口场景下步骤的公共实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiSceneStepInstanceBind> findApiSceneStepInstanceBind(@NotNull String id){
        ApiSceneStepInstanceBind apiSceneStepInstanceBind = apiSceneStepInstanceBindService.findApiSceneStepInstanceBind(id);

        return Result.ok(apiSceneStepInstanceBind);
    }

    @RequestMapping(path="/findAllApiSceneStepInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllApiSceneStepInstanceBind",desc = "查找所有接口场景下步骤的公共实例")
    public Result<List<ApiSceneStepInstanceBind>> findAllApiSceneStepInstanceBind(){
        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList = apiSceneStepInstanceBindService.findAllApiSceneStepInstanceBind();

        return Result.ok(apiSceneStepInstanceBindList);
    }

    @RequestMapping(path = "/findApiSceneStepInstanceBindList",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiSceneStepInstanceBindList",desc = "查询接口场景下步骤的实例公共实例")
//    @ApiParam(name = "apiSceneStepInstanceBindQuery",desc = "apiSceneStepInstanceBindQuery",required = true)
    public Result<List<ApiSceneStepInstanceBind>> findApiSceneStepInstanceBindList(@RequestBody @Valid @NotNull ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery){
        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList = apiSceneStepInstanceBindService.findApiSceneStepInstanceBindList(apiSceneStepInstanceBindQuery);

        return Result.ok(apiSceneStepInstanceBindList);
    }

    @RequestMapping(path = "/findApiSceneStepInstanceBindPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiSceneStepInstanceBindPage",desc = "按分页查询接口场景下步骤的公共实例")
//    @ApiParam(name = "apiSceneStepInstanceBindQuery",desc = "apiSceneStepInstanceBindQuery",required = true)
    public Result<Pagination<ApiSceneStepInstanceBind>> findApiSceneStepInstanceBindPage(@RequestBody @Valid @NotNull ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery){
        Pagination<ApiSceneStepInstanceBind> pagination = apiSceneStepInstanceBindService.findApiSceneStepInstanceBindPage(apiSceneStepInstanceBindQuery);

        return Result.ok(pagination);
    }

}
