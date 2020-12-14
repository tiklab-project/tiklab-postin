package com.darthcloud.apibox.workspace.model;

import com.darthcloud.dal.jpa.annotation.criteria.Criteria;
import com.darthcloud.dal.jpa.annotation.criteria.PageField;
import com.darthcloud.dal.jpa.annotation.criteria.QueryField;
import com.darthcloud.dal.jpa.annotation.criteria.SortField;
import com.darthcloud.dal.jpa.model.criteria.*;
import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
@Criteria
public class WorkspaceQuery {

    @ApiProperty(name ="workspaceName",desc = "空间名称，模糊匹配")
    @QueryField(type = QueryTypeEnum.like)
    private String workspaceName;

    @ApiProperty(name ="sortParams",desc = "排序参数")
    @SortField
    private List<SortParam> sortParams = SortParamBuilder.instance().add("workspaceName", SortTypeEnum.asc).get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    @PageField
    private PageParam pageParam = new PageParam();

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public List<SortParam> getSortParams() {
        return sortParams;
    }

    public void setSortParams(List<SortParam> sortParams) {
        this.sortParams = sortParams;
    }

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }
}