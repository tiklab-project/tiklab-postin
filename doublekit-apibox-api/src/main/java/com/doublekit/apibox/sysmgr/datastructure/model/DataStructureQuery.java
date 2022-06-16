package com.doublekit.apibox.sysmgr.datastructure.model;

import com.doublekit.core.page.Page;
import com.doublekit.core.order.Order;
import com.doublekit.core.order.OrderBuilders;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class DataStructureQuery {

    @ApiProperty(name ="workspaceId",desc = "空间ID，精确匹配")
    private String workspaceId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name ="name",desc = "接口名称，模糊匹配")
    private String name;

    public String getWorkspaceId() {
        return workspaceId;
    }

    public DataStructureQuery setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
            this.name = name;
        }
}