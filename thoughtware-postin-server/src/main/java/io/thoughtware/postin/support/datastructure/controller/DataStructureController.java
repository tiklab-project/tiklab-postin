package io.thoughtware.postin.support.datastructure.controller;

import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.support.datastructure.model.DataStructure;
import io.thoughtware.postin.support.datastructure.model.DataStructureQuery;
import io.thoughtware.postin.support.datastructure.service.DataStructureService;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * 数据结构 控制器
 */
@RestController
@RequestMapping("/dataStructure")
@Api(name = "DataStructureController",desc = "数据结构管理")
public class DataStructureController {

    private static Logger logger = LoggerFactory.getLogger(DataStructureController.class);

    @Autowired
    private DataStructureService dataStructureService;

    @RequestMapping(path="/createDataStructure",method = RequestMethod.POST)
    @ApiMethod(name = "createDataStructure",desc = "添加数据结构")
    @ApiParam(name = "dataStructure",desc = "添加数据结构对象",required = true)
    public Result<String> createDataStructure(@RequestBody @NotNull @Valid DataStructure dataStructure){
        String id = dataStructureService.createDataStructure(dataStructure);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateDataStructure",method = RequestMethod.POST)
    @ApiMethod(name = "updateDataStructure",desc = "更新数据结构")
    @ApiParam(name = "dataStructure",desc = "更新数据结构对象",required = true)
    public Result<Void> updateDataStructure(@RequestBody @NotNull @Valid DataStructure dataStructure){
        dataStructureService.updateDataStructure(dataStructure);

        return Result.ok();
    }

    @RequestMapping(path="/deleteDataStructure",method = RequestMethod.POST)
    @ApiMethod(name = "deleteDataStructure",desc = "根据id删除数据结构")
    @ApiParam(name = "id",desc = "删除id",required = true)
    public Result<Void> deleteDataStructure(@NotNull String id){
        dataStructureService.deleteDataStructure(id);

        return Result.ok();
    }

    @RequestMapping(path="/findDataStructure",method = RequestMethod.POST)
    @ApiMethod(name = "findDataStructure",desc = "根据id查询数据结构")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<DataStructure> findDataStructure(@NotNull String id){
        DataStructure dataStructure = dataStructureService.findDataStructure(id);

        return Result.ok(dataStructure);
    }

    @RequestMapping(path="/findAllDataStructure",method = RequestMethod.POST)
    @ApiMethod(name = "findAllDataStructure",desc = "查询所有数据结构")
    public Result<List<DataStructure>> findAllDataStructure(){
        List<DataStructure> dataStructureList = dataStructureService.findAllDataStructure();

        return Result.ok(dataStructureList);
    }

    @RequestMapping(path = "/findDataStructureList",method = RequestMethod.POST)
    @ApiMethod(name = "findDataStructureList",desc = "根据查询对象查询数据结构")
    @ApiParam(name = "dataStructureQuery",desc = "查询对象",required = true)
    public Result<List<DataStructure>> findDataStructureList(@RequestBody @Valid @NotNull DataStructureQuery dataStructureQuery){
        List<DataStructure> dataStructureList = dataStructureService.findDataStructureList(dataStructureQuery);

        return Result.ok(dataStructureList);
    }

    @RequestMapping(path = "/findDataStructurePage",method = RequestMethod.POST)
    @ApiMethod(name = "findDataStructurePage",desc = "根据查询对象分页查询数据结构")
    @ApiParam(name = "dataStructureQuery",desc = "查询对象",required = true)
    public Result<Pagination<DataStructure>> findDataStructurePage(@RequestBody @Valid @NotNull DataStructureQuery dataStructureQuery){
        Pagination<DataStructure> pagination = dataStructureService.findDataStructurePage(dataStructureQuery);

        return Result.ok(pagination);
    }

}
