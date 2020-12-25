package com.darthcloud.apibox.apxmethod.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.beans.annotation.Mapper;
import com.darthcloud.beans.annotation.Mapping;
import com.darthcloud.beans.annotation.Mappings;
import com.darthcloud.join.annotation.Join;
import com.darthcloud.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetClass = "com.darthcloud.apibox.apxmethod.entity.ApxMethodPo")
@Join
public class ApxMethod {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="name",eg="@text32",required = true)
    private java.lang.String name;

    @ApiProperty(name="parentCategory",desc="上级分类",eg="@selectOne")
    @Mappings({
            @Mapping(source = "id",target = "parentCategoryId")
    })
    @JoinQuery(id = "id")
    private Category parentCategory;

    @ApiProperty(name="sort",desc="sort",eg="@int16")
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
