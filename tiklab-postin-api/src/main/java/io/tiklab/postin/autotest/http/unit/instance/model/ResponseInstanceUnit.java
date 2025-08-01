package io.tiklab.postin.autotest.http.unit.instance.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 接口单元
 * http协议
 * 响应数据实例 模型
 */
@ApiModel
@Mapper
@Join
public class ResponseInstanceUnit extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="apiUnitInstance",desc="所属测试实例",required = true)
    @Mappings({
            @Mapping(source = "apiUnitInstance.id",target = "apiUnitInstanceId")
    })
    @JoinField(key = "id")
    private ApiUnitInstance apiUnitInstance;

    @ApiProperty(name="responseHeader",desc="响应头数据")
    private String responseHeader;

    @ApiProperty(name="responseBody",desc="响应体数据")
    private String responseBody;

    private List<AssertInstanceUnit> assertInstanceUnitList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiUnitInstance getApiUnitInstance() {
        return apiUnitInstance;
    }

    public void setApiUnitInstance(ApiUnitInstance apiUnitInstance) {
        this.apiUnitInstance = apiUnitInstance;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public List<AssertInstanceUnit> getAssertInstanceList() {
        return assertInstanceUnitList;
    }

    public void setAssertInstanceList(List<AssertInstanceUnit> assertInstanceUnitList) {
        this.assertInstanceUnitList = assertInstanceUnitList;
    }
}
