package io.tiklab.postin.workspace.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;

import java.util.List;

/**
 * @pi.model: WorkspaceFollowQuery
 */
@ApiModel
public class WorkspaceFollowQuery {

    /**
     * @pi.name: userId
     * @pi.value: userId
     */
    @ApiProperty(name ="userId",desc = "userId")
    private String userId;

    /**
     * @pi.name: orderParams
     * @pi.value: []
     */
    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    /**
     * @pi.name: pageParam
     * @pi.value: {}
     */
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