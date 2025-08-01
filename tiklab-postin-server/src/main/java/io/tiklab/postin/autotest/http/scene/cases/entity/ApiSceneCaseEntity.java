package io.tiklab.postin.autotest.http.scene.cases.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 接口性能测试用例 实体
 */
@Entity
@Table(name="autotest_api_scene")
public class ApiSceneCaseEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    //用例id
    @Column(name = "testcase_id",length = 32)
    private String testCaseId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }
}
