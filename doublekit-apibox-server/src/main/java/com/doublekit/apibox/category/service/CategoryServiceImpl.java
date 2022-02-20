package com.doublekit.apibox.category.service;

import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apidef.model.MethodExQuery;
import com.doublekit.apibox.apidef.service.MethodService;
import com.doublekit.apibox.category.dao.CategoryDao;
import com.doublekit.apibox.category.entity.CategoryEntity;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.category.model.CategoryQuery;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.Pagination;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
* 用户服务业务处理
*/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    MethodService methodService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createCategory(@NotNull @Valid Category category) {
        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);
        if (StringUtils.isEmpty(category.getId())) {
            UUID uniqueKey = UUID.randomUUID();
            categoryEntity.setId(uniqueKey.toString());
        }

        String id = categoryDao.createCategory(categoryEntity);

        return id;
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {

        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);

        categoryDao.updateCategory(categoryEntity);


    }

    @Override
    public void deleteCategory(@NotNull String id) {

        categoryDao.deleteCategory(id);
        List<MethodEx> methodList = methodService.findMethodList(new MethodExQuery().setCategoryId(id));
        if (CollectionUtils.isNotEmpty(methodList)){
            for (MethodEx methodEx:methodList){
                methodService.deleteMethod(methodEx.getId());
            }
        }

    }

    @Override
    public Category findOne(String id) {
        CategoryEntity categoryEntity = categoryDao.findCategory(id);

        Category category = BeanMapper.map(categoryEntity, Category.class);
        return category;
    }

    @Override
    public List<Category> findList(List<String> idList) {
        List<CategoryEntity> categoryEntityList =  categoryDao.findCategoryList(idList);

        List<Category> categoryList = BeanMapper.mapList(categoryEntityList,Category.class);
        return categoryList;
    }

    @Override
    public Category findCategory(@NotNull String id) {
        Category category = findOne(id);

        joinTemplate.joinQuery(category);
        return category;
    }

    @Override
    public List<Category> findAllCategory() {
        List<CategoryEntity> categoryEntityList =  categoryDao.findAllCategory();

        List<Category> categoryList = BeanMapper.mapList(categoryEntityList,Category.class);

        joinTemplate.joinQuery(categoryList);

        return categoryList;
    }

    @Override
    public List<Category> findCategoryList(CategoryQuery categoryQuery) {
        List<CategoryEntity> categoryEntityList = categoryDao.findCategoryList(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(categoryEntityList,Category.class);

        joinTemplate.joinQuery(categoryList);

        return categoryList;
    }

    @Override
    public Pagination<Category> findCategoryPage(CategoryQuery categoryQuery) {

        Pagination<CategoryEntity>  pagination = categoryDao.findCategoryPage(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(pagination.getDataList(),Category.class);

        joinTemplate.joinQuery(categoryList);

        return PaginationBuilder.build(pagination,categoryList);
    }

    @Override
    public List<Category> findCategoryListTree(CategoryQuery categoryQuery) {
        //查找所有符合条件列表
        List<Category> matchCategoryList = findCategoryList(categoryQuery);

        //查找并设置分类下面的接口
        findCategoryMethodList(matchCategoryList);

        List<Category> categories = packageResult(matchCategoryList);

        return categories;
    }

    @Override
    public List<Category> likeFindCategoryListTree(CategoryQuery categoryQuery){
        List<Category> categories=null;
        if (StringUtils.isEmpty(categoryQuery.getName())){
            categories=  findCategoryListTree(categoryQuery);
        } else {
            MethodExQuery methodExQuery = new MethodExQuery();
            methodExQuery.setWorkspaceId(categoryQuery.getWorkspaceId());
            methodExQuery.setName(categoryQuery.getName());
            List<MethodEx> methodList = methodService.findMethodList(methodExQuery);

            if(CollectionUtils.isNotEmpty(methodList)){
                //查询空间下所有目录，将name 设为null 不想模糊匹配目录
                List<Category> categoryList = findCategoryList(categoryQuery.setName(null));

                //查询目录下面的接口，并放到相应的目录下
                List<Category> methodInCategoryList = findMethodInCategoryList(categoryList, methodList);

                List<String> categoryParentByMethod = findCategoryParentByMethod(methodInCategoryList);

                List<Category> categoryMethods = tes(categoryParentByMethod, methodInCategoryList);

                categories = packageResult(categoryMethods);
            }
        }

        return categories;
    }

    private List<Category>  tes(List<String> categoryParentByMethod, List<Category> methodInCategoryList) {
        List<Category> categoryList = new ArrayList<>();
        List<String> collect = categoryParentByMethod.stream().distinct().collect(Collectors.toList());
        for (String categoryId:collect){
            List<Category> cate = methodInCategoryList.stream().filter(category -> categoryId.equals(category.getId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(cate)){
                Category category = cate.get(0);
                categoryList.add(category);
            }
        }
        return categoryList;

    }

    private List<String> findCategoryParentByMethod(List<Category> methodInCategoryList) {
        List<String> categoryIdList = new ArrayList<>();

        //
        List<Category> collect = methodInCategoryList.stream().filter(a -> CollectionUtils.isNotEmpty(a.getCategoryMethod())).collect(Collectors.toList());
        for (Category category:collect){
            Category parentCategory = category.getParentCategory();
            addParentCategoryId(categoryIdList,parentCategory);
            categoryIdList.add(category.getId());
        }
        return categoryIdList;
    }

    public void addParentCategoryId(List<String> categoryIdList,Category parentCategory){
        if (ObjectUtils.isNotEmpty(parentCategory)){
            categoryIdList.add(parentCategory.getId());
            if (ObjectUtils.isNotEmpty(parentCategory.getParentCategory())){
                addParentCategoryId( categoryIdList,parentCategory.getParentCategory());
            }

        }
    }


    public List<Category> packageResult(List<Category> matchCategoryList){
        //查找第一级分类列表
        List<Category> topCategoryList = findTopCategoryList(matchCategoryList);

        //查找并设置子分类列表
        if(topCategoryList != null){
            for(Category topCategory:topCategoryList){
                setChildren(matchCategoryList,topCategory);
            }
        }
        return topCategoryList;
    }
    /**
     * 查询目录下的接口，并放到相应的目录下面
     * @param categoryList
     * @param methodList
     * @return
     */
    private List<Category> findMethodInCategoryList(List<Category> categoryList, List<MethodEx> methodList) {

        List<Category> collect = categoryList.stream().map(category -> {

            List<MethodEx> methods = methodList.stream().filter(item -> category.getId().equals(item.getCategory().getId())).collect(Collectors.toList());
            category.setCategoryMethod(methods);

            return category;

        }).collect(Collectors.toList());

        return collect;
    }


    /**
     * 查找第一级分类列表
     * @param matchCategoryList
     * @return
     */
    List<Category> findTopCategoryList(List<Category> matchCategoryList){
        return matchCategoryList.stream()
                .filter(category -> (category.getParentCategory() == null || category.getParentCategory().getId() == null))
                .collect(Collectors.toList());
    }

    /**
     * 查找分类列表下的接口
     * @param matchCategoryList
     * @return
     */
    List<Category> findCategoryMethodList(List<Category> matchCategoryList){

        List<Category> categoryList = matchCategoryList.stream().map(category -> {
            MethodExQuery methodExQuery = new MethodExQuery();
            methodExQuery.setCategoryId(category.getId());
            List<MethodEx> methodList = methodService.findMethodList(methodExQuery);

            category.setCategoryMethod(methodList);
            return category;

        }).collect(Collectors.toList());

        return  categoryList;
    }

    /**
     * 查找并设置下级分类列表
     * @param matchCategoryList
     * @param parentCaegory
     */
    void setChildren(List<Category> matchCategoryList,Category parentCaegory){
        List<Category> childCategoryList = matchCategoryList.stream()
                .filter(category -> (category.getParentCategory() != null && category.getParentCategory().getId() != null && category.getParentCategory().getId().equals(parentCaegory.getId())))
                .collect(Collectors.toList());

        if(childCategoryList != null && childCategoryList.size() > 0){
            parentCaegory.setChildren(childCategoryList);

            for(Category category:childCategoryList){
                setChildren(matchCategoryList,category);
            }
        }
    }




}