package com.doublekit.apibox.workspace.service;

import com.doublekit.apibox.workspace.dao.WorkspaceDao;
import com.doublekit.apibox.workspace.entity.WorkspacePo;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.apibox.workspace.model.WorkspaceQuery;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.Pagination;
import com.doublekit.dss.client.DssClient;
import com.doublekit.eam.common.Ticket;
import com.doublekit.eam.common.TicketContext;
import com.doublekit.eam.common.TicketHolder;
import com.doublekit.privilege.prjprivilege.service.DmPrjRoleService;
import com.doublekit.user.dmuser.model.DmUser;
import com.doublekit.user.dmuser.service.DmUserService;
import com.doublekit.user.user.model.User;
import org.springframework.beans.BeanUtils;
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
    DmUserService dmUserService;

    @Autowired
    DmPrjRoleService dmPrjRoleService;

    @Autowired
    DssClient dssClient;

    @Override
    public String createWorkspace(@NotNull @Valid Workspace workspace) {
        //创建项目
        WorkspacePo workspacePo = BeanMapper.map(workspace, WorkspacePo.class);

        String projectId = workspaceDao.createWorkspace(workspacePo);

        //初始化项目成员
        String ticketId = TicketHolder.get();
        Ticket ticket = TicketContext.get(ticketId);
        String userId = ticket.getUserId();
        DmUser dmUser = new DmUser();
        dmUser.setDomainId(projectId);
        dmUser.setUser(new User().setId(userId));
        dmUserService.createDmUser(dmUser);

        //初始化项目权限
        dmPrjRoleService.initDmPrjRoles(projectId,userId);

        //添加索引
        Workspace entity = findWorkspace(projectId);
        dssClient.save(entity);
        return projectId;
    }

    @Override
    public void updateWorkspace(@NotNull @Valid Workspace workspace) {
        //更新数据
        WorkspacePo workspacePo = BeanMapper.map(workspace, WorkspacePo.class);

        workspaceDao.updateWorkspace(workspacePo);

        //更新索引
        Workspace entity = findWorkspace(workspace.getId());
        dssClient.update(entity);
    }

    @Override
    public void deleteWorkspace(@NotNull String id) {
        //删除数据
        workspaceDao.deleteWorkspace(id);

        //删除索引
        dssClient.delete(Workspace.class,id);
    }

    @Override
    public Workspace findOne(String id) {
        WorkspacePo workspacePo = workspaceDao.findWorkspace(id);

        return BeanMapper.map(workspacePo, Workspace.class);
    }

    @Override
    public List<Workspace> findList(List<String> idList) {
        List<WorkspacePo> workspacePoList =  workspaceDao.findWorkspaceList(idList);

        return BeanMapper.mapList(workspacePoList,Workspace.class);
    }

    @Override
    public Workspace findWorkspace(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<Workspace> findAllWorkspace() {
        List<WorkspacePo> workspacePoList =  workspaceDao.findAllWorkspace();

        return BeanMapper.mapList(workspacePoList,Workspace.class);
    }

    @Override
    public List<Workspace> findWorkspaceList(WorkspaceQuery workspaceQuery) {
        List<WorkspacePo> workspacePoList = workspaceDao.findWorkspaceList(workspaceQuery);

        return BeanMapper.mapList(workspacePoList,Workspace.class);
    }

    @Override
    public Pagination<Workspace> findWorkspacePage(WorkspaceQuery workspaceQuery) {
        Pagination<Workspace> pg = new Pagination<>();

        Pagination<WorkspacePo>  pagination = workspaceDao.findWorkspacePage(workspaceQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Workspace> workspaceList = BeanMapper.mapList(pagination.getDataList(),Workspace.class);
        pg.setDataList(workspaceList);
        return pg;
    }
}