package net.tiklab.postin.workspace.service;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.logging.modal.LoggingType;
import net.tiklab.logging.service.LoggingTypeService;
import net.tiklab.message.message.model.SendMessageNotice;
import net.tiklab.message.message.service.SendMessageNoticeService;
import net.tiklab.postin.apidef.apix.model.Apix;
import net.tiklab.postin.apidef.apix.model.ApixQuery;
import net.tiklab.postin.apidef.apix.service.ApixService;
import net.tiklab.postin.apitest.http.httpcase.model.HttpTestcase;
import net.tiklab.postin.apitest.http.httpcase.model.HttpTestcaseQuery;
import net.tiklab.postin.apitest.http.httpcase.service.HttpTestcaseService;
import net.tiklab.postin.category.model.Category;
import net.tiklab.postin.category.model.CategoryQuery;
import net.tiklab.postin.category.service.CategoryService;
import net.tiklab.postin.sysmgr.datastructure.model.DataStructure;
import net.tiklab.postin.sysmgr.datastructure.model.DataStructureQuery;
import net.tiklab.postin.sysmgr.datastructure.service.DataStructureService;
import net.tiklab.postin.utils.LogUnit;
import net.tiklab.postin.utils.PostInUnit;
import net.tiklab.postin.workspace.dao.WorkspaceDao;
import net.tiklab.postin.workspace.entity.WorkspaceEntity;
import net.tiklab.postin.workspace.model.*;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.dss.client.DssClient;
import net.tiklab.join.JoinTemplate;
import net.tiklab.privilege.role.service.DmRoleService;
import net.tiklab.user.user.model.DmUser;
import net.tiklab.user.user.model.DmUserQuery;
import net.tiklab.user.user.model.User;
import net.tiklab.user.user.service.DmUserService;
import net.tiklab.user.user.service.UserService;
import net.tiklab.utils.context.LoginContext;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

import static net.tiklab.postin.utils.MessageTemplateConstant.*;

/**
* 用户服务业务处理
*/
@Service
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
    HttpTestcaseService httpTestcaseService;

    @Autowired
    DataStructureService dataStructureService;

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

    @Autowired
    DssClient disClient;

    @Autowired
    UserService userService;

    @Autowired
    PostInUnit postInUnit;


    @Override
    public String createWorkspace(@NotNull @Valid Workspace workspace) throws Exception {
        String userId =LoginContext.getLoginId();
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

        //初始化项目权限
        dmRoleService.initDmRoles(workspaceId,userId,"postin" );

        //拉入创建人
        DmUser dmUser = new DmUser();
        dmUser.setDomainId(workspaceId);

        List<String> memberList = workspace.getMemberList();
        for(String memberId : memberList){
            dmUser.setType(0);
            dmUser.setTagValue(memberId);

            if(Objects.equals(memberId,userId)){
                dmUser.setType(1);
            }

            dmUser.setTag(0);

            dmUserService.createDmUser(dmUser);
        }

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

        //删除数据
        workspaceDao.deleteWorkspace(id);
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

        //删除角色以及相关的关联表
        dmRoleService.deleteDmRoleByDomainId(id);

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
        //查询dmuser list
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setTagValue(workspaceQuery.getUserId());
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

        //查询空间列表
        WorkspaceQuery workspaceQuery1 = new WorkspaceQuery();
        List<Workspace> workspaceList = findWorkspaceList(workspaceQuery1);

        //存储list
        ArrayList<Workspace> arrayList = new ArrayList<>();

        //把我参与的空间 存储到list中
        if(CollectionUtils.isNotEmpty(dmUserList)){
            for(DmUser dmUser : dmUserList){
                for(Workspace workspace : workspaceList){
                    if(Objects.equals(dmUser.getDomainId(), workspace.getId())){
                        arrayList.add(workspace);
                    }
                }
            }
        }


        joinTemplate.joinQuery(arrayList);

        return arrayList;
    }

    @Override
    public WorkspaceHomeTotal findWorkspaceHomeTotal(String userId) {
        WorkspaceHomeTotal workspaceHomeTotal = new WorkspaceHomeTotal();

        //所有空间总和
        int all = 0;
        List<Workspace> allWorkspace = findAllWorkspace();
        if(CollectionUtils.isNotEmpty(allWorkspace)){
            all = allWorkspace.size();
        }

        //个人创建的空间 总和
        int create = 0;
        List<Workspace> workspaceList = findWorkspaceList(new WorkspaceQuery().setUserId(userId));
        if(CollectionUtils.isNotEmpty(workspaceList)){
            create=workspaceList.size();
        }

        //个人加入的空间 总和
        int join = 0;
        List<Workspace> workspaceJoinList = findWorkspaceJoinList(new WorkspaceQuery().setUserId(userId));
        if(CollectionUtils.isNotEmpty(workspaceJoinList)){
            join = workspaceJoinList.size();
        }

        //个人关注的空间 总和
        int follow = 0;
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(new WorkspaceFollowQuery().setUserId(userId));
        if(CollectionUtils.isNotEmpty(workspaceFollowList)){
            follow = workspaceFollowList.size();
        }


        workspaceHomeTotal.setAllTotal(all);
        workspaceHomeTotal.setCreateTotal(create);
        workspaceHomeTotal.setJoinTotal(join);
        workspaceHomeTotal.setFollowTotal(follow);


        return workspaceHomeTotal;
    }

    @Override
    public WorkspaceTotal findWorkspaceTotal(String id) {
        WorkspaceTotal workspaceTotal = new WorkspaceTotal();

        //获取分组的总和
        List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setWorkspaceId(id));
        workspaceTotal.setCategoryTotal(categoryList.size());

        //获取接口总和，case总和
        int apiCount=0;
        int caseCount = 0;
        for(Category category :categoryList){
            List<Apix>  apixList = apixService.findApixList(new ApixQuery().setCategoryId(category.getId()));
            apiCount+=apixList.size();

            int apiCaseCount=0;
            for (Apix apix: apixList){
                List<HttpTestcase> testcaseList = httpTestcaseService.findTestcaseList(new HttpTestcaseQuery().setHttpId(apix.getId()));
                apiCaseCount+=testcaseList.size();
            }

            caseCount+=apiCaseCount;
        }

        List<DataStructure> dataStructureList = dataStructureService.findDataStructureList(new DataStructureQuery().setWorkspaceId(id));

        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(id);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

        workspaceTotal.setModelTotal(dataStructureList.size());
        workspaceTotal.setApiTotal(apiCount);
        workspaceTotal.setCaseTotal(caseCount);
        workspaceTotal.setMemberTotal(dmUserList.size());



        return workspaceTotal;
    }

}