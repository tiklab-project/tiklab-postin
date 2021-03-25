package com.darthcloud.apibox.category.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.apibox.category.model.CategoryQuery;
import com.darthcloud.apibox.category.service.CategoryService;
import com.darthcloud.common.Pagination;
import com.darthcloud.common.Result;
import com.darthcloud.apibox.annotation.ApiMethod;
import com.darthcloud.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.darthcloud.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/category")
@Api(name = "CategoryController",desc = "分类管理")
public class CategoryController {

    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path="/createCategory",method = RequestMethod.POST)
    @ApiMethod(name = "createCategory",desc = "创建分类")
    @ApiParam(name = "category",desc = "分类DTO",required = true)
    public Result<String> createCategory(@RequestBody @NotNull @Valid Category category){
        String id = categoryService.createCategory(category);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateCategory",method = RequestMethod.POST)
    @ApiMethod(name = "updateCategory",desc = "更新分类")
    @ApiParam(name = "category",desc = "分类DTO",required = true)
    public Result<Void> updateCategory(@RequestBody @NotNull @Valid Category category){
        categoryService.updateCategory(category);

        return Result.ok();
    }

    @RequestMapping(path="/deleteCategory",method = RequestMethod.POST)
    @ApiMethod(name = "deleteCategory",desc = "根据分类ID删除分类")
    @ApiParam(name = "id",desc = "分类ID",required = true)
    public Result<Void> deleteCategory(@NotNull String id){
        categoryService.deleteCategory(id);

        return Result.ok();
    }

    @RequestMapping(path="/findCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findCategory",desc = "根据分类ID查找分类")
    @ApiParam(name = "id",desc = "分类ID",required = true)
    public Result<Category> findCategory(@NotNull String id){
        Category category = categoryService.findCategory(id);

        return Result.ok(category);
    }

    @RequestMapping(path="/findAllCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findAllCategory",desc = "查找所有分类")
    public Result<List<Category>> findAllCategory(){
        List<Category> categoryList = categoryService.findAllCategory();

        return Result.ok(categoryList);
    }

    @Validator
    @RequestMapping(path = "/findCategoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryList",desc = "根据查询对象查找分类列表")
    @ApiParam(name = "categoryQuery",desc = "查询对象",required = true)
    public Result<List<Category>> findCategoryList(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        List<Category> categoryList = categoryService.findCategoryList(categoryQuery);

        return Result.ok(categoryList);
    }

    @Validator
    @RequestMapping(path = "/findCategoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryPage",desc = "根据查询对象按分页查找分类列表")
    @ApiParam(name = "categoryQuery",desc = "查询对象",required = true)
    public Result<Pagination<Category>> findCategoryPage(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        Pagination<Category> pagination = categoryService.findCategoryPage(categoryQuery);

        return Result.ok(pagination);
    }

    @Validator
    @RequestMapping(path = "/findCategoryListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryListTree",desc = "根据查询对象查找分类列表树")
    @ApiParam(name = "categoryQuery",desc = "查询对象",required = true)
    public Result<List<Category>> findCategoryListTree(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        List<Category> categoryList = categoryService.findCategoryListTree(categoryQuery);

        return Result.ok(categoryList);
    }

}
