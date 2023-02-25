package net.tiklab.postin.api.http.mock.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class MockQuery {

    @ApiProperty(name ="httpId",desc = "接口ID，精确匹配")
    private String httpId;

    @ApiProperty(name ="name",desc = "mock名称，模糊匹配")
    private String name;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getHttpId() {
        return httpId;
    }

    public MockQuery setHttpId(String httpId) {
        this.httpId = httpId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}