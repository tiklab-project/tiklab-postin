package com.darthcloud.apibox.api.apidef.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.api.apidef.model.RawResponse;
import com.darthcloud.apibox.api.apidef.model.RawResponseQuery;
import com.darthcloud.apibox.api.apidef.service.RawResponseService;
import com.darthcloud.common.Pagination;
import com.darthcloud.common.Result;
import com.darthcloud.apibox.annotation.ApiMethod;
import com.darthcloud.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.darthcloud.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/rawResponse")
@Api(name = "RawResponseController",desc = "Raw自定义文本响应结果管理")
public class RawResponseController {

    private static Logger logger = LoggerFactory.getLogger(RawResponseController.class);

    @Autowired
    private RawResponseService rawResponseService;

    @RequestMapping(path="/createRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "createRawResponse",desc = "createRawResponse")
    @ApiParam(name = "rawResponse",desc = "rawResponse",required = true)
    public Result<String> createRawResponse(@RequestBody @NotNull @Valid RawResponse rawResponse){
        String id = rawResponseService.createRawResponse(rawResponse);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "updateRawResponse",desc = "updateRawResponse")
    @ApiParam(name = "rawResponse",desc = "rawResponse",required = true)
    public Result<Void> updateRawResponse(@RequestBody @NotNull @Valid RawResponse rawResponse){
        rawResponseService.updateRawResponse(rawResponse);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRawResponse",desc = "deleteRawResponse")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawResponse(@NotNull String id){
        rawResponseService.deleteRawResponse(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "findRawResponse",desc = "findRawResponse")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawResponse> findRawResponse(@NotNull String id){
        RawResponse rawResponse = rawResponseService.findRawResponse(id);

        return Result.ok(rawResponse);
    }

    @RequestMapping(path="/findAllRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRawResponse",desc = "findAllRawResponse")
    public Result<List<RawResponse>> findAllRawResponse(){
        List<RawResponse> rawResponseList = rawResponseService.findAllRawResponse();

        return Result.ok(rawResponseList);
    }

    @Validator
    @RequestMapping(path = "/findRawResponseList",method = RequestMethod.POST)
    @ApiMethod(name = "findRawResponseList",desc = "findRawResponseList")
    @ApiParam(name = "rawResponseQuery",desc = "rawResponseQuery",required = true)
    public Result<List<RawResponse>> findRawResponseList(@RequestBody @Valid @NotNull RawResponseQuery rawResponseQuery){
        List<RawResponse> rawResponseList = rawResponseService.findRawResponseList(rawResponseQuery);

        return Result.ok(rawResponseList);
    }

    @Validator
    @RequestMapping(path = "/findRawResponsePage",method = RequestMethod.POST)
    @ApiMethod(name = "findRawResponsePage",desc = "findRawResponsePage")
    @ApiParam(name = "rawResponseQuery",desc = "rawResponseQuery",required = true)
    public Result<Pagination<List<RawResponse>>> findRawResponsePage(@RequestBody @Valid @NotNull RawResponseQuery rawResponseQuery){
        Pagination<List<RawResponse>> pagination = rawResponseService.findRawResponsePage(rawResponseQuery);

        return Result.ok(pagination);
    }

}
