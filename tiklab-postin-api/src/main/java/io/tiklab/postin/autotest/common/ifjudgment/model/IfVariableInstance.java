package io.tiklab.postin.autotest.common.ifjudgment.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;


/**
 * if中的值比较 模型
 */
@ApiModel
@Mapper
@Join
public class IfVariableInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="stepInstanceId",desc="stepInstanceId")
    private String stepInstanceId;

    @ApiProperty(name="variable",desc="变量")
    private String variable;

    @ApiProperty(name="compare",desc="比较")
    private Integer compare;

    @ApiProperty(name="expect",desc="变量值")
    private String expect;

    @ApiProperty(name="result",desc="结果")
    private int result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Integer getCompare() {
        return compare;
    }

    public void setCompare(Integer compare) {
        this.compare = compare;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getStepInstanceId() {
        return stepInstanceId;
    }

    public void setStepInstanceId(String stepInstanceId) {
        this.stepInstanceId = stepInstanceId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
