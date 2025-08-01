package io.tiklab.postin.autotest.testplan.quartz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

import java.sql.Timestamp;

/**
 * 定时任务 模型
 */
@ApiModel
@Mapper
@Join
public class QuartzPlan extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testPlanId",desc="testPlanId")
    private String testPlanId;

    @ApiProperty(name="exeType",desc="exeType")
    private Integer[] weekList;

    @ApiProperty(name="time",desc="time")
    private String time;

    @ApiProperty(name="exeType",desc="exeType")
    private Integer exeType;

    @ApiProperty(name="state",desc="state")
    private Integer state;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    private String workspaceId;
    private QuartzTimePlan quartzTimePlan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }

    public Integer[] getWeekList() {
        return weekList;
    }

    public void setWeekList(Integer[] weekList) {
        this.weekList = weekList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getExeType() {
        return exeType;
    }

    public void setExeType(Integer exeType) {
        this.exeType = exeType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public QuartzTimePlan getQuartzTimePlan() {
        return quartzTimePlan;
    }

    public void setQuartzTimePlan(QuartzTimePlan quartzTimePlan) {
        this.quartzTimePlan = quartzTimePlan;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }
}
