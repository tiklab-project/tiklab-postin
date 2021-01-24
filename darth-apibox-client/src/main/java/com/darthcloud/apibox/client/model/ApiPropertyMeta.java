package com.darthcloud.apibox.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.darthcloud.apibox.annotation.ApiProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApiPropertyMeta extends ParamItem{

    @JSONField(serialize = false)
    private Field field;

    @JSONField(serialize = false)
    private ApiProperty apiProperty;

    public ApiPropertyMeta(Field field, ApiProperty apiProperty) {
        this.field = field;
        this.apiProperty = apiProperty;
    }

    public ApiPropertyMeta(Field field, ApiProperty apiProperty,Type type,Type paramType) {
        this.field = field;
        this.apiProperty = apiProperty;
        this.setType(type);
        this.setParamType(paramType);

        this.setName(apiProperty.name());
        this.setDesc(apiProperty.desc());
        this.setRequired(apiProperty.required());
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public ApiProperty getApiProperty() {
        return apiProperty;
    }

    public void setApiProperty(ApiProperty apiProperty) {
        this.apiProperty = apiProperty;
    }
}
