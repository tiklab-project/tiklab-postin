package io.tiklab.postin.api.apix.service;


import io.tiklab.postin.api.apix.dao.ApixDao;
import io.tiklab.postin.api.apix.entity.ApixEntity;
import io.tiklab.postin.api.apix.model.ApiRecent;
import io.tiklab.postin.api.apix.model.ApiRecentQuery;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;

import io.tiklab.join.JoinTemplate;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.ws.ws.service.WSApiService;
import io.tiklab.postin.common.LogUnit;
import io.tiklab.postin.common.PostInUnit;

import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.security.logging.model.LoggingType;
import io.tiklab.security.logging.service.LoggingTypeService;
import io.tiklab.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.tiklab.postin.common.EnumTemplateConstant.*;

/**
 * 接口公共实体
 */
@Service
@Exporter
public class ApixServiceImpl implements ApixService {

    @Autowired
    ApixDao apixDao;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    WSApiService wsApiService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiRecentService apiRecentService;

//    @Autowired
//    DssClient disClient;

    @Autowired
    LogUnit logUnit;

    @Autowired
    PostInUnit postInUnit;

    @Autowired
    LoggingTypeService loggingTypeService;

    @Override
    public String createApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);

        if(apix.getId()!=null){
            apixEntity.setId(apix.getId());
        }else {
            String id = postInUnit.generateId();
            apixEntity.setId(id);
        }

        //初始化项目成员
        String userId = LoginContext.getLoginId();
        apixEntity.setCreateUser(userId);
        apixEntity.setStatusId("developmentid");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        apixEntity.setCreateTime(timestamp);
        apixEntity.setUpdateTime(timestamp);

        String id = apixDao.createApix(apixEntity);


        //日志
//        Map<String,String> map = new HashMap<>();
//        map.put("name",apix.getName());
//        map.put("id",apix.getId());
//        map.put("workspaceId",apix.getWorkspaceId());
//        if(postInUnit.getUser()==null){
//            map.put("user","admin");
//        }else {
//            map.put("user",postInUnit.getUser().getNickname());
//        }
//        map.put("mode","接口");
//        map.put("images","/images/log.png");
//        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_CREATE_ID);
//        map.put("actionType",oplogTypeOne.getName());
//        logUnit.log(LOG_TYPE_CREATE_ID,"api",map);

        //添加索引
//        Apix entity = findApix(id);
//        disClient.save(entity);

        //发送消息
//        sendMessageForCreate(entity);

       //动态


        return id;
    }

    @Override
    public void updateApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);

        apixEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String userId = LoginContext.getLoginId();
        apixEntity.setUpdateUser(userId);

        apixDao.updateApix(apixEntity);

        //更新索引
//        Apix entity = findApix(apix.getId());
//        disClient.update(entity);

        //发送消息
//        sendMessageForCreate(entity);

        //日志
        Map<String,String> map = new HashMap<>();
        map.put("name",apix.getName());
        map.put("id",apix.getId());
        map.put("workspaceId",apix.getWorkspaceId());
        if(postInUnit.getUser()==null){
            map.put("user","admin");
        }else {
            map.put("user",postInUnit.getUser().getNickname());
        }
        map.put("mode","接口");
        map.put("images","/images/log.png");
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_UPDATE_ID);
        map.put("actionType",oplogTypeOne.getName());

        logUnit.log(LOG_TYPE_UPDATE_ID,"api",map);
    }

    @Override
    public void deleteApix(@NotNull String id) {
        ApixEntity apix = apixDao.findApix(id);

        if(Objects.equals(apix.getProtocolType(), "http")){
            //http协议的接口。id与apix的公共表相同
            httpApiService.deleteHttpApi(id);
        }else {
            wsApiService.deleteWSApi(id);
        }

        //删除最近
        ApiRecentQuery apiRecentQuery = new ApiRecentQuery();
        apiRecentQuery.setApixId(id);
        List<ApiRecent> apiRecentList = apiRecentService.findApiRecentList(apiRecentQuery);
        for(ApiRecent apiRecent:apiRecentList){
            if(apiRecent.getApix().getId()==id){
                apiRecentService.deleteApiRecent(id);
            }
        }


        //删除接口
        apixDao.deleteApix(id);

        //删除索引
//        disClient.delete(Apix.class,id);
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
    public int findApixNum(ApixQuery apixQuery) {
        int apixNum = apixDao.findApixNum(apixQuery);
        return apixNum;
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