package io.tiklab.postin.autotest.http.perf.execute.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.perf.execute.model.ApiPerfTestRequest;
import io.tiklab.postin.autotest.http.perf.execute.service.ApiPerfExecuteDispatchService;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 接口性能测试调度 控制器
 */
@RestController
@RequestMapping("/apiPerfTestDispatch")
//@Api(name = "ApiPerfTestDispatchController",desc = "api性能测试执行调度")
public class ApiPerfTestDispatchController {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfTestDispatchController.class);

    @Autowired
    ApiPerfExecuteDispatchService apiPerfExecuteDispatchService;

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
//    @ApiMethod(name = "execute", desc = "执行性能测试")
//    @ApiParam(name = "apiPerfTestRequest", desc = "执行需要传的参数", required = true)
    public Result<Boolean> execute(@RequestBody @Valid @NotNull ApiPerfTestRequest apiPerfTestRequest) {
        Boolean execute = apiPerfExecuteDispatchService.execute(apiPerfTestRequest);

        return Result.ok(execute);
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
//    @ApiMethod(name = "result", desc = "获取性能测试结果")
//    @ApiParam(name = "apiPerfId", desc = "执行需要传的参数", required = true)
    public Result<ApiPerfInstance> exeResult(String apiPerfId) {
        ApiPerfInstance result = apiPerfExecuteDispatchService.result(apiPerfId);
        return Result.ok(result);
    }

    @RequestMapping(path = "/stop", method = RequestMethod.POST)
//    @ApiMethod(name = "stop", desc = "停止执行")
//    @ApiParam(name = "apiPerfId", desc = "停止执行", required = true)
    public Result<Void> stopTest(@NotNull String apiPerfId) {
        apiPerfExecuteDispatchService.stopTest(apiPerfId);
        return Result.ok();
    }


}
