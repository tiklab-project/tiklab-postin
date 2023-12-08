package io.thoughtware.postin.workspace.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.core.page.Page;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.rpc.annotation.Exporter;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class WorkspaceQuery implements Serializable {

    @ApiProperty(name ="userId",desc = "用户ID，精确匹配")
    private String userId;

    @ApiProperty(name ="workspaceName",desc = "空间名称，模糊匹配")
    private String workspaceName;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("workspaceName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getUserId() {
        return userId;
    }

    public WorkspaceQuery setUserId(String userId) {
        this.userId = userId;
        return this;
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