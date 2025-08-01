package io.tiklab.postin.autotest.http.perf.instance.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstance;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstanceQuery;
import io.tiklab.postin.autotest.http.perf.instance.service.ApiPerfInstanceService;
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
 * 接口性能历史实例 控制器
 */
@RestController
@RequestMapping("/apiPerfInstance")
//@Api(name = "ApiPerfInstanceController",desc = "api性能测试实例管理")
public class ApiPerfInstanceController {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfInstanceController.class);

    @Autowired
    private ApiPerfInstanceService apiPerfInstanceService;

    @RequestMapping(path="/createApiPerfInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "createApiPerfInstance",desc = "创建性能测试汇总报告")
//    @ApiParam(name = "apiPerfInstance",desc = "apiPerfInstance",required = true)
    public Result<String> createApiPerfInstance(@RequestBody @NotNull @Valid ApiPerfInstance apiPerfInstance){
        String id = apiPerfInstanceService.createApiPerfInstance(apiPerfInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiPerfInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "updateApiPerfInstance",desc = "修改性能测试汇总报告")
//    @ApiParam(name = "apiPerfInstance",desc = "apiPerfInstance",required = true)
    public Result<Void> updateApiPerfInstance(@RequestBody @NotNull @Valid ApiPerfInstance apiPerfInstance){
        apiPerfInstanceService.updateApiPerfInstance(apiPerfInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiPerfInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteApiPerfInstance",desc = "删除性能测试汇总报告")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiPerfInstance(@NotNull String id){
        apiPerfInstanceService.deleteApiPerfInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiPerfInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfInstance",desc = "通过id查询性能测试汇总报告")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiPerfInstance> findApiPerfInstance(@NotNull String id){
        ApiPerfInstance apiPerfInstance = apiPerfInstanceService.findApiPerfInstance(id);

        return Result.ok(apiPerfInstance);
    }

    @RequestMapping(path="/findAllApiPerfInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllApiPerfInstance",desc = "查询所有性能测试汇总报告")
    public Result<List<ApiPerfInstance>> findAllApiPerfInstance(){
        List<ApiPerfInstance> apiPerfInstanceList = apiPerfInstanceService.findAllApiPerfInstance();

        return Result.ok(apiPerfInstanceList);
    }

    @RequestMapping(path = "/findApiPerfInstanceList",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfInstanceList",desc = "通过条件查询性能测试汇总报告")
//    @ApiParam(name = "apiPerfInstanceQuery",desc = "apiPerfInstanceQuery",required = true)
    public Result<List<ApiPerfInstance>> findApiPerfInstanceList(@RequestBody @Valid @NotNull ApiPerfInstanceQuery apiPerfInstanceQuery){
        List<ApiPerfInstance> apiPerfInstanceList = apiPerfInstanceService.findApiPerfInstanceList(apiPerfInstanceQuery);

        return Result.ok(apiPerfInstanceList);
    }

    @RequestMapping(path = "/findApiPerfInstancePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiPerfInstancePage",desc = "通过条件分页查询性能测试汇总报告")
//    @ApiParam(name = "apiPerfInstanceQuery",desc = "apiPerfInstanceQuery",required = true)
    public Result<Pagination<ApiPerfInstance>> findApiPerfInstancePage(@RequestBody @Valid @NotNull ApiPerfInstanceQuery apiPerfInstanceQuery){
        Pagination<ApiPerfInstance> pagination = apiPerfInstanceService.findApiPerfInstancePage(apiPerfInstanceQuery);

        return Result.ok(pagination);
    }

}
