package io.tiklab.postin.api.http.test.instance.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.test.instance.model.RequestInstance;
import io.tiklab.postin.api.http.test.instance.model.RequestInstanceQuery;
import io.tiklab.postin.api.http.test.instance.service.RequestInstanceService;
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
@RequestMapping("/requestInstance")
//@Api(name = "RequestInstanceController",desc = "测试实例-请求管理")
public class RequestInstanceController {

    private static Logger logger = LoggerFactory.getLogger(RequestInstanceController.class);

    @Autowired
    private RequestInstanceService requestInstanceService;

    @RequestMapping(path="/createRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "createRequestInstance",desc = "createRequestInstance")
//    @ApiParam(name = "requestInstance",desc = "requestInstance",required = true)
    public Result<String> createRequestInstance(@RequestBody @NotNull @Valid RequestInstance requestInstance){
        String id = requestInstanceService.createRequestInstance(requestInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "updateRequestInstance",desc = "updateRequestInstance")
//    @ApiParam(name = "requestInstance",desc = "requestInstance",required = true)
    public Result<Void> updateRequestInstance(@RequestBody @NotNull @Valid RequestInstance requestInstance){
        requestInstanceService.updateRequestInstance(requestInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteRequestInstance",desc = "deleteRequestInstance")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestInstance(@NotNull String id){
        requestInstanceService.deleteRequestInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findRequestInstance",desc = "findRequestInstance")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestInstance> findRequestInstance(@NotNull String id){
        RequestInstance requestInstance = requestInstanceService.findRequestInstance(id);

        return Result.ok(requestInstance);
    }

    @RequestMapping(path="/findAllRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllRequestInstance",desc = "findAllRequestInstance")
    public Result<List<RequestInstance>> findAllRequestInstance(){
        List<RequestInstance> requestInstanceList = requestInstanceService.findAllRequestInstance();

        return Result.ok(requestInstanceList);
    }


    @RequestMapping(path = "/findRequestInstanceList",method = RequestMethod.POST)
//    @ApiMethod(name = "findRequestInstanceList",desc = "findRequestInstanceList")
//    @ApiParam(name = "requestInstanceQuery",desc = "requestInstanceQuery",required = true)
    public Result<List<RequestInstance>> findRequestInstanceList(@RequestBody @Valid @NotNull RequestInstanceQuery requestInstanceQuery){
        List<RequestInstance> requestInstanceList = requestInstanceService.findRequestInstanceList(requestInstanceQuery);

        return Result.ok(requestInstanceList);
    }


    @RequestMapping(path = "/findRequestInstancePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findRequestInstancePage",desc = "findRequestInstancePage")
//    @ApiParam(name = "requestInstanceQuery",desc = "requestInstanceQuery",required = true)
    public Result<Pagination<RequestInstance>> findRequestInstancePage(@RequestBody @Valid @NotNull RequestInstanceQuery requestInstanceQuery){
        Pagination<RequestInstance> pagination = requestInstanceService.findRequestInstancePage(requestInstanceQuery);

        return Result.ok(pagination);
    }

}
