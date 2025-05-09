package io.tiklab.postin.review.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;

/**
 *评审模型
 */
@ApiModel
@Mapper
@Join
public class Review extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="workspaceId",desc="所属项目")
    private String workspaceId;

    @ApiProperty(name="name",desc="名称",required = true)
    private String name;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

    @ApiProperty(name = "status", desc = "状态", eg = "0: 未开始， 1：进行中 2：已完成")
    private Integer status;

    @ApiProperty(name = "type", desc = "评审类型", eg = "0: 单人， 1：多人")
    private Integer type;

    @ApiProperty(name="createUser",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUserId")
    })
    @JoinQuery(key = "id")
    private User createUser;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="startTime",desc="开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp startTime;

    @ApiProperty(name="endTime",desc="结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
