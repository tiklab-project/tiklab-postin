package com.doublekit.apibox.workspace.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.common.page.Page;
import com.doublekit.dal.jpa.annotation.criteria.*;
import com.doublekit.common.order.Order;
import com.doublekit.common.order.OrderBuilders;

import java.util.List;

@ApiModel
@CriteriaQuery(entityAlias = "WorkspaceEntity")
public class WorkspaceQuery {

    @ApiProperty(name ="userId",desc = "用户ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String userId;

    @ApiProperty(name ="workspaceName",desc = "空间名称，模糊匹配")
    @QueryField(type = QueryTypeEnum.like)
    private String workspaceName;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<Order> orderParams = OrderBuilders.instance().asc("workspaceName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private Page pageParam = new Page();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
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
}