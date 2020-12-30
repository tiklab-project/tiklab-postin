package com.darthcloud.apibox.apxmethod.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dal.jpa.criteria.annotation.Criteria;
import com.darthcloud.dal.jpa.criteria.annotation.OrderField;
import com.darthcloud.dal.jpa.criteria.annotation.PageField;
import com.darthcloud.dal.jpa.criteria.annotation.QueryField;
import com.darthcloud.dal.jpa.criteria.model.*;

import java.util.List;

@ApiModel
@Criteria
public class ApxMethodQuery {

    @ApiProperty(name ="name",desc = "接口名称，模糊匹配")
    @QueryField(type = QueryTypeEnum.like)
    private String name;

    @ApiProperty(name ="categoryId",desc = "分类ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String categoryId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = OrderParamBuilder.instance().add("name", OrderTypeEnum.asc).get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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