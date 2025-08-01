package io.tiklab.postin.autotest.category.model;


import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 空间关注 模型
 * @pi.model: Category
 */
@ApiModel
@Mapper(targetName  = "io.tiklab.postin.autotest.category.entity.CategoryAutoEntity")
@Join
public class CategoryAuto extends BaseModel{

    private static final long serialVersionUID = 6590165929566174830L;

    /**
     * @pi.name: id
     * @pi.value: categoryId
     */
    @ApiProperty(name="id",desc="id")
    private String id;

    /**
     * @pi.name: name
     * @pi.value: 分组名称
     */
    @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private String name;

    /**
     * @pi.model: Repository
     */
    @NotNull
    @ApiProperty(name="workspaceId",desc="仓库id")
    private String workspaceId;

    /**
     * @pi.name: parentId
     * @pi.value: 所属父级
     */
    @ApiProperty(name="parentId",desc="上级分类")
    private String parentId;

    /**
     * @pi.name: children
     * @pi.value: []
     */
    @ApiProperty(name="children",desc="下级分类列表")
    private List<CategoryAuto> children;

    /**
     * @pi.name: children
     * @pi.value: []
     */
    @ApiProperty(name="nodeList",desc="分类用例")
    private List<TestCase> nodeList=new ArrayList<>();

    /**
     * @pi.name: caseNum
     * @pi.value: 目录下的用例数
     */
    @ApiProperty(name="caseNum",desc="目录下的用例数")
    private Integer caseNum;

    private List<String> categoryIds;

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

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public List<CategoryAuto> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryAuto> children) {
        this.children = children;
    }

    public List<TestCase> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<TestCase> nodeList) {
        this.nodeList = nodeList;
    }


    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
