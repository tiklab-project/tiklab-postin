package io.tiklab.postin.autotest.http.unit.cases.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.postin.autotest.test.model.TestCase;

import java.io.Serializable;

/**
 * 接口单元用例 实体
 */
@Entity
@Table(name="autotest_api_unit")
public class ApiUnitCaseEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 用例
    @Column(name = "testcase_id",length = 32,notNull = true)
    private String testCaseId;

    // 路径
    @Column(name = "path",length = 256)
    private String path;

    // 请求类型,GET/POST
    @Column(name = "method_type",length = 32)
    private String methodType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public TestCase setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
        return null;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }
}
