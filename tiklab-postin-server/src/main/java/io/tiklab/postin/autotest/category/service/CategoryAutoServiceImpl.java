package io.tiklab.postin.autotest.category.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.autotest.category.dao.CategoryAutoDao;
import io.tiklab.postin.autotest.category.entity.CategoryAutoEntity;
import io.tiklab.postin.autotest.category.model.CategoryAuto;
import io.tiklab.postin.autotest.category.model.CategoryAutoQuery;
import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.postin.autotest.test.model.TestCaseQuery;
import io.tiklab.postin.autotest.test.service.TestCaseService;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

/**
* 目录 服务
*/
@Service
public class CategoryAutoServiceImpl implements CategoryAutoService {

    @Autowired
    CategoryAutoDao categoryAutoDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;


    @Override
    public String createCategory(@NotNull @Valid CategoryAuto categoryAuto) {
        CategoryAutoEntity categoryAutoEntity = BeanMapper.map(categoryAuto, CategoryAutoEntity.class);

        return categoryAutoDao.createCategory(categoryAutoEntity);
    }

    @Override
    public void updateCategory(@NotNull @Valid CategoryAuto categoryAuto) {
        CategoryAutoEntity categoryAutoEntity = BeanMapper.map(categoryAuto, CategoryAutoEntity.class);

        categoryAutoDao.updateCategory(categoryAutoEntity);
    }

    @Override
    public void deleteCategory(@NotNull String id) {

        categoryAutoDao.deleteCategory(id);
    }

