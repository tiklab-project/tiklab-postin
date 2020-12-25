package com.darthcloud.apibox.category.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.workspace.model.Workspace;
import com.darthcloud.beans.annotation.Mapper;
import com.darthcloud.beans.annotation.Mapping;
import com.darthcloud.beans.annotation.Mappings;
import com.darthcloud.join.annotation.Join;
import com.darthcloud.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetClass = "com.darthcloud.apibox.category.entity.CategoryPo")
@Join
public class Category {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="分类名称",eg="@text32",required = true)
    private java.lang.String name;

    @ApiProperty(name="workspace",desc="所属空间",eg="@selectOne")
    @Mappings({
            @Mapping(source = "id",target = "workspaceId")
    })
    @JoinQuery(id = "id")
    private Workspace workspace;

    @ApiProperty(name="parentCategory",desc="上级分类",eg="@selectOne")
    @Mappings({
            @Mapping(source = "id",target = "parentCategoryId")
    })
    @JoinQuery(id = "id")
    private Category parentCategory;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
