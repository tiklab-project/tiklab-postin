package com.darthcloud.apibox.apimock.entity;


import com.darthcloud.dal.jpa.annotation.mapper.Column;
import com.darthcloud.dal.jpa.annotation.mapper.Id;
import com.darthcloud.dal.jpa.annotation.mapper.Table;

import java.io.Serializable;

@Table(name="apibox_response_result_mock")
public class ResponseResultMockPo implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "mock_id",length = 32,notNull = true)
    private String mockId;

    @Column(name = "result_type",length = 32,notNull = true)
    private String resultType;

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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}