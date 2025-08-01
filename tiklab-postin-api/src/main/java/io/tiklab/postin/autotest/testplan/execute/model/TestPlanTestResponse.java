package io.tiklab.postin.autotest.testplan.execute.model;

import io.tiklab.postin.autotest.testplan.instance.model.TestPlanCaseInstanceBind;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstance;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 测试数据返回 模型
 */
@ApiModel
public class TestPlanTestResponse extends BaseModel{

    @ApiProperty(name="status",desc="状态类型：0：未开始，1：正在执行,2：结束")
    private Integer status;

    @ApiProperty(name="testPlanInstance",desc="测试实例")
    private TestPlanInstance testPlanInstance;

    @ApiProperty(name="testPlanCaseInstanceList",desc="测试计划用例历史")
    private List<TestPlanCaseInstanceBind> testPlanCaseInstanceList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TestPlanInstance getTestPlanInstance() {
        return testPlanInstance;
    }

    public void setTestPlanInstance(TestPlanInstance testPlanInstance) {
        this.testPlanInstance = testPlanInstance;
    }

    public List<TestPlanCaseInstanceBind> getTestPlanCaseInstanceList() {
        return testPlanCaseInstanceList;
    }

    public void setTestPlanCaseInstanceList(List<TestPlanCaseInstanceBind> testPlanCaseInstanceList) {
        this.testPlanCaseInstanceList = testPlanCaseInstanceList;
    }
}
