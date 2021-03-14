package com.darthcloud.apibox.apimock.model;

import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dal.jpa.annotation.criteria.CriteriaQuery;
import com.darthcloud.dal.jpa.annotation.criteria.OrderField;
import com.darthcloud.dal.jpa.annotation.criteria.PageField;
import com.darthcloud.dal.jpa.annotation.criteria.QueryField;
import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.dal.jpa.model.OrderParam;
import com.darthcloud.dal.jpa.model.PageParam;
import com.darthcloud.dal.jpa.annotation.criteria.QueryTypeEnum;
import com.darthcloud.dal.jpa.model.Orders;

import java.util.List;

@ApiModel
@CriteriaQuery
public class QueryParamMockQuery {

    @ApiProperty(name ="mockId",desc = "mockId，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String mockId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = Orders.instance().asc("paramName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getMockId() {
        return mockId;
    }

    public void setMockId(String mockId) {
        this.mockId = mockId;
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