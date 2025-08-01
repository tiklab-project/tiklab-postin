package io.tiklab.postin.autotest.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.autotest.category.model.CategoryAuto;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 测试用例 模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.postin.autotest.test.entity.TestCasesEntity")
@Join
public class TestCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="caseKey",desc="用例key")
    private String caseKey;

    @NotNull
    @ApiProperty(name="name",desc="用例名",required = true)
    private String name;

    @ApiProperty(name="category",desc="所属模块")
    @Mappings({
            @Mapping(source = "category.id",target = "categoryId")
    })
    @JoinField(key = "id")
    private CategoryAuto category;

    @ApiProperty(name="仓库Id",desc="仓库id")
    private String workspaceId;

    @ApiProperty(name="apiId",desc="apiId")
    private String apiId;

    @ApiProperty(name="仓库",desc="仓库模型")
    @Mappings({
            @Mapping(source = "workspace.id",target = "workspaceId")
    })
    @JoinField(key = "id")
    private Workspace workspace;

    @NotNull
    @ApiProperty(name="testType",desc="测试类型:auto,perform,function",required = true)
    private String testType;

    @NotNull
    @ApiProperty(name="caseType",desc="用例类型，例：api-unit,api-scene",required = true)
    private String caseType;

    @ApiProperty(name="user",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinField(key = "id")
    private User createUser;

    @ApiProperty(name="updateUser",desc="更新人")
    @Mappings({
            @Mapping(source = "updateUser.id",target = "updateUser")
    })
    @JoinField(key = "id")
    private User updateUser;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp updateTime;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

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


    public String getId() {
        return id;
    }

    public TestCase setId(String id) {
        this.id = id;
        return this;
    }

    public String getCaseKey() {
        return caseKey;
    }

    public void setCaseKey(String caseKey) {
        this.caseKey = caseKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryAuto getCategory() {
        return category;
    }

    public void setCategory(CategoryAuto category) {
        this.category = category;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }


}
