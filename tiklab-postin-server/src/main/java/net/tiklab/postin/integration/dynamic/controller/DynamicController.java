package net.tiklab.postin.integration.dynamic.controller;


import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.postin.integration.dynamic.entity.DynamicEntity;
import net.tiklab.postin.integration.dynamic.model.Dynamic;
import net.tiklab.postin.integration.dynamic.model.DynamicQuery;
import net.tiklab.postin.integration.dynamic.service.DynamicService;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
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

@RestController
@RequestMapping("/dynamic")
@Api(name = "DynamicController",desc = "动态管理")
public class DynamicController {

    private static Logger logger = LoggerFactory.getLogger(DynamicEntity.class);
    
    @Autowired
    private DynamicService dynamicService;


    @RequestMapping(path="/createDynamic",method = RequestMethod.POST)
    @ApiMethod(name = "createDynamic",desc = "创建动态")
    @ApiParam(name = "dynamic",desc = "动态DTO",required = true)
    public Result<String> createDynamic(@RequestBody @NotNull @Valid @ApiParam Dynamic dynamic){
        String id = dynamicService.createDynamic(dynamic);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateDynamic",method = RequestMethod.POST)
    @ApiMethod(name = "updateDynamic",desc = "更新动态")
    @ApiParam(name = "dynamic",desc = "动态DTO",required = true)
    public Result<Void> updateDynamic(@RequestBody @NotNull @Valid @ApiParam Dynamic dynamic){
        dynamicService.updateDynamic(dynamic);

        return Result.ok();
    }

    @RequestMapping(path="/deleteDynamic",method = RequestMethod.POST)
    @ApiMethod(name = "deleteDynamic",desc = "根据动态ID删除动态")
    @ApiParam(name = "id",desc = "动态ID",required = true)
    public Result<Void> deleteDynamic(@NotNull String id){
        dynamicService.deleteDynamic(id);

        return Result.ok();
    }

    @RequestMapping(path="/findDynamic",method = RequestMethod.POST)
    @ApiMethod(name = "findDynamic",desc = "根据动态ID查找动态")
    @ApiParam(name = "id",desc = "动态ID",required = true)
    public Result<Dynamic> findDynamic(@NotNull String id){
        Dynamic dynamic = dynamicService.findDynamic(id);

        return Result.ok(dynamic);
    }

    @RequestMapping(path="/findAllDynamic",method = RequestMethod.POST)
    @ApiMethod(name = "findAllDynamic",desc = "查找所有动态")
    public Result<List<Dynamic>> findAllDynamic(){
        List<Dynamic> dynamicList = dynamicService.findAllDynamic();

        return Result.ok(dynamicList);
    }


    @RequestMapping(path = "/findDynamicList",method = RequestMethod.POST)
    @ApiMethod(name = "findDynamicList",desc = "根据查询对象查询动态列表")
    @ApiParam(name = "dynamicQuery",desc = "查询对象",required = true)
    public Result<List<Dynamic>> findDynamicList(@RequestBody @Valid @NotNull DynamicQuery dynamicQuery){
        List<Dynamic> dynamicList = dynamicService.findDynamicList(dynamicQuery);

        return Result.ok(dynamicList);
    }


    @RequestMapping(path = "/findDynamicPage",method = RequestMethod.POST)
    @ApiMethod(name = "findDynamicPage",desc = "根据查询对象按分页查询动态")
    @ApiParam(name = "dynamicQuery",desc = "查询对象",required = true)
    public Result<Pagination<Dynamic>> findDynamicPage(@RequestBody @Valid @NotNull DynamicQuery dynamicQuery){
        Pagination<Dynamic> pagination = dynamicService.findDynamicPage(dynamicQuery);

        return Result.ok(pagination);
    }

}
