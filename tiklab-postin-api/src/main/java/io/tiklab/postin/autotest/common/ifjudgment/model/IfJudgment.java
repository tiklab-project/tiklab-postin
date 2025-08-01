package io.tiklab.postin.autotest.common.ifjudgment.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

import java.util.List;


/**
 * if判断 模型
 */
@ApiModel
@Mapper
@Join
public class IfJudgment extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="caseId",desc="用例id")
    private String caseId;

    @ApiProperty(name="relation",desc="条件关系:and/or")
    private String relation;

    @ApiProperty(name="ifVariableList",desc="if 变量列表")
    private List<IfVariable> ifVariableList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public List<IfVariable> getIfVariableList() {
        return ifVariableList;
    }

    public void setIfVariableList(List<IfVariable> ifVariableList) {
        this.ifVariableList = ifVariableList;
    }
}
