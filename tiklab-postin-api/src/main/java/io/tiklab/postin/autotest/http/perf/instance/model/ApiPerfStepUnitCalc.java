package io.tiklab.postin.autotest.http.perf.instance.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.autotest.common.utils.PercentileEstimator;
import io.tiklab.toolkit.beans.annotation.Mapper;

import java.util.Map;

/**
 * 接口性能关联的场景用例下的单个接口单元 模型
 */
@ApiModel
@Mapper
public class ApiPerfStepUnitCalc extends BaseModel {
    private String id;
    private String name;
    private String apiPerfStepId;
    private String apiPerfStepUnitId;
    private String apiPerfStepInstanceId;
    private int totalRequests;
    private int successfulRequests;
    private int failedRequests;
    private double avgElapsedTime;
    private double maxElapsedTime;
    private double minElapsedTime;
    private double totalElapsedTime;
    private double tps;
    private double errorRate;
    private double percentile90;
    private double percentile95;
    private double percentile99;
    private Map<Integer, PercentileEstimator> percentileEstimators;

    public ApiPerfStepUnitCalc() {
        this.successfulRequests = 0;
        this.failedRequests = 0;
        this.totalElapsedTime = 0.0;
        this.maxElapsedTime = Double.MIN_VALUE;
        this.minElapsedTime = Double.MAX_VALUE;
        this.avgElapsedTime = 0.0;
        this.tps = 0.0;
        this.errorRate = 0.0;
        this.percentile90 = 0.0;
        this.percentile95 = 0.0;
        this.percentile99 = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiPerfStepId() {
        return apiPerfStepId;
    }

    public void setApiPerfStepId(String apiPerfStepId) {
        this.apiPerfStepId = apiPerfStepId;
    }

    public String getApiPerfStepUnitId() {
        return apiPerfStepUnitId;
    }

    public void setApiPerfStepUnitId(String apiPerfStepUnitId) {
        this.apiPerfStepUnitId = apiPerfStepUnitId;
    }

    public String getApiPerfStepInstanceId() {
        return apiPerfStepInstanceId;
    }

    public void setApiPerfStepInstanceId(String apiPerfStepInstanceId) {
        this.apiPerfStepInstanceId = apiPerfStepInstanceId;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public int getSuccessfulRequests() {
        return successfulRequests;
    }

    public void setSuccessfulRequests(int successfulRequests) {
        this.successfulRequests = successfulRequests;
    }

    public int getFailedRequests() {
        return failedRequests;
    }

    public void setFailedRequests(int failedRequests) {
        this.failedRequests = failedRequests;
    }

    public double getAvgElapsedTime() {
        return avgElapsedTime;
    }

    public void setAvgElapsedTime(double avgElapsedTime) {
        this.avgElapsedTime = avgElapsedTime;
    }

    public double getMaxElapsedTime() {
        return maxElapsedTime;
    }

    public void setMaxElapsedTime(double maxElapsedTime) {
        this.maxElapsedTime = maxElapsedTime;
    }

    public double getMinElapsedTime() {
        return minElapsedTime;
    }

    public void setMinElapsedTime(double minElapsedTime) {
        this.minElapsedTime = minElapsedTime;
    }

    public double getTotalElapsedTime() {
        return totalElapsedTime;
    }

    public void setTotalElapsedTime(double totalElapsedTime) {
        this.totalElapsedTime = totalElapsedTime;
    }

    public double getTps() {
        return tps;
    }

    public void setTps(double tps) {
        this.tps = tps;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(double errorRate) {
        this.errorRate = errorRate;
    }

    public double getPercentile90() {
        return percentile90;
    }

    public void setPercentile90(double percentile90) {
        this.percentile90 = percentile90;
    }

    public double getPercentile95() {
        return percentile95;
    }

    public void setPercentile95(double percentile95) {
        this.percentile95 = percentile95;
    }

    public double getPercentile99() {
        return percentile99;
    }

    public void setPercentile99(double percentile99) {
        this.percentile99 = percentile99;
    }

    public Map<Integer, PercentileEstimator> getPercentileEstimators() {
        return percentileEstimators;
    }

    public void setPercentileEstimators(Map<Integer, PercentileEstimator> percentileEstimators) {
        this.percentileEstimators = percentileEstimators;
    }
}
