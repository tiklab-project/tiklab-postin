package io.thoughtware.postin.workspace.service;

import io.thoughtware.postin.api.http.test.instance.service.TestInstanceService;
import io.thoughtware.postin.node.model.Node;
import io.thoughtware.postin.node.model.NodeQuery;
import io.thoughtware.postin.node.service.NodeService;
import io.thoughtware.postin.workspace.dao.WorkspaceDao;
import io.thoughtware.postin.workspace.entity.WorkspaceEntity;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.postin.category.model.Category;
import io.thoughtware.postin.category.model.CategoryQuery;
import io.thoughtware.postin.category.service.CategoryService;
import io.thoughtware.postin.support.apistatus.service.ApiStatusService;
import io.thoughtware.postin.support.datastructure.model.DataStructure;
import io.thoughtware.postin.support.datastructure.model.DataStructureQuery;
import io.thoughtware.postin.support.datastructure.service.DataStructureService;
import io.thoughtware.postin.common.PostInUnit;
import io.thoughtware.postin.support.environment.model.Environment;
import io.thoughtware.postin.support.environment.service.EnvironmentService;
import io.thoughtware.privilege.role.model.RoleUser;
import io.thoughtware.privilege.role.service.RoleUserService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.postin.workspace.model.*;
import io.thoughtware.privilege.dmRole.service.DmRoleService;
import io.thoughtware.privilege.role.model.PatchUser;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.dmUser.service.DmUserService;
import io.thoughtware.user.user.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static io.thoughtware.postin.common.EnumTemplateConstant.*;

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
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestInstanceService testInstanceService;

    @Autowired
    RoleUserService roleUserService;


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
        node.setWorkspaceId(workspaceId);
        node.setName("默认分组");
        category.setNode(node);
        categoryService.createCategory(category);

        //初始化一个mock
        Environment environment = new Environment();
        environment.setWorkspaceId(workspaceId);
        environment.setName("Mock");
        String mockUrl = baseUrl+"/mockx/"+workspaceId;
        environment.setUrl(mockUrl);
        environmentService.createEnvironment(environment);

        //拉入创建人 关联权限
        initProjectDmRole(workspace.getUserList(),workspaceId);

        Map<String,String> map = new HashMap<>();
        map.put("workspaceName",workspace.getWorkspaceName());
        map.put("workspaceId",workspaceId);
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

        joinTemplate.joinQuery(workspaceEntityList);

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


        joinTemplate.joinQuery(arrayList);

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
        String id = roleAdmin.getUser().getId();

        if (Objects.isNull(userList) || userList.isEmpty()){
            dmRoleService.initDmRoles(workspaceId, loginId,2);
            if (loginId.equals(id)){
                return;
            }
            dmRoleService.initDmRoles(workspaceId, id,1);
        }else {
            boolean isSuperAdmin = false;

            for(PatchUser user:userList){
                if(user.getUserId().equals(loginId)){
                    user.setRoleType(2);
                    isSuperAdmin=true;
                }
            }

            if(!isSuperAdmin){
                PatchUser patchUser = new PatchUser();
                patchUser.setUserId(loginId);
                patchUser.setRoleType(2);
                userList.add(patchUser);
            }

            // 判断系统管理员是否在其中
            List<PatchUser> list = userList.stream()
                    .filter(patchUser -> patchUser.getUserId().equals(id))
                    .toList();
            if (list.isEmpty()){
                PatchUser patchUser = new PatchUser(id);
                userList.add(patchUser);
            }
            //关联权限
            dmRoleService.initPatchDmRole(workspaceId,userList);
        }
    }

}