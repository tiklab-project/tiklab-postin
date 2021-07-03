package com.doublekit.apibox.apimock.model;

import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dal.jpa.annotation.criteria.CriteriaQuery;
import com.doublekit.dal.jpa.annotation.criteria.OrderField;
import com.doublekit.dal.jpa.annotation.criteria.PageField;
import com.doublekit.dal.jpa.annotation.criteria.QueryField;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.dal.jpa.model.OrderParam;
import com.doublekit.dal.jpa.model.PageParam;
import com.doublekit.dal.jpa.annotation.criteria.QueryTypeEnum;
import com.doublekit.dal.jpa.model.Orders;

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