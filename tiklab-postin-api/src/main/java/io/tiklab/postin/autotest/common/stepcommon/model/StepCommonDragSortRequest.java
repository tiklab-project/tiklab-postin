package io.tiklab.postin.autotest.common.stepcommon.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 步骤拖拽排序请求模型
 */
@ApiModel
public class StepCommonDragSortRequest {

    @NotNull
    @ApiProperty(name = "sourceStepId", desc = "拖拽源步骤ID", required = true)
    private String sourceStepId;

    @ApiProperty(name = "targetParentId", desc = "目标父级ID，null表示拖拽到根级别")
    private String targetParentId;

    @NotNull
    @ApiProperty(name = "targetSort", desc = "目标排序位置", required = true)
    private Integer targetSort;

    @ApiProperty(name = "caseId", desc = "用例ID")
    private String caseId;

    public String getSourceStepId() {
        return sourceStepId;
    }

    public void setSourceStepId(String sourceStepId) {
        this.sourceStepId = sourceStepId;
    }

    public String getTargetParentId() {
        return targetParentId;
    }

    public void setTargetParentId(String targetParentId) {
        this.targetParentId = targetParentId;
    }

    public Integer getTargetSort() {
        return targetSort;
    }

    public void setTargetSort(Integer targetSort) {
        this.targetSort = targetSort;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}
