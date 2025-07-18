package io.tiklab.postin.workspace.service;

import io.tiklab.postin.api.http.test.instance.service.TestInstanceService;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.model.NodeQuery;
import io.tiklab.postin.node.service.NodeService;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.service.EnvServerService;
import io.tiklab.postin.workspace.dao.WorkspaceDao;
import io.tiklab.postin.workspace.entity.WorkspaceEntity;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.model.CategoryQuery;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.support.apistatus.service.ApiStatusService;
import io.tiklab.postin.support.datastructure.model.DataStructure;
import io.tiklab.postin.support.datastructure.model.DataStructureQuery;
import io.tiklab.postin.support.datastructure.service.DataStructureService;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.postin.support.environment.model.Environment;
import io.tiklab.postin.support.environment.service.EnvironmentService;
import io.tiklab.privilege.role.model.RoleUser;
import io.tiklab.privilege.role.service.RoleUserService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.postin.workspace.model.*;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

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
    NodeService nodeService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    DataStructureService dataStructureService;

    @Autowired
    ApiStatusService apiStatusService;

    @Autowired
    EnvironmentService environmentService;

    @Autowired
    EnvServerService envServerService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestInstanceService testInstanceService;

    @Autowired
    RoleUserService roleUserService;

    @Autowired
    UserProcessor userService;


