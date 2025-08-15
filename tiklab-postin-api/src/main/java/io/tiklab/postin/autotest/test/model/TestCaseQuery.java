package io.tiklab.postin.autotest.test.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class TestCaseQuery implements Serializable {

    @ApiProperty(name = "categoryId", desc = "测试用例id")
    private String categoryId;

    @ApiProperty(name = "name",desc = "模糊查询")
    private String name;

    @ApiProperty(name = "id", desc = "接口步骤ID，精确匹配")
    private String id;

    @ApiProperty(name="status",desc="状态")
    private Integer status;

    @ApiProperty(name="仓库Id",desc="workspaceId")
    private String workspaceId;

    @ApiProperty(name="接口Id",desc="apiId")
    private String apiId;

    @ApiProperty(name = "testType", desc = "测试类型：api，app")
    private String testType;

    @ApiProperty(name = "caseType", desc = "用例类型: unit,scene,perform,func")
    private String caseType;

    @ApiProperty(name = "caseTypeList", desc = "caseTypeList")
    private String[] caseTypeList;

    @ApiProperty(name = "notInList", desc = "不包含")
    private String[] notInList;

    @ApiProperty(name = "inList", desc = "包含")
    private String[] inList;

    @ApiProperty(name = "createUser", desc = "创建人")
    private String createUser;

    @ApiProperty(name = "director", desc = "负责人")
    private String director;

    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("sort").get();


    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    private String[] categoryIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public TestCaseQuery setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String[] getCaseTypeList() {
        return caseTypeList;
    }

    public void setCaseTypeList(String[] caseTypeList) {
        this.caseTypeList = caseTypeList;
    }

    public List<Order> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }

    public Page getPageParam() {
        return pageParam;
    }

    public void setPageParam(Page pageParam) {
        this.pageParam = pageParam;
    }

    public String[] getNotInList() {
        return notInList;
    }

    public void setNotInList(String[] notInList) {
        this.notInList = notInList;
    }

    public String[] getInList() {
        return inList;
    }

    public void setInList(String[] inList) {
        this.inList = inList;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String[] categoryIds) {
        this.categoryIds = categoryIds;
    }
}