package io.tiklab.postin.autotest.http.perf.cases.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 接口性能 实体
 */
@Entity @Table(name="autotest_api_perfcase")
public class ApiPerfCaseEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 公共用例id
    @Column(name = "testcase_id",length = 32)
    private String testCaseId;

    // 绑定的用例id  接口单元或接口场景
    @Column(name = "bind_case_Id")
    private String bindCaseId;

    // 线程数,并发用户数
    @Column(name = "thread_count")
    private Integer threadCount;

    // 执行类型，1:次数 2:时间
    @Column(name = "execute_type")
    private Integer executeType;

    // 次数: 执行次数
    @Column(name = "execute_count")
    private Integer executeCount;

    // 时间: 时间类型
    @Column(name = "time_type")
    private String timeType;

    // 时间: 时间数
    @Column(name = "time_count")
    private Integer timeCount;

    // 并发用户数到达指定个数所花费的时间
    @Column(name = "ramp_up")
    private Integer rampUp;

    // 分几步达到
    @Column(name = "step")
    private Integer step;


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


    public String getBindCaseId() {
        return bindCaseId;
    }

    public void setBindCaseId(String bindCaseId) {
        this.bindCaseId = bindCaseId;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }

    public Integer getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(Integer executeCount) {
        this.executeCount = executeCount;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public Integer getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(Integer timeCount) {
        this.timeCount = timeCount;
    }

    public Integer getRampUp() {
        return rampUp;
    }

    public void setRampUp(Integer rampUp) {
        this.rampUp = rampUp;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
