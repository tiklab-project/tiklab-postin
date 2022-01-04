package com.doublekit.apibox.workspace.service;

import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.category.model.CategoryQuery;
import com.doublekit.apibox.category.service.CategoryService;
import com.doublekit.apibox.workspace.dao.WorkspaceDao;
import com.doublekit.apibox.workspace.entity.WorkspaceEntity;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.apibox.workspace.model.WorkspaceQuery;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.Pagination;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.dss.client.DssClient;
import com.doublekit.eam.common.Ticket;
import com.doublekit.eam.common.TicketContext;
import com.doublekit.eam.common.TicketHolder;
import com.doublekit.privilege.role.service.DmRoleService;
import com.doublekit.user.user.model.DmUser;
import com.doublekit.user.user.model.User;
import com.doublekit.user.user.service.DmUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    WorkspaceDao workspaceDao;

    @Autowired
    CategoryService categoryService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    DssClient dssClient;

    @Override
    public String createWorkspace(@NotNull @Valid Workspace workspace) {
        //创建项目
        WorkspaceEntity workspaceEntity = BeanMapper.map(workspace, WorkspaceEntity.class);

        //初始化项目成员
        String ticketId = TicketHolder.get();
        Ticket ticket = TicketContext.get(ticketId);
        String userId = ticket.getUserId();
//        String userId = CurrentRegUser.getInstace().findCreatUser();

        workspaceEntity.setUserId(userId);
        String projectId = workspaceDao.createWorkspace(workspaceEntity);

        DmUser dmUser = new DmUser();
        dmUser.setDomainId(projectId);
        dmUser.setUser(new User().setId(userId));
        dmUserService.createDmUser(dmUser);

        //初始化项目权限
        dmRoleService.initDmRoles(projectId,userId);

        //添加索引
        Workspace entity = findWorkspace(projectId);
//        dssClient.save(entity);
        return projectId;
    }

    @Override
    public void updateWorkspace(@NotNull @Valid Workspace workspace) {
        //更新数据
        WorkspaceEntity workspaceEntity = BeanMapper.map(workspace, WorkspaceEntity.class);

        workspaceDao.updateWorkspace(workspaceEntity);

        //更新索引
        Workspace entity = findWorkspace(workspace.getId());
        dssClient.update(entity);
    }

    @Override
    public void deleteWorkspace(@NotNull String id) {
        //删除数据
        workspaceDao.deleteWorkspace(id);
        List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setWorkspaceId(id));
        if(CollectionUtils.isNotEmpty(categoryList)){
            for(Category category:categoryList){
                categoryService.deleteCategory(category.getId());
            }
        }
        //删除索引
//        dssClient.delete(Workspace.class,id);
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

        return BeanMapper.mapList(workspaceEntityList,Workspace.class);
    }

    @Override
    public List<Workspace> findWorkspaceList(WorkspaceQuery workspaceQuery) {
        List<WorkspaceEntity> workspaceEntityList = workspaceDao.findWorkspaceList(workspaceQuery);

        return BeanMapper.mapList(workspaceEntityList,Workspace.class);
    }

    @Override
    public Pagination<Workspace> findWorkspacePage(WorkspaceQuery workspaceQuery) {

        Pagination<WorkspaceEntity>  pagination = workspaceDao.findWorkspacePage(workspaceQuery);

        List<Workspace> workspaceList = BeanMapper.mapList(pagination.getDataList(),Workspace.class);
        return PaginationBuilder.build(pagination,workspaceList);
    }


    @Override
    public List<Workspace> findWorkspaceJoinList(WorkspaceQuery workspaceQuery) {
        List<WorkspaceEntity> workspaceEntityList = workspaceDao.findWorkspaceJoinList(workspaceQuery);
        System.out.println(System.getProperty("user.dir"));
        return BeanMapper.mapList(workspaceEntityList,Workspace.class);
    }

}