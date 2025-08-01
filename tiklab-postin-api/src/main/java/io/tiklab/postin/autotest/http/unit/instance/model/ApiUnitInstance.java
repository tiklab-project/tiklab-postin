package io.tiklab.postin.autotest.http.unit.instance.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;

/**
 * 用例实例或用例步骤记录 模型
 */
@ApiModel
@Mapper
@Join
public class ApiUnitInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiUnit",desc="所属接口")
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinField(key = "id")
    private ApiUnitCase apiUnit;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="elapsedTime",desc="耗时")
    private Double elapsedTime;

    @ApiProperty(name="requestInstance",desc="实例-请求部分")
    private RequestInstanceUnit requestInstanceUnit;

    @ApiProperty(name="responseInstance",desc="实例-响应部分")
    private ResponseInstanceUnit responseInstanceUnit;

    @ApiProperty(name="result",desc="结果  1,0")
    private Integer result;

    @ApiProperty(name="statusCode",desc="状态码")
    private Integer statusCode;

    @ApiProperty(name = "errMessage", desc = "错误信息")
    private String errMessage;

    @ApiProperty(name="createUser",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinField(key = "id")
    private User createUser;

    @ApiProperty(name="executeNumber",desc="执行次数")
    private Integer executeNumber;

    @ApiProperty(name="afterScript",desc="后置脚本执行后返回的数据")
    private String afterScript;


    @ApiProperty(name="variableJson",desc="返回环境变量")
    private JSONObject variableJson;


    public ApiUnitInstance() {
        this.elapsedTime=0.0;
        this.result=0;
    }

    public String getId() {
        return id;
    }

    public ApiUnitInstance setId(String id) {
        this.id = id;
        return this;
    }

    public ApiUnitCase getApiUnit() {
        return apiUnit;
    }

    public void setApiUnit(ApiUnitCase apiUnit) {
        this.apiUnit = apiUnit;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public RequestInstanceUnit getRequestInstance() {
        return requestInstanceUnit;
    }

    public void setRequestInstance(RequestInstanceUnit requestInstanceUnit) {
        this.requestInstanceUnit = requestInstanceUnit;
    }

    public ResponseInstanceUnit getResponseInstance() {
        return responseInstanceUnit;
    }

    public void setResponseInstance(ResponseInstanceUnit responseInstanceUnit) {
        this.responseInstanceUnit = responseInstanceUnit;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }

    public JSONObject getVariableJson() {
        return variableJson;
    }

    public void setVariableJson(JSONObject variableJson) {
        this.variableJson = variableJson;
    }

}
