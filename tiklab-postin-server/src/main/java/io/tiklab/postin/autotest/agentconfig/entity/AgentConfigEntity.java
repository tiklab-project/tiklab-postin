package io.tiklab.postin.autotest.agentconfig.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * agent配置 实体
 */
@Entity
@Table(name="autotest_agent_config")
public class AgentConfigEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    // 名称
    @Column(name = "name")
    private String name;

    // 地址
    @Column(name = "address")
    private String address;

    // 状态
    @Column(name = "status")
    private String status;

    // 状态
    @Column(name = "enable")
    private Integer enable;

    // 创建时间
    @Column(name = "update_time")
    private Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
