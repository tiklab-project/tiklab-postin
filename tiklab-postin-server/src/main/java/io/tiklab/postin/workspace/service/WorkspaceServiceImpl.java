package io.tiklab.postin.workspace.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.test.instance.service.TestInstanceService;
import io.tiklab.postin.autotest.test.service.TestCaseService;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import static io.tiklab.postin.common.EnumTemplateConstant.LOG_TYPE_CREATE_ID;
import static io.tiklab.postin.common.EnumTemplateConstant.LOG_TYPE_UPDATE_ID;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.model.NodeQuery;
import io.tiklab.postin.node.service.NodeService;
import io.tiklab.postin.support.apistatus.service.ApiStatusService;
import io.tiklab.postin.support.datastructure.model.DataStructure;
import io.tiklab.postin.support.datastructure.model.DataStructureQuery;
import io.tiklab.postin.support.datastructure.service.DataStructureService;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.model.Environment;
import io.tiklab.postin.support.environment.service.EnvServerService;
import io.tiklab.postin.support.environment.service.EnvironmentService;
import io.tiklab.postin.workspace.dao.WorkspaceDao;
import io.tiklab.postin.workspace.entity.WorkspaceEntity;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.model.WorkspaceFollow;
import io.tiklab.postin.workspace.model.WorkspaceFollowQuery;
import io.tiklab.postin.workspace.model.WorkspaceQuery;
import io.tiklab.postin.workspace.model.WorkspaceRecent;
import io.tiklab.postin.workspace.model.WorkspaceRecentQuery;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.privilege.role.model.RoleUser;
import io.tiklab.privilege.role.service.RoleUserService;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;

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

    @Autowired
    ApixService apixService;

    @Autowired
    TestCaseService testCaseService;


