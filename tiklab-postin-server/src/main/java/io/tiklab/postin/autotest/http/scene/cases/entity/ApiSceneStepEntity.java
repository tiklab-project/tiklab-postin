package io.tiklab.postin.autotest.http.scene.cases.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 接口场景下绑定的步骤 实体
 */
@Entity
@Table(name="autotest_api_scene_step")
public class ApiSceneStepEntity implements Serializable {

    @Id
//   @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属场景
    @Column(name = "api_scene_id",length = 32)
    private String apiSceneId;

    // 绑定的单元用例
    @Column(name = "api_unit_id",length = 32)
    private String apiUnitId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiSceneId() {
        return apiSceneId;
    }

    public void setApiSceneId(String apiSceneId) {
        this.apiSceneId = apiSceneId;
    }

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

}
