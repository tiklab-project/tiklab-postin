package com.doublekit.apibox.apitest.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.common.page.Page;
import com.doublekit.dal.jpa.criteria.annotation.*;
import com.doublekit.dal.jpa.criteria.model.Order;
import com.doublekit.dal.jpa.criteria.model.OrderBuilders;
import com.doublekit.dal.jpa.criteria.annotation.QueryTypeEnum;

import java.util.List;

@ApiModel
@CriteriaQuery(entityAlias = "BinaryParamCaseEntity")
public class BinaryParamCaseQuery {

        @ApiProperty(name ="testcaseId",desc = "接口用例ID，精确匹配")
        @QueryField(type = QueryTypeEnum.equal)
        private String testcaseId;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        @OrderField
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        @PageField
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

        public String getTestcaseId() {
                return testcaseId;
        }

        public void setTestcaseId(String testcaseId) {
                this.testcaseId = testcaseId;
        }
}