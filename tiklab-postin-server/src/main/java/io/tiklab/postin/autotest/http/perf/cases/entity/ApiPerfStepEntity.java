package io.tiklab.postin.autotest.http.perf.cases.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 接口性能下场景步骤 实体
 */
@Entity
@Table(name="autotest_api_perf_step")
public class ApiPerfStepEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口性能
    @Column(name = "api_perf_id")
    private String apiPerfId;

    // 绑定的
    @Column(name = "case_id")
    private String caseId;

    //用例类型 api-unit、api-scene
    @Column(name = "case_type")
    private String caseType;

    // 线程数
    @Column(name = "thread_count")
    private Integer threadCount;

    // 执行类型，1:次数 2:时间
    @Column(name = "execute_type")
    private Integer executeType;

    // 执行次数
    @Column(name = "execute_count")
    private Integer executeCount;

    // 时间类型
    @Column(name = "time_type")
    private String timeType;

    // 时间数
    @Column(name = "time_count")
    private Integer timeCount;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getApiPerfId() {
        return apiPerfId;
    }

    public void setApiPerfId(String apiPerfId) {
        this.apiPerfId = apiPerfId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
}
