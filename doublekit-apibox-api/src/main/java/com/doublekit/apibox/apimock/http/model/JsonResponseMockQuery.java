package com.doublekit.apibox.apimock.http.model;

import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.core.page.Page;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.core.order.Order;
import com.doublekit.core.order.OrderBuilders;

import java.util.List;

@ApiModel
public class JsonResponseMockQuery {

    @ApiProperty(name ="mockId",desc = "所属mockId，精确匹配")
    private String mockId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getMockId() {
        return mockId;
    }

    public JsonResponseMockQuery setMockId(String mockId) {
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