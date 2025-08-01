package io.tiklab.postin.autotest.http.unit.instance.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用例实例或用例步骤记录
 */
@Entity
@Table(name="autotest_api_unit_instance")
public class ApiUnitInstanceEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    //所属接口
    @Column(name = "api_unit_id",length = 32)
    private String apiUnitId;

    //执行次数
    @Column(name = "execute_number")
    private Integer executeNumber;

    // 状态码
    @Column(name = "status_code")
    private String statusCode;

    // 结果
    @Column(name = "result")
    private Integer result;

    // 创建人
    @Column(name = "create_user")
    private String createUser;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 耗时
    @Column(name = "elapsed_time")
    private Double elapsedTime;

    // 错误信息
    @Column(name = "err_message",length = 2048)
    private String errMessage;

    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

}
