package io.tiklab.postin.autotest.http.perf.instance.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 接口性能历史实例明细 实体
 */
@Entity
@Table(name="autotest_api_perf_instance_report")
public class ApiPerfInstanceReportEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口性能历史Id
    @Column(name = "api_perf_instance_id")
    private String apiPerfInstanceId;

    // 时间戳
    @Column(name = "timestamp")
    private Timestamp timestamp;

    // 线程数
    @Column(name = "thread_count")
    private Integer threadCount;

    // tps
    @Column(name = "tps")
    private Double tps;

    // 平均时间
    @Column(name = "avg_elapsed_time")
    private Double avgElapsedTime;

    // 错误率
    @Column(name = "error_rate")
    private String errorRate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiPerfInstanceId() {
        return apiPerfInstanceId;
    }

    public void setApiPerfInstanceId(String apiPerfInstanceId) {
        this.apiPerfInstanceId = apiPerfInstanceId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public Double getTps() {
        return tps;
    }

    public void setTps(Double tps) {
        this.tps = tps;
    }

    public Double getAvgElapsedTime() {
        return avgElapsedTime;
    }

    public void setAvgElapsedTime(Double avgElapsedTime) {
        this.avgElapsedTime = avgElapsedTime;
    }

    public String getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(String errorRate) {
        this.errorRate = errorRate;
    }
}
