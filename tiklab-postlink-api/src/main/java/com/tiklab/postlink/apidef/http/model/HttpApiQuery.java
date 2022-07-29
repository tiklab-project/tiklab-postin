package com.tiklab.postlink.apidef.http.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.core.order.Order;
import com.tiklab.core.order.OrderBuilders;
import com.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class HttpApiQuery {

    @ApiProperty(name ="apixId",desc = "基础id，精确匹配")
    private String apixId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getApixId() {
        return apixId;
    }

    public HttpApiQuery setApixId(String apixId) {
        this.apixId = apixId;
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