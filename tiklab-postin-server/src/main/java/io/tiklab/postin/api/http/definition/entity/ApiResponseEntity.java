package io.tiklab.postin.api.http.definition.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 定义
 * http协议
 * 响应区 实体
 */
@Entity
@Table(name="postin_http_response")
public class ApiResponseEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 40)
    private String id;

    //所属接口
    @Column(name = "http_id",length = 40,notNull = true)
    private String httpId;

    //状态码
    @Column(name = "http_code",length = 32)
    private Integer httpCode;

    //创建时间
    @Column(name = "create_time",length = 4)
    private Timestamp createTime;

    //名称
    @Column(name = "name",length = 64)
    private String name;

    //数据类型
    @Column(name = "data_type",length = 32)
    private String dataType;

    //json串
    @Column(name = "json_text")
    private String jsonText;

    //raw类型
    @Column(name = "raw_text")
    private String rawText;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }
}
