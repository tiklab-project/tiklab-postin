package io.tiklab.postin.autotest.common.stepcommon.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.http.definition.model.OperateDatabase;
import io.tiklab.postin.api.http.definition.model.OperateScript;
import io.tiklab.postin.autotest.common.forstep.model.ForStep;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgment;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneStep;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

/**
 * 公共步骤 模型
 */
@ApiModel
@Mapper
@Join
public class StepCommon extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="caseId",desc="caseId")
    private String caseId;

    @ApiProperty(name = "parentId",desc="父级id")
    private String parentId;

    @ApiProperty(name="type",desc="步骤类型")
    private String type;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="oldSort",desc="原排序")
    private Integer oldSort;

    @ApiProperty(name="result",desc="用于从测试计划进入功能用例结果展示")
    private Integer result;

    @ApiProperty(name="stepCommonList",desc="子步骤")
    private List<StepCommon> children;

    private IfJudgment ifJudgment;
    private ApiSceneStep apiSceneStep;
    private OperateScript operateScript;
    private OperateDatabase operateDatabase;
    private ForStep forStep;
    private ApiSceneTestRequest apiSceneTestRequest;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getOldSort() {
        return oldSort;
    }

    public void setOldSort(Integer oldSort) {
        this.oldSort = oldSort;
    }

    public IfJudgment getIfJudgment() {
        return ifJudgment;
    }

    public void setIfJudgment(IfJudgment ifJudgment) {
        this.ifJudgment = ifJudgment;
    }

    public ApiSceneTestRequest getApiSceneTestRequest() {
        return apiSceneTestRequest;
    }

    public void setApiSceneTestRequest(ApiSceneTestRequest apiSceneTestRequest) {
        this.apiSceneTestRequest = apiSceneTestRequest;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public OperateScript getOperateScript() {
        return operateScript;
    }

    public void setOperateScript(OperateScript operateScript) {
        this.operateScript = operateScript;
    }

    public OperateDatabase getOperateDatabase() {
        return operateDatabase;
    }

    public void setOperateDatabase(OperateDatabase operateDatabase) {
        this.operateDatabase = operateDatabase;
    }

    public ForStep getForStep() {
        return forStep;
    }

    public void setForStep(ForStep forStep) {
        this.forStep = forStep;
    }

    public ApiSceneStep getApiSceneStep() {
        return apiSceneStep;
    }

    public void setApiSceneStep(ApiSceneStep apiSceneStep) {
        this.apiSceneStep = apiSceneStep;
    }

    public List<StepCommon> getChildren() {
        return children;
    }

    public void setChildren(List<StepCommon> children) {
        this.children = children;
    }
}