    @Override
    public Boolean isCategoryHasCases(String id) {
        int testCaseNumByCategoryId = testCaseService.findTestCaseNumByCategoryId(id);
        if(testCaseNumByCategoryId > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void deleteAllCategory(String workspaceId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(CategoryAutoEntity.class)
                .eq("workspaceId",workspaceId)
                .get();
        categoryAutoDao.deleteCategory(deleteCondition);
    }

    @Override
    public CategoryAuto findOne(String id) {
        CategoryAutoEntity categoryAutoEntity = categoryAutoDao.findCategory(id);

        CategoryAuto categoryAuto = BeanMapper.map(categoryAutoEntity, CategoryAuto.class);
        return categoryAuto;
    }

    @Override
    public List<CategoryAuto> findList(List<String> idList) {
        List<CategoryAutoEntity> categoryAutoEntityList =  categoryAutoDao.findCategoryList(idList);

        List<CategoryAuto> categoryAutoList =  BeanMapper.mapList(categoryAutoEntityList, CategoryAuto.class);
        return categoryAutoList;
    }

    @Override
    public CategoryAuto findCategory(@NotNull String id) {
        CategoryAuto categoryAuto = findOne(id);

        joinTemplate.joinQuery(categoryAuto);
        return categoryAuto;
    }

    @Override
    public int findCategoryNum(String workspaceId) {
        int categoryNum = categoryAutoDao.findCategoryNum(workspaceId);
        return categoryNum;
    }

    @Override
    public List<CategoryAuto> findAllCategory() {
        List<CategoryAutoEntity> categoryAutoEntityList = categoryAutoDao.findAllCategory();

        List<CategoryAuto> categoryAutoList = BeanMapper.mapList(categoryAutoEntityList, CategoryAuto.class);

        joinTemplate.joinQuery(categoryAutoList);
        return categoryAutoList;
    }

    @Override
    public List<CategoryAuto> findCategoryList(CategoryAutoQuery categoryAutoQuery) {
        List<CategoryAutoEntity> categoryAutoEntityList = categoryAutoDao.findCategoryList(categoryAutoQuery);

        List<CategoryAuto> categoryAutoList = BeanMapper.mapList(categoryAutoEntityList, CategoryAuto.class);

//        if(categoryList != null){
//            for(Category category : categoryList){
//                category.setCaseNum(testCaseService.countCasesByCategoryId(category.getId()));
//            }
//        }

        joinTemplate.joinQuery(categoryAutoList);

        return categoryAutoList;
    }

    @Override
    public Pagination<CategoryAuto> findCategoryPage(CategoryAutoQuery categoryAutoQuery) {

        Pagination<CategoryAutoEntity>  pagination = categoryAutoDao.findCategoryPage(categoryAutoQuery);

        List<CategoryAuto> categoryAutoList = BeanMapper.mapList(pagination.getDataList(), CategoryAuto.class);

        joinTemplate.joinQuery(categoryAutoList);

        return PaginationBuilder.build(pagination, categoryAutoList);
    }

    @Override
    public List<CategoryAuto> findCategoryListTree(CategoryAutoQuery categoryAutoQuery) {
        //查找所有符合条件列表
        List<CategoryAuto> matchCategoryAutoList = findCategoryList(categoryAutoQuery);

        //查找并设置分类下面的用例数
        List<CategoryAuto> categoryAutoMethodList = findCategoryMethodList(matchCategoryAutoList, categoryAutoQuery);

        //查找第一级分类列表
        List<CategoryAuto> topCategoryAutoList = findTopCategoryList(categoryAutoMethodList);

        //查找并设置子分类列表
        if(topCategoryAutoList != null){
            for(CategoryAuto topCategoryAuto : topCategoryAutoList){
                setChildren(matchCategoryAutoList, topCategoryAuto);
            }
        }
        return topCategoryAutoList;
    }

    @Override
    public List<CategoryAuto> findCategoryListTreeTable(CategoryAutoQuery categoryAutoQuery){
        List<CategoryAuto> matchCategoryAutoList = findCategoryList(categoryAutoQuery);

        // 递归向上查找所有缺失的父节点
        Set<String> seenIds = matchCategoryAutoList.stream()
                .map(CategoryAuto::getId)
                .collect(Collectors.toSet());

        List<CategoryAuto> allCategoryAutoList = new ArrayList<>(matchCategoryAutoList);
        for (CategoryAuto categoryAuto : matchCategoryAutoList) {
            addMissingParents(categoryAuto, allCategoryAutoList, seenIds);
        }

        // 构造目录树
        List<CategoryAuto> topCategoryAutoList = findTopCategoryList(allCategoryAutoList);
        for (CategoryAuto top : topCategoryAutoList) {
            setChildren(allCategoryAutoList, top);
        }

        return topCategoryAutoList;
    }

    /**
     * 递归添加缺失的父节点
     */
    private void addMissingParents(CategoryAuto categoryAuto, List<CategoryAuto> allList, Set<String> seenIds) {
        String parentId = categoryAuto.getParentId();
        while (parentId != null && !seenIds.contains(parentId)) {
            CategoryAuto parent = findCategory(parentId);
            if (parent == null) break;
            allList.add(parent);
            seenIds.add(parent.getId());
            parentId = parent.getParentId();
        }
    }

    /**
     * 查找分类列表下的用例
     * @param matchCategoryAutoList
     * @return
     */
    List<CategoryAuto> findCategoryMethodList(List<CategoryAuto> matchCategoryAutoList, CategoryAutoQuery categoryAutoQuery){
        List<CategoryAuto> categoryAutoList = matchCategoryAutoList.stream().map(category -> {
            TestCaseQuery testCaseQuery = new TestCaseQuery();
            testCaseQuery.setCategoryId(category.getId());
            testCaseQuery.setCaseType(categoryAutoQuery.getCaseType());
            testCaseQuery.setName(categoryAutoQuery.getName());
            List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);
            category.setCaseNum(testCaseList.size());
            category.setNodeList(testCaseList);
            return category;
        }).collect(Collectors.toList());


        return categoryAutoList;
    }

    /**
     * 查找第一级分类列表
     * @param matchCategoryAutoList
     * @return
     */
    List<CategoryAuto> findTopCategoryList(List<CategoryAuto> matchCategoryAutoList){
        return matchCategoryAutoList.stream()
                .filter(category -> (category.getParentId() == null || ObjectUtils.isEmpty(category.getParentId())))
                .collect(Collectors.toList());
    }

    /**
     * 查找并设置下级分类列表
     * @param matchCategoryAutoList
     * @param parentCategoryAuto
     */
    void setChildren(List<CategoryAuto> matchCategoryAutoList, CategoryAuto parentCategoryAuto) {
        List<CategoryAuto> childCategoryAutoList = matchCategoryAutoList.stream()
                .filter(category -> category.getParentId() != null && category.getParentId().equals(parentCategoryAuto.getId()))
                .collect(Collectors.toList());

        Set<String> categoryIds = new HashSet<>();
        categoryIds.add(parentCategoryAuto.getId().toString()); // 包含自身

        if (!childCategoryAutoList.isEmpty()) {
            parentCategoryAuto.setChildren(childCategoryAutoList);

            for (CategoryAuto child : childCategoryAutoList) {
                setChildren(matchCategoryAutoList, child); // 递归设置子节点

                List<String> childIds = child.getCategoryIds();
                if (childIds != null) {
                    categoryIds.addAll(childIds); // 合并子节点的 ID
                }
            }
        }


        parentCategoryAuto.setCategoryIds(new ArrayList<>(categoryIds)); // 转为 List<String>
    }



}