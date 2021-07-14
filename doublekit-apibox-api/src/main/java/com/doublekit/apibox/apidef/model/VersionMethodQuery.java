package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dal.jpa.criteria.annotation.CriteriaQuery;
import com.doublekit.dal.jpa.criteria.annotation.QueryField;
import com.doublekit.dal.jpa.criteria.annotation.QueryTypeEnum;

@ApiModel
@CriteriaQuery
public class VersionMethodQuery {

    @ApiProperty(name ="currentId",desc = "当前方法id")
    @QueryField(type = QueryTypeEnum.equal)
    private String currentId;

    @ApiProperty(name ="oldId",desc = "旧版本的id")
    @QueryField(type = QueryTypeEnum.equal)
    private String  oldId;

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }
}
