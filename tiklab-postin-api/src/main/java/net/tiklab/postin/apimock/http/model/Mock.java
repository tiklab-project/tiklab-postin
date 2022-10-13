package net.tiklab.postin.apimock.http.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.postin.apidef.http.model.HttpApi;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@ApiModel
@Join
@Mapper(targetAlias = "MockEntity")
public class Mock extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="http",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "http.id",target = "httpId")
    })
    private HttpApi http;

    @NotNull
    @ApiProperty(name="name",desc="mock名称")
    private java.lang.String name;

    @ApiProperty(name="desc",desc="描述")
    private java.lang.String desc;

    @ApiProperty(name="createUser",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    private User createUser;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @NotNull
    @ApiProperty(name="enable",desc="是否启用,0:未启用;1:启用",eg="@int16")
    private java.lang.Integer enable = 1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpApi getHttp() {
        return http;
    }

    public void setHttp(HttpApi http) {
        this.http = http;
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

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}