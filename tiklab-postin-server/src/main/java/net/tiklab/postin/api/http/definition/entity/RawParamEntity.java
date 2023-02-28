package net.tiklab.postin.api.http.definition.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 定义
 * http协议
 * 请求中raw 实体
 */
@Entity
@Table(name="postin_http_request_raw")
public class RawParamEntity implements Serializable {

    @Id
    @Column(name = "id",length = 40)
    private String id;

    //所属接口
    @Column(name = "http_id",length = 40,notNull = true)
    private String httpId;

    //raw文本
    @Column(name = "raw",length = 2048,notNull = true)
    private String raw;

    //媒体类型 如: application/json
    @Column(name = "type",length = 32,notNull = true)
    private String type;

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

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
