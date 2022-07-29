package com.tiklab.postlink.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.tiklab.postlink.annotation.ApiProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class ApiPropertyMeta extends TypeMeta {

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
