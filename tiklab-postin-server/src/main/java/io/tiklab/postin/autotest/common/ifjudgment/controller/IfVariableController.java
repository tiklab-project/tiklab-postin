package io.tiklab.postin.autotest.common.ifjudgment.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariable;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariableQuery;
import io.tiklab.postin.autotest.common.ifjudgment.service.IfVariableService;
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
 * 值判断 控制器
 */
@RestController
@RequestMapping("/ifVariable")
//@Api(name = "IfVariableController",desc = "值判断管理")
public class IfVariableController {

    private static Logger logger = LoggerFactory.getLogger(IfVariableController.class);

    @Autowired
    private IfVariableService ifVariableService;

    @RequestMapping(path="/createIfVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "createIfVariable",desc = "创建值判断")
//    @ApiParam(name = "ifVariable",desc = "ifVariable",required = true)
    public Result<String> createIfVariable(@RequestBody @NotNull @Valid IfVariable ifVariable){
        String id = ifVariableService.createIfVariable(ifVariable);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateIfVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "updateIfVariable",desc = "修改值判断")
//    @ApiParam(name = "ifVariable",desc = "ifVariable",required = true)
    public Result<Void> updateIfVariable(@RequestBody @NotNull @Valid IfVariable ifVariable){
        ifVariableService.updateIfVariable(ifVariable);

        return Result.ok();
    }

    @RequestMapping(path="/deleteIfVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteIfVariable",desc = "删除值判断")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteIfVariable(@NotNull String id){
        ifVariableService.deleteIfVariable(id);

        return Result.ok();
    }

    @RequestMapping(path="/findIfVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "findIfVariable",desc = "通过id查询")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<IfVariable> findIfVariable(@NotNull String id){
        IfVariable ifVariable = ifVariableService.findIfVariable(id);

        return Result.ok(ifVariable);
    }


    @RequestMapping(path = "/findIfVariableList",method = RequestMethod.POST)
//    @ApiMethod(name = "findIfVariableList",desc = "通过查询对象查询")
//    @ApiParam(name = "ifVariableQuery",desc = "ifVariableQuery",required = true)
    public Result<List<IfVariable>> findIfVariableList(@RequestBody @Valid @NotNull IfVariableQuery ifVariableQuery){
            List<IfVariable> ifVariableList = ifVariableService.findIfVariableList(ifVariableQuery);

        return Result.ok(ifVariableList);
    }


}
