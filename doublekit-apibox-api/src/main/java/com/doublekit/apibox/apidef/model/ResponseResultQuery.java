package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dal.jpa.annotation.criteria.CriteriaQuery;
import com.doublekit.dal.jpa.annotation.criteria.QueryField;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.dal.jpa.annotation.criteria.QueryTypeEnum;

@ApiModel
@CriteriaQuery(entityAlias = "ResponseResultEntity")
public class ResponseResultQuery {

    @ApiProperty(name ="methodId",desc = "接口ID，精确匹配")
    @QueryField(type = QueryTypeEnum.equal)
    private String methodId;

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }


}