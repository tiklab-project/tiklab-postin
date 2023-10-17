package io.tiklab.postin.api.apix.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.api.apix.model.ApiRequest;
import io.tiklab.postin.api.apix.model.ApiRequestQuery;
import io.tiklab.postin.api.apix.service.ApiRequestService;
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
 * 接口请求控制器
 */
@RestController
@RequestMapping("/apiRequest")
@Api(name = "ApiRequestController",desc = "Http接口定义中的请求")
public class ApiRequestController {

    private static Logger logger = LoggerFactory.getLogger(ApiRequestController.class);

    @Autowired
    private ApiRequestService apiRequestService;

    @RequestMapping(path="/createApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "createApiRequest",desc = "创建定义请求")
    @ApiParam(name = "apiRequest",desc = "apiRequest",required = true)
    public Result<String> createApiRequest(@RequestBody @NotNull @Valid ApiRequest apiRequest){
        String id = apiRequestService.createApiRequest(apiRequest);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiRequest",desc = "更新定义请求")
    @ApiParam(name = "apiRequest",desc = "apiRequest",required = true)
    public Result<Void> updateApiRequest(@RequestBody @NotNull @Valid ApiRequest apiRequest){
        apiRequestService.updateApiRequest(apiRequest);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiRequest",desc = "删除定义请求")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiRequest(@NotNull String id){
        apiRequestService.deleteApiRequest(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRequest",desc = "通过id查询定义请求")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiRequest> findApiRequest(@NotNull String id){
        ApiRequest apiRequest = apiRequestService.findApiRequest(id);

        return Result.ok(apiRequest);
    }

    @RequestMapping(path="/findAllApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiRequest",desc = "查询所有定义请求")
    public Result<List<ApiRequest>> findAllApiRequest(){
        List<ApiRequest> apiRequestList = apiRequestService.findAllApiRequest();

        return Result.ok(apiRequestList);
    }

    @RequestMapping(path = "/findApiRequestList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRequestList",desc = "根据查询对象查询定义请求")
    @ApiParam(name = "apiRequestQuery",desc = "apiRequestQuery",required = true)
    public Result<List<ApiRequest>> findApiRequestList(@RequestBody @Valid @NotNull ApiRequestQuery apiRequestQuery){
        List<ApiRequest> apiRequestList = apiRequestService.findApiRequestList(apiRequestQuery);

        return Result.ok(apiRequestList);
    }

    @RequestMapping(path = "/findApiRequestPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRequestPage",desc = "根据查询对象按分页查找定义请求")
    @ApiParam(name = "apiRequestQuery",desc = "apiRequestQuery",required = true)
    public Result<Pagination<ApiRequest>> findApiRequestPage(@RequestBody @Valid @NotNull ApiRequestQuery apiRequestQuery){
        Pagination<ApiRequest> pagination = apiRequestService.findApiRequestPage(apiRequestQuery);

        return Result.ok(pagination);
    }

}
