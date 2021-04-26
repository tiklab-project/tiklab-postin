package com.darthcloud.apibox.workspace.service;

import com.darthcloud.apibox.workspace.dao.WorkspaceDao;
import com.darthcloud.apibox.workspace.entity.WorkspacePo;
import com.darthcloud.apibox.workspace.model.Workspace;
import com.darthcloud.apibox.workspace.model.WorkspaceQuery;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.common.Pagination;
import com.darthcloud.dss.client.DssClient;
import com.darthcloud.orga.user.model.DmUser;
import com.darthcloud.orga.user.model.User;
import com.darthcloud.orga.user.service.DmUserService;
import com.darthcloud.privilege.prjprivilege.service.DmPrjRoleService;
import com.darthcloud.web.context.UserContext;
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
        String masterId = UserContext.getInstance().getTicket();
        DmUser dmUser = new DmUser();
        dmUser.setDomainId(projectId);
        dmUser.setUser(new User().setId(masterId));
        dmUserService.createDmUser(dmUser);

        //初始化项目权限
        dmPrjRoleService.initDmPrjRoles(projectId,masterId);

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