package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dal.jpa.criteria.annotation.*;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.dal.jpa.criteria.model.OrderParam;
import com.doublekit.dal.jpa.criteria.model.Orders;
import com.doublekit.dal.jpa.criteria.model.PageParam;

import java.util.List;

@ApiModel
@CriteriaQuery
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