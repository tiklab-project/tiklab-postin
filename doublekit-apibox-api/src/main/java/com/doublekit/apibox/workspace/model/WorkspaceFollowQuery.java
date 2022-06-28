package com.doublekit.apibox.workspace.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.core.order.Order;
import com.doublekit.core.order.OrderBuilders;
import com.doublekit.core.page.Page;

import java.util.List;

@ApiModel
public class WorkspaceFollowQuery {
    @ApiProperty(name ="userId",desc = "userId")
    private String userId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

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

    public String getUserId() {
        return userId;
    }

    public WorkspaceFollowQuery setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}