package io.tiklab.postin.support.environment.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 环境中服务地址 实体
 */
@Entity
@Table(name="postin_env_server")
public class EnvServerEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    @Column(name="env_id")
    private String envId;

    // 名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 服务url
    @Column(name = "url",length = 256,notNull = true)
    private String url;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
