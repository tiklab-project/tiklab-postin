package io.tiklab.postin.autotest.http.unit.instance.controller;

import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstanceBind;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstanceBindQuery;
import io.tiklab.postin.autotest.http.unit.instance.service.ApiUnitInstanceBindService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;

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
 * 接口单元公共实例 控制器
 */
@RestController
@RequestMapping("/apiUnitInstanceBind")
//@Api(name = "ApiUnitInstanceBindController",desc = "ApiUnitInstanceBindController")
public class ApiUnitInstanceBindController {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitInstanceBindController.class);

    @Autowired
    private ApiUnitInstanceBindService apiUnitInstanceBindService;

    @RequestMapping(path="/createApiUnitInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "createApiUnitInstanceBind",desc = "创建接口单元公共实例")
//    @ApiParam(name = "apiUnitInstanceBind",desc = "apiUnitInstanceBind",required = true)
    public Result<String> createApiUnitInstanceBind(@RequestBody @NotNull @Valid ApiUnitInstanceBind apiUnitInstanceBind){
        String id = apiUnitInstanceBindService.createApiUnitInstanceBind(apiUnitInstanceBind);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiUnitInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "updateApiUnitInstanceBind",desc = "更新接口单元公共实例")
//    @ApiParam(name = "apiUnitInstanceBind",desc = "apiUnitInstanceBind",required = true)
    public Result<Void> updateApiUnitInstanceBind(@RequestBody @NotNull @Valid ApiUnitInstanceBind apiUnitInstanceBind){
        apiUnitInstanceBindService.updateApiUnitInstanceBind(apiUnitInstanceBind);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiUnitInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteApiUnitInstanceBind",desc = "删除接口单元公共实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiUnitInstanceBind(@NotNull String id){
        apiUnitInstanceBindService.deleteApiUnitInstanceBind(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiUnitInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiUnitInstanceBind",desc = "查找接口单元公共实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiUnitInstanceBind> findApiUnitInstanceBind(@NotNull String id){
        ApiUnitInstanceBind apiUnitInstanceBind = apiUnitInstanceBindService.findApiUnitInstanceBind(id);

        return Result.ok(apiUnitInstanceBind);
    }

    @RequestMapping(path="/findAllApiUnitInstanceBind",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllApiUnitInstanceBind",desc = "查找所有接口单元公共实例")
    public Result<List<ApiUnitInstanceBind>> findAllApiUnitInstanceBind(){
        List<ApiUnitInstanceBind> apiUnitInstanceBindList = apiUnitInstanceBindService.findAllApiUnitInstanceBind();

        return Result.ok(apiUnitInstanceBindList);
    }

    @RequestMapping(path = "/findApiUnitInstanceBindList",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiUnitInstanceBindList",desc = "查询接口单元公共实例列表")
//    @ApiParam(name = "apiUnitInstanceBindQuery",desc = "apiUnitInstanceBindQuery",required = true)
    public Result<List<ApiUnitInstanceBind>> findApiUnitInstanceBindList(@RequestBody @Valid @NotNull ApiUnitInstanceBindQuery apiUnitInstanceBindQuery){
        List<ApiUnitInstanceBind> apiUnitInstanceBindList = apiUnitInstanceBindService.findApiUnitInstanceBindList(apiUnitInstanceBindQuery);

        return Result.ok(apiUnitInstanceBindList);
    }

    @RequestMapping(path = "/findApiUnitInstanceBindPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findApiUnitInstanceBindPage",desc = "按分页查询接口单元公共实例")
//    @ApiParam(name = "apiUnitInstanceBindQuery",desc = "apiUnitInstanceBindQuery",required = true)
    public Result<Pagination<ApiUnitInstanceBind>> findApiUnitInstanceBindPage(@RequestBody @Valid @NotNull ApiUnitInstanceBindQuery apiUnitInstanceBindQuery){
        Pagination<ApiUnitInstanceBind> pagination = apiUnitInstanceBindService.findApiUnitInstanceBindPage(apiUnitInstanceBindQuery);

        return Result.ok(pagination);
    }

}
