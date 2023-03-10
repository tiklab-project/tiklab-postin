package io.tiklab.postin.category.service;

import io.tiklab.core.context.LoginContext;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.category.dao.CategoryDao;
import io.tiklab.postin.category.entity.CategoryEntity;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.model.CategoryQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.postin.common.LogUnit;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.security.logging.model.LoggingType;
import io.tiklab.security.logging.service.LoggingTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static io.tiklab.postin.common.MessageTemplateConstant.*;

/**
* 分类 服务
*/
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
            String id = uid.trim().replaceAll("-", "");
            categoryEntity.setId(id);
        }
        String categoryId = categoryDao.createCategory(categoryEntity);

        //日志
        String userId = LoginContext.getLoginId();
        Map<String,String> map = new HashMap<>();
        map.put("name",category.getName());
        map.put("id",categoryId);
        map.put("workspaceId",category.getWorkspace().getId());
        map.put("user",postInUnit.getUser().getNickname());
        map.put("mode","目录");
        map.put("images","/images/log.png");
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_CREATE_ID);
        map.put("actionType",oplogTypeOne.getName());

        logUnit.log(LOG_TYPE_CREATE_ID,"category",map);

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
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_UPDATE_ID);
        map.put("actionType",oplogTypeOne.getName());

        logUnit.log(LOG_TYPE_UPDATE_ID,"category",map);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        Category category = findCategory(id);
        //日志
        String userId =LoginContext.getLoginId();
        Map<String,String> map = new HashMap<>();
        map.put("name",category.getName());
        map.put("workspaceId",category.getWorkspace().getId());
        map.put("id",category.getId());
        map.put("user",postInUnit.getUser().getNickname());
        map.put("mode","目录");
        map.put("images","/images/log.png");
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_DELETE_ID);
        map.put("actionType",oplogTypeOne.getName());

        logUnit.log(LOG_TYPE_DELETE_ID,"category",map);

        //删除目录
        categoryDao.deleteCategory(id);

        //删除目录下的接口
        List<Apix> apixList = apixService.findApixList(new ApixQuery().setCategoryId(id));
        if (CollectionUtils.isNotEmpty(apixList)){
            for (Apix apix : apixList){
                apixService.deleteApix(apix.getId());
            }
        }

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
//        categoryQuery.setParentId("111");
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
        if (ObjectUtils.isEmpty(categoryQuery.getName())){
            categories =  findCategoryListTree(categoryQuery);
        } else {
            ApixQuery apixQuery = new ApixQuery();
            apixQuery.setName(categoryQuery.getName());


            //查询出所有匹配的接口
            List<Apix> apixList = apixService.findApixList(apixQuery);

            if(CollectionUtils.isNotEmpty(apixList)){
                //查询空间下所有目录，将name 设为null 不想模糊匹配目录
                List<Category> categoryList = findCategoryList(categoryQuery.setName(null));

                //查询目录下面的接口，并放到相应的目录下
                List<Category> methodInCategoryList = findHttpApiInCategoryList(categoryList, apixList);

                List<String> categoryParentByMethod = findCategoryParentByMethod(methodInCategoryList);

                //满足条件的目录
                List<Category> categoryMethods = findAllCategories(categoryParentByMethod, methodInCategoryList);

                categories = packageResult(categoryMethods);
            }
        }

        return categories;
    }

    /**
     * 查询符合条件的所有目录
     * @param categoryParentByMethod
     * @param methodInCategoryList
     * @return
     */
    private List<Category>  findAllCategories(List<String> categoryParentByMethod, List<Category> methodInCategoryList) {
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

    /**
     * 通过接口，查询目录id，父级id
     * @param methodInCategoryList
     * @return
     */
    private List<String> findCategoryParentByMethod(List<Category> methodInCategoryList) {
        List<String> categoryIdList = new ArrayList<>();

        //
        List<Category> collect = methodInCategoryList.stream().filter(a -> CollectionUtils.isNotEmpty(a.getNodeList())).collect(Collectors.toList());
        for (Category category:collect){
            Category parentCategory = category.getParent();
            addParentCategoryId(categoryIdList,parentCategory);
            categoryIdList.add(category.getId());
        }
        return categoryIdList;
    }


    public void addParentCategoryId(List<String> categoryIdList,Category parentCategory){
        if (parentCategory != null){
            categoryIdList.add(parentCategory.getId());

            if (parentCategory.getParent() != null){
                addParentCategoryId( categoryIdList,parentCategory.getParent());
            }
        }
    }

    /**
     * 封装目录返回结果
     * @param matchCategoryList
     * @return
     */
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
     * @param apixList
     * @return
     */
    private List<Category> findHttpApiInCategoryList(List<Category> categoryList, List<Apix> apixList) {
        ArrayList<Apix> arrayList = new ArrayList<>();
        for (Apix apix : apixList) {
            //去除带版本的api，因为跟随初始的api，没有设置apiUid的就是初始api
            if(!ObjectUtils.isEmpty(apix.getApiUid())){
                continue;
            }
            //通过初始api，查询下面所有版本，拿到最新版本的api
            List<Apix> versionList = apixService.findApixList(new ApixQuery().setApiUid(apix.getId()));
            if(CollectionUtils.isNotEmpty(versionList)){
                Apix recentApi = versionList.get(0);
                arrayList.add(recentApi);
            }else {
                arrayList.add(apix);
            }
        }

        List<Category> collect = categoryList.stream().map(category -> {

            List<Apix> apixes = arrayList.stream().filter(item -> category.getId().equals(item.getCategory().getId())).collect(Collectors.toList());

            category.setNodeList(apixes);

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
                .filter(category -> (category.getParent() == null || category.getParent().getId() == null))
                .collect(Collectors.toList());
    }

    /**
     * 查找分类列表下的接口
     * @param matchCategoryList
     * @return
     */
    List<Category> findCategoryMethodList(List<Category> matchCategoryList){

        List<Category> categoryList = matchCategoryList.stream().map(category -> {

            ArrayList<Apix> arrayList = new ArrayList<>();
            List<Apix> apixList = apixService.findApixList(new ApixQuery().setCategoryId(category.getId()));
            for (Apix apix : apixList) {
                //去除带版本的api，因为跟随初始的api，没有设置apiUid的就是初始api
                if(!ObjectUtils.isEmpty(apix.getApiUid())){
                    continue;
                }

                //通过初始api，查询下面所有版本，拿到最新版本的api
                List<Apix> versionList = apixService.findApixList(new ApixQuery().setApiUid(apix.getId()));
                if(CollectionUtils.isNotEmpty(versionList)){
                    Apix recentApi = versionList.get(0);
                    arrayList.add(recentApi);
                }else {
                    arrayList.add(apix);
                }

            }

            category.setNodeList(arrayList);

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
                .filter(category -> (category.getParent() != null && category.getParent().getId() != null && category.getParent().getId().equals(parentCaegory.getId())))
                .collect(Collectors.toList());

        if(childCategoryList != null && childCategoryList.size() > 0){
            parentCaegory.setChildren(childCategoryList);

            for(Category category:childCategoryList){
                setChildren(matchCategoryList,category);
            }
        }
    }




}