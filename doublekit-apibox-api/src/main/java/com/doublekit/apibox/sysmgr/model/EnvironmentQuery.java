package com.doublekit.apibox.sysmgr.model;

import com.doublekit.dal.jpa.annotation.criteria.*;
import com.doublekit.dal.jpa.model.OrderParam;
import com.doublekit.dal.jpa.model.Orders;
import com.doublekit.dal.jpa.model.PageParam;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
@CriteriaQuery
public class EnvironmentQuery {

    @ApiProperty(name ="name",desc = "分类名称，模糊匹配")
    @QueryField(type = QueryTypeEnum.like)
    private String name;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = Orders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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