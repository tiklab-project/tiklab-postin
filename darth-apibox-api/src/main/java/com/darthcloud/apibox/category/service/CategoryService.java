package com.darthcloud.apibox.category.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.apibox.category.model.CategoryQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
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
    Pagination<List<Category>> findCategoryPage(CategoryQuery categoryQuery);

}