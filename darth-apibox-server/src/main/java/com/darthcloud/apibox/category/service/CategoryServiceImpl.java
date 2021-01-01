package com.darthcloud.apibox.category.service;

import com.darthcloud.apibox.category.dao.CategoryDao;
import com.darthcloud.apibox.category.entity.CategoryPo;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.apibox.category.model.CategoryQuery;

import com.darthcloud.apibox.node.dao.NodeDao;
import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
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
    JoinQuery joinQuery;

    @Override
    public String createCategory(@NotNull @Valid Category category) {
        CategoryPo categoryPo = BeanMapper.map(category, CategoryPo.class);

        String id = categoryDao.createCategory(categoryPo);

//        //创建node
//        NodePo nodePo = BeanMapper.map(categoryPo, NodePo.class);
//
//        nodeDao.createNode(nodePo);
        return id;
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {
        CategoryPo categoryPo = BeanMapper.map(category, CategoryPo.class);

        categoryDao.updateCategory(categoryPo);

//        //更新node
//        NodePo nodePo = BeanMapper.map(categoryPo, NodePo.class);
//
//        nodeDao.createNode(nodePo);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        categoryDao.deleteCategory(id);

        //删除node
//        nodeDao.deleteNode(id);
    }

    @Override
    public Category findCategory(@NotNull String id) {
        CategoryPo categoryPo = categoryDao.findCategory(id);

        Category category = BeanMapper.map(categoryPo, Category.class);

        joinQuery.queryOne(category);

        return category;
    }

    @Override
    public List<Category> findAllCategory() {
        List<CategoryPo> categoryPoList =  categoryDao.findAllCategory();

        List<Category> categoryList = BeanMapper.mapList(categoryPoList,Category.class);

        joinQuery.queryList(categoryList);

        return categoryList;
    }

    @Override
    public List<Category> findCategoryList(CategoryQuery categoryQuery) {
        List<CategoryPo> categoryPoList = categoryDao.findCategoryList(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(categoryPoList,Category.class);

        joinQuery.queryList(categoryList);

        return categoryList;
    }

    @Override
    public Pagination<List<Category>> findCategoryPage(CategoryQuery categoryQuery) {
        Pagination<List<Category>> pg = new Pagination<>();

        Pagination<List<CategoryPo>>  pagination = categoryDao.findCategoryPage(categoryQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Category> categoryList = BeanMapper.mapList(pagination.getDataList(),Category.class);
        pg.setDataList(categoryList);

        joinQuery.queryList(categoryList);

        return pg;
    }

    @Override
    public List<Category> findCategoryListTree(CategoryQuery categoryQuery) {
        //查找所有符合条件列表
        List<Category> matchCategoryList = findCategoryList(categoryQuery);

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