package io.tiklab.postin.autotest.http.unit.cases.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;

/**
 * ApiKey自定义认证 实体
 */
@Entity
@Table(name="autotest_api_auth_apikey")
public class AuthApiKeyUnitEntity implements Serializable {

    //id不自动生成，一对一使用apiId
    @Id
//    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    // 所属接口单元用例
    @Column(name = "api_unit_id",length = 32,notNull = true)
    private String apiUnitId;

    //自定义的属性名
    @Column(name = "name")
    private String name;

    //属性值
    @Column(name = "value")
    private String value;

    //属性位置 header/query
    @Column(name = "apikey_in")
    private String apikeyIn;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getApikeyIn() {
        return apikeyIn;
    }

    public void setApikeyIn(String apikeyIn) {
        this.apikeyIn = apikeyIn;
    }
}
