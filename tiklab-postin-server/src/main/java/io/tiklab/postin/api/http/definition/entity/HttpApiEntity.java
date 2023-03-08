package io.tiklab.postin.api.http.definition.entity;


import io.tiklab.dal.jpa.annotation.*;

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

    //路径
    @Column(name = "path",length = 256,notNull = true)
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }
}
