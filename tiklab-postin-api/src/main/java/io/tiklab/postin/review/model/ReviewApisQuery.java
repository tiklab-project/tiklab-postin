package io.tiklab.postin.review.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class ReviewApisQuery implements Serializable {

    @ApiProperty(name = "name", desc = "接口名称")
    private String name;

    @ApiProperty(name = "categoryId", desc = "id")
    private String categoryId;

    @ApiProperty(name ="reviewId",desc = "reviewId，精确匹配")
    private String reviewId;

    @ApiProperty(name = "result", desc = "result，精确匹配")
    private Integer result;

    @ApiProperty(name = "workspaceId", desc = "workspaceId，精确匹配")
    private String workspaceId;

    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
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


    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}