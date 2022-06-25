package com.doublekit.apibox.integration.version.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.core.BaseModel;
import com.doublekit.join.annotation.Join;


@ApiModel
@Join
public class CompareVersion extends BaseModel {

    @ApiProperty(name ="currentId",desc = "当前方法id")
    private String currentId;

    @ApiProperty(name ="versionId",desc = "旧版本的id")
    private String versionId;


    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
