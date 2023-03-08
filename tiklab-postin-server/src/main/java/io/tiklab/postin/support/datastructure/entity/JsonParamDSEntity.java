package io.tiklab.postin.support.datastructure.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * json 类型  实体
 */
@Entity @Table(name="postin_model_json")
public class JsonParamDSEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 名称
    @Column(name = "param_name",length = 64,notNull = true)
    private String paramName;

    // 所属数据结构
    @Column(name = "subject_id",length = 32)
    private String dataStructureId;

    // 数据类型
    @Column(name = "data_type",length = 32)
    private String dataType;

    // 是否必选
    @Column(name = "required")
    private Integer required;

    // 描述
    @Column(name = "description",length = 128)
    private String description;

    // 所属上级
    @Column(name = "parent_id",length = 32)
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDataStructureId() {
        return dataStructureId;
    }

    public void setDataStructureId(String dataStructureId) {
        this.dataStructureId = dataStructureId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
