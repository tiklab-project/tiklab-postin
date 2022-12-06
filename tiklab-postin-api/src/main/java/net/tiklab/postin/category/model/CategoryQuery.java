package net.tiklab.postin.category.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.core.page.Page;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;

import java.util.List;

@ApiModel
public class CategoryQuery {

    @ApiProperty(name ="workspaceId",desc = "空间ID，精确匹配")
    private String workspaceId;

    @ApiProperty(name ="type",desc = "type:quick，api ,精确匹配")
    private String type;

    @ApiProperty(name ="parentId",desc = "parentId ,精确匹配")
    private String parentId;

    @ApiProperty(name ="name",desc = "分类名称，模糊匹配")
    private String name;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getWorkspaceId() {
        return workspaceId;
    }

    public CategoryQuery setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public CategoryQuery setName(String name) {
        this.name = name;
        return this;
    }
}