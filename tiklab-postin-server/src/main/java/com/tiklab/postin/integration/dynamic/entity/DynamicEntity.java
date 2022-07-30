package com.tiklab.postin.integration.dynamic.entity;


import com.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity @Table(name="postin_dynamic")
public class DynamicEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "workspace_id",length = 32,notNull = true)
    private String workspaceId;

    @Column(name = "user_id",length = 32)
    private String userId;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "model_id",length = 32,notNull = true)
    private String modelId;

    @Column(name = "model",length = 32,notNull = true)
    private String model;

    @Column(name = "dynamic_type",length = 32,notNull = true)
    private String dynamicType;

    @Column(name = "operation_time",length = 32,notNull = true)
    private Timestamp operationTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDynamicType() {
        return dynamicType;
    }

    public void setDynamicType(String dynamicType) {
        this.dynamicType = dynamicType;
    }

    public Timestamp getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Timestamp operationTime) {
        this.operationTime = operationTime;
    }

}
