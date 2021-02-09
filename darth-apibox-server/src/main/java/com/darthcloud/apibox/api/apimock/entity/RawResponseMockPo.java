package com.darthcloud.apibox.api.apimock.entity;


import com.darthcloud.dal.jpa.annotation.Column;
import com.darthcloud.dal.jpa.annotation.Id;
import com.darthcloud.dal.jpa.annotation.Table;

import java.io.Serializable;

@Table(name="apibox_raw_response_mock")
public class RawResponseMockPo implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "mock_id",length = 32,notNull = true)
    private String mockId;

    @Column(name = "result",length = 2048)
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
