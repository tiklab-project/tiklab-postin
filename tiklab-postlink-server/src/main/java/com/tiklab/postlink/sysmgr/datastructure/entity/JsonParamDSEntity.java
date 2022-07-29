package com.tiklab.postlink.sysmgr.datastructure.entity;


import com.tiklab.dal.jpa.annotation.Column;
import com.tiklab.dal.jpa.annotation.GeneratorValue;
import com.tiklab.dal.jpa.annotation.Id;
import com.tiklab.dal.jpa.annotation.Table;import com.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="postlink_model_json")
public class JsonParamDSEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "param_name",length = 64,notNull = true)
    private String paramName;

    @Column(name = "subject_id",length = 32)
    private String dataStructureId;

    @Column(name = "data_type",length = 32)
    private String dataType;

    @Column(name = "required")
    private Integer required;

    @Column(name = "description",length = 128)
    private String description;

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
