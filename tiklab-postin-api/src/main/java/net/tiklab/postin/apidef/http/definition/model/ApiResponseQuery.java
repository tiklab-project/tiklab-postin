package net.tiklab.postin.apidef.http.definition.model;

import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.postin.annotation.ApiModel;

import java.util.List;

@ApiModel
public class ApiResponseQuery {

    @ApiProperty(name ="id",desc = "ID，精确匹配")
    private String id;

    @ApiProperty(name ="methodId",desc = "接口ID，精确匹配")
    private String httpId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("createTime").get();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpId() {
        return httpId;
    }

    public ApiResponseQuery setHttpId(String httpId) {
        this.httpId = httpId;
        return this;
    }

    public List<Order> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }
}