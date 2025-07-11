package io.tiklab.postin.api.http.definition.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * BearerToken认证 实体
 */
@Entity
@Table(name="postin_http_auth_bearer")
public class AuthBearerEntity implements Serializable {

    //id不自动生成，一对一使用apiId
    @Id
//    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //所属接口
    @Column(name = "api_id")
    private String apiId;

    //属性名，固定的Authorization
    @Column(name = "key")
    private String key;

    //属性值，Bearer <token>
    @Column(name = "value")
    private String value;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
