package io.thoughtware.postin.api.http.definition.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.postin.annotation.ApiModel;

import java.util.List;

@ApiModel
public class ApiResponseQuery {

    @ApiProperty(name ="id",desc = "ID")
    private String id;

    @ApiProperty(name ="httpId",desc = "接口ID")
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