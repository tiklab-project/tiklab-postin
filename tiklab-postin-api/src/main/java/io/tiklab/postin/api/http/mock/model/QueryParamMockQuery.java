package io.tiklab.postin.api.http.mock.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.annotation.ApiModel;

import java.util.List;

@ApiModel
public class QueryParamMockQuery {

    @ApiProperty(name ="mockId",desc = "mockId，精确匹配")
    private String mockId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("paramName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getMockId() {
        return mockId;
    }

    public QueryParamMockQuery setMockId(String mockId) {
        this.mockId = mockId;
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
}