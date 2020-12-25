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
@Api(name = "CategoryController",desc = "CategoryController")
public class CategoryController {

    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path="/createCategory",method = RequestMethod.POST)
    @ApiMethod(name = "createCategory",desc = "createCategory")
    @ApiParam(name = "category",desc = "category",required = true)
    public Result<String> createCategory(@RequestBody @NotNull @Valid Category category){
        String id = categoryService.createCategory(category);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateCategory",method = RequestMethod.POST)
    @ApiMethod(name = "updateCategory",desc = "updateCategory")
    @ApiParam(name = "category",desc = "category",required = true)
    public Result<Void> updateCategory(@RequestBody @NotNull @Valid Category category){
        categoryService.updateCategory(category);

        return Result.ok();
    }

    @RequestMapping(path="/deleteCategory",method = RequestMethod.POST)
    @ApiMethod(name = "deleteCategory",desc = "deleteCategory")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteCategory(@NotNull String id){
        categoryService.deleteCategory(id);

        return Result.ok();
    }

    @RequestMapping(path="/findCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findCategory",desc = "findCategory")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Category> findCategory(@NotNull String id){
        Category category = categoryService.findCategory(id);

        return Result.ok(category);
    }

    @RequestMapping(path="/findAllCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findAllCategory",desc = "findAllCategory")
    public Result<List<Category>> findAllCategory(){
        List<Category> categoryList = categoryService.findAllCategory();

        return Result.ok(categoryList);
    }

    @Validator
    @RequestMapping(path = "/findCategoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryList",desc = "findCategoryList")
    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<List<Category>> findCategoryList(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        List<Category> categoryList = categoryService.findCategoryList(categoryQuery);

        return Result.ok(categoryList);
    }

    @Validator
    @RequestMapping(path = "/findCategoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryPage",desc = "findCategoryPage")
    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<Pagination<List<Category>>> findCategoryPage(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        Pagination<List<Category>> pagination = categoryService.findCategoryPage(categoryQuery);

        return Result.ok(pagination);
    }

}
