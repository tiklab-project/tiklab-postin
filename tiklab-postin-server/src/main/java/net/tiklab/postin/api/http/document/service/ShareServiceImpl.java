package net.tiklab.postin.api.http.document.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.postin.api.apix.model.Apix;
import net.tiklab.postin.api.apix.model.ApixQuery;
import net.tiklab.postin.api.apix.service.ApixService;
import net.tiklab.postin.api.http.definition.model.HttpApi;
import net.tiklab.postin.api.http.definition.service.HttpApiService;
import net.tiklab.postin.api.http.document.dao.ShareDao;
import net.tiklab.postin.api.http.document.entity.ShareEntity;
import net.tiklab.postin.category.model.Category;
import net.tiklab.postin.category.model.CategoryQuery;
import net.tiklab.postin.category.service.CategoryService;
import net.tiklab.postin.api.http.document.model.Share;
import net.tiklab.postin.api.http.document.model.ShareQuery;


import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* ShareServiceImpl
*/
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareDao shareDao;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApixService apixService;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createShare(@NotNull @Valid Share share) {
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);
        shareEntity.setId(share.getCode());
        shareEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));


        return shareDao.createShare(shareEntity);
    }

    @Override
    public void updateShare(@NotNull @Valid Share share) {
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);
        shareEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        shareDao.updateShare(shareEntity);
    }

    @Override
    public void deleteShare(@NotNull String id) {
        shareDao.deleteShare(id);
    }

    @Override
    public Share findOne(String id) {
        ShareEntity shareEntity = shareDao.findShare(id);

        Share share = BeanMapper.map(shareEntity, Share.class);
        return share;
    }

    @Override
    public List<Share> findList(List<String> idList) {
        List<ShareEntity> shareEntityList =  shareDao.findShareList(idList);

        List<Share> shareList =  BeanMapper.mapList(shareEntityList,Share.class);
        return shareList;
    }

    @Override
    public Share findShare(@NotNull String id) {
        Share share = findOne(id);

        joinTemplate.joinQuery(share);

        return share;
    }

    @Override
    public List<Share> findAllShare() {
        List<ShareEntity> shareEntityList =  shareDao.findAllShare();

        List<Share> shareList =  BeanMapper.mapList(shareEntityList,Share.class);

        joinTemplate.joinQuery(shareList);

        return shareList;
    }

    @Override
    public List<Share> findShareList(ShareQuery shareQuery) {
        List<ShareEntity> shareEntityList = shareDao.findShareList(shareQuery);

        List<Share> shareList = BeanMapper.mapList(shareEntityList,Share.class);

        joinTemplate.joinQuery(shareList);

        return shareList;
    }

    @Override
    public Pagination<Share> findSharePage(ShareQuery shareQuery) {
        Pagination<ShareEntity>  pagination = shareDao.findSharePage(shareQuery);

        List<Share> shareList = BeanMapper.mapList(pagination.getDataList(),Share.class);

        joinTemplate.joinQuery(shareList);

        return PaginationBuilder.build(pagination,shareList);
    }

    @Override
    public Share findShareByUrlId(String id) {
        Share shareSql = findShare(id);

        Share share = new Share();
        share.setVisibility(shareSql.getVisibility());

        return share;
    }

    @Override
    public HashMap verify(Share share) {
        Share share1 = findShare(share.getId());

        HashMap<String, String> hashMap = new HashMap<>();

        //如果密码相同返回状态
        if(Objects.equals(share.getPassword(),share1.getPassword())){
            hashMap.put("status","success");
        }else {
            hashMap.put("status","error");
        }

        return hashMap;
    }



    @Override
    public List<Category> findShareTree(String id) {
        //通过分享id 查询 targetId
        Share share = findShare(id);
        String targetId = share.getTargetId();

        switch (share.getTargetType()){
            case "workspace":
                return findCategoryByWorkspace(targetId);
            case "category":
                return findCategoryByCategory(targetId);
            case "api":
                return findCategoryByApi(targetId);
            default:
                break;
        }

        return null;
    }


    /**
     * 通过Workspace 查询的树形列表
     * @param workspaceId
     * @return
     */
    private List<Category> findCategoryByWorkspace(String workspaceId){
        CategoryQuery categoryQuery = new CategoryQuery();
        categoryQuery.setWorkspaceId(workspaceId);
        List<Category> categoryList = categoryService.findCategoryList(categoryQuery);

        //获取顶层目录
        List<Category> topCategoryList = categoryList.stream()
                .filter(category -> category.getParent() == null)
                .collect(Collectors.toList());

        ArrayList<Category> newCategoryList = new ArrayList<>();
        for(Category category:topCategoryList){
            List<Category> categoryByCategory = findCategoryByCategory(category.getId());

            newCategoryList.addAll(categoryByCategory);
        }

        return newCategoryList;
    }


    /**
     * 通过category 查询的树形列表
     * @param categoryId
     * @return
     */
    private List<Category> findCategoryByCategory(String categoryId){

        Category category = categoryService.findCategory(categoryId);

        //通过当前目录查询子目录
        CategoryQuery categoryQuery = new CategoryQuery();
        categoryQuery.setParentId(category.getId());
        List<Category> categoryList = categoryService.findCategoryList(categoryQuery);

        //通过当前目录查询下面接口
        ApixQuery apixQuery = new ApixQuery();
        apixQuery.setCategoryId(category.getId());
        List<Apix> apixList = apixService.findApixList(apixQuery);

        //如果接口不为空
        if(CollectionUtils.isNotEmpty(apixList)){
            category.setNodeList(apixList);
        }

        //如果有子目录递归
        if(CollectionUtils.isNotEmpty(categoryList)){

            ArrayList<Category> newCategoryList = new ArrayList<>();
            for(Category children : categoryList){
                List<Category> categoryByCategory = findCategoryByCategory(children.getId());

                newCategoryList.addAll(categoryByCategory);
            }

            category.setChildren(newCategoryList);
        }


        ArrayList<Category> arrayList = new ArrayList<>();
        arrayList.add(category);

        return arrayList;
    }



    /**
     * 通过api查询
     * @param apiId
     * @return
     */
    private List<Category> findCategoryByApi(String apiId){
        //通过targetId查询到当前api
        Apix apix = apixService.findApix(apiId);
        //把http set到apix中
        HttpApi httpApi = httpApiService.findHttpApi(apiId);
        apix.setHttpApi(httpApi);

        //通过apix中的category查询所属的目录
        Category category = categoryService.findCategory(apix.getCategory().getId());

        ArrayList<Apix> list = new ArrayList<>();
        list.add(apix);

        //把当前的api set到 category中的nodeList中
        category.setNodeList(list);


        ArrayList<Category> categoryList = new ArrayList<>();

        //如果parent 为空 直接返回当前 categoryList，否则递归
        if(Objects.isNull(category.getParent())){
            categoryList.add(category);
        }else {
            Category category1 = loopParentApi(category.getParent().getId(), category);
            categoryList.add(category1);
        }

        return categoryList;
    }
    /**
     * 通过接口查询中的 递归，用于递归目录
     * @param parentId
     * @param categoryFirst
     * @return
     */
    private Category loopParentApi(String parentId, Category categoryFirst){
        ArrayList<Category> arrayList = new ArrayList<>();
        arrayList.add(categoryFirst);

        Category category = categoryService.findCategory(parentId);

        category.setChildren(arrayList);

        if(!Objects.isNull(category.getParent())){
            loopParentApi(category.getParent().getId(), category);
        }

        return category;
    }


    /**
     * 接口 根据类型匹配
     * @param id
     * @return
     */
    @Override
    public HttpApi findHttpApi(String id) {
        HttpApi httpApi = httpApiService.findHttpApi(id);

        return  httpApi;
    }


}