package com.doublekit.apibox.apitest.apiinstance.model;

import com.doublekit.common.page.Page;
import com.doublekit.dal.jpa.annotation.criteria.*;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.common.order.Order;
import com.doublekit.common.order.OrderBuilders;

import java.util.List;

@ApiModel
@CriteriaQuery(entityAlias = "RequestInstanceEntity")
public class RequestInstanceQuery {

    @ApiProperty(name ="instanceId",desc = "测试实例ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String instanceId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private Page pageParam = new Page();

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
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