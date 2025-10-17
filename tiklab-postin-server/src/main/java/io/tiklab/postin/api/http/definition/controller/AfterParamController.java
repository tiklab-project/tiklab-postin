package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.api.http.definition.model.AfterParam;
import io.tiklab.postin.api.http.definition.model.AfterParamQuery;
import io.tiklab.postin.api.http.definition.service.AfterParamService;
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
 * 后置 控制器
 */
@RestController
@RequestMapping("/afterParam")
//@Api(name = "AfterParamController",desc = "后置管理")
public class AfterParamController {

    private static Logger logger = LoggerFactory.getLogger(AfterParamController.class);

    @Autowired
    private AfterParamService afterParamService;

    @RequestMapping(path="/createAfterParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createAfterParam",desc = "创建后置")
//    @ApiParam(name = "afterParam",desc = "后置DTO",required = true)
    public Result<String> createAfterParam(@RequestBody @NotNull @Valid AfterParam afterParam){
        String id = afterParamService.createAfterParam(afterParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAfterParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateAfterParam",desc = "更新后置")
//    @ApiParam(name = "afterParam",desc = "后置DTO",required = true)
    public Result<Void> updateAfterParam(@RequestBody @NotNull @Valid AfterParam afterParam){
        afterParamService.updateAfterParam(afterParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAfterParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteAfterParam",desc = "根据ID删除后置")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteAfterParam(@NotNull String id){
        afterParamService.deleteAfterParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAfterParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAfterParam",desc = "根据ID查找后置")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<AfterParam> findAfterParam(@NotNull String id){
        AfterParam afterParam = afterParamService.findAfterParam(id);

        return Result.ok(afterParam);
    }

    @RequestMapping(path = "/findAfterParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findAfterParamList",desc = "根据查询参数查找后置列表")
//    @ApiParam(name = "afterParamQuery",desc = "afterParamQuery",required = true)
    public Result<List<AfterParam>> findFormParamList(@RequestBody @Valid @NotNull AfterParamQuery afterParamQuery){
        List<AfterParam> afterParamList = afterParamService.findAfterParamList(afterParamQuery);

        return Result.ok(afterParamList);
    }

    @RequestMapping(path = "/updateAfterParamSort",method = RequestMethod.POST)
    public Result<Void> updateAfterParamSort( @NotNull String id,  @NotNull Integer newSort){
        afterParamService.updateAfterParamSort(id, newSort);

        return Result.ok();
    }


}
