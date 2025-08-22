package io.tiklab.postin.api.http.definition.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;


/**
 *  db操作中的变量 模型
 */
@ApiModel
@Mapper
public class OperateDatabaseVariable extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name="operationId",desc="所属操作id")
    private String operationId;

    @ApiProperty(name="name",desc="名称")
    private String name;

    @ApiProperty(name="variableType",desc="类型")
    private String variableType;

    @ApiProperty(name="jsonPath",desc="jsonPath")
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
