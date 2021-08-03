package com.doublekit.apibox.datastructure.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.datastructure.model.JsonParamDS;
import com.doublekit.apibox.datastructure.model.JsonParamDSQuery;
import com.doublekit.apibox.datastructure.service.JsonParamDSService;
import com.doublekit.common.Pagination;
import com.doublekit.common.Result;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
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
 * JsonParamDSController
 */
@RestController
@RequestMapping("/jsonParamDS")
@Api(name = "JsonParamDSController",desc = "json 类型的数据结构内容管理")
public class JsonParamDSController {

    private static Logger logger = LoggerFactory.getLogger(JsonParamDSController.class);

    @Autowired
    private JsonParamDSService jsonParamDSService;

    @RequestMapping(path="/createJsonParamDS",method = RequestMethod.POST)
    @ApiMethod(name = "createJsonParamDS",desc = "添加json 类型的数据结构内容")
    @ApiParam(name = "jsonParamDS",desc = "添加对象",required = true)
    public Result<String> createJsonParamDS(@RequestBody @NotNull @Valid JsonParamDS jsonParamDS){
        String id = jsonParamDSService.createJsonParamDS(jsonParamDS);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParamDS",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonParamDS",desc = "修改json 类型的数据结构内容")
    @ApiParam(name = "jsonParamDS",desc = "修改对象",required = true)
    public Result<Void> updateJsonParamDS(@RequestBody @NotNull @Valid JsonParamDS jsonParamDS){
        jsonParamDSService.updateJsonParamDS(jsonParamDS);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonParamDS",method = RequestMethod.POST)
    @ApiMethod(name = "deleteJsonParamDS",desc = "删除json 类型的数据结构内容")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteJsonParamDS(@NotNull String id){
        jsonParamDSService.deleteJsonParamDS(id);

        return Result.ok();
    }


    @RequestMapping(path="/findJsonParamDS",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamDS",desc = "通过id查询json 类型的数据结构内容")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<JsonParamDS> findJsonParamDS(@NotNull String id){
        JsonParamDS jsonParamDS = jsonParamDSService.findJsonParamDS(id);

        return Result.ok(jsonParamDS);
    }

    @RequestMapping(path="/findAllJsonParamDS",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonParamDS",desc = "查询所有json 类型的数据结构内容")
    public Result<List<JsonParamDS>> findAllJsonParamDS(){
        List<JsonParamDS> jsonParamDSList = jsonParamDSService.findAllJsonParamDS();

        return Result.ok(jsonParamDSList);
    }

    @RequestMapping(path = "/findJsonParamDSList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamDSList",desc = "通过查询对象 查询json 类型的数据结构内容")
    @ApiParam(name = "jsonParamDSQuery",desc = "jsonParamDSQuery",required = true)
    public Result<List<JsonParamDS>> findJsonParamDSList(@RequestBody @Valid @NotNull JsonParamDSQuery jsonParamDSQuery){
        List<JsonParamDS> jsonParamDSList = jsonParamDSService.findJsonParamDSList(jsonParamDSQuery);

        return Result.ok(jsonParamDSList);
    }

    @RequestMapping(path = "/findJsonParamDSListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamDSList",desc = "通过查询对象 查询json 类型的数据结构内容树")
    @ApiParam(name = "jsonParamDSQuery",desc = "jsonParamDSQuery",required = true)
    public Result<List<JsonParamDS>> findJsonParamDSListTree(@RequestBody @Valid @NotNull JsonParamDSQuery jsonParamDSQuery){
        List<JsonParamDS> jsonParamDSList = jsonParamDSService.findJsonParamDSListTree(jsonParamDSQuery);

        return Result.ok(jsonParamDSList);
    }

    @RequestMapping(path = "/findJsonParamDSPage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamDSPage",desc = "通过查询对象分页查询json 类型的数据结构内容")
    @ApiParam(name = "jsonParamDSQuery",desc = "jsonParamDSQuery",required = true)
    public Result<Pagination<JsonParamDS>> findJsonParamDSPage(@RequestBody @Valid @NotNull JsonParamDSQuery jsonParamDSQuery){
        Pagination<JsonParamDS> pagination = jsonParamDSService.findJsonParamDSPage(jsonParamDSQuery);

        return Result.ok(pagination);
    }

}
