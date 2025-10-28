package io.tiklab.postin.autotest.http.scene.execute.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.postin.autotest.http.scene.execute.service.ApiSceneExecuteDispatchService;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;

/**
 * 接口场景测试调度 控制器
 */
@RestController
@RequestMapping("/apiSceneTestDispatch")
//@Api(name = "ApiSceneTestDispatchController",desc = "api场景测试调度")
public class ApiSceneTestDispatchController {

    @Autowired
    private ApiSceneExecuteDispatchService apiSceneExecuteDispatchService;

    @RequestMapping(path="/execute",method = RequestMethod.POST)
//    @ApiMethod(name = "execute",desc = "执行场景用例")
//    @ApiParam(name = "apiSceneTestRequest",desc = "apiSceneTestRequest",required = true)
    public Result<ApiSceneTestResponse> execute(@RequestBody @NotNull @Valid ApiSceneTestRequest apiSceneTestRequest){
        ApiSceneTestResponse execute = apiSceneExecuteDispatchService.execute(apiSceneTestRequest);

        return Result.ok(execute);
    }


    @RequestMapping(path="/result",method = RequestMethod.POST)
//    @ApiMethod(name = "execute",desc = "获取测试用例结果")
//    @ApiParam(name = "apiSceneId",desc = "apiSceneId",required = true)
    public Result<ApiSceneInstance> result(@NotNull @Valid String apiSceneId){
        ApiSceneInstance result = apiSceneExecuteDispatchService.result(apiSceneId);

        return Result.ok(result);
    }

}
