package io.tiklab.postin.autotest.http.perf.cases.model;

import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 接口性能 模型
 */
@ApiModel
@Mapper
@Join
public class ApiPerfCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testCases",desc="用例")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinField(key = "id")
    private TestCase testCase;


    @ApiProperty(name="bindCaseId",desc="绑定用例id，接口单元或接口场景")
    @Mappings({
            @Mapping(source = "bindCase.id",target = "bindCaseId")
    })
    @JoinField(key = "id")
    private TestCase bindCase;

    @ApiProperty(name="threadCount",desc="线程数")
    private Integer threadCount;

    @ApiProperty(name="executeType",desc="执行类型，1:次数 2:时间")
    private Integer executeType;

    @ApiProperty(name="executeCount",desc="执行次数")
    private Integer executeCount;

    @ApiProperty(name="timeType",desc="时间类型")
    private String timeType;

    @ApiProperty(name="timeCount",desc="时间数")
    private Integer timeCount;


//    @ApiProperty(name="stepNum",desc="步骤数量")
//    private int stepNum;
//
//    @ApiProperty(name="instanceNum",desc="历史数量")
//    private int instanceNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public TestCase getBindCase() {
        return bindCase;
    }

    public void setBindCase(TestCase bindCase) {
        this.bindCase = bindCase;
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
