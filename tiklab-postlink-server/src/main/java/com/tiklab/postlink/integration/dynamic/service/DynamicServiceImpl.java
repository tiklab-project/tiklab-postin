package com.tiklab.postlink.integration.dynamic.service;

import com.tiklab.postlink.integration.dynamic.dao.DynamicDao;
import com.tiklab.postlink.integration.dynamic.entity.DynamicEntity;
import com.tiklab.postlink.integration.dynamic.model.Dynamic;
import com.tiklab.postlink.integration.dynamic.model.DynamicQuery;

import com.tiklab.beans.BeanMapper;
import com.tiklab.core.page.Pagination;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DynamicServiceImpl
*/
@Service
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    DynamicDao dynamicDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createDynamic(@NotNull @Valid Dynamic dynamic) {
        DynamicEntity dynamicEntity = BeanMapper.map(dynamic, DynamicEntity.class);

        return dynamicDao.createDynamic(dynamicEntity);
    }


    @Override
    public void updateDynamic(@NotNull @Valid Dynamic dynamic) {
        DynamicEntity dynamicEntity = BeanMapper.map(dynamic, DynamicEntity.class);

        dynamicDao.updateDynamic(dynamicEntity);
    }

    @Override
    public void deleteDynamic(@NotNull String id) {
        dynamicDao.deleteDynamic(id);
    }

    @Override
    public Dynamic findOne(String id) {
        DynamicEntity dynamicEntity = dynamicDao.findDynamic(id);

        Dynamic dynamic = BeanMapper.map(dynamicEntity, Dynamic.class);
        return dynamic;
    }

    @Override
    public List<Dynamic> findList(List<String> idList) {
        List<DynamicEntity> dynamicEntityList =  dynamicDao.findDynamicList(idList);

        List<Dynamic> dynamicList =  BeanMapper.mapList(dynamicEntityList,Dynamic.class);
        return dynamicList;
    }

    @Override
    public Dynamic findDynamic(@NotNull String id) {
        Dynamic dynamic = findOne(id);

        joinTemplate.joinQuery(dynamic);

        return dynamic;
    }

    @Override
    public List<Dynamic> findAllDynamic() {
        List<DynamicEntity> dynamicEntityList =  dynamicDao.findAllDynamic();

        List<Dynamic> dynamicList =  BeanMapper.mapList(dynamicEntityList,Dynamic.class);

        joinTemplate.joinQuery(dynamicList);

        return dynamicList;
    }

    @Override
    public List<Dynamic> findDynamicList(DynamicQuery dynamicQuery) {
        List<DynamicEntity> dynamicEntityList = dynamicDao.findDynamicList(dynamicQuery);

        List<Dynamic> dynamicList = BeanMapper.mapList(dynamicEntityList,Dynamic.class);

        joinTemplate.joinQuery(dynamicList);

        return dynamicList;
    }

    @Override
    public Pagination<Dynamic> findDynamicPage(DynamicQuery dynamicQuery) {
        Pagination<DynamicEntity>  pagination = dynamicDao.findDynamicPage(dynamicQuery);

        List<Dynamic> dynamicList = BeanMapper.mapList(pagination.getDataList(),Dynamic.class);

        joinTemplate.joinQuery(dynamicList);

        return PaginationBuilder.build(pagination,dynamicList);
    }
}