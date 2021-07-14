package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.dss.annotation.IndexField;
import com.doublekit.dss.annotation.IndexQueryField;
import com.doublekit.join.annotation.Join;


@ApiModel
@Join
public class VersionMethod {

    @ApiProperty(name="methodEx",desc="基础信息",required = true)
    @IndexField
    @IndexQueryField
    private MethodEx methodEx;

    @ApiProperty(name="versionRespon",desc="响应参数",required = true)
    @IndexField
    @IndexQueryField
    private VersionRespon versionRespon;

    @ApiProperty(name="versionRequest",desc="请求参数",required = true)
    @IndexField
    @IndexQueryField
    private VersionRequest versionRequest;

    public MethodEx getMethodEx() {
        return methodEx;
    }

    public void setMethodEx(MethodEx methodEx) {
        this.methodEx = methodEx;
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

