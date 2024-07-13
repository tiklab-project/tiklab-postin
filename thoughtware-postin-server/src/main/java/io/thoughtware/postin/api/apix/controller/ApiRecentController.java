package io.thoughtware.postin.api.apix.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;

import io.thoughtware.postin.api.apix.model.ApiRecent;
import io.thoughtware.postin.api.apix.model.ApiRecentQuery;
import io.thoughtware.postin.api.apix.service.ApiRecentService;
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
 * 最近访问接口 控制器
 */
@RestController
@RequestMapping("/apiRecent")
@Api(name = "ApiRecentController",desc = "ApiRecentController")
public class ApiRecentController {

    private static Logger logger = LoggerFactory.getLogger(ApiRecentController.class);

    @Autowired
    private ApiRecentService apiRecentService;

    @RequestMapping(path="/createApiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "createApiRecent",desc = "创建最近访问接口")
    @ApiParam(name = "apiRecent",desc = "apiRecent",required = true)
    public Result<String> createApiRecent(@RequestBody @NotNull @Valid ApiRecent apiRecent){
        String id = apiRecentService.createApiRecent(apiRecent);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiRecent",desc = "更新最近访问接口")
    @ApiParam(name = "apiRecent",desc = "apiRecent",required = true)
    public Result<Void> updateApiRecent(@RequestBody @NotNull @Valid ApiRecent apiRecent){
        apiRecentService.updateApiRecent(apiRecent);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiRecent",desc = "删除最近访问接口")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiRecent(@NotNull String id){
        apiRecentService.deleteApiRecent(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRecent",desc = "通过id查找最近访问接口")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiRecent> findApiRecent(@NotNull String id){
        ApiRecent apiRecent = apiRecentService.findApiRecent(id);

        return Result.ok(apiRecent);
    }

    @RequestMapping(path="/findAllApiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiRecent",desc = "查找所有最近访问接口")
    public Result<List<ApiRecent>> findAllApiRecent(){
        List<ApiRecent> apiRecentList = apiRecentService.findAllApiRecent();

        return Result.ok(apiRecentList);
    }

    @RequestMapping(path = "/findApiRecentList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRecentList",desc = "根据查询参数查找最近访问接口")
    @ApiParam(name = "apiRecentQuery",desc = "apiRecentQuery",required = true)
    public Result<List<ApiRecent>> findApiRecentList(@RequestBody @Valid @NotNull ApiRecentQuery apiRecentQuery){
        List<ApiRecent> apiRecentList = apiRecentService.findApiRecentList(apiRecentQuery);

        return Result.ok(apiRecentList);
    }

    @RequestMapping(path = "/findApiRecentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRecentPage",desc = "根据查询参数按分页查找最近访问接口")
    @ApiParam(name = "apiRecentQuery",desc = "apiRecentQuery",required = true)
    public Result<Pagination<ApiRecent>> findApiRecentPage(@RequestBody @Valid @NotNull ApiRecentQuery apiRecentQuery){
        Pagination<ApiRecent> pagination = apiRecentService.findApiRecentPage(apiRecentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/apiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "apiRecent",desc = "最近访问接口")
    @ApiParam(name = "apiRecent",desc = "apiRecent",required = true)
    public Result<Void> apiRecent(@RequestBody @NotNull @Valid ApiRecent apiRecent){
        apiRecentService.apiRecent(apiRecent);

        return Result.ok();
    }

}
