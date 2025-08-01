package io.tiklab.postin.autotest.category.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.autotest.category.model.CategoryAuto;
import io.tiklab.postin.autotest.category.model.CategoryAutoQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 目录 服务接口
*/
@JoinProvider(model = CategoryAuto.class)
public interface CategoryAutoService {

    /**
    * 创建目录
    * @param categoryAuto
    * @return
    */
    String createCategory(@NotNull @Valid CategoryAuto categoryAuto);

    /**
    * 更新目录
    * @param categoryAuto
    */
    void updateCategory(@NotNull @Valid CategoryAuto categoryAuto);

    /**
    * 删除目录
    * @param id
    */
    void deleteCategory(@NotNull String id);

    /**
     * 判断目录下是否有用例
     * @param id
     * @return
     */
    Boolean isCategoryHasCases(String id);


    /**
     * 通过workspaceId删除所有目录
     * @param workspaceId
     */
    void deleteAllCategory(@NotNull String workspaceId);

    @FindOne
    CategoryAuto findOne(@NotNull String id);
    @FindList
    List<CategoryAuto> findList(List<String> idList);

    /**
    * 根据id查找目录
    * @param id
    * @return
    */
    CategoryAuto findCategory(@NotNull String id);


    /**
     * 查询分组总数
     * @param workspaceId
     * @return
     */
    int findCategoryNum(String workspaceId);


    /**
    * 查找所有目录
    * @return
    */
    @FindAll
    List<CategoryAuto> findAllCategory();

    /**
    * 查询目录列表
    * @param categoryAutoQuery
    * @return
    */
    List<CategoryAuto> findCategoryList(CategoryAutoQuery categoryAutoQuery);

    /**
    * 按分页查询目录
    * @param categoryAutoQuery
    * @return
    */
    Pagination<CategoryAuto> findCategoryPage(CategoryAutoQuery categoryAutoQuery);
    /**
     * 通过查询对象查询分组树
     * 带用例
     * @param categoryAutoQuery
     * @return
     */
    List<CategoryAuto> findCategoryListTree(CategoryAutoQuery categoryAutoQuery);

    /**
     * 通过查询对象查询分组树
     * 不带用例
     * @param categoryAutoQuery
     * @return
     */
    List<CategoryAuto> findCategoryListTreeTable(CategoryAutoQuery categoryAutoQuery);
}