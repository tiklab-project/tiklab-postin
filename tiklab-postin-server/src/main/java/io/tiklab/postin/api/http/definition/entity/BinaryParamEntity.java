package io.tiklab.postin.api.http.definition.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 定义
 * http协议’
 * binary 实体
 */
@Entity
@Table(name = "postin_http_request_binary")
public class BinaryParamEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id", length = 40)
    private String id;

    //所属接口
    @Column(name = "http_id",length = 40,notNull = true)
    private String httpId;

    //文件名
    @Column(name = "filename",length = 64, notNull = true)
    private String fileName;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
