package com.darthcloud.apibox.client.model;

import com.darthcloud.apibox.annotation.ApiProperty;

import java.lang.reflect.Type;

public class ApiPropertyMeta {

    private Type type;

    private ApiProperty apiProperty;

    public ApiPropertyMeta(Type type, ApiProperty apiProperty) {
        this.type = type;
        this.apiProperty = apiProperty;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ApiProperty getApiProperty() {
        return apiProperty;
    }

    public void setApiProperty(ApiProperty apiProperty) {
        this.apiProperty = apiProperty;
    }
}
