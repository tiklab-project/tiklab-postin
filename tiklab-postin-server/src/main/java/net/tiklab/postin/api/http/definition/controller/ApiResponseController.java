package net.tiklab.postin.api.http.definition.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.api.http.definition.model.ApiResponse;
import net.tiklab.postin.api.http.definition.model.ApiResponseQuery;
import net.tiklab.postin.api.http.definition.service.ApiResponseService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
 * 接口响应控制器
 */
@RestController
@RequestMapping("/apiResponse")
@Api(name = "ApiResponseController",desc = "Http接口定义中的响应")
public class ApiResponseController {

    private static Logger logger = LoggerFactory.getLogger(ApiResponseController.class);

    @Autowired
    private ApiResponseService apiResponseService;

    @RequestMapping(path="/createApiResponse",method = RequestMethod.POST)
    @ApiMethod(name = "createApiResponse",desc = "创建定义响应")
    @ApiParam(name = "apiResponse",desc = "apiResponse",required = true)
    public Result<String> createApiResponse(@RequestBody @NotNull @Valid ApiResponse apiResponse){
        String id = apiResponseService.createApiResponse(apiResponse);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiResponse",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiResponse",desc = "更新定义响应")
    @ApiParam(name = "apiResponse",desc = "apiResponse",required = true)
    public Result<Void> updateApiResponse(@RequestBody @NotNull @Valid ApiResponse apiResponse){
        apiResponseService.updateApiResponse(apiResponse);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiResponse",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiResponse",desc = "删除定义响应")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiResponse(@NotNull String id){
        apiResponseService.deleteApiResponse(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiResponse",method = RequestMethod.POST)
    @ApiMethod(name = "findApiResponse",desc = "通过id查询定义响应")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiResponse> findApiResponse(@NotNull String id){
        ApiResponse apiResponse = apiResponseService.findApiResponse(id);

        return Result.ok(apiResponse);
    }

    @RequestMapping(path="/findAllApiResponse",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiResponse",desc = "查询所有定义响应")
    public Result<List<ApiResponse>> findAllApiResponse(){
        List<ApiResponse> apiResponseList = apiResponseService.findAllApiResponse();

        return Result.ok(apiResponseList);
    }


    @RequestMapping(path = "/findApiResponseList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiResponseList",desc = "根据查询对象查询定义响应")
    @ApiParam(name = "apiResponseQuery",desc = "apiResponseQuery",required = true)
    public Result<List<ApiResponse>> findApiResponseList(@RequestBody @Valid @NotNull ApiResponseQuery apiResponseQuery){
        List<ApiResponse> apiResponseList = apiResponseService.findApiResponseList(apiResponseQuery);

        return Result.ok(apiResponseList);
    }


    @RequestMapping(path = "/findApiResponsePage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiResponsePage",desc = "根据查询对象按分页查找定义响应")
    @ApiParam(name = "apiResponseQuery",desc = "apiResponseQuery",required = true)
    public Result<Pagination<ApiResponse>> findApiResponsePage(@RequestBody @Valid @NotNull ApiResponseQuery apiResponseQuery){
        Pagination<ApiResponse> pagination = apiResponseService.findApiResponsePage(apiResponseQuery);

        return Result.ok(pagination);
    }

}
