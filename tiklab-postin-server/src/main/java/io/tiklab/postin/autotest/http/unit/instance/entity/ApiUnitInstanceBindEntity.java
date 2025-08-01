package io.tiklab.postin.autotest.http.unit.instance.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 接口单元实例中间表 实体
 */
@Entity
@Table(name="autotest_api_unit_instance_bind")
public class ApiUnitInstanceBindEntity implements Serializable {

    @Id
//     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 接口单元用例
    @Column(name = "api_unit_id",length = 32)
    private String apiUnitId;

    // 所属接口单元历史实例
    @Column(name = "api_unit_instance_id",length = 32)
    private String apiUnitInstanceId;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

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

    public String getApiUnitInstanceId() {
        return apiUnitInstanceId;
    }

    public void setApiUnitInstanceId(String apiUnitInstanceId) {
        this.apiUnitInstanceId = apiUnitInstanceId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
