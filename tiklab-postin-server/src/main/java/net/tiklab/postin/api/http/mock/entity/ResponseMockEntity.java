package net.tiklab.postin.api.http.mock.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * mock
 * http协议
 * 响应体 实体
 */
@Entity
@Table(name="postin_http_mock_response")
public class ResponseMockEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    //所属mock
    @Column(name = "mock_id",length = 32,notNull = true)
    private String mockId;

    //响应体类型
    @Column(name = "body_type",length = 2048)
    private String bodyType;

    //http 状态码
    @Column(name = "http_code",length = 2048)
    private String httpCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMockId() {
        return mockId;
    }

    public void setMockId(String mockId) {
        this.mockId = mockId;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
