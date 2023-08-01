package io.tiklab.postin.api.http.test.instance.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;

import javax.validation.constraints.NotNull;

/**
 * http协议
 * 响应实例模型
 */
@ApiModel
@Join
@Mapper(targetName  = "io.tiklab.postin.api.http.test.instance.entity.ResponseInstanceEntity")
public class ResponseInstance extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="httpInstance",desc="所属测试实例",required = true)
    @Mappings({
            @Mapping(source = "httpInstance.id",target = "httpInstanceId")
    })
    private HttpInstance httpInstance;

    @ApiProperty(name="headers",desc="请求头")
    private java.lang.String headers;

    @ApiProperty(name="body",desc="请求参数")
    private java.lang.String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpInstance getHttpInstance() {
        return httpInstance;
    }

    public void setHttpInstance(HttpInstance httpInstance) {
        this.httpInstance = httpInstance;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
