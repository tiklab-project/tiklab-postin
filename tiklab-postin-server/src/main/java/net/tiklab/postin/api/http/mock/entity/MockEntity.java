package net.tiklab.postin.api.http.mock.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * mock 实体
 */
@Entity
@Table(name="postin_http_mock")
public class MockEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    //所属接口
    @Column(name = "http_id",length = 32,notNull = true)
    private String httpId;

    //名称
    @Column(name = "name",length = 64)
    private String name;

    //创建人
    @Column(name = "create_user",length = 32 )
    private String createUser;

    //创建时间
    @Column(name = "create_time",length = 4)
    private Timestamp createTime;

    //是否可用
    @Column(name = "enable",length = 2)
    private Integer enable;

    //描述
    @Column(name = "description",length = 128)
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpId() {
        return httpId;
    }

    public void setHttpId(String httpId) {
        this.httpId = httpId;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
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
