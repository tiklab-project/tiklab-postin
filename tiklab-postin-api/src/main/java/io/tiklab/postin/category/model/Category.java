package io.tiklab.postin.category.model;

import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *分类 模型
 */
@ApiModel
@Join
@Mapper(targetName = "io.tiklab.postin.category.entity.CategoryEntity")
public class Category extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @ApiProperty(name="node",desc="所属公共定义")
    private Node node;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
