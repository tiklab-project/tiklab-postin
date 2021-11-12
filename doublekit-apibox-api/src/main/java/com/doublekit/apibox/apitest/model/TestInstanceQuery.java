package com.doublekit.apibox.apitest.model;

import com.doublekit.dal.jpa.criteria.annotation.*;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dal.jpa.criteria.model.OrderParam;
import com.doublekit.dal.jpa.criteria.model.Orders;
import com.doublekit.dal.jpa.criteria.model.PageParam;
import com.doublekit.dal.jpa.criteria.annotation.QueryTypeEnum;

import java.util.List;

@ApiModel
@CriteriaQuery(entityAlias = "TestInstanceEntity")
public class TestInstanceQuery {

    @ApiProperty(name ="testcaseId",desc = "接口用例ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String testcaseId;

    @ApiProperty(name ="methodId",desc = "接口用例ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String methodId;

    @ApiProperty(name ="type",desc = "类型，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String type;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = Orders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
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

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}