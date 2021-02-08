package com.darthcloud.apibox.api.apidef.entity;


import com.darthcloud.dal.jpa.annotation.Column;
import com.darthcloud.dal.jpa.annotation.Id;
import com.darthcloud.dal.jpa.annotation.Table;

import java.io.Serializable;

@Table(name="apibox_pre_script")
public class PreScriptPo implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "method_id",length = 32,notNull = true)
    private String methodId;

    @Column(name = "script",length = 2048,notNull = true)
    private String script;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
