package io.thoughtware.postin.api.apix.controller;

import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.api.apix.model.Apix;
import io.thoughtware.postin.api.apix.model.ApixQuery;
import io.thoughtware.postin.api.apix.service.ApixService;
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
 * 接口公共控制器
 */
@RestController
@RequestMapping("/apx")
@Api(name = "ApixController",desc = "接口公共字段")
public class ApixController {

    private static Logger logger = LoggerFactory.getLogger(ApixController.class);

    @Autowired
    private ApixService apixService;

    @RequestMapping(path="/createApix",method = RequestMethod.POST)
    @ApiMethod(name = "createApix",desc = "创建接口公共字段")
    @ApiParam(name = "apix",desc = "apix",required = true)
    public Result<String> createApix(@RequestBody @NotNull @Valid Apix apix){
        String id = apixService.createApix(apix);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApix",method = RequestMethod.POST)
    @ApiMethod(name = "updateApix",desc = "更新接口公共字段")
    @ApiParam(name = "apix",desc = "apix",required = true)
    public Result<Void> updateApix(@RequestBody @NotNull @Valid Apix apix){
        apixService.updateApix(apix);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApix",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApix",desc = "删除接口公共字段")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApix(@NotNull String id){
        apixService.deleteApix(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApix",method = RequestMethod.POST)
    @ApiMethod(name = "findApix",desc = "根据接口公共字段ID查找接口公共字段")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Apix> findApix(@NotNull String id){
        Apix apix = apixService.findApix(id);

        return Result.ok(apix);
    }

    @RequestMapping(path="/findAllApix",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApix",desc = "查找所有接口公共")
    public Result<List<Apix>> findAllApix(){
        List<Apix> apixList = apixService.findAllApix();

        return Result.ok(apixList);
    }

    @RequestMapping(path = "/findApixList",method = RequestMethod.POST)
    @ApiMethod(name = "findApixList",desc = "根据查询对象查找接口公共列表")
    @ApiParam(name = "apixQuery",desc = "查询对象",required = true)
    public Result<List<Apix>> findApixList(@RequestBody @Valid @NotNull ApixQuery apixQuery){
        List<Apix> apixList = apixService.findApixList(apixQuery);

        return Result.ok(apixList);
    }

    @RequestMapping(path = "/findApixPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApixPage",desc = "根据查询对象按分页查询接口公共列表")
    @ApiParam(name = "apixQuery",desc = "查询对象",required = true)
    public Result<Pagination<Apix>> findApixPage(@RequestBody @Valid @NotNull ApixQuery apixQuery){
        Pagination<Apix> pagination = apixService.findApixPage(apixQuery);

        return Result.ok(pagination);
    }

}
