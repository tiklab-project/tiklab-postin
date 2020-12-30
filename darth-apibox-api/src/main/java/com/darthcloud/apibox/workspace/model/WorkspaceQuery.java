package com.darthcloud.apibox.workspace.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dal.jpa.criteria.annotation.Criteria;
import com.darthcloud.dal.jpa.criteria.annotation.OrderField;
import com.darthcloud.dal.jpa.criteria.annotation.PageField;
import com.darthcloud.dal.jpa.criteria.annotation.QueryField;
import com.darthcloud.dal.jpa.criteria.model.*;

import java.util.List;

@ApiModel
@Criteria
public class WorkspaceQuery {

    @ApiProperty(name ="workspaceName",desc = "空间名称，模糊匹配")
    @QueryField(type = QueryTypeEnum.like)
    private String workspaceName;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    @OrderField
    private List<OrderParam> orderParams = OrderParamBuilder.instance().add("workspaceName", OrderTypeEnum.asc).get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

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
}