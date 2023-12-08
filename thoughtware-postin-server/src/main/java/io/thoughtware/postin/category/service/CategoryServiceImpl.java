package io.thoughtware.postin.category.service;

import io.thoughtware.postin.common.EnumTemplateConstant;
import io.thoughtware.postin.common.LogUnit;
import io.thoughtware.postin.common.PostInUnit;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.postin.api.apix.model.Apix;
import io.thoughtware.postin.api.apix.model.ApixQuery;
import io.thoughtware.postin.api.apix.service.ApixService;
import io.thoughtware.postin.category.dao.CategoryDao;
import io.thoughtware.postin.category.entity.CategoryEntity;
import io.thoughtware.postin.category.model.Category;
import io.thoughtware.postin.category.model.CategoryQuery;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.security.logging.model.LoggingType;
import io.thoughtware.security.logging.service.LoggingTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
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
    LoggingTypeService loggingTypeService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    LogUnit logUnit;

    @Autowired
    PostInUnit postInUnit;

    @Override
    public String createCategory(@NotNull @Valid Category category) {
        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);
        if (StringUtils.isEmpty(category.getId())) {
            String uid = UUID.randomUUID().toString();
            String id = uid.trim().replaceAll("-", "").substring(0, 12);;
            categoryEntity.setId(id);
        }
        String categoryId = categoryDao.createCategory(categoryEntity);

        //日志
