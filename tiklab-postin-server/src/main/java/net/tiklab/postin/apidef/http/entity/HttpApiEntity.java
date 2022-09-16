package net.tiklab.postin.apidef.http.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;


@Entity
@Table(name="postin_api_http")
public class HttpApiEntity implements Serializable {

    @Id
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "apix_id",length = 40)
    private String apixId;

    @Column(name = "path",length = 256,notNull = true)
    private String path;

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
