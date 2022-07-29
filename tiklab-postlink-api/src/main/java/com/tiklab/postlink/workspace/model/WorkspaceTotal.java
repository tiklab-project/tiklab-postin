package com.tiklab.postlink.workspace.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.core.BaseModel;

@ApiModel
public class WorkspaceTotal extends BaseModel {

    @ApiProperty(name="apiTotal",desc="接口总和")
    private Integer apiTotal;

    @ApiProperty(name="categoryTotal",desc="分组总和")
    private Integer categoryTotal;

    @ApiProperty(name="caseTotal",desc="用例总和")
    private Integer caseTotal;

    @ApiProperty(name="caseTotal",desc="模型总和")
    private Integer modelTotal;

    @ApiProperty(name="memberTotal",desc="成员总和")
    private Integer memberTotal;

    public Integer getApiTotal() {
        return apiTotal;
    }

    public void setApiTotal(Integer apiTotal) {
        this.apiTotal = apiTotal;
    }

    public Integer getCategoryTotal() {
        return categoryTotal;
    }

    public void setCategoryTotal(Integer categoryTotal) {
        this.categoryTotal = categoryTotal;
    }

    public Integer getCaseTotal() {
        return caseTotal;
    }

    public void setCaseTotal(Integer caseTotal) {
        this.caseTotal = caseTotal;
    }

    public Integer getModelTotal() {
        return modelTotal;
    }

    public void setModelTotal(Integer modelTotal) {
        this.modelTotal = modelTotal;
    }

    public Integer getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(Integer memberTotal) {
        this.memberTotal = memberTotal;
    }
}
