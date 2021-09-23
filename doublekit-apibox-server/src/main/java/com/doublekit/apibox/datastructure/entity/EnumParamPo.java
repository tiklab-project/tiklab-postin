package com.doublekit.apibox.datastructure.entity;


import com.doublekit.dal.jpa.mapper.annotation.Column;
import com.doublekit.dal.jpa.mapper.annotation.GeneratorValue;
import com.doublekit.dal.jpa.mapper.annotation.Id;
import com.doublekit.dal.jpa.mapper.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table(name="stru_enum")
public class EnumParamPo implements Serializable {

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
}
