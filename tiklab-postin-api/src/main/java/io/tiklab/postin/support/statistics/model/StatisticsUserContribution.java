package io.tiklab.postin.support.statistics.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.user.user.model.User;

@ApiModel
@Mapper
public class StatisticsUserContribution {
    private User createUser;
    private Integer createdCount;


    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Integer getCreatedCount() {
        return createdCount;
    }

    public void setCreatedCount(Integer createdCount) {
        this.createdCount = createdCount;
    }
}
