package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.unit.cases.model.RawResponseUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RawResponseUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.RawResponseUnitService;
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
 * 响应中raw 控制器
 */
@RestController
@RequestMapping("/rawResponseUnit")
//@Api(name = "RawResponseController",desc = "接口用例步骤RawResponseController")
public class RawResponseUnitController {

    private static Logger logger = LoggerFactory.getLogger(RawResponseUnitController.class);

    @Autowired
    private RawResponseUnitService rawResponseUnitService;

    @RequestMapping(path="/createRawResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "createRawResponse",desc = "创建响应中raw")
//    @ApiParam(name = "rawResponseUnit",desc = "rawResponse",required = true)
    public Result<String> createRawResponse(@RequestBody @NotNull @Valid RawResponseUnit rawResponseUnit){
        String id = rawResponseUnitService.createRawResponse(rawResponseUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "updateRawResponse",desc = "更新响应中raw")
//    @ApiParam(name = "rawResponseUnit",desc = "rawResponse",required = true)
    public Result<Void> updateRawResponse(@RequestBody @NotNull @Valid RawResponseUnit rawResponseUnit){
        rawResponseUnitService.updateRawResponse(rawResponseUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteRawResponse",desc = "删除响应中raw")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawResponse(@NotNull String id){
        rawResponseUnitService.deleteRawResponse(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawResponse",desc = "查找响应中raw")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawResponseUnit> findRawResponse(@NotNull String id){
        RawResponseUnit rawResponseUnit = rawResponseUnitService.findRawResponse(id);

        return Result.ok(rawResponseUnit);
    }

    @RequestMapping(path="/findAllRawResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllRawResponse",desc = "查找所有响应中raw")
    public Result<List<RawResponseUnit>> findAllRawResponse(){
        List<RawResponseUnit> rawResponseUnitList = rawResponseUnitService.findAllRawResponse();

        return Result.ok(rawResponseUnitList);
    }

    @RequestMapping(path = "/findRawResponseList",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawResponseList",desc = "根据查询参数查询响应中raw列表")
//    @ApiParam(name = "rawResponseUnitQuery",desc = "rawResponseQuery",required = true)
    public Result<List<RawResponseUnit>> findRawResponseList(@RequestBody @Valid @NotNull RawResponseUnitQuery rawResponseUnitQuery){
        List<RawResponseUnit> rawResponseUnitList = rawResponseUnitService.findRawResponseList(rawResponseUnitQuery);

        return Result.ok(rawResponseUnitList);
    }

    @RequestMapping(path = "/findRawResponsePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findRawResponsePage",desc = "根据查询参数按分页查询响应中raw")
//    @ApiParam(name = "rawResponseUnitQuery",desc = "rawResponseQuery",required = true)
    public Result<Pagination<RawResponseUnit>> findRawResponsePage(@RequestBody @Valid @NotNull RawResponseUnitQuery rawResponseUnitQuery){
        Pagination<RawResponseUnit> pagination = rawResponseUnitService.findRawResponsePage(rawResponseUnitQuery);

        return Result.ok(pagination);
    }

}
