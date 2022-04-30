package com.doublekit.apibox.apitest.http.httpcase.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.common.page.Page;
import com.doublekit.common.order.Order;
import com.doublekit.common.order.OrderBuilders;

import java.util.List;

@ApiModel
public class BinaryParamCaseQuery {

        @ApiProperty(name ="httpCaseId",desc = "接口用例ID，精确匹配")
        private String httpCaseId;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

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

        public String getHttpCaseId() {
                return httpCaseId;
        }

        public void setHttpCaseId(String httpCaseId) {
                this.httpCaseId = httpCaseId;
        }
}