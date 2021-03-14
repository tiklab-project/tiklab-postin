package com.darthcloud.apibox.apitest.model;

import com.darthcloud.dal.jpa.annotation.criteria.*;
import com.darthcloud.dal.jpa.annotation.criteria.QueryTypeEnum;
import com.darthcloud.dal.jpa.model.*;
import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dal.jpa.model.Orders;

import java.util.List;

@ApiModel
@CriteriaQuery
public class RequestInstanceQuery {

    @ApiProperty(name ="instanceId",desc = "测试实例ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String instanceId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = Orders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<OrderParam> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<OrderParam> orderParams) {
        this.orderParams = orderParams;
    }

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }
}