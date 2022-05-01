package com.doublekit.apiboxpiugin.version.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.dis.annotation.IndexField;
import com.doublekit.dis.annotation.IndexQueryField;
import com.doublekit.join.annotation.Join;


@ApiModel
@Join
public class VersionMethod {

    @ApiProperty(name="methodEx",desc="基础信息",required = true)
    @IndexField
    @IndexQueryField
    private Apix apix;

    @ApiProperty(name="versionRespon",desc="响应参数",required = true)
    @IndexField
    @IndexQueryField
    private VersionRespon versionRespon;

    @ApiProperty(name="versionRequest",desc="请求参数",required = true)
    @IndexField
    @IndexQueryField
    private VersionRequest versionRequest;

    public Apix getApix() {
        return apix;
    }

    public void setApix(Apix apix) {
        this.apix = apix;
    }

    public VersionRespon getVersionRespon() {
        return versionRespon;
    }

    public void setVersionRespon(VersionRespon versionRespon) {
        this.versionRespon = versionRespon;
    }

    public VersionRequest getVersionRequest() {
        return versionRequest;
    }

    public void setVersionRequest(VersionRequest versionRequest) {
        this.versionRequest = versionRequest;
    }
}

