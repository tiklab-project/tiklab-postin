package com.darthcloud.apibox.apidef.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.dsl.beans.annotation.Mapping;
import com.darthcloud.dsl.beans.annotation.Mappings;
import com.darthcloud.dss.store.annotation.Index;
import com.darthcloud.dss.store.annotation.IndexField;
import com.darthcloud.dss.store.annotation.IndexId;
import com.darthcloud.dss.store.annotation.IndexQueryField;
import com.darthcloud.dsl.join.annotation.Join;
import com.darthcloud.dsl.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Index
public class MethodEx implements java.io.Serializable{

    @ApiProperty(name="id",desc="唯一ID")
    @IndexId
    @IndexField
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="category",desc="所属分类",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "id",target = "categoryId")
    })
    @JoinField(id = "id")
    @IndexField
    private Category category;

    @NotNull
    @ApiProperty(name="name",desc="接口名称",eg="@text32",required = true)
    @IndexField
    @IndexQueryField
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="requestType",desc="请求类型",required = true)
    @IndexField
    private java.lang.String requestType;

    @NotNull
    @ApiProperty(name="path",desc="接口路径",required = true)
    @IndexField
    private java.lang.String path;

    @ApiProperty(name="desc",desc="描述")
    @IndexField
    private java.lang.String desc;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    public String getId() {
        return id;
    }

    public MethodEx setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
