package io.tiklab.postin.support.datastructure.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.support.datastructure.model.EnumParam;
import io.tiklab.postin.support.datastructure.model.EnumParamQuery;
import io.tiklab.postin.support.datastructure.service.EnumParamService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * enum类型的数据结构 控制器
 */
@RestController
@RequestMapping("/enumParamDS")
//@Api(name = "EnumParamController",desc = "enum类型的数据结构")
public class EnumParamController {

    private static Logger logger = LoggerFactory.getLogger(EnumParamController.class);

    @Autowired
    private EnumParamService enumParamService;

    @RequestMapping(path="/createEnumParamDS",method = RequestMethod.POST)
//    @ApiMethod(name = "createEnumParam",desc = "创建枚举类型数据结构内容")
//    @ApiParam(name = "enumParam",desc = "添加对象",required = true)
    public Result<String> createEnumParam(@RequestBody @NotNull @Valid EnumParam enumParam){
        String id = enumParamService.createEnumParam(enumParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateEnumParamDS",method = RequestMethod.POST)
//    @ApiMethod(name = "updateEnumParam",desc = "更新枚举类型数据结构内容")
//    @ApiParam(name = "enumParam",desc = "更新对象",required = true)
    public Result<Void> updateEnumParam(@RequestBody @NotNull @Valid EnumParam enumParam){
        enumParamService.updateEnumParam(enumParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteEnumParamDS",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteEnumParam",desc = "删除枚举类型数据结构内容")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteEnumParam(@NotNull String id){
        enumParamService.deleteEnumParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findEnumParamDS",method = RequestMethod.POST)
//    @ApiMethod(name = "findEnumParam",desc = "根据id查询枚举类型内容")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<EnumParam> findEnumParam(@NotNull String id){
        EnumParam enumParam = enumParamService.findEnumParam(id);

        return Result.ok(enumParam);
    }

    @RequestMapping(path="/findAllEnumParamDS",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllEnumParam",desc = "查询所有枚举类型内容")
    public Result<List<EnumParam>> findAllEnumParam(){
        List<EnumParam> enumParamList = enumParamService.findAllEnumParam();

        return Result.ok(enumParamList);
    }

    @RequestMapping(path = "/findEnumParamListDS",method = RequestMethod.POST)
//    @ApiMethod(name = "findEnumParamList",desc = "根据查询对象 查询枚举类型内容")
//    @ApiParam(name = "enumParamQuery",desc = "查询对象",required = true)
    public Result<List<EnumParam>> findEnumParamList(@RequestBody @Valid @NotNull EnumParamQuery enumParamQuery){
        List<EnumParam> enumParamList = enumParamService.findEnumParamList(enumParamQuery);

        return Result.ok(enumParamList);
    }

    @RequestMapping(path = "/findEnumParamPageDS",method = RequestMethod.POST)
//    @ApiMethod(name = "findEnumParamPage",desc = "根据查询对象查询枚举类型数据结构内容")
//    @ApiParam(name = "enumParamQuery",desc = "查询对象",required = true)
    public Result<Pagination<EnumParam>> findEnumParamPage(@RequestBody @Valid @NotNull EnumParamQuery enumParamQuery){
        Pagination<EnumParam> pagination = enumParamService.findEnumParamPage(enumParamQuery);

        return Result.ok(pagination);
    }

}
