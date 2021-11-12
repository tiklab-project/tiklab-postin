package com.doublekit.apibox.apitest.model;

import com.doublekit.common.page.Page;
import com.doublekit.dal.jpa.criteria.annotation.*;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dal.jpa.criteria.model.Order;
import com.doublekit.dal.jpa.criteria.model.OrderBuilders;
import com.doublekit.dal.jpa.criteria.annotation.QueryTypeEnum;

import java.util.List;

@ApiModel
@CriteriaQuery(entityAlias = "RequestHeaderCase")
public class RequestHeaderCaseQuery {

    @ApiProperty(name ="testcaseId",desc = "接口用例ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String testcaseId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<Order> orderParams = OrderBuilders.instance().asc("headerName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private Page pageParam = new Page();

    public String getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
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