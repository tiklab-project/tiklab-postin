package com.doublekit.apibox.sysmgr.datastructure.model;

import com.doublekit.dal.jpa.annotation.criteria.*;
import com.doublekit.common.order.Order;
import com.doublekit.common.order.OrderBuilders;
import com.doublekit.common.page.Page;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
@CriteriaQuery(entityAlias = "EnumParamEntity")
public class EnumParamQuery {

        @ApiProperty(name ="methodId",desc = "主表ID，精确匹配")
        @QueryField(type = QueryTypeEnum.equal)
        private  String dataStructureId;

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

        public String getDataStructureId() {
            return dataStructureId;
        }

        public void setDataStructureId(String dataStructureId) {
            this.dataStructureId = dataStructureId;
        }
}