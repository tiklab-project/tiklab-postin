package io.tiklab.postin.api.ws.ws.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;

/**
 * ws协议 实体
 */
@Entity
@Table(name="postin_api_ws")
public class WSApiEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    //所属接口
    @Column(name = "apix_id",length = 32)
    private String apixId;

    //路径
    @Column(name = "path",length = 256,notNull = true)
    private String path;


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

}
