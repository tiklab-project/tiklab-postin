package io.tiklab.postin.workspace.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.model.CategoryQuery;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.support.apistatus.service.ApiStatusService;
import io.tiklab.postin.support.datastructure.model.DataStructure;
import io.tiklab.postin.support.datastructure.model.DataStructureQuery;
import io.tiklab.postin.support.datastructure.service.DataStructureService;
import io.tiklab.postin.common.LogUnit;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.postin.support.environment.service.EnvironmentService;
import io.tiklab.postin.workspace.dao.WorkspaceDao;
import io.tiklab.postin.workspace.entity.WorkspaceEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
//import io.tiklab.dss.client.DssClient;
import io.tiklab.join.JoinTemplate;
import io.tiklab.postin.workspace.model.*;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.security.logging.model.LoggingType;
import io.tiklab.security.logging.service.LoggingTypeService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

import static io.tiklab.postin.common.EnumTemplateConstant.*;

/**
* 空间服务
*/
@Service
@Exporter
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    WorkspaceDao workspaceDao;

    @Autowired
    WorkspaceRecentService workspaceRecentService;

    @Autowired
    WorkspaceFollowService workspaceFollowService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApixService apixService;


    @Autowired
    DataStructureService dataStructureService;

    @Autowired
    ApiStatusService apiStatusService;

    @Autowired
    EnvironmentService environmentService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    LogUnit logUnit;

    @Autowired
    LoggingTypeService loggingTypeService;

    @Autowired
    SendMessageNoticeService sendMessageNoticeService;

//    @Autowired
//    DssClient disClient;

    @Autowired
    UserService userService;

    @Autowired
    PostInUnit postInUnit;

    @Value("${base.url:null}")
    String baseUrl;

    @Override
    public String createWorkspace(@NotNull @Valid Workspace workspace) throws Exception {
        String userId = LoginContext.getLoginId();
        User userInfo = userService.findUser(userId);

        //创建项目
        WorkspaceEntity workspaceEntity = BeanMapper.map(workspace, WorkspaceEntity.class);
        workspaceEntity.setUserId(userId);
        String workspaceId = workspaceDao.createWorkspace(workspaceEntity);


        //初始化默认分组
        Category category = new Category();
        Workspace ws = new Workspace();
        ws.setId(workspaceId);
        category.setWorkspace(ws);
        category.setName("默认分组");
        categoryService.createCategory(category);

        //拉入创建人 关联权限
        dmRoleService.initPatchDmRole(workspaceId,workspace.getUserList(), "postin");

        //日志
        Map<String,String> map = new HashMap<>();
        map.put("name",workspace.getWorkspaceName());
        map.put("workspaceId",workspaceId);
        map.put("user",postInUnit.getUser().getNickname());
        map.put("mode","空间");
        map.put("images",workspace.getIconUrl());
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_CREATE_ID);
        map.put("actionType",oplogTypeOne.getName());
        logUnit.log(LOG_TYPE_CREATE_ID,"workspace",map);

        //消息
        //站内信
        SendMessageNotice messageDispatchNotice = new SendMessageNotice();
        Map<String,String> site_mail_Map = new HashMap<>();
        site_mail_Map.put("name",workspace.getWorkspaceName());
        site_mail_Map.put("id",workspaceId);
        site_mail_Map.put("userName",userInfo.getNickname());
        site_mail_Map.put("images",workspace.getIconUrl());
        String siteMailMsg = JSONObject.toJSONString(site_mail_Map);

        messageDispatchNotice.setSiteData(siteMailMsg);

        //邮箱
        messageDispatchNotice.setEmailData(siteMailMsg);

        //钉钉
        Map<String,String> DD_MSGMap = new HashMap<>();
        DD_MSGMap.put("name",workspace.getWorkspaceName());
        DD_MSGMap.put("userName",userInfo.getNickname());
        DD_MSGMap.put("images",workspace.getIconUrl());
        messageDispatchNotice.setDingdingData(JSONObject.toJSONString(DD_MSGMap));

        //企业微信
        Map<String,String> WX_MSGMap = new HashMap<>();
        WX_MSGMap.put("name",workspace.getWorkspaceName());
        WX_MSGMap.put("userName",userInfo.getNickname());
        messageDispatchNotice.setQywechatData(JSONObject.toJSONString(WX_MSGMap));

        messageDispatchNotice.setId("MESSAGE_NOTICE_ID");
        messageDispatchNotice.setBaseUrl(baseUrl);
        sendMessageNoticeService.createMessageItem(messageDispatchNotice);




        //添加索引
