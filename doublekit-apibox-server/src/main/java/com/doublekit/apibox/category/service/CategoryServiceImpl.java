package com.doublekit.apibox.category.service;

import com.doublekit.apibox.apidef.dao.MethodDao;
import com.doublekit.apibox.apidef.entity.MethodEntity;
import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apidef.model.MethodExQuery;
import com.doublekit.apibox.category.dao.CategoryDao;
import com.doublekit.apibox.category.entity.CategoryEntity;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.category.model.CategoryQuery;

import com.doublekit.apibox.node.dao.NodeDao;
import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    NodeDao nodeDao;

    @Autowired
    JoinTemplate joinQuery;

    @Autowired
    MethodDao methodDao;

    @Override
    public String createCategory(@NotNull @Valid Category category) {
        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);

        String id = categoryDao.createCategory(categoryEntity);

//        //创建node
//        NodeEntity nodeEntity = BeanMapper.map(categoryEntity, NodeEntity.class);
//
//        nodeDao.createNode(nodeEntity);
        return id;
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {
        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);

        categoryDao.updateCategory(categoryEntity);

//        //更新node
//        NodeEntity nodeEntity = BeanMapper.map(categoryEntity, NodeEntity.class);
//
//        nodeDao.createNode(nodeEntity);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        categoryDao.deleteCategory(id);

        //删除node
//        nodeDao.deleteNode(id);
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

        joinQuery.queryOne(category);
        return category;
    }

    @Override
    public List<Category> findAllCategory() {
        List<CategoryEntity> categoryEntityList =  categoryDao.findAllCategory();

        List<Category> categoryList = BeanMapper.mapList(categoryEntityList,Category.class);

        joinQuery.queryList(categoryList);

        return categoryList;
    }

    @Override
    public List<Category> findCategoryList(CategoryQuery categoryQuery) {
        List<CategoryEntity> categoryEntityList = categoryDao.findCategoryList(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(categoryEntityList,Category.class);

        joinQuery.queryList(categoryList);

        return categoryList;
    }

    @Override
    public Pagination<Category> findCategoryPage(CategoryQuery categoryQuery) {

        Pagination<CategoryEntity>  pagination = categoryDao.findCategoryPage(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(pagination.getDataList(),Category.class);

        joinQuery.queryList(categoryList);

        return PaginationBuilder.build(pagination,categoryList);
    }

    @Override
    public List<Category> findCategoryListTree(CategoryQuery categoryQuery) {
        //查找所有符合条件列表
        List<Category> matchCategoryList = findCategoryList(categoryQuery);

        //查找并设置分类下面的接口
        findCategoryMethodList(matchCategoryList);

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
            List<MethodEntity> methodList = methodDao.findMethodList(methodExQuery);
            List<MethodEx> methodExes = BeanMapper.mapList(methodList, MethodEx.class);
            category.setCategoryMethod(methodExes);
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