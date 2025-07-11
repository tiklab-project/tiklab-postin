package io.tiklab.postin.api.apix.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;

/**
 * 定义
 * http协议
 * 请求中json 实体
 */
@Entity
@Table(name="postin_api_request_json")
public class JsonParamEntity implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    //所属接口
    @Column(name = "api_id")
    private String apiId;

    @Column(name = "json_text")
    private String jsonText;


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

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }
}
