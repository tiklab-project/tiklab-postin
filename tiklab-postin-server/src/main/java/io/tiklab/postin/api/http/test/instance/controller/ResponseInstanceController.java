package io.tiklab.postin.api.http.test.instance.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.test.instance.model.ResponseInstances;
import io.tiklab.postin.api.http.test.instance.model.ResponseInstanceQuery;
import io.tiklab.postin.api.http.test.instance.service.ResponseInstanceService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/responseInstance")
@Api(name = "ResponseInstanceController",desc = "测试实例-响应管理")
public class ResponseInstanceController {

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceController.class);

    @Autowired
    private ResponseInstanceService responseInstanceService;

    @RequestMapping(path="/createResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseInstance",desc = "createResponseInstance")
    @ApiParam(name = "responseInstance",desc = "responseInstance",required = true)
    public Result<String> createResponseInstance(@RequestBody @NotNull @Valid ResponseInstances responseInstances){
        String id = responseInstanceService.createResponseInstance(responseInstances);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseInstance",desc = "updateResponseInstance")
    @ApiParam(name = "responseInstance",desc = "responseInstance",required = true)
    public Result<Void> updateResponseInstance(@RequestBody @NotNull @Valid ResponseInstances responseInstances){
        responseInstanceService.updateResponseInstance(responseInstances);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseInstance",desc = "deleteResponseInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseInstance(@NotNull String id){
        responseInstanceService.deleteResponseInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseInstance",desc = "findResponseInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseInstances> findResponseInstance(@NotNull String id){
        ResponseInstances responseInstances = responseInstanceService.findResponseInstance(id);

        return Result.ok(responseInstances);
    }

    @RequestMapping(path="/findAllResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseInstance",desc = "findAllResponseInstance")
    public Result<List<ResponseInstances>> findAllResponseInstance(){
        List<ResponseInstances> responseInstancesList = responseInstanceService.findAllResponseInstance();

        return Result.ok(responseInstancesList);
    }


    @RequestMapping(path = "/findResponseInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseInstanceList",desc = "findResponseInstanceList")
    @ApiParam(name = "responseInstanceQuery",desc = "responseInstanceQuery",required = true)
    public Result<List<ResponseInstances>> findResponseInstanceList(@RequestBody @Valid @NotNull ResponseInstanceQuery responseInstanceQuery){
        List<ResponseInstances> responseInstancesList = responseInstanceService.findResponseInstanceList(responseInstanceQuery);

        return Result.ok(responseInstancesList);
    }


    @RequestMapping(path = "/findResponseInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseInstancePage",desc = "findResponseInstancePage")
    @ApiParam(name = "responseInstanceQuery",desc = "responseInstanceQuery",required = true)
    public Result<Pagination<ResponseInstances>> findResponseInstancePage(@RequestBody @Valid @NotNull ResponseInstanceQuery responseInstanceQuery){
        Pagination<ResponseInstances> pagination = responseInstanceService.findResponseInstancePage(responseInstanceQuery);

        return Result.ok(pagination);
    }

}
