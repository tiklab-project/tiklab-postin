package com.darthcloud.apibox.category.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dal.jpa.criteria.annotation.CriteriaQuery;
import com.darthcloud.dal.jpa.criteria.annotation.OrderField;
import com.darthcloud.dal.jpa.criteria.annotation.PageField;
import com.darthcloud.dal.jpa.criteria.annotation.QueryField;
import com.darthcloud.dal.jpa.criteria.model.*;
import com.darthcloud.dal.jpa.criteria.support.Orders;

import java.util.List;

@ApiModel
@CriteriaQuery
public class CategoryQuery {

    @ApiProperty(name ="workspaceId",desc = "空间ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String workspaceId;

    @ApiProperty(name ="name",desc = "分类名称，模糊匹配")
    @QueryField(type = QueryTypeEnum.like)
    private String name;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = Orders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}