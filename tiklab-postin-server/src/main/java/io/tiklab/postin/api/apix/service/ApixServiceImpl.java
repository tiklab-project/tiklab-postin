package io.tiklab.postin.api.apix.service;

import io.tiklab.postin.api.apix.entity.ApiListEntity;
import io.tiklab.postin.api.apix.model.*;
import io.tiklab.postin.api.http.document.service.ShareService;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.postin.api.apix.dao.ApixDao;
import io.tiklab.postin.api.apix.entity.ApixEntity;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.service.NodeService;
import io.tiklab.security.logging.logging.service.LoggingTypeService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;

import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.ws.ws.service.WSApiService;

import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

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
    PostInUnit postInUnit;

    @Autowired
    LoggingTypeService loggingTypeService;

    @Autowired
    NodeService nodeService;

    @Autowired
    ShareService shareService;

    @Override
    public String createApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);

        if(apix.getId()!=null){
            apixEntity.setId(apix.getId());
        }else {
            String id = postInUnit.generateId();
            apixEntity.setId(id);
        }

        apixEntity.setStatusId("developmentid");
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
        apixDao.updateApix(apixEntity);

        Node node = apix.getNode();
        node.setParentId(apix.getCategoryId());
        nodeService.updateNode(node);

        //更新索引
//        Apix entity = findApix(apix.getId());
//        disClient.update(entity);

        //发送消息
//        sendMessageForCreate(entity);


    }

    @Override
    public void deleteApix(@NotNull String id) {

        //删除最近
        ApiRecentQuery apiRecentQuery = new ApiRecentQuery();
        apiRecentQuery.setApixId(id);
        List<ApiRecent> apiRecentList = apiRecentService.findApiRecentList(apiRecentQuery);
        for(ApiRecent apiRecent:apiRecentList){
            if(Objects.equals(apiRecent.getApix().getId(), id)){
                apiRecentService.deleteApiRecent(id);
            }
        }


        //删除接口
        apixDao.deleteApix(id);

        //删除分享
        shareService.deleteShareByApiId(id);

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
    public int findApixNum(String workspaceId) {
        int apixNum = apixDao.findApixNum(workspaceId);
        return apixNum;
    }

    @Override
    public Apix findApix(@NotNull String id) {
        Apix apix = findOne(id);
        joinTemplate.joinQuery(apix);

        Node node = nodeService.findNode(id);
        apix.setNode(node);

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

        for (Apix apix: apixList){
            Node node = nodeService.findNode(apix.getId());
            apix.setNode(node);
        }

        return apixList;
    }

    @Override
    public Pagination<ApiList> findApixPage(ApixQuery apixQuery) {

        Pagination<ApiListEntity>  pagination = apixDao.findApiPage(apixQuery);

        List<ApiList> apixList = BeanMapper.mapList(pagination.getDataList(), ApiList.class);
        joinTemplate.joinQuery(apixList);

        return PaginationBuilder.build(pagination, apixList);
    }


}