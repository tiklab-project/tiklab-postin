package io.thoughtware.postin.category.service;

import io.thoughtware.core.page.Pagination;

import io.thoughtware.postin.category.model.Category;
import io.thoughtware.postin.category.model.CategoryQuery;
import io.thoughtware.join.annotation.FindList;
import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.join.annotation.FindAll;
import io.thoughtware.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 分类服务接口
*/
@JoinProvider(model = Category.class)
public interface CategoryService {

    /**
    * 创建分类
    * @param category
    * @return
    */
    String createCategory(@NotNull @Valid Category category);

    /**
    * 更新分类
    * @param category
    */
    void updateCategory(@NotNull @Valid Category category);

    /**
    * 删除分类
    * @param id
    */
    void deleteCategory(@NotNull String id);

    @FindOne
    Category findOne(@NotNull String id);

    @FindList
    List<Category> findList(List<String> idList);

    /**
    * 查找分类
    * @param id
    * @return
    */
    Category findCategory(@NotNull String id);

    /**
    * 查找所有分类
    * @return
    */
    @FindAll
    List<Category> findAllCategory();

    /**
     * 查询分组总数
     * @param workspaceId
     * @return
     */
    int findCategoryNum(String workspaceId);


    /**
    * 查询列表分类
    * @param categoryQuery
    * @return
    */
    List<Category> findCategoryList(CategoryQuery categoryQuery);

    /**
    * 按分页查询分类
    * @param categoryQuery
    * @return
    */
    Pagination<Category> findCategoryPage(CategoryQuery categoryQuery);

    /**
     * 模糊查找分类列表树
     * @param categoryQuery
     * @return
     */
    List<Category> findCategoryListTree(CategoryQuery categoryQuery);


    /**
     * 查找当前目录，并查询当前目录下的子目录，接口
     * @param id
     * @return
     */
    List<Category> findCategoryAddSon(@NotNull String id);
}