package io.tiklab.postin.api.apix.model;

import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;

import java.util.List;

@ApiModel
public class RequestHeaderQuery {

    @ApiProperty(name="apiId",desc="所属接口")
    private String apiId;

    @ApiProperty(name ="workspaceId",desc = "workspaceId，精确匹配")
    private String workspaceId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("headerName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getApiId() {
        return apiId;
    }

    public RequestHeaderQuery setApiId(String apiId) {
        this.apiId = apiId;
        return this;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
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