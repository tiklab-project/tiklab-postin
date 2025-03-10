package io.tiklab.postin.category.service;

import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.category.dao.CategoryDao;
import io.tiklab.postin.category.entity.CategoryEntity;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.model.CategoryQuery;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.service.NodeService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* 分类 服务
*/
@Exporter
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ApixService apixService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    NodeService nodeService;

    @Autowired
    PostInUnit postInUnit;


    @Override
    public String createCategory(@NotNull @Valid Category category) {
        String id = postInUnit.generateId();

        if(category.getId()==null){
            category.setId(id);
        }

        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);
        String categoryId = categoryDao.createCategory(categoryEntity);

        Node node = category.getNode();
        if(node.getId()==null){
            node.setId(categoryId);
        }
        node.setType(MagicValue.CATEGORY);
        nodeService.createNode(node);

        return categoryId;
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {
//        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);
//        categoryDao.updateCategory(categoryEntity);

        Node node = category.getNode();
        nodeService.updateNode(node);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        //删除目录
        categoryDao.deleteCategory(id);
    }

    @Override
    public Category findOne(String id) {
        CategoryEntity categoryEntity = categoryDao.findCategory(id);

        return BeanMapper.map(categoryEntity, Category.class);
    }

    @Override
    public List<Category> findList(List<String> idList) {
        List<CategoryEntity> categoryEntityList =  categoryDao.findCategoryList(idList);

        return BeanMapper.mapList(categoryEntityList,Category.class);
    }


    @Override
    public Category findCategory(@NotNull String id) {
        Category category = findOne(id);
        joinTemplate.joinQuery(category);

        if(category!=null){
            Node node = nodeService.findNode(id);
            category.setNode(node);
        }

        return category;
    }


    @Override
    public List<Category>  findCategoryAddSon(String id) {

        Category category = findCategory(id);

        //通过当前目录查询子目录
        CategoryQuery categoryQuery = new CategoryQuery();
//        categoryQuery.setParentId(category.getId());
        List<Category> categoryList = findCategoryList(categoryQuery);

        //通过当前目录查询下面接口
        ApixQuery apixQuery = new ApixQuery();
        apixQuery.setCategoryId(category.getId());
        List<Apix> apixList = apixService.findApixList(apixQuery);

        //如果接口不为空，把详情set到公共apix里
        if(CollectionUtils.isNotEmpty(apixList)){
//            category.setNodeList(apixList);
        }

        //如果有子目录递归
        if(CollectionUtils.isNotEmpty(categoryList)){

            ArrayList<Category> newCategoryList = new ArrayList<>();
            for(Category children : categoryList){
                List<Category> categoryByCategory = findCategoryAddSon(children.getId());

                newCategoryList.addAll(categoryByCategory);
            }

//            category.setChildren(newCategoryList);
        }


        ArrayList<Category> arrayList = new ArrayList<>();
        arrayList.add(category);

        return arrayList;
    }



    @Override
    public List<Category> findAllCategory() {
        List<CategoryEntity> categoryEntityList =  categoryDao.findAllCategory();

        List<Category> categoryList = BeanMapper.mapList(categoryEntityList,Category.class);

        joinTemplate.joinQuery(categoryList);

        return categoryList;
    }

    @Override
    public int findCategoryNum(String workspaceId) {
        int categoryNum = categoryDao.findCategoryNum(workspaceId);
        return categoryNum;
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
//        CategoryQuery newQuery = new CategoryQuery();
//        newQuery.setWorkspaceId(categoryQuery.getWorkspaceId());
//        List<Category> categoryList = findCategoryList(newQuery);
//
//        if (ObjectUtils.isEmpty(categoryQuery.getName())){
//            ApixQuery apixQuery = new ApixQuery();
//            apixQuery.setWorkspaceId(categoryQuery.getWorkspaceId());
//            List<Apix> apixList = apixService.findApixList(apixQuery);
//
//            return buildCategoryTree(categoryList, apixList);
//        }else {
//            ApixQuery apixQuery = new ApixQuery();
//            apixQuery.setWorkspaceId(categoryQuery.getWorkspaceId());
//            apixQuery.setName(categoryQuery.getName());
//            List<Apix> matchedApixList = apixService.findApixList(apixQuery);
//
//            // 构建带有树形结构的 Category List
//            List<Category> categories = buildTree(categoryList,matchedApixList);
//            return categories;
//
//        }
        return null;
    }

    /**
     * 构造目录树
     * @param categoryList
     * @param apixList
     * @return
     */
    public List<Category> buildCategoryTree(List<Category> categoryList, List<Apix> apixList) {
        List<Category> topList = categoryList.stream()
//                .filter(category -> category.getParent() == null || category.getParent().getId() == null)
                .collect(Collectors.toList());

        for (Category topCategory : topList) {
            buildCategoryNode(topCategory, categoryList, apixList,topList);
        }

        return topList;
    }


    private void buildCategoryNode(Category parentCategory, List<Category> categoryList, List<Apix> apixList, List<Category> topList) {
        List<Category> children = categoryList.stream()
//                .filter(category -> !topList.contains(category) &&  parentCategory.getId().equals(category.getParent().getId()))
                .collect(Collectors.toList());

        for (Category child : children) {
            buildCategoryNode(child, categoryList, apixList, topList);
        }

        List<Apix> nodeList = apixList.stream()
//                .filter(apix -> parentCategory.getId().equals(apix.getCategory().getId()))
                .collect(Collectors.toList());

//        parentCategory.setChildren(children);
//        parentCategory.setNodeList(nodeList);
    }


    public List<Category> buildTree(List<Category> categories, List<Apix> apis) {

        Map<String, Category> map = categories.stream()
                .collect(Collectors.toMap(Category::getId, c -> c));

        List<Category> roots = categories.stream()
//                .filter(c -> c.getParent() == null)
                .collect(Collectors.toList());

        buildTree(roots, map);

        associateApis(roots, apis, map);

        return roots;
    }

    private void buildTree(List<Category> nodes, Map<String, Category> map) {
        for (Category node : nodes) {
            List<Category> children = map.values().stream()
//                    .filter(c ->c.getParent()!=null&& Objects.equals(c.getParent().getId(), node.getId()))
                    .collect(Collectors.toList());

//            node.setChildren(children);
            buildTree(children, map);
        }
    }

    private void associateApis(List<Category> categories, List<Apix> apis, Map<String, Category> categoryMap) {

        for (Apix api : apis) {
//            String categoryId = api.getCategory().getId();
//            Category category = categoryMap.get(categoryId);
//            if (category != null) {
//                category.getNodeList().add(api);
//            }
        }
    }



}