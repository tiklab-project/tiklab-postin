package net.tiklab.postin.apidef.apix.service;

import net.tiklab.postin.apidef.apix.dao.ApixDao;
import net.tiklab.postin.apidef.apix.entity.ApixEntity;
import net.tiklab.postin.apidef.apix.model.Apix;
import net.tiklab.postin.apidef.apix.model.ApixQuery;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.dss.client.DssClient;
import net.tiklab.join.JoinTemplate;
import net.tiklab.message.message.service.MessageService;
import net.tiklab.postin.utils.LogUnit;
import net.tiklab.rpc.annotation.Exporter;
import net.tiklab.user.user.model.User;
import net.tiklab.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    DssClient disClient;

    @Autowired
    LogUnit logUnit;

    @Override
    public String createApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);


        //初始化项目成员
        String userId = LoginContext.getLoginId();
        apixEntity.setCreateUser(userId);

        apixEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String id = apixDao.createApix(apixEntity);


        //日志
        Map<String,String> map = new HashMap<>();
        map.put("name",apix.getName());
        map.put("id",apix.getId());
        map.put("workspaceId",apix.getWorkspaceId());
        map.put("user",userId);
        map.put("mode","接口");
        map.put("images","/images/log.png");
        logUnit.log("CREATE_TYPE","api",map);

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
        map.put("user",userId);
        map.put("mode","接口");
        map.put("images","/images/log.png");
        logUnit.log("UPDATE_TYPE","api",map);

    }

    @Override
    public void deleteApix(@NotNull String id) {
        ApixEntity apix = apixDao.findApix(id);

        //日志
        String userId = LoginContext.getLoginId();
        Map<String,String> map = new HashMap<>();
        map.put("name",apix.getName());
        map.put("id",apix.getId());
        map.put("user",userId);
        map.put("workspaceId",apix.getWorkspaceId());
        map.put("mode","接口");
        map.put("images","/images/log.png");
        logUnit.log("DELETE_TYPE","api",map);

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