//        Workspace entity = findWorkspace(workspaceId);
//        disClient.save(entity);

        return workspaceId;
    }

    @Override
    public void updateWorkspace(@NotNull @Valid Workspace workspace) {
        //更新数据
        WorkspaceEntity workspaceEntity = BeanMapper.map(workspace, WorkspaceEntity.class);

        workspaceDao.updateWorkspace(workspaceEntity);

        //日志
        Map<String,String> map = new HashMap<>();
        map.put("name",workspace.getWorkspaceName());
        map.put("workspaceId",workspace.getId());
        map.put("user",postInUnit.getUser().getNickname());
        map.put("mode","空间");
        map.put("images",workspace.getIconUrl());
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_UPDATE_ID);
        map.put("actionType",oplogTypeOne.getName());
        logUnit.log(LOG_TYPE_UPDATE_ID,"workspace",map);

        //更新索引
//        Workspace entity = findWorkspace(workspace.getId());
//        disClient.update(entity);
    }

    @Override
    public void deleteWorkspace(@NotNull String id) {
        Workspace workspace = findWorkspace(id);

        //日志
        Map<String,String> map = new HashMap<>();
        map.put("name",workspace.getWorkspaceName());
        map.put("workspaceId",workspace.getId());
        map.put("user",postInUnit.getUser().getNickname());
        map.put("mode","空间");
        map.put("images",workspace.getIconUrl());
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_DELETE_ID);
        map.put("actionType",oplogTypeOne.getName());
        logUnit.log(LOG_TYPE_DELETE_ID,"workspace",map);


        List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setWorkspaceId(id));
        if(CollectionUtils.isNotEmpty(categoryList)){
            for(Category category:categoryList){
                categoryService.deleteCategory(category.getId());
            }
        }
        String loginId = LoginContext.getLoginId();
        //删除关注的
        WorkspaceFollowQuery workspaceFollowQuery = new WorkspaceFollowQuery();
        workspaceFollowQuery.setUserId(loginId);
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(workspaceFollowQuery);
        if(CollectionUtils.isNotEmpty(workspaceFollowList)){
            for(WorkspaceFollow workspaceFollow: workspaceFollowList){
                if(Objects.equals(workspaceFollow.getWorkspace().getId(), id)){
                    workspaceFollowService.deleteWorkspaceFollow(workspaceFollow.getWorkspace().getId());
                }
            }
        }

        //删除最近
        WorkspaceRecentQuery workspaceRecentQuery = new WorkspaceRecentQuery();
        workspaceRecentQuery.setUserId(loginId);
        List<WorkspaceRecent> recentList = workspaceRecentService.findRecentList(workspaceRecentQuery);
        if(CollectionUtils.isNotEmpty(recentList)){
            for(WorkspaceRecent workspaceRecent : recentList){
                if(Objects.equals(workspaceRecent.getWorkspace().getId(),id)){
                    workspaceRecentService.deleteWorkspaceRecent(workspaceRecent.getId());
                }
            }
        }

        //删除状态
        apiStatusService.deleteAllApiStatus(id);

        //删除数据结构
        DataStructureQuery dataStructureQuery = new DataStructureQuery();
        dataStructureQuery.setWorkspaceId(id);
        List<DataStructure> dataStructureList = dataStructureService.findDataStructureList(dataStructureQuery);
        if(dataStructureList!=null){
            for(DataStructure dataStructure:dataStructureList){
                dataStructureService.deleteDataStructure(dataStructure.getId());
            }
        }

        //删除环境
        environmentService.deleteAllEnvironment(id);

        //删除角色以及相关的关联表
        dmRoleService.deleteDmRoleByDomainId(id);

        //删除空间
        workspaceDao.deleteWorkspace(id);

        //删除索引
