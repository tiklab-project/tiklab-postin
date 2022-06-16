package com.doublekit.apibox.integration.dynamic.service;

import com.doublekit.apibox.integration.dynamic.dao.DynamicDao;
import com.doublekit.apibox.integration.dynamic.entity.DynamicEntity;
import com.doublekit.apibox.integration.dynamic.model.Dynamic;
import com.doublekit.apibox.integration.dynamic.model.DynamicQuery;

import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.Pagination;
import com.doublekit.core.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

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