package com.tiklab.postin.category.model;

import com.tiklab.postin.annotation.ApiModel;
import com.tiklab.postin.annotation.ApiProperty;
import com.tiklab.postin.apidef.apix.model.Apix;
import com.tiklab.postin.workspace.model.Workspace;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;

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

    @ApiProperty(name="parentCategory",desc="上级分类",eg="@selectOne")
    @Mappings({
            @Mapping(source = "parentCategory.id",target = "parentCategoryId")
    })
    @JoinQuery(key = "id")
    private Category parentCategory;

    @ApiProperty(name="children",desc="下级分类列表")
    private List<Category> children = new ArrayList<>();

    @ApiProperty(name="categoryMethod",desc="分类下的接口")
    private List<Apix> categoryMethod=new ArrayList<>();

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

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<Apix> getCategoryMethod() {
        return categoryMethod;
    }

    public void setCategoryMethod(List<Apix> categoryMethod) {
        this.categoryMethod = categoryMethod;
    }
}