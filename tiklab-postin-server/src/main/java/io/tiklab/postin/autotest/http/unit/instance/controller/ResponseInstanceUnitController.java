package io.tiklab.postin.autotest.http.unit.instance.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.instance.model.ResponseInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.ResponseInstanceUnitQuery;
import io.tiklab.postin.autotest.http.unit.instance.service.ResponseInstanceUnitService;
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
 * 响应数据实例 控制器
 */
@RestController
@RequestMapping("/responseInstanceUnit")
//@Api(name = "ResponseInstanceController",desc = "测试实例——响应管理")
public class ResponseInstanceUnitController {

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceUnitController.class);

    @Autowired
    private ResponseInstanceUnitService responseInstanceUnitService;

    @RequestMapping(path="/createResponseInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "createResponseInstance",desc = "创建响应数据实例")
//    @ApiParam(name = "responseInstance",desc = "responseInstance",required = true)
    public Result<String> createResponseInstance(@RequestBody @NotNull @Valid ResponseInstanceUnit responseInstanceUnit){
        String id = responseInstanceUnitService.createResponseInstance(responseInstanceUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "updateResponseInstance",desc = "更新响应数据实例")
//    @ApiParam(name = "responseInstance",desc = "responseInstance",required = true)
    public Result<Void> updateResponseInstance(@RequestBody @NotNull @Valid ResponseInstanceUnit responseInstanceUnit){
        responseInstanceUnitService.updateResponseInstance(responseInstanceUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteResponseInstance",desc = "删除响应数据实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseInstance(@NotNull String id){
        responseInstanceUnitService.deleteResponseInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findResponseInstance",desc = "根据id查找响应数据实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseInstanceUnit> findResponseInstance(@NotNull String id){
        ResponseInstanceUnit responseInstanceUnit = responseInstanceUnitService.findResponseInstance(id);

        return Result.ok(responseInstanceUnit);
    }

    @RequestMapping(path="/findAllResponseInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllResponseInstance",desc = "查找所有响应数据实例")
    public Result<List<ResponseInstanceUnit>> findAllResponseInstance(){
        List<ResponseInstanceUnit> responseInstanceUnitList = responseInstanceUnitService.findAllResponseInstance();

        return Result.ok(responseInstanceUnitList);
    }

    @RequestMapping(path = "/findResponseInstanceList",method = RequestMethod.POST)
//    @ApiMethod(name = "findResponseInstanceList",desc = "根据查询参数查询响应数据实例列表")
//    @ApiParam(name = "responseInstanceQuery",desc = "responseInstanceQuery",required = true)
    public Result<List<ResponseInstanceUnit>> findResponseInstanceList(@RequestBody @Valid @NotNull ResponseInstanceUnitQuery responseInstanceUnitQuery){
        List<ResponseInstanceUnit> responseInstanceUnitList = responseInstanceUnitService.findResponseInstanceList(responseInstanceUnitQuery);

        return Result.ok(responseInstanceUnitList);
    }

    @RequestMapping(path = "/findResponseInstancePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findResponseInstancePage",desc = "根据查询参数按分页查询响应数据实例")
//    @ApiParam(name = "responseInstanceQuery",desc = "responseInstanceQuery",required = true)
    public Result<Pagination<ResponseInstanceUnit>> findResponseInstancePage(@RequestBody @Valid @NotNull ResponseInstanceUnitQuery responseInstanceUnitQuery){
        Pagination<ResponseInstanceUnit> pagination = responseInstanceUnitService.findResponseInstancePage(responseInstanceUnitQuery);

        return Result.ok(pagination);
    }

}
