package com.darthcloud.apibox.workspace.service;

import com.darthcloud.apibox.workspace.dao.WorkspaceDao;
import com.darthcloud.apibox.workspace.entity.WorkspacePo;
import com.darthcloud.apibox.workspace.model.Workspace;
import com.darthcloud.apibox.workspace.model.WorkspaceQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    WorkspaceDao workspaceDao;

    @Override
    public String createWorkspace(@NotNull @Valid Workspace workspace) {
        WorkspacePo workspacePo = BeanMapper.map(workspace, WorkspacePo.class);

        return workspaceDao.createWorkspace(workspacePo);
    }

    @Override
    public void updateWorkspace(@NotNull @Valid Workspace workspace) {
        WorkspacePo workspacePo = BeanMapper.map(workspace, WorkspacePo.class);

        workspaceDao.updateWorkspace(workspacePo);
    }

    @Override
    public void deleteWorkspace(@NotNull String id) {
        workspaceDao.deleteWorkspace(id);
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
    public Pagination<List<Workspace>> findWorkspacePage(WorkspaceQuery workspaceQuery) {
        Pagination<List<Workspace>> pg = new Pagination<>();

        Pagination<List<WorkspacePo>>  pagination = workspaceDao.findWorkspacePage(workspaceQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Workspace> workspaceList = BeanMapper.mapList(pagination.getDataList(),Workspace.class);
        pg.setDataList(workspaceList);
        return pg;
    }
}