package com.tiklab.postin.apitest.http.httpcase.model;

import com.tiklab.postin.annotation.ApiModel;
import com.tiklab.postin.annotation.ApiProperty;
import com.tiklab.core.order.Order;
import com.tiklab.core.order.OrderBuilders;
import com.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class RawParamCaseQuery {

    @ApiProperty(name ="httpCaseId",desc = "接口用例ID，精确匹配")
    private String httpCaseId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("testcaseId").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getHttpCaseId() {
        return httpCaseId;
    }

    public void setHttpCaseId(String httpCaseId) {
        this.httpCaseId = httpCaseId;
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