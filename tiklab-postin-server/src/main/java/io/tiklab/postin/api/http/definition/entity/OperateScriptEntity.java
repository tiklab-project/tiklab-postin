package io.tiklab.postin.api.http.definition.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 自定义脚本 实体
 */
@Entity
@Table(name="postin_api_request_operate_script")
public class OperateScriptEntity implements Serializable {

    @Id
//    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //所属操作id
    @Column(name = "operation_id")
    private String operationId;

    //自定义脚本
    @Column(name = "script_text")
    private String scriptText;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }


    public String getScriptText() {
        return scriptText;
    }

    public void setScriptText(String scriptText) {
        this.scriptText = scriptText;
    }
}