//    @Autowired
//    DssClient disClient;

    @Autowired
    PostInUnit postInUnit;

    @Value("${external.url:http://localhost:9300}")
    String baseUrl;



    @Override
    public String createWorkspace(@NotNull @Valid Workspace workspace) throws Exception {
        String userId = LoginContext.getLoginId();


        //创建项目
        WorkspaceEntity workspaceEntity = BeanMapper.map(workspace, WorkspaceEntity.class);
        workspaceEntity.setUserId(userId);
        workspaceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
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
        environment.setId(workspaceId);
        environment.setName("Mock环境");
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
        Workspace workspace = findOne(id);
        joinTemplate.joinQuery(workspace, new String[]{"user"});
        return workspace;
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

        WorkspaceQuery processQuery = new WorkspaceQuery();
        processQuery.setWorkspaceName(workspaceQuery.getWorkspaceName());
        processQuery.setOrderParams(workspaceQuery.getOrderParams());
        List<Workspace> workspaceList = findWorkspaceList(processQuery);

        String loginId = LoginContext.getLoginId();

        // 查询用户加入的空间ID，减少多次数据库调用
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(loginId);
        Set<String> joinedWorkspaceIds = dmUserService.findDmUserList(dmUserQuery)
                .stream()
                .map(DmUser::getDomainId)
                .collect(Collectors.toSet());

        // 过滤当前用户可见的空间
        List<Workspace> result = workspaceList.stream()
                .filter(ws -> ws.getVisibility() == 0 || joinedWorkspaceIds.contains(ws.getId()))
                .collect(Collectors.toList());

        // 设置关注状态
        Set<String> followedWorkspaceIds = new HashSet<>(getFollowedWorkspaceIds());
        result.forEach(ws -> ws.setIsFollow(followedWorkspaceIds.contains(ws.getId()) ? 1 : 0));

        // 关联查询
        joinTemplate.joinQuery(result, new String[]{"user"});

        return result;
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

    @Override
    public Map<String, Object> getWorkspaceNumber() {
        HashMap<String, Object> numMap = new HashMap<>();

        List<Workspace> workspaceJoinList = findWorkspaceJoinList(new WorkspaceQuery());
        int size = workspaceJoinList.size();
        numMap.put("all",size);

        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(new WorkspaceFollowQuery());
        int followSize = workspaceFollowList.size();
        numMap.put("follow",followSize);

        WorkspaceQuery workspaceQuery = new WorkspaceQuery();
        workspaceQuery.setUserId(LoginContext.getLoginId());
        List<Workspace> workspaceList = findWorkspaceList(workspaceQuery);
        int mySize = workspaceList.size();
        numMap.put("create",mySize);

        return numMap;
    }

    @Override
    public Pagination<Workspace> findWorkspacePageByType(WorkspaceQuery workspaceQuery) {
        String type = workspaceQuery.getType();
        String loginId = LoginContext.getLoginId();
        
        switch (type) {
            case "create":
                return findCreatedWorkspaces(workspaceQuery, loginId);
            case "follow":
                return findFollowedWorkspaces(workspaceQuery, loginId);
            default:
                return findAllVisibleWorkspaces(workspaceQuery, loginId);
        }
    }

    /**
     * 获取我创建的空间
     */
    private Pagination<Workspace> findCreatedWorkspaces(WorkspaceQuery workspaceQuery, String loginId) {
        workspaceQuery.setUserId(loginId);
        
        Pagination<WorkspaceEntity> pagination = workspaceDao.findWorkspacePage(workspaceQuery);
        List<Workspace> workspaceList = BeanMapper.mapList(pagination.getDataList(), Workspace.class);
        
        enrichWorkspaceData(workspaceList);
        joinTemplate.joinQuery(workspaceList, new String[]{"user"});
        
        return PaginationBuilder.build(pagination, workspaceList);
    }

    /**
     * 获取我关注的空间
     */
    private Pagination<Workspace> findFollowedWorkspaces(WorkspaceQuery workspaceQuery, String loginId) {
        WorkspaceFollowQuery followQuery = new WorkspaceFollowQuery();
        followQuery.setUserId(loginId);
        followQuery.setOrderParams(workspaceQuery.getOrderParams());
        followQuery.setPageParam(workspaceQuery.getPageParam());
        
        Pagination<WorkspaceFollow> followPagination = workspaceFollowService.findWorkspaceFollowPage(followQuery);
        List<Workspace> workspaceList = extractWorkspacesFromFollows(followPagination.getDataList(), workspaceQuery.getWorkspaceName());
        
        return PaginationBuilder.build(followPagination, workspaceList);
    }

    /**
     * 从关注列表中提取工作空间
     */
    private List<Workspace> extractWorkspacesFromFollows(List<WorkspaceFollow> followList, String workspaceName) {
        List<Workspace> workspaceList = followList.stream()
                .map(WorkspaceFollow::getWorkspace)
                .filter(workspace -> workspaceName == null || workspace.getWorkspaceName().contains(workspaceName))
                .collect(Collectors.toList());
        return workspaceList;
    }

    /**
     * 获取所有可见的空间（包括我加入的和公开的）
     */
    private Pagination<Workspace> findAllVisibleWorkspaces(WorkspaceQuery workspaceQuery, String loginId) {
        workspaceQuery.setVisibility(0);
        List<Workspace> list = findWorkspaceList(workspaceQuery);

        // 1. 可见的 workspace ids
        Set<String> visibleIds = list.stream()
                .map(Workspace::getId)
                .collect(Collectors.toSet());

        // 2. 用户加入的 workspace ids
        Set<String> joinedIds = getJoinedWorkspaceIds(loginId);

        // 3. 合并
        Set<String> allIds = new HashSet<>();
        allIds.addAll(visibleIds);
        allIds.addAll(joinedIds);

        // 4. 根据合并后的 id 查询完整 workspace 信息
        WorkspaceQuery allQuery = new WorkspaceQuery();
        allQuery.setIds(allIds.toArray(new String[0]));
        allQuery.setOrderParams(workspaceQuery.getOrderParams());
        allQuery.setPageParam(workspaceQuery.getPageParam());
        allQuery.setWorkspaceName(workspaceQuery.getWorkspaceName());
        Pagination<WorkspaceEntity> pagination = workspaceDao.findWorkspacePage(allQuery);
        List<Workspace> workspaceList = BeanMapper.mapList(pagination.getDataList(), Workspace.class);

        enrichWorkspaceData(workspaceList);
        joinTemplate.joinQuery(workspaceList, new String[]{"user"});

        return PaginationBuilder.build(pagination, workspaceList);
    }

    /**
     * 获取用户加入的工作空间ID集合
     */
    private Set<String> getJoinedWorkspaceIds(String loginId) {
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(loginId);
        return dmUserService.findDmUserList(dmUserQuery)
                .stream()
                .map(DmUser::getDomainId)
                .collect(Collectors.toSet());
    }


    /**
     * 设置关注状态、API数量、用例数量
     */
    private void enrichWorkspaceData(List<Workspace> workspaceList) {
        Set<String> followedWorkspaceIds = new HashSet<>(getFollowedWorkspaceIds());
        
        for (Workspace workspace : workspaceList) {
            // 设置关注状态
            workspace.setIsFollow(followedWorkspaceIds.contains(workspace.getId()) ? 1 : 0);
            
            // 设置API数量
            int apiNum = apixService.findApixNum(workspace.getId());
            workspace.setApiNum(apiNum);
            
            // 设置用例数量
            int caseNum = testCaseService.findTestCaseNum(workspace.getId());
            workspace.setCaseNum(caseNum);
        }
    }

}