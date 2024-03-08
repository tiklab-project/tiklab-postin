package io.thoughtware.postin.category.model;

import io.thoughtware.postin.api.apix.model.Apix;
import io.thoughtware.postin.node.model.Node;
import io.thoughtware.postin.workspace.model.Workspace;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *分类 模型
 */
@ApiModel
@Join
@Mapper(targetName = "io.thoughtware.postin.category.entity.CategoryEntity")
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
