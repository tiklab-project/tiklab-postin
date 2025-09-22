package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.PathParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.PathParamUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.PathParamUnitService;
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
 * 路径 控制器
 */
@RestController
@RequestMapping("/pathParamUnit")
//@Api(name = "PathParamUnitController",desc = "路径管理")
public class PathParamUnitController {

    private static Logger logger = LoggerFactory.getLogger(PathParamUnitController.class);

    @Autowired
    private PathParamUnitService pathParamUnitService;

    @RequestMapping(path="/createPathParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "createPathParamUnit",desc = "创建路径")
//    @ApiParam(name = "pathParamUnit",desc = "路径DTO",required = true)
    public Result<String> createPathParamUnit(@RequestBody @NotNull @Valid PathParamUnit pathParamUnit){
        String id = pathParamUnitService.createPathParamUnit(pathParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePathParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "updatePathParamUnit",desc = "更新路径")
//    @ApiParam(name = "pathParamUnit",desc = "路径DTO",required = true)
    public Result<Void> updatePathParamUnit(@RequestBody @NotNull @Valid PathParamUnit pathParamUnit){
        pathParamUnitService.updatePathParamUnit(pathParamUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deletePathParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "deletePathParamUnit",desc = "根据ID删除路径")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deletePathParamUnit(@NotNull String id){
        pathParamUnitService.deletePathParamUnit(id);

        return Result.ok();
    }

    @RequestMapping(path="/findPathParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "findPathParamUnit",desc = "根据ID查找路径")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<PathParamUnit> findPathParamUnit(@NotNull String id){
        PathParamUnit pathParamUnit = pathParamUnitService.findPathParamUnit(id);

        return Result.ok(pathParamUnit);
    }

    @RequestMapping(path="/findAllPathParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllPathParamUnit",desc = "查找所有路径列表")
    public Result<List<PathParamUnit>> findAllPathParamUnit(){
        List<PathParamUnit> pathParamUnitList = pathParamUnitService.findAllPathParamUnit();

        return Result.ok(pathParamUnitList);
    }


    @RequestMapping(path = "/findPathParamUnitList",method = RequestMethod.POST)
//    @ApiMethod(name = "findPathParamUnitList",desc = "根据查询对象查询路径列表")
//    @ApiParam(name = "pathParamUnitQuery",desc = "查询对象",required = true)
    public Result<List<PathParamUnit>> findPathParamUnitList(@RequestBody @Valid @NotNull PathParamUnitQuery pathParamUnitQuery){
        List<PathParamUnit> pathParamUnitList = pathParamUnitService.findPathParamUnitList(pathParamUnitQuery);

        return Result.ok(pathParamUnitList);
    }


    @RequestMapping(path = "/findPathParamUnitPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findPathParamUnitPage",desc = "根据查询对象按分页查询路径列表")
//    @ApiParam(name = "pathParamUnitQuery",desc = "查询对象",required = true)
    public Result<Pagination<PathParamUnit>> findPathParamUnitPage(@RequestBody @Valid @NotNull PathParamUnitQuery pathParamUnitQuery){
        Pagination<PathParamUnit> pagination = pathParamUnitService.findPathParamUnitPage(pathParamUnitQuery);

        return Result.ok(pagination);
    }

}
