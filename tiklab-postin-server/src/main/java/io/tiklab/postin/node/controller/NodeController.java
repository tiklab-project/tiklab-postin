package io.tiklab.postin.node.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.model.NodeQuery;
import io.tiklab.postin.node.service.NodeService;
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
 * 接口公共控制器
 */
@RestController
@RequestMapping("/node")
@Api(name = "NodeController",desc = "接口公共字段")
public class NodeController {

    private static Logger logger = LoggerFactory.getLogger(NodeController.class);

    @Autowired
    private NodeService nodeService;

    @RequestMapping(path="/deleteNode",method = RequestMethod.POST)
    @ApiMethod(name = "deleteNode",desc = "删除接口公共字段")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteNode(@NotNull String id){
        nodeService.deleteNode(id);

        return Result.ok();
    }

    @RequestMapping(path="/findNode",method = RequestMethod.POST)
    @ApiMethod(name = "findNode",desc = "查找接口公共字段")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Node> findNode(@NotNull String id){
        Node node = nodeService.findNode(id);

        return Result.ok(node);
    }

    @RequestMapping(path = "/findNodePage",method = RequestMethod.POST)
    @ApiMethod(name = "findNodePage",desc = "根据查询对象模糊查找分类列表树")
    @ApiParam(name = "nodeQuery",desc = "查询对象",required = true)
    public Result<Pagination<Node>> findNodePage(@RequestBody @Valid @NotNull NodeQuery nodeQuery){
        Pagination<Node> nodePage = nodeService.findNodePage(nodeQuery);

        return Result.ok(nodePage);
    }


    @RequestMapping(path = "/findNodeTree",method = RequestMethod.POST)
    @ApiMethod(name = "findNodeTree",desc = "根据查询对象模糊查找分类列表树")
    @ApiParam(name = "nodeQuery",desc = "查询对象",required = true)
    public Result<List<Node>> findNodeTree(@RequestBody @Valid @NotNull NodeQuery nodeQuery){
        List<Node> nodeTree = nodeService.findNodeTree(nodeQuery);

        return Result.ok(nodeTree);
    }

}
