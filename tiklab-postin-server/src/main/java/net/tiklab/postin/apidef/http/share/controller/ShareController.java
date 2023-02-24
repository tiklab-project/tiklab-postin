package net.tiklab.postin.apidef.http.share.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.postin.apidef.http.definition.model.HttpApi;
import net.tiklab.postin.category.model.Category;
import net.tiklab.postin.apidef.http.share.model.Share;
import net.tiklab.postin.apidef.http.share.model.ShareQuery;
import net.tiklab.postin.apidef.http.share.service.ShareService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * ShareController
 */
@RestController
@RequestMapping("/share")
@Api(name = "ShareController",desc = "ShareController")
public class ShareController {

    private static Logger logger = LoggerFactory.getLogger(ShareController.class);

    @Autowired
    private ShareService shareService;

    @RequestMapping(path="/createShare",method = RequestMethod.POST)
    @ApiMethod(name = "createShare",desc = "createShare")
    @ApiParam(name = "share",desc = "share",required = true)
    public Result<String> createShare(@RequestBody @NotNull @Valid Share share){
        String id = shareService.createShare(share);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateShare",method = RequestMethod.POST)
    @ApiMethod(name = "updateShare",desc = "updateShare")
    @ApiParam(name = "share",desc = "share",required = true)
    public Result<Void> updateShare(@RequestBody @NotNull @Valid Share share){
        shareService.updateShare(share);

        return Result.ok();
    }

    @RequestMapping(path="/deleteShare",method = RequestMethod.POST)
    @ApiMethod(name = "deleteShare",desc = "deleteShare")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteShare(@NotNull String id){
        shareService.deleteShare(id);

        return Result.ok();
    }

    @RequestMapping(path="/findShare",method = RequestMethod.POST)
    @ApiMethod(name = "findShare",desc = "findShare")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Share> findShare(@NotNull String id){
        Share share = shareService.findShare(id);

        return Result.ok(share);
    }

    @RequestMapping(path="/findAllShare",method = RequestMethod.POST)
    @ApiMethod(name = "findAllShare",desc = "findAllShare")
    public Result<List<Share>> findAllShare(){
        List<Share> shareList = shareService.findAllShare();

        return Result.ok(shareList);
    }

    @RequestMapping(path = "/findShareList",method = RequestMethod.POST)
    @ApiMethod(name = "findShareList",desc = "findShareList")
    @ApiParam(name = "shareQuery",desc = "shareQuery",required = true)
    public Result<List<Share>> findShareList(@RequestBody @Valid @NotNull ShareQuery shareQuery){
        List<Share> shareList = shareService.findShareList(shareQuery);

        return Result.ok(shareList);
    }

    @RequestMapping(path = "/findSharePage",method = RequestMethod.POST)
    @ApiMethod(name = "findSharePage",desc = "findSharePage")
    @ApiParam(name = "shareQuery",desc = "shareQuery",required = true)
    public Result<Pagination<Share>> findSharePage(@RequestBody @Valid @NotNull ShareQuery shareQuery){
        Pagination<Share> pagination = shareService.findSharePage(shareQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/{id}",method = RequestMethod.GET)
    public Result<Share> findShareByUrlId(@PathVariable String id){
        Share shareByUrlId = shareService.findShareByUrlId(id);

        return Result.ok(shareByUrlId);
    }

    @RequestMapping(path="/findShareTree",method = RequestMethod.POST)
    public Result<List<Category>> findShareTree(@NotNull String id){
        List<Category> shareTree = shareService.findShareTree(id);

        return Result.ok(shareTree);
    }

    @RequestMapping(path="/verify",method = RequestMethod.POST)
    public Result<HashMap> verify(@RequestBody @NotNull Share share){
        HashMap verify = shareService.verify(share);

        return Result.ok(verify);
    }

    @RequestMapping(path="/findHttpApi",method = RequestMethod.POST)
    @ApiMethod(name = "findHttpApi",desc = "findHttpApi")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<HttpApi> findHttpApi(@NotNull String id){
        HttpApi httpApi = shareService.findHttpApi(id);

        return Result.ok(httpApi);
    }


}
