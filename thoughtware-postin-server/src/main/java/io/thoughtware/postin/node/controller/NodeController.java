package io.thoughtware.postin.node.controller;

import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.postin.node.model.Node;
import io.thoughtware.postin.node.model.NodeQuery;
import io.thoughtware.postin.node.service.NodeService;
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

    @RequestMapping(path = "/findNodeTree",method = RequestMethod.POST)
    @ApiMethod(name = "findNodeTree",desc = "根据查询对象模糊查找分类列表树")
    @ApiParam(name = "nodeQuery",desc = "查询对象",required = true)
    public Result<List<Node>> findNodeTree(@RequestBody @Valid @NotNull NodeQuery nodeQueryQuery){
        List<Node> nodeTree = nodeService.findNodeTree(nodeQueryQuery);

        return Result.ok(nodeTree);
    }

}
