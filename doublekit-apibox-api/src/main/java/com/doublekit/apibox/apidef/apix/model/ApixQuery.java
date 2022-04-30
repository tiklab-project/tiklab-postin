package com.doublekit.apibox.apidef.apix.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.common.order.Order;
import com.doublekit.common.order.OrderBuilders;
import com.doublekit.common.page.Page;

import java.util.List;

@ApiModel
public class ApixQuery {
    @ApiProperty(name = "name",desc = "模糊查询")
    private String name;

    @ApiProperty(name ="categoryId",desc = "分类id，精确匹配")
    private String categoryId;

    @ApiProperty(name="protocolType",desc = "协议类型，精确匹配")
    private String protocolType;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getCategoryId() {
        return categoryId;
    }

    public ApixQuery setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}