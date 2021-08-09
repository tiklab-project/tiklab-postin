package com.doublekit.apibox.datastructure.model;

import com.doublekit.dal.jpa.criteria.annotation.*;
import com.doublekit.dal.jpa.criteria.model.OrderParam;
import com.doublekit.dal.jpa.criteria.model.Orders;
import com.doublekit.dal.jpa.criteria.model.PageParam;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
@CriteriaQuery
public class EnumParamQuery {

        @ApiProperty(name ="methodId",desc = "主表ID，精确匹配")
        @QueryField(type = QueryTypeEnum.equal)
        private  String DataStructureId;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        @OrderField
        private List<OrderParam> orderParams = Orders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        @PageField
        private PageParam pageParam = new PageParam();


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

        public String getDataStructureId() {
            return DataStructureId;
        }

        public void setDataStructureId(String dataStructureId) {
            DataStructureId = dataStructureId;
        }
}