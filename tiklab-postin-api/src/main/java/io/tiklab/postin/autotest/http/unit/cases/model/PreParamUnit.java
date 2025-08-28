package io.tiklab.postin.autotest.http.unit.cases.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.http.definition.model.OperateDatabase;
import io.tiklab.postin.api.http.definition.model.OperateScript;
import io.tiklab.toolkit.beans.annotation.Mapper;


/**
 * 前置 模型
 */
@ApiModel
@Mapper
public class PreParamUnit extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name="apiUnitId",desc="所属用例")
    private String apiUnitId;

    @ApiProperty(name="name",desc="名称")
    private String name;

    @ApiProperty(name="type",desc="操作类型")
    private String type;

    @ApiProperty(name="enabled",desc="是否启用")
    private Integer enabled;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="operateDatabase",desc="数据库操作")
    private OperateDatabase operateDatabase;

    @ApiProperty(name="operateScript",desc="脚本操作")
    private OperateScript operateScript;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public OperateDatabase getOperateDatabase() {
        return operateDatabase;
    }

    public void setOperateDatabase(OperateDatabase operateDatabase) {
        this.operateDatabase = operateDatabase;
    }

    public OperateScript getOperateScript() {
        return operateScript;
    }

    public void setOperateScript(OperateScript operateScript) {
        this.operateScript = operateScript;
    }
}
