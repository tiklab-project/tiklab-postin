package io.tiklab.postin.autotest.http.scene.instance.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.sql.Timestamp;

/**
 * 场景历史实例 实体
 */
@Entity
@Table(name="autotest_api_scene_instance")
public class ApiSceneInstanceEntity {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 测试用例id
    @Column(name = "api_scene_id")
    private String apiSceneId;

    //测试结果 1,0
    @Column(name = "result")
    private Integer result;

    // 步骤数量
    @Column(name = "test_number")
    private Integer testNumber;

    // 测试通过数量
    @Column(name = "pass_number")
    private Integer passNumber;

    // 未通过数量
    @Column(name = "fail_number")
    private Integer failNumber;

    //测试通过率
    @Column(name = "pass_rate")
    private String passRate;

    // 耗时
    @Column(name = "elapsed_time")
    private Integer elapsedTime;

    // 创建时间
    @Column(name = "create_time",notNull = true)
    private Timestamp createTime;

    // 创建人
    @Column(name = "create_user",notNull = true)
    private String createUser;

    // 执行次数
    @Column(name = "execute_number")
    private Integer executeNumber;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(Integer testNumber) {
        this.testNumber = testNumber;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getApiSceneId() {
        return apiSceneId;
    }

    public void setApiSceneId(String apiSceneId) {
        this.apiSceneId = apiSceneId;
    }

    public Integer getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(Integer passNumber) {
        this.passNumber = passNumber;
    }

    public Integer getFailNumber() {
        return failNumber;
    }

    public void setFailNumber(Integer failNumber) {
        this.failNumber = failNumber;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }
}
