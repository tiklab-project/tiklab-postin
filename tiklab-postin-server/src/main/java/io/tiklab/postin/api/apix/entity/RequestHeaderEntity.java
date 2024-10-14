package io.tiklab.postin.api.apix.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 请求头 实体
 */
@Entity
@Table(name="postin_api_request_header")
public class RequestHeaderEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 40)
    private String id;

    //所属接口
    @Column(name = "api_id",length = 40)
    private String apiId;

    //所属空间用于全局
    @Column(name = "workspace_id",length = 40)
    private String workspaceId;

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

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
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
