package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dal.jpa.criteria.annotation.*;
import com.doublekit.dal.jpa.criteria.model.OrderParam;
import com.doublekit.dal.jpa.criteria.model.Orders;
import com.doublekit.dal.jpa.criteria.model.PageParam;
import com.doublekit.dal.jpa.criteria.annotation.QueryTypeEnum;

import java.util.List;

@ApiModel
@CriteriaQuery
public class MethodExQuery {
    @ApiProperty(name ="id",desc = "方法id")
    @QueryField(type = QueryTypeEnum.equal)
    private String id;
    @ApiProperty(name ="categoryId",desc = "分类ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String categoryId;

    @ApiProperty(name ="name",desc = "接口名称，模糊匹配")
    @QueryField(type = QueryTypeEnum.like)
    private String name;
    @ApiProperty(name ="versionCode",desc = "版本号精确查询")
    @QueryField(type = QueryTypeEnum.equal)
    private String versionCode;
    @ApiProperty(name ="onVersionId",desc = "上个版本号id精确查询")
    @QueryField(type = QueryTypeEnum.equal)
    private String onVersionId;
    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = Orders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<OrderParam> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<OrderParam> orderParams) {
        this.orderParams = orderParams;
    }

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getOnVersionId() {
        return onVersionId;
    }

    public void setOnVersionId(String onVersionId) {
        this.onVersionId = onVersionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}