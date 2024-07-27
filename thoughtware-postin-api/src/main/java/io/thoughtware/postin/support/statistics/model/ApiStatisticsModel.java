package io.thoughtware.postin.support.statistics.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.join.annotation.Join;

import java.util.Date;

/**
 * 获取系统统计信息 模型
 */
@ApiModel
@Join
public class ApiStatisticsModel extends BaseModel {

    private String workspaceId;

    @ApiProperty(name="startTime",desc="开始时间")
    private java.util.Date startTime;

    @ApiProperty(name="endTime",desc="结束时间")
    private java.util.Date endTime;

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
