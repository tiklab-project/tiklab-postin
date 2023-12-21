package io.thoughtware.postin.support.apistatus.controller;

import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.support.apistatus.model.ApiStatus;
import io.thoughtware.postin.support.apistatus.model.ApiStatusQuery;
import io.thoughtware.postin.support.apistatus.service.ApiStatusService;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * 接口状态 控制器
 */
@RestController
@RequestMapping("/apiStatus")
@Api(name = "ApiStatusController",desc = "ApiStatusController")
public class ApiStatusController {

    private static Logger logger = LoggerFactory.getLogger(ApiStatusController.class);

    @Autowired
    private ApiStatusService apiStatusService;

    @RequestMapping(path="/createApiStatus",method = RequestMethod.POST)
    @ApiMethod(name = "createApiStatus",desc = "创建接口状态")
    @ApiParam(name = "apiStatus",desc = "apiStatus",required = true)
    public Result<String> createApiStatus(@RequestBody @NotNull @Valid ApiStatus apiStatus){
        String id = apiStatusService.createApiStatus(apiStatus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiStatus",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiStatus",desc = "更新接口状态")
    @ApiParam(name = "apiStatus",desc = "apiStatus",required = true)
    public Result<Void> updateApiStatus(@RequestBody @NotNull @Valid ApiStatus apiStatus){
        apiStatusService.updateApiStatus(apiStatus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiStatus",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiStatus",desc = "删除接口状态")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiStatus(@NotNull String id){
        apiStatusService.deleteApiStatus(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiStatus",method = RequestMethod.POST)
    @ApiMethod(name = "findApiStatus",desc = "根据id查找接口状态")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiStatus> findApiStatus(@NotNull String id){
        ApiStatus apiStatus = apiStatusService.findApiStatus(id);

        return Result.ok(apiStatus);
    }

    @RequestMapping(path="/findAllApiStatus",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiStatus",desc = "查找所有接口状态")
    public Result<List<ApiStatus>> findAllApiStatus(){
        List<ApiStatus> apiStatusList = apiStatusService.findAllApiStatus();

        return Result.ok(apiStatusList);
    }

    @RequestMapping(path = "/findApiStatusList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiStatusList",desc = "根据查询参数查找接口状态")
    @ApiParam(name = "apiStatusQuery",desc = "apiStatusQuery",required = true)
    public Result<List<ApiStatus>> findApiStatusList(@RequestBody @Valid @NotNull ApiStatusQuery apiStatusQuery){
        List<ApiStatus> apiStatusList = apiStatusService.findApiStatusList(apiStatusQuery);

        return Result.ok(apiStatusList);
    }

    @RequestMapping(path = "/findApiStatusPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiStatusPage",desc = "根据查询参数按分页查找接口状态")
    @ApiParam(name = "apiStatusQuery",desc = "apiStatusQuery",required = true)
    public Result<Pagination<ApiStatus>> findApiStatusPage(@RequestBody @Valid @NotNull ApiStatusQuery apiStatusQuery){
        Pagination<ApiStatus> pagination = apiStatusService.findApiStatusPage(apiStatusQuery);

        return Result.ok(pagination);
    }

}