//    @Autowired
//    DssClient disClient;

    @Autowired
    PostInUnit postInUnit;

    @Value("${base.url:null}")
    String baseUrl;



    @Override
    public String createWorkspace(@NotNull @Valid Workspace workspace) throws Exception {
        String userId = LoginContext.getLoginId();


        //创建项目
        WorkspaceEntity workspaceEntity = BeanMapper.map(workspace, WorkspaceEntity.class);
        workspaceEntity.setUserId(userId);
        String workspaceId = workspaceDao.createWorkspace(workspaceEntity);


        //初始化默认分组
        Category category = new Category();
        Node node = new Node();
        Workspace workspace1 = new Workspace();
        workspace1.setId(workspaceId);
        node.setWorkspace(workspace1);
        node.setName("默认分组");
        category.setNode(node);
        categoryService.createCategory(category);

        //初始化一个mock
        Environment environment = new Environment();
        environment.setWorkspaceId(workspaceId);
        environment.setName("Mock");
        String envId = environmentService.createEnvironment(environment);
        EnvServer envServer = new EnvServer();
        envServer.setEnvId(envId);
        envServer.setName("默认服务");
        envServer.setUrl(baseUrl+"/mockx/"+workspaceId);
        envServerService.createEnvServer(envServer);

        //拉入创建人 关联权限
        initProjectDmRole(workspace.getUserList(),workspaceId);

        Map<String,String> map = new HashMap<>();
        map.put("workspaceName",workspace.getWorkspaceName());
        map.put("workspaceId",workspaceId);
        User user = userService.findUser(userId);
        map.put("userName",user.getNickname());
        map.put("link","/workspace/overview/${workspaceId}");
        //日志
        postInUnit.log(LOG_TYPE_CREATE_ID,"workspace",map);

        //消息
        postInUnit.message(map);




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
        map.put("workspaceName",workspace.getWorkspaceName());
        map.put("workspaceId",workspace.getId());
        map.put("link","/workspace/overview/${workspaceId}");
        postInUnit.log(LOG_TYPE_UPDATE_ID,"workspace",map);

        //更新索引
//        Workspace entity = findWorkspace(workspace.getId());
//        disClient.update(entity);
    }

    @Override
    public void deleteWorkspace(@NotNull String id) {
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setWorkspaceId(id);
        List<Node> nodeList = nodeService.findNodeList(nodeQuery);
        if(CollectionUtils.isNotEmpty(nodeList)){
            for (Node node : nodeList){
                nodeService.deleteNode(node.getId());
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

        testInstanceService.deleteAllTestInstance(id);

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

        joinTemplate.joinQuery(workspaceEntityList,new String[]{
                "user"
        });

        return BeanMapper.mapList(workspaceEntityList,Workspace.class);
    }


    @Override
    public List<Workspace> findWorkspaceList(WorkspaceQuery workspaceQuery) {
        List<WorkspaceEntity> workspaceEntityList = workspaceDao.findWorkspaceList(workspaceQuery);
        List<Workspace> workspaceList = BeanMapper.mapList(workspaceEntityList, Workspace.class);

        // 获取所有关注的 Workspace 的 ID 列表
        List<String> followedWorkspaceIds = getFollowedWorkspaceIds();

        // 设置是否关注
        for (Workspace workspace : workspaceList) {
            if (followedWorkspaceIds.contains(workspace.getId())) {
                workspace.setIsFollow(1);
            } else {
                workspace.setIsFollow(0);
            }
        }

        joinTemplate.joinQuery(workspaceList,new String[]{
                "user"
        });

        return workspaceList;
    }

    @Override
    public Pagination<Workspace> findWorkspacePage(WorkspaceQuery workspaceQuery) {

        Pagination<WorkspaceEntity>  pagination = workspaceDao.findWorkspacePage(workspaceQuery);

        List<Workspace> workspaceList = BeanMapper.mapList(pagination.getDataList(),Workspace.class);

        joinTemplate.joinQuery(workspaceList,new String[]{
                "user"
        });
        return PaginationBuilder.build(pagination,workspaceList);
    }

    @Override
    public List<Workspace> findWorkspaceJoinList(WorkspaceQuery workspaceQuery) {

        //查询空间列表
        WorkspaceQuery processQuery = new WorkspaceQuery();
        processQuery.setWorkspaceName(workspaceQuery.getWorkspaceName());
        processQuery.setOrderParams(workspaceQuery.getOrderParams());
        List<Workspace> workspaceList = findWorkspaceList(processQuery);

        //存储list
        ArrayList<Workspace> arrayList = new ArrayList<>();

        for(Workspace workspace : workspaceList){


            //如果是公共：0，都能查看
            if(workspace.getVisibility().equals(0)){
                arrayList.add(workspace);
            }else {
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
        }

        // 获取所有关注的 Workspace 的 ID 列表
        List<String> followedWorkspaceIds = getFollowedWorkspaceIds();
        // 设置是否关注
        for (Workspace workspace : arrayList) {
            if (followedWorkspaceIds.contains(workspace.getId())) {
                workspace.setIsFollow(1);
            } else {
                workspace.setIsFollow(0);
            }
        }


        joinTemplate.joinQuery(arrayList,new String[]{
                "user"
        });

        return arrayList;
    }

    /**
     * 获取当前用户关注的 Workspace 的 ID 列表
     */
    private List<String> getFollowedWorkspaceIds() {
        WorkspaceFollowQuery workspaceFollowQuery = new WorkspaceFollowQuery();
        workspaceFollowQuery.setUserId(LoginContext.getLoginId());
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(workspaceFollowQuery);

        return workspaceFollowList.stream()
                .map(workspaceFollow -> workspaceFollow.getWorkspace().getId())
                .collect(Collectors.toList());
    }


    /**
     * 创建项目权限
     * @param userList
     * @param workspaceId
     */
    public void initProjectDmRole(List<PatchUser> userList, String workspaceId) {
        String loginId = LoginContext.getLoginId();

        // 获取系统超级管理员
        RoleUser roleAdmin = roleUserService.findUserRoleAdmin();
        String adminId = roleAdmin.getUser().getId();

        // 使用 Set 来跟踪用户 ID
        Set<String> userIdSet = userList != null ? userList.stream().map(PatchUser::getUserId).collect(Collectors.toSet()) : new HashSet<>();

        if (userIdSet.isEmpty()) {
            // 初始化用户列表并添加管理员和当前用户
            userList = new ArrayList<>();
            userList.add(createPatchUser(adminId, 2));
            if (!loginId.equals(adminId)) {
                userList.add(createPatchUser(loginId, 1));
            }
        } else {
            // 设置当前用户角色
            userList.forEach(user -> {
                if (user.getUserId().equals(loginId)) {
                    user.setRoleType(1);
                }
                if (user.getUserId().equals(adminId)) {
                    user.setRoleType(2);
                }
            });

            // 如果超级管理员不在列表中，添加
            if (!userIdSet.contains(adminId)) {
                userList.add(createPatchUser(adminId, 2));
            }
        }

        // 关联权限
        dmRoleService.initPatchDmRole(workspaceId, userList);
    }

    // 工具方法：创建 PatchUser
    private PatchUser createPatchUser(String userId, int roleType) {
        PatchUser user = new PatchUser();
        user.setUserId(userId);
        user.setRoleType(roleType);
        return user;
    }

}