package io.tiklab.postin.autotest.testplan.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.category.model.Category;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 测试计划中绑定的用例 模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.postin.autotest.testplan.cases.entity.PlanCaseEntity")
@Join
public class PlanCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="name",desc="用例名",required = true)
    private String name;
    @ApiProperty(name="category",desc="所属模块")
    @Mappings({
            @Mapping(source = "category.id",target = "categoryId")
    })
    @JoinField(key = "id")
    private Category category;

    @ApiProperty(name="testType",desc="类型，例：api,ui")
    private String testType;

    @ApiProperty(name="caseType",desc="用例类型，例：api-unit,api-scene")
    private String caseType;

    @ApiProperty(name="user",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinField(key = "id")
    private User createUser;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="planCaseId",desc="用于plan 绑定的case，返回的id做映射")
    private String planCaseId;

    @ApiProperty(name="director",desc="负责人")
    @Mappings({
            @Mapping(source = "director.id",target = "director")
    })
    @JoinField(key = "id")
    private User director;

    @ApiProperty(name="status",desc="状态")
    private Integer status;

    @ApiProperty(name="priorityLevel",desc="优先级")
    private Integer priorityLevel;

    @ApiProperty(name="result",desc="功能用例的结果")
    private Integer result;

    public String getId() {
        return id;
    }

    public PlanCase setId(String id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPlanCaseId() {
        return planCaseId;
    }

    public void setPlanCaseId(String planCaseId) {
        this.planCaseId = planCaseId;
    }

    public User getDirector() {
        return director;
    }

    public void setDirector(User director) {
        this.director = director;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
