package com.darthcloud.apibox.api.apimock.model;

import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dal.jpa.criteria.annotation.*;
import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.dal.jpa.criteria.model.OrderParam;
import com.darthcloud.dal.jpa.criteria.model.PageParam;
import com.darthcloud.dal.jpa.criteria.model.QueryTypeEnum;
import com.darthcloud.dal.jpa.criteria.support.Orders;

import java.util.List;

@ApiModel
@CriteriaQuery
public class ResponseHeaderMockQuery {

    @ApiProperty(name ="mockId",desc = "mockId，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String mockId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = Orders.instance().asc("id").get();

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