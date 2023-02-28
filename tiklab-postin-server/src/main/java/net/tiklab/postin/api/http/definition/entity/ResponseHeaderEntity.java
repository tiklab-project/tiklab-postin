package net.tiklab.postin.api.http.definition.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 定义
 * http协议
 * 响应 实体
 */
@Entity
@Table(name="postin_http_response_header")
public class ResponseHeaderEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 40)
    private String id;

    //所属接口
    @Column(name = "http_id",length = 40,notNull = true)
    private String httpId;

    //参数名称
    @Column(name = "header_name",length = 64,notNull = true)
    private String headerName;

    //是否必选
    @Column(name = "required",length = 2,notNull = true)
    private Integer required;

    //描述说明
    @Column(name = "description",length = 128)
    private String desc;

    //示例值
    @Column(name = "value",length = 128)
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

    public String getHttpId() {
        return httpId;
    }

    public void setHttpId(String httpId) {
        this.httpId = httpId;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
