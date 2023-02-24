package net.tiklab.postin.apidef.http.definition.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.postin.apidef.http.definition.model.ApiRequest;
import net.tiklab.postin.apidef.http.definition.model.ApiRequestQuery;
import net.tiklab.postin.apidef.http.definition.service.ApiRequestService;
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
 * ApiRequestController
 */
@RestController
@RequestMapping("/apiRequest")
@Api(name = "ApiRequestController",desc = "ApiRequestController")
public class ApiRequestController {

    private static Logger logger = LoggerFactory.getLogger(ApiRequestController.class);

    @Autowired
    private ApiRequestService apiRequestService;

    @RequestMapping(path="/createApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "createApiRequest",desc = "createApiRequest")
    @ApiParam(name = "apiRequest",desc = "apiRequest",required = true)
    public Result<String> createApiRequest(@RequestBody @NotNull @Valid ApiRequest apiRequest){
        String id = apiRequestService.createApiRequest(apiRequest);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiRequest",desc = "updateApiRequest")
    @ApiParam(name = "apiRequest",desc = "apiRequest",required = true)
    public Result<Void> updateApiRequest(@RequestBody @NotNull @Valid ApiRequest apiRequest){
        apiRequestService.updateApiRequest(apiRequest);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiRequest",desc = "deleteApiRequest")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiRequest(@NotNull String id){
        apiRequestService.deleteApiRequest(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRequest",desc = "findApiRequest")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiRequest> findApiRequest(@NotNull String id){
        ApiRequest apiRequest = apiRequestService.findApiRequest(id);

        return Result.ok(apiRequest);
    }

    @RequestMapping(path="/findAllApiRequest",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiRequest",desc = "findAllApiRequest")
    public Result<List<ApiRequest>> findAllApiRequest(){
        List<ApiRequest> apiRequestList = apiRequestService.findAllApiRequest();

        return Result.ok(apiRequestList);
    }

    @RequestMapping(path = "/findApiRequestList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRequestList",desc = "findApiRequestList")
    @ApiParam(name = "apiRequestQuery",desc = "apiRequestQuery",required = true)
    public Result<List<ApiRequest>> findApiRequestList(@RequestBody @Valid @NotNull ApiRequestQuery apiRequestQuery){
        List<ApiRequest> apiRequestList = apiRequestService.findApiRequestList(apiRequestQuery);

        return Result.ok(apiRequestList);
    }

    @RequestMapping(path = "/findApiRequestPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRequestPage",desc = "findApiRequestPage")
    @ApiParam(name = "apiRequestQuery",desc = "apiRequestQuery",required = true)
    public Result<Pagination<ApiRequest>> findApiRequestPage(@RequestBody @Valid @NotNull ApiRequestQuery apiRequestQuery){
        Pagination<ApiRequest> pagination = apiRequestService.findApiRequestPage(apiRequestQuery);

        return Result.ok(pagination);
    }

}
