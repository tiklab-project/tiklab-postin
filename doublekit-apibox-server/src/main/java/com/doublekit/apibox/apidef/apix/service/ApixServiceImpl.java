package com.doublekit.apibox.apidef.apix.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.doublekit.apibox.apidef.apix.dao.ApixDao;
import com.doublekit.apibox.apidef.apix.entity.ApixEntity;
import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.apibox.apidef.http.support.MessageTemplateConstant;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.Pagination;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.dss.client.DssClient;
import com.doublekit.eam.common.TicketHolder;
import com.doublekit.join.JoinTemplate;
import com.doublekit.message.message.model.Message;
import com.doublekit.message.message.model.MessageReceiver;
import com.doublekit.message.message.model.MessageTemplate;
import com.doublekit.message.message.service.MessageService;
import com.doublekit.rpc.annotation.Exporter;
import com.doublekit.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* BasedefServiceImpl
*/
@Service
@Exporter
public class ApixServiceImpl implements ApixService {

    @Autowired
    ApixDao apixDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);



        //初始化项目成员
        String userId = LoginContext.getLoginId();
        apixEntity.setCreateUser(userId);

        apixEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        apixEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String id = apixDao.createApix(apixEntity);

        return id;
    }

    @Override
    public void updateApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);

        apixEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String userId = LoginContext.getLoginId();
        apixEntity.setUpdateUser(userId);
        apixDao.updateApix(apixEntity);


    }

    @Override
    public void deleteApix(@NotNull String id) {
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