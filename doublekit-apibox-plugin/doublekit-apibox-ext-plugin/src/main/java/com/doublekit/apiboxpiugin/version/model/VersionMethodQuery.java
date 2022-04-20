package com.doublekit.apiboxpiugin.version.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;

@ApiModel
public class VersionMethodQuery {

    @ApiProperty(name ="currentId",desc = "当前方法id")
    private String currentId;

    @ApiProperty(name ="oldId",desc = "旧版本的id")
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
