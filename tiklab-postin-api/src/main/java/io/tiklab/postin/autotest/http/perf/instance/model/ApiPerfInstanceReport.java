package io.tiklab.postin.autotest.http.perf.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * 接口性能实例明细 模型
 */
@ApiModel
@Mapper
public class ApiPerfInstanceReport extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiPerfInstanceId",desc="所属接口性能历史Id")
    private String apiPerfInstanceId;

    @ApiProperty(name="timestamp",desc="时间戳")
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    private Timestamp timestamp;

    @ApiProperty(name="threadCount",desc="线程数")
    private Integer threadCount;

    @ApiProperty(name="tps",desc="tps")
    private Double tps;

    @ApiProperty(name="avgElapsedTime",desc="平均时间")
    private Double avgElapsedTime;

    @ApiProperty(name="errorRate",desc="错误率")
    private  String  errorRate;

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
