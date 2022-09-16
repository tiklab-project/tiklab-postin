package net.tiklab.postin.category.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.category.model.Category;
import net.tiklab.postin.category.model.CategoryQuery;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = Category.class)
public interface CategoryService {

    /**
    * 创建用户
    * @param category
    * @return
    */
    String createCategory(@NotNull @Valid Category category);

    /**
    * 更新用户
    * @param category
    */
    void updateCategory(@NotNull @Valid Category category);

    /**
    * 删除用户
    * @param id
    */
    void deleteCategory(@NotNull String id);

    @FindOne
    Category findOne(@NotNull String id);

    @FindList
    List<Category> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    Category findCategory(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<Category> findAllCategory();

    /**
    * 查询列表
    * @param categoryQuery
    * @return
    */
    List<Category> findCategoryList(CategoryQuery categoryQuery);

    /**
    * 按分页查询
    * @param categoryQuery
    * @return
    */
    Pagination<Category> findCategoryPage(CategoryQuery categoryQuery);

    /**
     * 查询列表树
     * @param categoryQuery
     * @return
     */
    List<Category> findCategoryListTree(CategoryQuery categoryQuery);

    /**
     * 模糊查找列表树
     * @param categoryQuery
     * @return
     */
    List<Category> likeFindCategoryListTree(CategoryQuery categoryQuery);

}