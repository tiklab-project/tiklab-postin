package com.tiklab.postlink.sysmgr.datastructure.model;

import com.tiklab.core.order.Order;
import com.tiklab.core.order.OrderBuilders;
import com.tiklab.core.page.Page;
import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class JsonParamDSQuery {
        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        @ApiProperty(name ="dataStructureId",desc = "主表ID，精确匹配")
        private  String dataStructureId;

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