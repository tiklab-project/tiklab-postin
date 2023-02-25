package net.tiklab.postin.category.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.postin.api.apix.model.Apix;
import net.tiklab.postin.workspace.model.Workspace;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ApiModel
@Join
@Mapper(targetAlias = "CategoryEntity")
public class Category extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="分类名称",eg="@text32",required = true)
    private java.lang.String name;

    @ApiProperty(name="workspace",desc="所属空间",eg="@selectOne")
    @Mappings({
            @Mapping(source = "workspace.id",target = "workspaceId")
    })
    @JoinQuery(key = "id")
    private Workspace workspace;

    @ApiProperty(name="parent",desc="上级分类",eg="@selectOne")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private Category parent;

    @ApiProperty(name="children",desc="下级分类列表")
    private List<Category> children = new ArrayList<>();

    @ApiProperty(name="nodeList",desc="分类下的接口")
    private List<Apix> nodeList=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<Apix> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Apix> nodeList) {
        this.nodeList = nodeList;
    }
}
