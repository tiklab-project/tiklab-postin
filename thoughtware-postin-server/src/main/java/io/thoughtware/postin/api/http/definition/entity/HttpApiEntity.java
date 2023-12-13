package io.thoughtware.postin.api.http.definition.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 定义
 * http协议 实体
 */
@Entity
@Table(name="postin_api_http")
public class HttpApiEntity implements Serializable {

    @Id
    @Column(name = "id",length = 40)
    private String id;

    //所属接口
    @Column(name = "apix_id",length = 40)
    private String apixId;

    //请求类型
    @Column(name = "method_type",length = 32,notNull = true)
    private String methodType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApixId() {
        return apixId;
    }

    public void setApixId(String apixId) {
        this.apixId = apixId;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }
}