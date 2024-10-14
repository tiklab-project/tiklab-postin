package io.tiklab.postin.api.apix.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;

/**
 * 定义
 * http协议
 * 请求区 实体
 */
@Entity
@Table(name="postin_api_request")
public class ApiRequestEntity implements Serializable {

    @Id
    @Column(name = "id",length = 40)
    private String id;

    //所属接口
    @Column(name = "api_id",length = 40)
    private String apiId;

    //请求体类型
    @Column(name = "body_type",length = 32)
    private String bodyType;

    //前置脚本
    @Column(name = "pre_script")
    private String preScript;

    //后置脚本
    @Column(name = "after_script")
    private String afterScript;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getPreScript() {
        return preScript;
    }

    public void setPreScript(String preScript) {
        this.preScript = preScript;
    }

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }
}
