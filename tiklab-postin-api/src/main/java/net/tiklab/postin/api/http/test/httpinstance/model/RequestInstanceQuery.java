package net.tiklab.postin.api.http.test.httpinstance.model;

import net.tiklab.core.page.Page;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;

import java.util.List;

@ApiModel
public class RequestInstanceQuery {

    @ApiProperty(name ="httpInstanceId",desc = "测试实例ID，精确匹配")
    private String httpInstanceId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getHttpInstanceId() {
        return httpInstanceId;
    }

    public void setHttpInstanceId(String httpInstanceId) {
        this.httpInstanceId = httpInstanceId;
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