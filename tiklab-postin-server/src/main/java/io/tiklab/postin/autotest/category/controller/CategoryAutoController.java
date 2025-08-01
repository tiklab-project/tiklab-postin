package io.tiklab.postin.autotest.category.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.autotest.category.model.CategoryAuto;
import io.tiklab.postin.autotest.category.model.CategoryAutoQuery;
import io.tiklab.postin.autotest.category.service.CategoryAutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @pi.protocol: http
 * @pi.groupName: 目录模块管理
 */
@RestController
@RequestMapping("/categoryAuto")
//@Api(name = "CategoryController",desc = "目录管理")
public class CategoryAutoController {

    private static Logger logger = LoggerFactory.getLogger(CategoryAutoController.class);

    @Autowired
    private CategoryAutoService categoryAutoService;

    /**
     * @pi.name:创建目录
     * @pi.path:/category/createCategory
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: io.tiklab.postin.test.model=Category
     */
    @RequestMapping(path="/createCategory",method = RequestMethod.POST)
//    @ApiMethod(name = "createCategory",desc = "创建目录")
//    @ApiParam(name = "category",desc = "category",required = true)
    public Result<String> createCategory(@RequestBody @NotNull @Valid CategoryAuto categoryAuto){
        String id = categoryAutoService.createCategory(categoryAuto);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateCategory",method = RequestMethod.POST)
//    @ApiMethod(name = "updateCategory",desc = "更新目录")
//    @ApiParam(name = "category",desc = "category",required = true)
    public Result<Void> updateCategory(@RequestBody @NotNull @Valid CategoryAuto categoryAuto){
        categoryAutoService.updateCategory(categoryAuto);

        return Result.ok();
    }

    /**
     * @pi.name:删除目录
     * @pi.path:/category/deleteCategory
     * @pi.method:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=workspaceId;
     */
    @RequestMapping(path="/deleteCategory",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteCategory",desc = "删除目录")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteCategory(@NotNull String id){
        categoryAutoService.deleteCategory(id);

        return Result.ok();
    }

    @RequestMapping(path="/isCategoryHasCases",method = RequestMethod.POST)
//    @ApiMethod(name = "isCategoryHasCases",desc = "判断目录下是否有用例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Boolean> isCategoryHasCases(@NotNull String id){
        Boolean categoryHasCases = categoryAutoService.isCategoryHasCases(id);

        return Result.ok(categoryHasCases);
    }

    @RequestMapping(path="/findCategory",method = RequestMethod.POST)
//    @ApiMethod(name = "findCategory",desc = "根据id查找目录")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<CategoryAuto> findCategory(@NotNull String id){
        CategoryAuto categoryAuto = categoryAutoService.findCategory(id);

        return Result.ok(categoryAuto);
    }

    @RequestMapping(path="/findAllCategory",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllCategory",desc = "查找所有目录")
    public Result<List<CategoryAuto>> findAllCategory(){
        List<CategoryAuto> categoryAutoList = categoryAutoService.findAllCategory();

        return Result.ok(categoryAutoList);
    }

    @RequestMapping(path = "/findCategoryList",method = RequestMethod.POST)
//    @ApiMethod(name = "findCategoryList",desc = "查询目录列表")
//    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<List<CategoryAuto>> findCategoryList(@RequestBody @Valid @NotNull CategoryAutoQuery categoryAutoQuery){
        List<CategoryAuto> categoryAutoList = categoryAutoService.findCategoryList(categoryAutoQuery);

        return Result.ok(categoryAutoList);
    }

    @RequestMapping(path = "/findCategoryPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findCategoryPage",desc = "按分页查询目录")
//    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<Pagination<CategoryAuto>> findCategoryPage(@RequestBody @Valid @NotNull CategoryAutoQuery categoryAutoQuery){
        Pagination<CategoryAuto> pagination = categoryAutoService.findCategoryPage(categoryAutoQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findCategoryListTree",method = RequestMethod.POST)
//    @ApiMethod(name = "findCategoryListTree",desc = "根据查询对象查找分类列表树（带用例）")
//    @ApiParam(name = "categoryQuery",desc = "查询对象",required = true)
    public Result<List<CategoryAuto>> findCategoryListTree(@RequestBody @Valid @NotNull CategoryAutoQuery categoryAutoQuery){
        List<CategoryAuto> categoryAutoList = categoryAutoService.findCategoryListTree(categoryAutoQuery);

        return Result.ok(categoryAutoList);
    }

    @RequestMapping(path = "/findCategoryListTreeTable",method = RequestMethod.POST)
//    @ApiMethod(name = "findCategoryListTreeTable",desc = "根据查询对象查找分类列表树（不带用例）")
//    @ApiParam(name = "categoryQuery",desc = "查询对象",required = true)
    public Result<List<CategoryAuto>> findCategoryListTreeTable(@RequestBody @Valid @NotNull CategoryAutoQuery categoryAutoQuery){
        List<CategoryAuto> categoryAutoList = categoryAutoService.findCategoryListTreeTable(categoryAutoQuery);

        return Result.ok(categoryAutoList);
    }


}