//        Map<String,String> map = new HashMap<>();
//        map.put("name",category.getName());
//        map.put("id",categoryId);
//        map.put("workspaceId",category.getWorkspace().getId());
//        map.put("user",postInUnit.getUser().getNickname());
//        map.put("mode","目录");
//        map.put("images","/images/log.png");
//        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_CREATE_ID);
//        map.put("actionType",oplogTypeOne.getName());
//
//        logUnit.log(LOG_TYPE_CREATE_ID,"category",map);

        return categoryId;
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {
        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);

        categoryDao.updateCategory(categoryEntity);

        //日志
        String userId =LoginContext.getLoginId();
        Map<String,String> map = new HashMap<>();
        map.put("name",category.getName());
        map.put("id",category.getId());
        map.put("workspaceId",category.getWorkspace().getId());
        map.put("user",postInUnit.getUser().getNickname());
        map.put("mode","目录");
        map.put("images","/images/log.png");
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(EnumTemplateConstant.LOG_TYPE_UPDATE_ID);
        map.put("actionType",oplogTypeOne.getName());

        logUnit.log(EnumTemplateConstant.LOG_TYPE_UPDATE_ID,"category",map);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        Category category = findCategory(id);
        //日志
        Map<String,String> map = new HashMap<>();
        map.put("name",category.getName());
        map.put("workspaceId",category.getWorkspace().getId());
        map.put("id",category.getId());
        map.put("user",postInUnit.getUser().getNickname());
        map.put("mode","目录");
        map.put("images","/images/log.png");
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(EnumTemplateConstant.LOG_TYPE_DELETE_ID);
        map.put("actionType",oplogTypeOne.getName());

        logUnit.log(EnumTemplateConstant.LOG_TYPE_DELETE_ID,"category",map);

        //删除目录下的接口
        List<Apix> apixList = apixService.findApixList(new ApixQuery().setCategoryId(id));
        if (CollectionUtils.isNotEmpty(apixList)){
            for (Apix apix : apixList){
                apixService.deleteApix(apix.getId());
            }
        }

        //删除子目录
        CategoryQuery categoryQuery = new CategoryQuery();
        categoryQuery.setParentId(id);
        List<Category> categoryList = findCategoryList(categoryQuery);
        if(categoryList!=null&categoryList.size()>0){
            for(Category categoryChild:categoryList){
                deleteCategory(categoryChild.getId());
            }
        }

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

        return category;
    }


    @Override
    public List<Category>  findCategoryAddSon(String id) {

        Category category = findCategory(id);

        //通过当前目录查询子目录
        CategoryQuery categoryQuery = new CategoryQuery();
        categoryQuery.setParentId(category.getId());
        List<Category> categoryList = findCategoryList(categoryQuery);

        //通过当前目录查询下面接口
        ApixQuery apixQuery = new ApixQuery();
        apixQuery.setCategoryId(category.getId());
        List<Apix> apixList = apixService.findApixList(apixQuery);

        //如果接口不为空，把详情set到公共apix里
        if(CollectionUtils.isNotEmpty(apixList)){
            category.setNodeList(apixList);
        }

        //如果有子目录递归
        if(CollectionUtils.isNotEmpty(categoryList)){

            ArrayList<Category> newCategoryList = new ArrayList<>();
            for(Category children : categoryList){
                List<Category> categoryByCategory = findCategoryAddSon(children.getId());

                newCategoryList.addAll(categoryByCategory);
            }

            category.setChildren(newCategoryList);
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

        List<Category> categoryList = findCategoryList(categoryQuery);
        if (ObjectUtils.isEmpty(categoryQuery.getName())){
            ApixQuery apixQuery = new ApixQuery();
            apixQuery.setWorkspaceId(categoryQuery.getWorkspaceId());
            List<Apix> apixList = apixService.findApixList(apixQuery);

            return buildCategoryTree(categoryList, apixList);
        }else {
            ApixQuery apixQuery = new ApixQuery();
            apixQuery.setWorkspaceId(categoryQuery.getWorkspaceId());
            apixQuery.setName(categoryQuery.getName());
            List<Apix> matchedApixList = apixService.findApixList(apixQuery);

            // 将匹配到的 apixList 放在对应的 Category 下
            for (Apix apix : matchedApixList) {
                for (Category category : categoryList) {
                    if (Objects.equals(category.getId(), apix.getCategory().getId())) {
                        category.getNodeList().add(apix);
                        break;
                    }
                }
            }

            // 构建带有树形结构的 Category List
            return buildCategoryTreeWithStructure(categoryList);

        }
    }


    public List<Category> buildCategoryTree(List<Category> categoryList, List<Apix> apixList) {
        List<Category> topList = categoryList.stream()
                .filter(category -> category.getParent() == null || category.getParent().getId() == null)
                .collect(Collectors.toList());

        for (Category topCategory : topList) {
            buildCategoryNode(topCategory, categoryList, apixList,topList);
        }

        return topList;
    }

    private void buildCategoryNode(Category parentCategory, List<Category> categoryList, List<Apix> apixList, List<Category> topList) {
        List<Category> children = categoryList.stream()
                .filter(category -> !topList.contains(category) &&  parentCategory.getId().equals(category.getParent().getId()))
                .collect(Collectors.toList());

        for (Category child : children) {
            buildCategoryNode(child, categoryList, apixList, topList);
        }

        List<Apix> nodeList = apixList.stream()
                .filter(apix -> parentCategory.getId().equals(apix.getCategory().getId()))
                .collect(Collectors.toList());

        parentCategory.setChildren(children);
        parentCategory.setNodeList(nodeList);
    }

    private List<Category> buildCategoryTreeWithStructure(List<Category> categoryList) {
        Map<String, Category> categoryMap = new HashMap<>();
        List<Category> result = new ArrayList<>();

        // 将所有的 Category 放入 Map 中，以便后续快速查找
        for (Category category : categoryList) {
            categoryMap.put(category.getId(), category);
        }

        // 遍历 Category 列表，将每个 Category 放入其父节点的 children 列表中
        for (Category category : categoryList) {
            if (category.getParent() == null) {
                // 如果没有父节点，则将其作为顶级节点添加到结果列表中
                result.add(category);
            } else {
                // 如果有父节点，则找到父节点并添加到其 children 列表中
                Category parent = categoryMap.get(category.getParent().getId());
                if (parent != null) {
                    parent.getChildren().add(category);
                }
            }
        }

        return result;
    }


}