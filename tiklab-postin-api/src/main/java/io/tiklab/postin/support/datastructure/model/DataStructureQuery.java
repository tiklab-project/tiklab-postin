package io.tiklab.postin.support.datastructure.model;

import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

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

    @ApiProperty(name ="dataType",desc = "数据类型")
    private String dataType;


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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}