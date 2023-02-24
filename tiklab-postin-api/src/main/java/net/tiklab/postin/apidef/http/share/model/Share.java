package net.tiklab.postin.apidef.http.share.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetAlias = "ShareEntity")
public class Share extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="code",desc="前端生成一个code，用于set Id")
    private java.lang.String code;

    @NotNull
    @ApiProperty(name="targetId",desc="targetId",required = true)
    private java.lang.String targetId;

    @NotNull
    @ApiProperty(name="targetType",desc="targetType",required = true)
    private java.lang.String targetType;

    @NotNull
    @ApiProperty(name="visibility",desc="visibility",required = true)
    private java.lang.Integer visibility;

    @ApiProperty(name="password",desc="password")
    private java.lang.String password;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="updateTime",desc="updateTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp updateTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public java.lang.String getTargetId() {
        return targetId;
    }

    public void setTargetId(java.lang.String targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public java.lang.Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(java.lang.Integer visibility) {
        this.visibility = visibility;
    }
    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }
    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
