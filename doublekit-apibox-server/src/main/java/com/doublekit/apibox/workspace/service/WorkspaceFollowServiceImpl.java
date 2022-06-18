package com.doublekit.apibox.workspace.service;

import com.doublekit.apibox.workspace.dao.WorkspaceFollowDao;
import com.doublekit.apibox.workspace.entity.WorkspaceFollowEntity;
import com.doublekit.apibox.workspace.model.WorkspaceFollow;
import com.doublekit.apibox.workspace.model.WorkspaceFollowQuery;

import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.Pagination;
import com.doublekit.core.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* WorkspaceFollowServiceImpl
*/
@Service
public class WorkspaceFollowServiceImpl implements WorkspaceFollowService {

    @Autowired
    WorkspaceFollowDao workspaceFollowDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createWorkspaceFollow(@NotNull @Valid WorkspaceFollow workspaceFollow) {
        WorkspaceFollowEntity workspaceFollowEntity = BeanMapper.map(workspaceFollow, WorkspaceFollowEntity.class);

        return workspaceFollowDao.createWorkspaceFollow(workspaceFollowEntity);
    }

    @Override
    public void updateWorkspaceFollow(@NotNull @Valid WorkspaceFollow workspaceFollow) {
        WorkspaceFollowEntity workspaceFollowEntity = BeanMapper.map(workspaceFollow, WorkspaceFollowEntity.class);

        workspaceFollowDao.updateWorkspaceFollow(workspaceFollowEntity);
    }

    @Override
    public void deleteWorkspaceFollow(@NotNull String id) {
        workspaceFollowDao.deleteWorkspaceFollow(id);
    }

    @Override
    public WorkspaceFollow findOne(String id) {
        WorkspaceFollowEntity workspaceFollowEntity = workspaceFollowDao.findWorkspaceFollow(id);

        WorkspaceFollow workspaceFollow = BeanMapper.map(workspaceFollowEntity, WorkspaceFollow.class);
        return workspaceFollow;
    }

    @Override
    public List<WorkspaceFollow> findList(List<String> idList) {
        List<WorkspaceFollowEntity> workspaceFollowEntityList =  workspaceFollowDao.findWorkspaceFollowList(idList);

        List<WorkspaceFollow> workspaceFollowList =  BeanMapper.mapList(workspaceFollowEntityList,WorkspaceFollow.class);
        return workspaceFollowList;
    }

    @Override
    public WorkspaceFollow findWorkspaceFollow(@NotNull String id) {
        WorkspaceFollow workspaceFollow = findOne(id);

        joinTemplate.joinQuery(workspaceFollow);

        return workspaceFollow;
    }

    @Override
    public List<WorkspaceFollow> findAllWorkspaceFollow() {
        List<WorkspaceFollowEntity> workspaceFollowEntityList =  workspaceFollowDao.findAllWorkspaceFollow();

        List<WorkspaceFollow> workspaceFollowList =  BeanMapper.mapList(workspaceFollowEntityList,WorkspaceFollow.class);

        joinTemplate.joinQuery(workspaceFollowList);

        return workspaceFollowList;
    }

    @Override
    public List<WorkspaceFollow> findWorkspaceFollowList(WorkspaceFollowQuery workspaceFollowQuery) {
        List<WorkspaceFollowEntity> workspaceFollowEntityList = workspaceFollowDao.findWorkspaceFollowList(workspaceFollowQuery);

        List<WorkspaceFollow> workspaceFollowList = BeanMapper.mapList(workspaceFollowEntityList,WorkspaceFollow.class);

        joinTemplate.joinQuery(workspaceFollowList);

        return workspaceFollowList;
    }

    @Override
    public Pagination<WorkspaceFollow> findWorkspaceFollowPage(WorkspaceFollowQuery workspaceFollowQuery) {
        Pagination<WorkspaceFollowEntity>  pagination = workspaceFollowDao.findWorkspaceFollowPage(workspaceFollowQuery);

        List<WorkspaceFollow> workspaceFollowList = BeanMapper.mapList(pagination.getDataList(),WorkspaceFollow.class);

        joinTemplate.joinQuery(workspaceFollowList);

        return PaginationBuilder.build(pagination,workspaceFollowList);
    }
}