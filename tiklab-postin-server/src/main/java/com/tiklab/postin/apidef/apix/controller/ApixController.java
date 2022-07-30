package com.tiklab.postin.apidef.apix.controller;

import com.tiklab.postin.annotation.Api;
import com.tiklab.postin.apidef.apix.model.Apix;
import com.tiklab.postin.apidef.apix.model.ApixQuery;
import com.tiklab.postin.apidef.apix.service.ApixService;
import com.tiklab.core.page.Pagination;
import com.tiklab.core.Result;
import com.tiklab.postin.annotation.ApiMethod;
import com.tiklab.postin.annotation.ApiParam;
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
 * BasedefController
 */
@RestController
@RequestMapping("/apix")
@Api(name = "ApixController",desc = "ApixController")
public class ApixController {

    private static Logger logger = LoggerFactory.getLogger(ApixController.class);

    @Autowired
    private ApixService apixService;

    @RequestMapping(path="/createApix",method = RequestMethod.POST)
    @ApiMethod(name = "createApix",desc = "createApix")
    @ApiParam(name = "apix",desc = "apix",required = true)
    public Result<String> createApix(@RequestBody @NotNull @Valid Apix apix){
        String id = apixService.createApix(apix);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApix",method = RequestMethod.POST)
    @ApiMethod(name = "updateApix",desc = "updateApix")
    @ApiParam(name = "apix",desc = "apix",required = true)
    public Result<Void> updateApix(@RequestBody @NotNull @Valid Apix apix){
        apixService.updateApix(apix);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApix",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApix",desc = "deleteApix")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApix(@NotNull String id){
        apixService.deleteApix(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApix",method = RequestMethod.POST)
    @ApiMethod(name = "findApix",desc = "findApix")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Apix> findApix(@NotNull String id){
        Apix apix = apixService.findApix(id);

        return Result.ok(apix);
    }

    @RequestMapping(path="/findAllApix",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApix",desc = "findAllApix")
    public Result<List<Apix>> findAllApix(){
        List<Apix> apixList = apixService.findAllApix();

        return Result.ok(apixList);
    }

    @RequestMapping(path = "/findApixList",method = RequestMethod.POST)
    @ApiMethod(name = "findApixList",desc = "findApixList")
    @ApiParam(name = "apixQuery",desc = "apixQuery",required = true)
    public Result<List<Apix>> findApixList(@RequestBody @Valid @NotNull ApixQuery apixQuery){
        List<Apix> apixList = apixService.findApixList(apixQuery);

        return Result.ok(apixList);
    }

    @RequestMapping(path = "/findApixPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApixPage",desc = "findApixPage")
    @ApiParam(name = "apixQuery",desc = "apixQuery",required = true)
    public Result<Pagination<Apix>> findApixPage(@RequestBody @Valid @NotNull ApixQuery apixQuery){
        Pagination<Apix> pagination = apixService.findApixPage(apixQuery);

        return Result.ok(pagination);
    }

}
