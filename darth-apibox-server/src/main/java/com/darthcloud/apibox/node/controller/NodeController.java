package com.darthcloud.apibox.node.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.node.model.Node;
import com.darthcloud.apibox.node.model.NodeQuery;
import com.darthcloud.apibox.node.service.NodeService;
import com.darthcloud.common.Pagination;
import com.darthcloud.common.Result;
import com.darthcloud.apibox.annotation.ApiMethod;
import com.darthcloud.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.darthcloud.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/node")
@Api(name = "NodeController",desc = "NodeController")
public class NodeController {

    private static Logger logger = LoggerFactory.getLogger(NodeController.class);

    @Autowired
    private NodeService nodeService;

    @RequestMapping(path="/createNode",method = RequestMethod.POST)
    @ApiMethod(name = "createNode",desc = "createNode")
    @ApiParam(name = "node",desc = "node",required = true)
    public Result<String> createNode(@RequestBody @NotNull @Valid Node node){
        String id = nodeService.createNode(node);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateNode",method = RequestMethod.POST)
    @ApiMethod(name = "updateNode",desc = "updateNode")
    @ApiParam(name = "node",desc = "node",required = true)
    public Result<Void> updateNode(@RequestBody @NotNull @Valid Node node){
        nodeService.updateNode(node);

        return Result.ok();
    }

    @RequestMapping(path="/deleteNode",method = RequestMethod.POST)
    @ApiMethod(name = "deleteNode",desc = "deleteNode")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteNode(@NotNull String id){
        nodeService.deleteNode(id);

        return Result.ok();
    }

    @RequestMapping(path="/findNode",method = RequestMethod.POST)
    @ApiMethod(name = "findNode",desc = "findNode")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Node> findNode(@NotNull String id){
        Node node = nodeService.findNode(id);

        return Result.ok(node);
    }

    @RequestMapping(path="/findAllNode",method = RequestMethod.POST)
    @ApiMethod(name = "findAllNode",desc = "findAllNode")
    public Result<List<Node>> findAllNode(){
        List<Node> nodeList = nodeService.findAllNode();

        return Result.ok(nodeList);
    }

    @Validator
    @RequestMapping(path = "/findNodeList",method = RequestMethod.POST)
    @ApiMethod(name = "findNodeList",desc = "findNodeList")
    @ApiParam(name = "nodeQuery",desc = "nodeQuery",required = true)
    public Result<List<Node>> findNodeList(@RequestBody @Valid @NotNull NodeQuery nodeQuery){
        List<Node> nodeList = nodeService.findNodeList(nodeQuery);

        return Result.ok(nodeList);
    }

    @Validator
    @RequestMapping(path = "/findNodePage",method = RequestMethod.POST)
    @ApiMethod(name = "findNodePage",desc = "findNodePage")
    @ApiParam(name = "nodeQuery",desc = "nodeQuery",required = true)
    public Result<Pagination<List<Node>>> findNodePage(@RequestBody @Valid @NotNull NodeQuery nodeQuery){
        Pagination<List<Node>> pagination = nodeService.findNodePage(nodeQuery);

        return Result.ok(pagination);
    }

}
