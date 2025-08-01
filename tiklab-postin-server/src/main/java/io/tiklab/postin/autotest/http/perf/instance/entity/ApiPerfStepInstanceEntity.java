package io.tiklab.postin.autotest.http.perf.instance.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 接口性能历史实例 实体
 */
@Entity
@Table(name="autotest_api_perf_step_instance")
public class ApiPerfStepInstanceEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口性能用例
    @Column(name = "api_perf_instance_id")
    private String apiPerfInstanceId;

    // 测试总次数
    @Column(name = "total_requests")
    private Integer totalRequests;

    @Column(name = "successful_requests")
    private Integer successfulRequests;

    @Column(name = "failed_requests")
    private Integer failedRequests;

    @Column(name = "avg_elapsed_time")
    private Double avgElapsedTime;

    @Column(name = "max_elapsed_time")
    private Double maxElapsedTime;

    @Column(name = "min_elapsed_time")
    private Double minElapsedTime;

    @Column(name = "total_elapsed_time")
    private Double totalElapsedTime;

    @Column(name = "tps")
    private Double tps;

    @Column(name = "error_rate")
    private Double errorRate;

    @Column(name = "percentile90")
    private Double percentile90;

    @Column(name = "percentile95")
    private Double percentile95;

    @Column(name = "percentile99")
    private Double percentile99;

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

    public Integer getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(Integer totalRequests) {
        this.totalRequests = totalRequests;
    }

    public Integer getSuccessfulRequests() {
        return successfulRequests;
    }

    public void setSuccessfulRequests(Integer successfulRequests) {
        this.successfulRequests = successfulRequests;
    }

    public Integer getFailedRequests() {
        return failedRequests;
    }

    public void setFailedRequests(Integer failedRequests) {
        this.failedRequests = failedRequests;
    }

    public Double getAvgElapsedTime() {
        return avgElapsedTime;
    }

    public void setAvgElapsedTime(Double avgElapsedTime) {
        this.avgElapsedTime = avgElapsedTime;
    }

    public Double getMaxElapsedTime() {
        return maxElapsedTime;
    }

    public void setMaxElapsedTime(Double maxElapsedTime) {
        this.maxElapsedTime = maxElapsedTime;
    }

    public Double getMinElapsedTime() {
        return minElapsedTime;
    }

    public void setMinElapsedTime(Double minElapsedTime) {
        this.minElapsedTime = minElapsedTime;
    }

    public Double getTotalElapsedTime() {
        return totalElapsedTime;
    }

    public void setTotalElapsedTime(Double totalElapsedTime) {
        this.totalElapsedTime = totalElapsedTime;
    }

    public Double getTps() {
        return tps;
    }

    public void setTps(Double tps) {
        this.tps = tps;
    }

    public Double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(Double errorRate) {
        this.errorRate = errorRate;
    }

    public Double getPercentile90() {
        return percentile90;
    }

    public void setPercentile90(Double percentile90) {
        this.percentile90 = percentile90;
    }

    public Double getPercentile95() {
        return percentile95;
    }

    public void setPercentile95(Double percentile95) {
        this.percentile95 = percentile95;
    }

    public Double getPercentile99() {
        return percentile99;
    }

    public void setPercentile99(Double percentile99) {
        this.percentile99 = percentile99;
    }
}
