package com.darthcloud.apibox.requestparam.model;

import com.darthcloud.dal.jpa.criteria.annotation.*;
import com.darthcloud.dal.jpa.criteria.model.*;
import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
@Criteria
public class RequestParamQuery {

    @ApiProperty(name ="methodId",desc = "接口ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String methodId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = OrderParamBuilder.instance().add("paramName", OrderTypeEnum.asc).get();

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
}