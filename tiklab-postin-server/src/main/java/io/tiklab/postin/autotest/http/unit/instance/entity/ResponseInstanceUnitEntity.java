package io.tiklab.postin.autotest.http.unit.instance.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 接口单元
 * http协议
 * 响应体实例 实体
 */
@Entity @Table(name="autotest_api_response_instance")
public class ResponseInstanceUnitEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    //所属测试实例id
    @Column(name = "api_unit_instance_id",length = 32)
    private String apiUnitInstanceId;

    //响应头
    @Column(name = "response_header",length = 2048)
    private String responseHeader;

    //响应体
    @Column(name = "response_body",length = 2048)
    private String responseBody;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUnitInstanceId() {
        return apiUnitInstanceId;
    }

    public void setApiUnitInstanceId(String apiUnitInstanceId) {
        this.apiUnitInstanceId = apiUnitInstanceId;
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
}
