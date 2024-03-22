package io.thoughtware.postin.api.http.document.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.postin.api.http.definition.model.HttpApi;
import io.thoughtware.postin.api.ws.ws.model.WSApi;
import io.thoughtware.postin.category.model.Category;
import io.thoughtware.postin.api.http.document.model.Share;
import io.thoughtware.postin.api.http.document.model.ShareQuery;
import io.thoughtware.postin.api.http.document.service.ShareService;

import io.thoughtware.postin.node.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * 分享控制器
 */
@RestController
@RequestMapping("/share")
@Api(name = "ShareController",desc = "分享文档控制器")
public class ShareController {

    private static Logger logger = LoggerFactory.getLogger(ShareController.class);

    @Autowired
    private ShareService shareService;

    @RequestMapping(path="/createShare",method = RequestMethod.POST)
    @ApiMethod(name = "createShare",desc = "创建分享")
    @ApiParam(name = "share",desc = "share",required = true)
    public Result<String> createShare(@RequestBody @NotNull @Valid Share share){
        String id = shareService.createShare(share);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateShare",method = RequestMethod.POST)
    @ApiMethod(name = "updateShare",desc = "更新分享")
    @ApiParam(name = "share",desc = "share",required = true)
    public Result<Void> updateShare(@RequestBody @NotNull @Valid Share share){
        shareService.updateShare(share);

        return Result.ok();
    }

    @RequestMapping(path="/deleteShare",method = RequestMethod.POST)
    @ApiMethod(name = "deleteShare",desc = "删除分享")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteShare(@NotNull String id){
        shareService.deleteShare(id);

        return Result.ok();
    }

    @RequestMapping(path="/findShare",method = RequestMethod.POST)
    @ApiMethod(name = "findShare",desc = "通过id查找分享")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Share> findShare(@NotNull String id){
        Share share = shareService.findShare(id);

        return Result.ok(share);
    }

    @RequestMapping(path="/findAllShare",method = RequestMethod.POST)
    @ApiMethod(name = "findAllShare",desc = "查找所有分享")
    public Result<List<Share>> findAllShare(){
        List<Share> shareList = shareService.findAllShare();

        return Result.ok(shareList);
    }

    @RequestMapping(path = "/findShareList",method = RequestMethod.POST)
    @ApiMethod(name = "findShareList",desc = "根据查询参数查找分享列表")
    @ApiParam(name = "shareQuery",desc = "shareQuery",required = true)
    public Result<List<Share>> findShareList(@RequestBody @Valid @NotNull ShareQuery shareQuery){
        List<Share> shareList = shareService.findShareList(shareQuery);

        return Result.ok(shareList);
    }

    @RequestMapping(path = "/findSharePage",method = RequestMethod.POST)
    @ApiMethod(name = "findSharePage",desc = "根据查询参数按分页查找列表")
    @ApiParam(name = "shareQuery",desc = "shareQuery",required = true)
    public Result<Pagination<Share>> findSharePage(@RequestBody @Valid @NotNull ShareQuery shareQuery){
        Pagination<Share> pagination = shareService.findSharePage(shareQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path="/{id}",method = RequestMethod.GET)
    @ApiMethod(name = "findShareByUrlId",desc = "根据url地址栏中地址查询分享")
    public Result<Share> findShareByUrlId(@PathVariable String id){
        Share shareByUrlId = shareService.findShareByUrlId(id);

        return Result.ok(shareByUrlId);
    }

    @RequestMapping(path="/findShareTree",method = RequestMethod.POST)
    @ApiMethod(name = "findShareTree",desc = "查找分享树形列表")
    public Result<List<Node>> findShareTree(@NotNull String id){
        List<Node> shareTree = shareService.findShareTree(id);

        return Result.ok(shareTree);
    }

    @RequestMapping(path="/verify",method = RequestMethod.POST)
    @ApiMethod(name = "verify",desc = "校验是否需要密码")
    public Result<HashMap> verify(@RequestBody @NotNull Share share){
        HashMap verify = shareService.verify(share);

        return Result.ok(verify);
    }

    @RequestMapping(path="/findHttpApi",method = RequestMethod.POST)
    @ApiMethod(name = "findHttpApi",desc = "查询接口")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<HttpApi> findHttpApi(@NotNull String id){
        HttpApi httpApi = shareService.findHttpApi(id);

        return Result.ok(httpApi);
    }

    @RequestMapping(path="/findWSApi",method = RequestMethod.POST)
    @ApiMethod(name = "findWSApi",desc = "查询接口")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WSApi> findWSApi(@NotNull String id){
        WSApi wsApi = shareService.findWSApi(id);

        return Result.ok(wsApi);
    }


}
