package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dal.jpa.annotation.criteria.*;
import com.doublekit.common.order.Order;
import com.doublekit.common.order.OrderBuilders;
import com.doublekit.common.page.Page;

import java.util.List;

@ApiModel
@CriteriaQuery(entityAlias = "MethodEntity")
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
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private Page pageParam = new Page();

    @ApiProperty(name ="workspaceId",desc = "空间id")
    private String workspaceId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public MethodExQuery setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public List<Order> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }

    public Page getPageParam() {
        return pageParam;
    }

    public void setPageParam(Page pageParam) {
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

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }
}