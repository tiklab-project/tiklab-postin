package io.tiklab.postin.autotest.http.scene.instance.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 接口场景下步骤的公共实例 实体
 */
@Entity
@Table(name="autotest_api_scene_step_instance_bind")
public class ApiSceneStepInstanceBindEntity implements Serializable {

    @Id
//     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 接口场景历史id
    @Column(name = "api_scene_instance_id",length = 32)
    private String apiSceneInstanceId;

    // 接口单元历史id
    @Column(name = "api_unit_instance_id",length = 32)
    private String apiUnitInstanceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiSceneInstanceId() {
        return apiSceneInstanceId;
    }

    public void setApiSceneInstanceId(String apiSceneInstanceId) {
        this.apiSceneInstanceId = apiSceneInstanceId;
    }

    public String getApiUnitInstanceId() {
        return apiUnitInstanceId;
    }

    public void setApiUnitInstanceId(String apiUnitInstanceId) {
        this.apiUnitInstanceId = apiUnitInstanceId;
    }
}
