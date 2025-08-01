package io.tiklab.postin.autotest.http.unit.instance.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.instance.model.RequestInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.RequestInstanceUnitQuery;
import io.tiklab.postin.autotest.http.unit.instance.service.RequestInstanceUnitService;
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
 * 请求数据实例 控制器
 */
@RestController
@RequestMapping("/requestInstanceUnit")
//@Api(name = "RequestInstanceController",desc = "测试实例——请求管理")
public class RequestInstanceUnitController {

    private static Logger logger = LoggerFactory.getLogger(RequestInstanceUnitController.class);

    @Autowired
    private RequestInstanceUnitService requestInstanceUnitService;

    @RequestMapping(path="/createRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "createRequestInstance",desc = "创建请求数据实例")
//    @ApiParam(name = "requestInstance",desc = "requestInstance",required = true)
    public Result<String> createRequestInstance(@RequestBody @NotNull @Valid RequestInstanceUnit requestInstanceUnit){
        String id = requestInstanceUnitService.createRequestInstance(requestInstanceUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "updateRequestInstance",desc = "更新请求数据实例")
//    @ApiParam(name = "requestInstance",desc = "requestInstance",required = true)
    public Result<Void> updateRequestInstance(@RequestBody @NotNull @Valid RequestInstanceUnit requestInstanceUnit){
        requestInstanceUnitService.updateRequestInstance(requestInstanceUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteRequestInstance",desc = "删除请求数据实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestInstance(@NotNull String id){
        requestInstanceUnitService.deleteRequestInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findRequestInstance",desc = "查找请求数据实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestInstanceUnit> findRequestInstance(@NotNull String id){
        RequestInstanceUnit requestInstanceUnit = requestInstanceUnitService.findRequestInstance(id);

        return Result.ok(requestInstanceUnit);
    }

    @RequestMapping(path="/findAllRequestInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllRequestInstance",desc = "查找所有请求数据实例")
    public Result<List<RequestInstanceUnit>> findAllRequestInstance(){
        List<RequestInstanceUnit> requestInstanceUnitList = requestInstanceUnitService.findAllRequestInstance();

        return Result.ok(requestInstanceUnitList);
    }

    @RequestMapping(path = "/findRequestInstanceList",method = RequestMethod.POST)
//    @ApiMethod(name = "findRequestInstanceList",desc = "根据查询参数查询请求数据实例列表")
//    @ApiParam(name = "requestInstanceQuery",desc = "requestInstanceQuery",required = true)
    public Result<List<RequestInstanceUnit>> findRequestInstanceList(@RequestBody @Valid @NotNull RequestInstanceUnitQuery requestInstanceUnitQuery){
        List<RequestInstanceUnit> requestInstanceUnitList = requestInstanceUnitService.findRequestInstanceList(requestInstanceUnitQuery);

        return Result.ok(requestInstanceUnitList);
    }

    @RequestMapping(path = "/findRequestInstancePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findRequestInstancePage",desc = "根据查询参数按分页查询请求数据实例")
//    @ApiParam(name = "requestInstanceQuery",desc = "requestInstanceQuery",required = true)
    public Result<Pagination<RequestInstanceUnit>> findRequestInstancePage(@RequestBody @Valid @NotNull RequestInstanceUnitQuery requestInstanceUnitQuery){
        Pagination<RequestInstanceUnit> pagination = requestInstanceUnitService.findRequestInstancePage(requestInstanceUnitQuery);

        return Result.ok(pagination);
    }

}
