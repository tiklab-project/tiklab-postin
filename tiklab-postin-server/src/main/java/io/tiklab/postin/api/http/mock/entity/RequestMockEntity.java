package io.tiklab.postin.api.http.mock.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * mock
 * http协议
 * 请求体 实体
 */
@Entity
@Table(name="postin_http_mock_request")
public class RequestMockEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    //所属mock
    @Column(name = "mock_id",length = 32,notNull = true)
    private String mockId;

    //请求体类型
    @Column(name = "body_type",length = 32,notNull = true)
    private String bodyType;

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

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
