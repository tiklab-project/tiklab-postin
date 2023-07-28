package io.tiklab.postin.workspace.model;

import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.user.user.model.User;

/**
 *
 * 空间关注 模型
 * @pi.model: io.tiklab.postin.workspace.model.WorkspaceFollow
 */
@ApiModel
@Join
@Mapper
public class WorkspaceFollow extends BaseModel {

    private int a;


    /**
     * @pi.name: id
     * @pi.dataType:string
     * @pi.desc: ID
     * @pi.value: WorkspaceFollowId
     */
    @ApiProperty(name="id",desc="id")
    private java.lang.String id;



    /**
     * @pi.model: workspace
     * @pi.desc: 所属空间
     */
    @ApiProperty(name="workspace",desc="所属空间",eg="@selectOne")
    @Mappings({
            @Mapping(source = "workspace.id",target = "workspaceId")
    })
    @JoinQuery(key = "id")
    private Workspace workspace;

    /**
     * @pi.model: user
     * @pi.desc: 所属用户
     */
    @ApiProperty(name="user",desc="所属用户",eg="@selectOne")
    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    @JoinQuery(key = "id")
    private User user;


    /**
     * @pi.name: createTime
     * @pi.dataType:datatime
     * @pi.desc: 创建时间
     * @pi.value: 2023-01-01 12:00:00
     */
    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
}
