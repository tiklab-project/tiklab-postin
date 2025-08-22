package io.tiklab.postin.support.dbconnect.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;


/**
 *  数据库连接 模型
 */
@ApiModel
@Mapper
public class DatabaseConnect extends BaseModel {

    @ApiProperty(name = "id", desc = "唯一标识")
    private String id;

    @ApiProperty(name = "workspaceId", desc = "所属空间")
    private String workspaceId;

    @ApiProperty(name = "userId", desc = "所属用户")
    private String userId;

    @ApiProperty(name = "name", desc = "名称")
    private String name;

    @ApiProperty(name = "type", desc = "数据库类型")
    private String type;

    @ApiProperty(name = "desc", desc = "描述")
    private String desc;

    private DatabaseConnectConfig config;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public DatabaseConnectConfig getConfig() {
        return config;
    }

    public void setConfig(DatabaseConnectConfig config) {
        this.config = config;
    }
}
