package io.tiklab.postin.api.ws.ws.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.apix.model.Apix;

/**
 * ws 特有字段模型
 */
@ApiModel
@Join
@Mapper
public class WSApi extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    private String id;

    @ApiProperty(name="apix",desc="所属接口公共定义")
    @Mappings({
            @Mapping(source = "apix.id",target = "apixId")
    })
    @JoinQuery(key = "id")
    private Apix apix;

    @ApiProperty(name="path",desc="路径",required = true)
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public WSApi setId(String id) {
        this.id = id;
        return this;
    }

    public Apix getApix() {
        return apix;
    }

    public void setApix(Apix apix) {
        this.apix = apix;
    }


}
