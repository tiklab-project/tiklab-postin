package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonParamUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.JsonParamUnitService;
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
 * 响应中 json 控制器
 */
@RestController
@RequestMapping("/jsonParamUnit")
//@Api(name = "JsonParamController",desc = "接口用例步骤json")
public class JsonParamUnitController {

    private static Logger logger = LoggerFactory.getLogger(JsonParamUnitController.class);

    @Autowired
    private JsonParamUnitService jsonParamUnitService;

    @RequestMapping(path="/createJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createJsonParam",desc = "创建json")
//    @ApiParam(name = "jsonParamUnit",desc = "jsonParam",required = true)
    public Result<String> createJsonParam(@RequestBody @NotNull @Valid JsonParamUnit jsonParamUnit){
        String id = jsonParamUnitService.createJsonParam(jsonParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateJsonParam",desc = "更新json")
//    @ApiParam(name = "jsonParamUnit",desc = "jsonParam",required = true)
    public Result<Void> updateJsonParam(@RequestBody @NotNull @Valid JsonParamUnit jsonParamUnit){
        jsonParamUnitService.updateJsonParam(jsonParamUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteJsonParam",desc = "删除json")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteJsonParam(@NotNull String id){
        jsonParamUnitService.deleteJsonParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonParam",desc = "根据id查找json")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<JsonParamUnit> findJsonParam(@NotNull String id){
        JsonParamUnit jsonParamUnit = jsonParamUnitService.findJsonParam(id);

        return Result.ok(jsonParamUnit);
    }

    @RequestMapping(path="/findAllJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllJsonParam",desc = "查找所有json")
    public Result<List<JsonParamUnit>> findAllJsonParam(){
        List<JsonParamUnit> jsonParamUnitList = jsonParamUnitService.findAllJsonParam();

        return Result.ok(jsonParamUnitList);
    }

    @RequestMapping(path = "/findJsonParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonParamList",desc = "findJsonParamList")
//    @ApiParam(name = "jsonParamUnitQuery",desc = "jsonParamQuery",required = true)
    public Result<List<JsonParamUnit>> findJsonParamList(@RequestBody @Valid @NotNull JsonParamUnitQuery jsonParamUnitQuery){
        List<JsonParamUnit> jsonParamUnitList = jsonParamUnitService.findJsonParamList(jsonParamUnitQuery);

        return Result.ok(jsonParamUnitList);
    }


    @RequestMapping(path = "/findJsonParamPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonParamPage",desc = "根据查询参数按分页查询json")
//    @ApiParam(name = "jsonParamUnitQuery",desc = "jsonParamQuery",required = true)
    public Result<Pagination<JsonParamUnit>> findJsonParamPage(@RequestBody @Valid @NotNull JsonParamUnitQuery jsonParamUnitQuery){
        Pagination<JsonParamUnit> pagination = jsonParamUnitService.findJsonParamPage(jsonParamUnitQuery);

        return Result.ok(pagination);
    }

}
