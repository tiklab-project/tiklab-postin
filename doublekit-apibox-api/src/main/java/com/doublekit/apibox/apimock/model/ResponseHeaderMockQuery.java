package com.doublekit.apibox.apimock.model;

import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.common.page.Page;
import com.doublekit.dal.jpa.criteria.annotation.CriteriaQuery;
import com.doublekit.dal.jpa.criteria.annotation.OrderField;
import com.doublekit.dal.jpa.criteria.annotation.PageField;
import com.doublekit.dal.jpa.criteria.annotation.QueryField;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.dal.jpa.criteria.model.Order;
import com.doublekit.dal.jpa.criteria.annotation.QueryTypeEnum;
import com.doublekit.dal.jpa.criteria.model.OrderBuilders;

import java.util.List;

@ApiModel
@CriteriaQuery(entityAlias = "ResponseHeaderMockEntity")
public class ResponseHeaderMockQuery {

    @ApiProperty(name ="mockId",desc = "mockId，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String mockId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private Page pageParam = new Page();

    public String getMockId() {
        return mockId;
    }

    public ResponseHeaderMockQuery setMockId(String mockId) {
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