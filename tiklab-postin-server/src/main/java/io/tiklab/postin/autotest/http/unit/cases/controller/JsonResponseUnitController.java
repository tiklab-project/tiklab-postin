package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonResponseUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonResponseUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.JsonResponseUnitService;
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
 * 响应中json 控制器
 */
@RestController
@RequestMapping("/jsonResponseUnit")
//@Api(name = "JsonResponseController",desc = "接口用例步骤JsonResponseController")
public class JsonResponseUnitController {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseUnitController.class);

    @Autowired
    private JsonResponseUnitService jsonResponseUnitService;

    @RequestMapping(path="/createJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "createJsonResponse",desc = "创建响应中json")
//    @ApiParam(name = "jsonResponseUnit",desc = "jsonResponse",required = true)
    public Result<String> createJsonResponse(@RequestBody @NotNull @Valid JsonResponseUnit jsonResponseUnit){
        String id = jsonResponseUnitService.createJsonResponse(jsonResponseUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "updateJsonResponse",desc = "更新响应中json")
//    @ApiParam(name = "jsonResponseUnit",desc = "jsonResponse",required = true)
    public Result<Void> updateJsonResponse(@RequestBody @NotNull @Valid JsonResponseUnit jsonResponseUnit){
        jsonResponseUnitService.updateJsonResponse(jsonResponseUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteJsonResponse",desc = "删除响应中json")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteJsonResponse(@NotNull String id){
        jsonResponseUnitService.deleteJsonResponse(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonResponse",desc = "根据id查找响应中json")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<JsonResponseUnit> findJsonResponse(@NotNull String id){
        JsonResponseUnit jsonResponseUnit = jsonResponseUnitService.findJsonResponse(id);

        return Result.ok(jsonResponseUnit);
    }

    @RequestMapping(path="/findAllJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllJsonResponse",desc = "查找所有响应中json")
    public Result<List<JsonResponseUnit>> findAllJsonResponse(){
        List<JsonResponseUnit> jsonResponseUnitList = jsonResponseUnitService.findAllJsonResponse();

        return Result.ok(jsonResponseUnitList);
    }

    @RequestMapping(path = "/findJsonResponseList",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonResponseList",desc = "根据查询参数查找查询响应中json列表")
//    @ApiParam(name = "jsonResponseUnitQuery",desc = "jsonResponseQuery",required = true)
    public Result<List<JsonResponseUnit>> findJsonResponseList(@RequestBody @Valid @NotNull JsonResponseUnitQuery jsonResponseUnitQuery){
        List<JsonResponseUnit> jsonResponseUnitList = jsonResponseUnitService.findJsonResponseList(jsonResponseUnitQuery);

        return Result.ok(jsonResponseUnitList);
    }

    @RequestMapping(path = "/findJsonResponsePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonResponsePage",desc = "根据查询参数查找按分页查询响应中json")
//    @ApiParam(name = "jsonResponseUnitQuery",desc = "jsonResponseQuery",required = true)
    public Result<Pagination<JsonResponseUnit>> findJsonResponsePage(@RequestBody @Valid @NotNull JsonResponseUnitQuery jsonResponseUnitQuery){
        Pagination<JsonResponseUnit> pagination = jsonResponseUnitService.findJsonResponsePage(jsonResponseUnitQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findJsonResponseListTree",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonResponseListTree",desc = "根据查询对象查询json响应结果列表树")
//    @ApiParam(name = "jsonResponseUnitQuery",desc = "查询对象",required = true)
    public Result<List<JsonResponseUnit>> findJsonResponseListTree(@RequestBody @Valid @NotNull JsonResponseUnitQuery jsonResponseUnitQuery){
        List<JsonResponseUnit> jsonResponseUnitList = jsonResponseUnitService.findJsonResponseListTree(jsonResponseUnitQuery);

        return Result.ok(jsonResponseUnitList);
    }

}
