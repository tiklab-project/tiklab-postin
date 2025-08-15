package io.tiklab.postin.support.basedata.parameter.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.support.basedata.parameter.model.BodyParam;
import io.tiklab.postin.support.basedata.parameter.model.BodyParamQuery;
import io.tiklab.postin.support.basedata.parameter.service.BodyParamService;
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
 * 基础数据中的body公共参数 (formdata/formurlencoded) 控制器
 */
@RestController
@RequestMapping("/bodyParam")
//@Api(name = "BodyParamController",desc = "body参数管理")
public class BodyParamController {

    private static Logger logger = LoggerFactory.getLogger(BodyParamController.class);

    @Autowired
    private BodyParamService bodyParamService;

    @RequestMapping(path="/createBodyParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createBodyParam",desc = "创建body-data参数")
//    @ApiParam(name = "bodyParam",desc = "bodyParam",required = true)
    public Result<String> createBodyParam(@RequestBody @NotNull @Valid BodyParam bodyParam){
        String id = bodyParamService.createBodyParam(bodyParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateBodyParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateBodyParam",desc = "更新body-data参数")
//    @ApiParam(name = "bodyParam",desc = "bodyParam",required = true)
    public Result<Void> updateBodyParam(@RequestBody @NotNull @Valid BodyParam bodyParam){
        bodyParamService.updateBodyParam(bodyParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteBodyParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteBodyParam",desc = "删除body-data参数")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteBodyParam(@NotNull String id){
        bodyParamService.deleteBodyParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findBodyParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findBodyParam",desc = "根据id查找body-data参数")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<BodyParam> findBodyParam(@NotNull String id){
        BodyParam bodyParam = bodyParamService.findBodyParam(id);

        return Result.ok(bodyParam);
    }

    @RequestMapping(path="/findAllBodyParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllBodyParam",desc = "查找所有body-data参数")
    public Result<List<BodyParam>> findAllBodyParam(){
        List<BodyParam> bodyParamList = bodyParamService.findAllBodyParam();

        return Result.ok(bodyParamList);
    }


    @RequestMapping(path = "/findBodyParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findBodyParamList",desc = "根据查询参数查找body-data参数")
//    @ApiParam(name = "bodyParamQuery",desc = "bodyParamQuery",required = true)
    public Result<List<BodyParam>> findBodyParamList(@RequestBody @Valid @NotNull BodyParamQuery bodyParamQuery){
        List<BodyParam> bodyParamList = bodyParamService.findBodyParamList(bodyParamQuery);

        return Result.ok(bodyParamList);
    }


    @RequestMapping(path = "/findBodyParamPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findBodyParamPage",desc = "根据查询参数按分页查找body-data参数")
//    @ApiParam(name = "bodyParamQuery",desc = "bodyParamQuery",required = true)
    public Result<Pagination<BodyParam>> findBodyParamPage(@RequestBody @Valid @NotNull BodyParamQuery bodyParamQuery){
        Pagination<BodyParam> pagination = bodyParamService.findBodyParamPage(bodyParamQuery);

        return Result.ok(pagination);
    }

}
