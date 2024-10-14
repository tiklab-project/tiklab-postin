package io.tiklab.postin.support.statistics.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

@ApiModel
public class StatisticsTrendQuery {


    private String workspaceId;

    @ApiProperty(name="startTime",desc="开始时间")
    private String startTime;

    @ApiProperty(name="endTime",desc="结束时间")
    private String endTime;

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}