//        disClient.delete(Workspace.class,id);
    }

    @Override
    public Workspace findOne(String id) {
        WorkspaceEntity workspaceEntity = workspaceDao.findWorkspace(id);

        return BeanMapper.map(workspaceEntity, Workspace.class);
    }

    @Override
    public List<Workspace> findList(List<String> idList) {
        List<WorkspaceEntity> workspaceEntityList =  workspaceDao.findWorkspaceList(idList);

        return BeanMapper.mapList(workspaceEntityList,Workspace.class);
    }

    @Override
    public Workspace findWorkspace(@NotNull String id) {

        return findOne(id);
    }

    @Override
    public List<Workspace> findAllWorkspace() {
        List<WorkspaceEntity> workspaceEntityList =  workspaceDao.findAllWorkspace();

        joinTemplate.joinQuery(workspaceEntityList);

        return BeanMapper.mapList(workspaceEntityList,Workspace.class);
    }

    @Override
    public List<Workspace> findWorkspaceList(WorkspaceQuery workspaceQuery) {
        List<WorkspaceEntity> workspaceEntityList = workspaceDao.findWorkspaceList(workspaceQuery);
        List<Workspace> workspaceList = BeanMapper.mapList(workspaceEntityList, Workspace.class);

        //关注
        WorkspaceFollowQuery workspaceFollowQuery = new WorkspaceFollowQuery();
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(workspaceFollowQuery);

        //设置是否关注
        if(CollectionUtils.isNotEmpty(workspaceList)&&CollectionUtils.isNotEmpty(workspaceFollowList)){
            for(Workspace workspace : workspaceList){
                for(WorkspaceFollow workspaceFollow: workspaceFollowList){
                    if(Objects.equals(workspace.getId(), workspaceFollow.getWorkspace().getId())){
                        workspace.setIsFollow(1);
                    }else {
                        workspace.setIsFollow(0);
                    }
                }
            }
        }else {
            for(Workspace workspace : workspaceList){
                workspace.setIsFollow(0);
            }
        }

        joinTemplate.joinQuery(workspaceList);

        return workspaceList;
    }

    @Override
    public Pagination<Workspace> findWorkspacePage(WorkspaceQuery workspaceQuery) {

        Pagination<WorkspaceEntity>  pagination = workspaceDao.findWorkspacePage(workspaceQuery);

        List<Workspace> workspaceList = BeanMapper.mapList(pagination.getDataList(),Workspace.class);

        joinTemplate.joinQuery(workspaceList);
        return PaginationBuilder.build(pagination,workspaceList);
    }

    @Override
    public List<Workspace> findWorkspaceJoinList(WorkspaceQuery workspaceQuery) {

        //查询空间列表
        WorkspaceQuery processQuery = new WorkspaceQuery();
        processQuery.setOrderParams(workspaceQuery.getOrderParams());
        List<Workspace> workspaceList = findWorkspaceList(processQuery);



        //存储list
        ArrayList<Workspace> arrayList = new ArrayList<>();

        for(Workspace workspace : workspaceList){
            //查询dmuser list
            DmUserQuery dmUserQuery = new DmUserQuery();
            dmUserQuery.setUserId(workspaceQuery.getUserId());
            dmUserQuery.setDomainId(workspace.getId());
            List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

            if(dmUserList==null||dmUserList.size()==0){
                continue;
            }

            for(DmUser dmUser : dmUserList){
                //如果空间成员与当前人相同
                if(Objects.equals(dmUser.getUser().getId(), workspaceQuery.getUserId())){
                    arrayList.add(workspace);
                }
            }
        }

        joinTemplate.joinQuery(arrayList);

        return arrayList;
    }



}