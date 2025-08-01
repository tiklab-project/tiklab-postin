package io.tiklab.postin.autotest.http.perf.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneCase;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.sql.Timestamp;

/**
 * 接口性能场景步骤 模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.postin.autotest.http.perf.cases.entity.ApiPerfStepEntity")
@Join
public class ApiPerfStep extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiPerf",desc="所属apiPerf")
    private String apiPerfId;

    @ApiProperty(name="caseId",desc="caseId")
    private String caseId;

    @ApiProperty(name="caseType",desc="用例类型")
    private String caseType;

    @ApiProperty(name="threadCount",desc="线程数")
    private Integer threadCount;

    @ApiProperty(name="executeCount",desc="执行数")
    private Integer executeCount;

    @ApiProperty(name="executeType",desc="1:次数 2:时间")
    private Integer executeType;

    @ApiProperty(name="timeType",desc="时间类型：hour、minute、second")
    private String timeType;

    @ApiProperty(name="timeCount",desc="时间类型：hour、minute、second")
    private Integer timeCount;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="apiScene",desc="所属apiScene")
    private ApiSceneCase apiScene;

    @ApiProperty(name="apiScene",desc="所属apiScene")
    private ApiUnitCase apiUnitCase;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiPerfId() {
        return apiPerfId;
    }

    public void setApiPerfId(String apiPerfId) {
        this.apiPerfId = apiPerfId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public ApiSceneCase getApiScene() {
        return apiScene;
    }

    public void setApiScene(ApiSceneCase apiScene) {
        this.apiScene = apiScene;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
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

    public ApiUnitCase getApiUnitCase() {
        return apiUnitCase;
    }

    public void setApiUnitCase(ApiUnitCase apiUnitCase) {
        this.apiUnitCase = apiUnitCase;
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
