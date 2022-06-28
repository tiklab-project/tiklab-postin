package com.doublekit.apibox.workspace.service;

import com.doublekit.apibox.workspace.dao.WorkspaceRecentDao;
import com.doublekit.apibox.workspace.entity.WorkspaceRecentEntity;
import com.doublekit.apibox.workspace.model.WorkspaceRecent;
import com.doublekit.apibox.workspace.model.WorkspaceRecentQuery;

import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.Pagination;
import com.doublekit.core.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


/**
* WorkspaceRecentServiceImpl
*/
@Service
public class WorkspaceRecentServiceImpl implements WorkspaceRecentService {

    @Autowired
    WorkspaceRecentDao workspaceRecentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createWorkspaceRecent(@NotNull @Valid WorkspaceRecent workspaceRecent) {
        WorkspaceRecentEntity workspaceRecentEntity = BeanMapper.map(workspaceRecent, WorkspaceRecentEntity.class);

        return workspaceRecentDao.createWorkspaceRecent(workspaceRecentEntity);
    }

    @Override
    public void updateWorkspaceRecent(@NotNull @Valid WorkspaceRecent workspaceRecent) {
        WorkspaceRecentEntity workspaceRecentEntity = BeanMapper.map(workspaceRecent, WorkspaceRecentEntity.class);

        workspaceRecentDao.updateWorkspaceRecent(workspaceRecentEntity);
    }

    @Override
    public void deleteWorkspaceRecent(@NotNull String id) {
        workspaceRecentDao.deleteWorkspaceRecent(id);
    }

    @Override
    public WorkspaceRecent findOne(String id) {
        WorkspaceRecentEntity workspaceRecentEntity = workspaceRecentDao.findWorkspaceRecent(id);

        WorkspaceRecent workspaceRecent = BeanMapper.map(workspaceRecentEntity, WorkspaceRecent.class);
        return workspaceRecent;
    }

    @Override
    public List<WorkspaceRecent> findList(List<String> idList) {
        List<WorkspaceRecentEntity> workspaceRecentEntityList =  workspaceRecentDao.findWorkspaceRecentList(idList);

        List<WorkspaceRecent> workspaceRecentList =  BeanMapper.mapList(workspaceRecentEntityList,WorkspaceRecent.class);
        return workspaceRecentList;
    }

    @Override
    public WorkspaceRecent findWorkspaceRecent(@NotNull String id) {
        WorkspaceRecent workspaceRecent = findOne(id);

        joinTemplate.joinQuery(workspaceRecent);

        return workspaceRecent;
    }

    @Override
    public List<WorkspaceRecent> findAllWorkspaceRecent() {
        List<WorkspaceRecentEntity> workspaceRecentEntityList =  workspaceRecentDao.findAllWorkspaceRecent();

        List<WorkspaceRecent> workspaceRecentList =  BeanMapper.mapList(workspaceRecentEntityList,WorkspaceRecent.class);

        joinTemplate.joinQuery(workspaceRecentList);

        return workspaceRecentList;
    }

    @Override
    public List<WorkspaceRecent> findWorkspaceRecentList(WorkspaceRecentQuery workspaceRecentQuery) {
        List<WorkspaceRecentEntity> workspaceRecentEntityList = workspaceRecentDao.findWorkspaceRecentList(workspaceRecentQuery);

        List<WorkspaceRecent> workspaceRecentList = BeanMapper.mapList(workspaceRecentEntityList,WorkspaceRecent.class);

        joinTemplate.joinQuery(workspaceRecentList);

        return workspaceRecentList;
    }

    @Override
    public Pagination<WorkspaceRecent> findWorkspaceRecentPage(WorkspaceRecentQuery workspaceRecentQuery) {
        Pagination<WorkspaceRecentEntity>  pagination = workspaceRecentDao.findWorkspaceRecentPage(workspaceRecentQuery);

        List<WorkspaceRecent> workspaceRecentList = BeanMapper.mapList(pagination.getDataList(),WorkspaceRecent.class);

        joinTemplate.joinQuery(workspaceRecentList);

        return PaginationBuilder.build(pagination,workspaceRecentList);
    }

    @Override
    public void workspaceRecent(WorkspaceRecent workspaceRecent) {

        WorkspaceRecentQuery workspaceRecentQuery = new WorkspaceRecentQuery();
        workspaceRecentQuery.setUserId(workspaceRecent.getUserId());
        workspaceRecentQuery.setWorkspaceId(workspaceRecent.getWorkspace().getId());

        List<WorkspaceRecent> workspaceRecentList = findWorkspaceRecentList(workspaceRecentQuery);

        if(!workspaceRecentList.isEmpty()&&workspaceRecentList.size()>0){
            WorkspaceRecent Recent = workspaceRecentList.get(0);

            workspaceRecent.setId(Recent.getId());
            workspaceRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            updateWorkspaceRecent(workspaceRecent);
        }else {
            workspaceRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            createWorkspaceRecent(workspaceRecent);
        }
    }


}