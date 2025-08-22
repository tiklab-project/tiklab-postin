package io.tiklab.postin.api.http.definition.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * db操作中的变量 实体
 */
@Entity
@Table(name="postin_api_request_operate_db_variable")
public class OperateDatabaseVariableEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //所属操作id
    @Column(name = "operation_id")
    private String operationId;

    //名称
    @Column(name = "name")
    private String name;

    //类型
    @Column(name = "variable_type")
    private String variableType;

    //jsonPath
    @Column(name = "json_path")
    private String jsonPath;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }
}
