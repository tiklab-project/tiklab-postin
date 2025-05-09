package io.tiklab.postin.support.statistics.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.user.user.model.User;

@ApiModel
public class StatisticsUserContributionQuery {

    private String workspaceId;

    private Integer top;

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
}
