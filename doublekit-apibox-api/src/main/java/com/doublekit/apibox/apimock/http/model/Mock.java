package com.doublekit.apibox.apimock.http.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.http.model.HttpApi;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.core.BaseModel;
import com.doublekit.dss.annotation.IndexField;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinQuery;
import com.doublekit.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.sql.Date;

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
    @JoinQuery(key = "id")
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
    @JoinQuery(key = "id")
    private User createUser;

    @ApiProperty(name="createTime",desc="创建时间")
    @IndexField
    private  Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
