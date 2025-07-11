package io.tiklab.postin.api.http.definition.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 认证（主） 实体
 */
@Entity
@Table(name="postin_http_auth")
public class AuthParamEntity implements Serializable {

    //id不自动生成，一对一使用apiId
    @Id
//    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //所属接口
    @Column(name = "api_id")
    private String apiId;

    //认证类型
    @Column(name = "type")
    private String type;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
