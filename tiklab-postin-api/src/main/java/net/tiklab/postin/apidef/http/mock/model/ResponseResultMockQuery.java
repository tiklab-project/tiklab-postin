package net.tiklab.postin.apidef.http.mock.model;

import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.core.page.Page;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;

import java.util.List;

@ApiModel
public class ResponseResultMockQuery {

    @ApiProperty(name ="mockId",desc = "所属mockId，精确匹配")
    private String mockId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getMockId() {
        return mockId;
    }

    public ResponseResultMockQuery setMockId(String mockId) {
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