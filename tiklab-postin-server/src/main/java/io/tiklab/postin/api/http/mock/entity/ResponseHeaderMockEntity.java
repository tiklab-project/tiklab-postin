package io.tiklab.postin.api.http.mock.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * mock
 * http协议
 * 响应头 实体
 */
@Entity
@Table(name="postin_http_mock_response_header")
public class ResponseHeaderMockEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    //所属mock
    @Column(name = "mock_id",length = 32,notNull = true)
    private String mockId;

    //参数名称
    @Column(name = "header_name",length = 64)
    private String headerName;

    //示例值
    @Column(name = "value",length = 64)
    private String value;

    //排序
    @Column(name = "sort",length = 4)
    private Integer sort;

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

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
