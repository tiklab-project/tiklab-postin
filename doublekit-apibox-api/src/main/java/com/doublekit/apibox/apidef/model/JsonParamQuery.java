package com.doublekit.apibox.apidef.model;

import com.doublekit.dal.jpa.criteria.annotation.*;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dal.jpa.criteria.model.OrderParam;
import com.doublekit.dal.jpa.criteria.model.Orders;
import com.doublekit.dal.jpa.criteria.model.PageParam;

import java.util.List;

@ApiModel
@CriteriaQuery
public class JsonParamQuery {

    @ApiProperty(name ="methodId",desc = "接口ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String methodId;

    @ApiProperty(name ="preVersionId",desc = "上个版本id，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String preVersionId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = Orders.instance().asc("paramName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
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

    public String getPreVersionId() {
        return preVersionId;
    }

    public void setPreVersionId(String preVersionId) {
        this.preVersionId = preVersionId;
    }
}