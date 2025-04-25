package io.tiklab.postin.support.environment.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 环境中变量 实体
 */
@Entity
@Table(name="postin_env_variable")
public class EnvVariableEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    @Column(name="env_id")
    private String envId;

    // 变量名称
    @Column(name = "name")
    private String name;

    // 变量值
    @Column(name = "value")
    private String value;

    // 描述
    @Column(name = "description")
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnvId() {
        return envId;
    }

    public void setEnvId(String envId) {
        this.envId = envId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
