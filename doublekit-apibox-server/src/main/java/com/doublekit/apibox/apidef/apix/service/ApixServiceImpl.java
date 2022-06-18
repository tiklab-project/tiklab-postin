package com.doublekit.apibox.apidef.apix.service;

import com.doublekit.apibox.apidef.apix.dao.ApixDao;
import com.doublekit.apibox.apidef.apix.entity.ApixEntity;
import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.apibox.category.entity.CategoryEntity;
import com.doublekit.apibox.integration.dynamic.model.Dynamic;
import com.doublekit.apibox.integration.dynamic.service.DynamicService;
import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.Pagination;
import com.doublekit.core.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import com.doublekit.rpc.annotation.Exporter;
import com.doublekit.user.user.model.User;
import com.doublekit.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* BasedefServiceImpl
*/
@Service
@Exporter
public class ApixServiceImpl implements ApixService {

    @Autowired
    ApixDao apixDao;

    @Autowired
    DynamicService dynamicService;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);


        //初始化项目成员
//        String userId = LoginContext.getLoginId();
        apixEntity.setCreateUser(apix.getUserId());

        apixEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String id = apixDao.createApix(apixEntity);

       //动态
        Dynamic dynamic = new Dynamic();
        dynamic.setWorkspaceId(apix.getWorkspaceId());
        dynamic.setUser(new User().setId(apix.getUserId()));
        dynamic.setName(apix.getName());
        dynamic.setDynamicType("add");
        dynamic.setModel("api");
        dynamic.setModelId(id);
        dynamic.setOperationTime(new Timestamp(System.currentTimeMillis()));
        dynamicService.createDynamic(dynamic);

        return id;
    }

    @Override
    public void updateApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);

        apixEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

//        String userId = LoginContext.getLoginId();
        apixEntity.setUpdateUser(apix.getUserId());

        apixDao.updateApix(apixEntity);


        //动态
        ApixEntity apix1 = apixDao.findApix(apix.getId());
        Dynamic dynamic = new Dynamic();
        dynamic.setWorkspaceId(apix1.getWorkspaceId());
        dynamic.setUser(new User().setId(apix.getUserId()));
        dynamic.setName(apix1.getName());
        dynamic.setDynamicType("edit");
        dynamic.setModel("api");
        dynamic.setModelId(apix1.getId());
        dynamic.setOperationTime(new Timestamp(System.currentTimeMillis()));
        dynamicService.createDynamic(dynamic);

    }

    @Override
    public void deleteApix(@NotNull String id) {
        ApixEntity apix = apixDao.findApix(id);

        Dynamic dynamic = new Dynamic();
        dynamic.setWorkspaceId(apix.getWorkspaceId());
        dynamic.setUser(new User().setId(apix.getCreateUser()));
        dynamic.setName(apix.getName());
        dynamic.setDynamicType("delete");
        dynamic.setModel("api");
        dynamic.setModelId(apix.getId());
        dynamic.setOperationTime(new Timestamp(System.currentTimeMillis()));
        dynamicService.createDynamic(dynamic);

        apixDao.deleteApix(id);
    }

    @Override
    public Apix findOne(String id) {
        ApixEntity apixEntity = apixDao.findApix(id);

        Apix apix = BeanMapper.map(apixEntity, Apix.class);
        return apix;
    }

    @Override
    public List<Apix> findList(List<String> idList) {
        List<ApixEntity> apixEntityList =  apixDao.findApixList(idList);

        List<Apix> apixList =  BeanMapper.mapList(apixEntityList, Apix.class);
        return apixList;
    }

    @Override
    public Apix findApix(@NotNull String id) {
        Apix apix = findOne(id);

        joinTemplate.joinQuery(apix);

        return apix;
    }

    @Override
    public List<Apix> findAllApix() {
        List<ApixEntity> apixEntityList =  apixDao.findAllApix();

        List<Apix> apixList =  BeanMapper.mapList(apixEntityList, Apix.class);

        joinTemplate.joinQuery(apixList);

        return apixList;
    }

    @Override
    public List<Apix> findApixList(ApixQuery apixQuery) {
        List<ApixEntity> apixEntityList = apixDao.findApixList(apixQuery);

        List<Apix> apixList = BeanMapper.mapList(apixEntityList, Apix.class);

        joinTemplate.joinQuery(apixList);

        return apixList;
    }

    @Override
    public Pagination<Apix> findApixPage(ApixQuery apixQuery) {
        Pagination<ApixEntity>  pagination = apixDao.findApixPage(apixQuery);

        List<Apix> apixList = BeanMapper.mapList(pagination.getDataList(), Apix.class);

        joinTemplate.joinQuery(apixList);

        return PaginationBuilder.build(pagination, apixList);
    }


}