package io.thoughtware.postin.workspace.service;

import io.thoughtware.postin.api.apix.model.Apix;
import io.thoughtware.postin.api.apix.model.ApixQuery;
import io.thoughtware.postin.api.apix.service.ApixService;
import io.thoughtware.postin.category.model.Category;
import io.thoughtware.postin.category.model.CategoryQuery;
import io.thoughtware.postin.category.service.CategoryService;
import io.thoughtware.postin.workspace.dao.WorkspaceRecentDao;
import io.thoughtware.postin.workspace.entity.WorkspaceRecentEntity;
import io.thoughtware.postin.workspace.model.*;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
* 最近访问空间 服务
*/
@Service
public class WorkspaceRecentServiceImpl implements WorkspaceRecentService {

    @Autowired
    WorkspaceRecentDao workspaceRecentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WorkspaceFollowService workspaceFollowService;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApixService apixService;

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

        return BeanMapper.map(workspaceRecentEntity, WorkspaceRecent.class);
    }

    @Override
    public List<WorkspaceRecent> findList(List<String> idList) {
        List<WorkspaceRecentEntity> workspaceRecentEntityList =  workspaceRecentDao.findWorkspaceRecentList(idList);

        return BeanMapper.mapList(workspaceRecentEntityList,WorkspaceRecent.class);
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
    public List<Workspace> findWorkspaceRecentList(WorkspaceRecentQuery workspaceRecentQuery) {
        List<WorkspaceRecentEntity> workspaceRecentEntityList = workspaceRecentDao.findWorkspaceRecentList(workspaceRecentQuery);
        List<WorkspaceRecent> workspaceRecentList = BeanMapper.mapList(workspaceRecentEntityList,WorkspaceRecent.class);

        //通过最近访问里面的空间id，查询到空间，添加到list里
        ArrayList<Workspace> workspaceList = new ArrayList<>();
        for(WorkspaceRecent workspaceRecent:workspaceRecentList){
            String workspaceId = workspaceRecent.getWorkspace().getId();
            Workspace workspace = workspaceService.findWorkspace(workspaceId);

            //获取分组的总数
            int categoryNum = categoryService.findCategoryNum(workspaceId);
            workspace.setCategoryNum(categoryNum);

            //获取接口总数
            int apiCount= apixService.findApixNum(workspaceId);
            workspace.setApiNum(apiCount);

            workspaceList.add(workspace);
        }

        return workspaceList;
    }

    @Override
    public List<WorkspaceRecent> findRecentList(WorkspaceRecentQuery workspaceRecentQuery) {
        List<WorkspaceRecentEntity> workspaceRecentEntityList = workspaceRecentDao.findWorkspaceRecentList(workspaceRecentQuery);
        List<WorkspaceRecent> workspaceRecentList = BeanMapper.mapList(workspaceRecentEntityList,WorkspaceRecent.class);
        joinTemplate.joinQuery(workspaceRecentList);

        return workspaceRecentList;
    }

    @Override
    public Pagination<Workspace> findWorkspaceRecentPage(WorkspaceRecentQuery workspaceRecentQuery) {
        Pagination<WorkspaceRecentEntity>  pagination = workspaceRecentDao.findWorkspaceRecentPage(workspaceRecentQuery);

        List<WorkspaceRecent> workspaceRecentList = BeanMapper.mapList(pagination.getDataList(),WorkspaceRecent.class);
        joinTemplate.joinQuery(workspaceRecentList);

        ArrayList<Workspace> workspaceList = new ArrayList<>();
        for(WorkspaceRecent workspaceRecent:workspaceRecentList){
            Workspace workspace = workspaceRecent.getWorkspace();

            //获取分组的总数
            int categoryNum = categoryService.findCategoryNum(workspace.getId());
            workspace.setCategoryNum(categoryNum);

            //获取接口总数
            int apiCount= apixService.findApixNum(workspace.getId());
            workspace.setApiNum(apiCount);
            workspaceList.add(workspace);
        }

        return PaginationBuilder.build(pagination,workspaceList);
    }

    @Override
    public void workspaceRecent(WorkspaceRecent workspaceRecent) {

        WorkspaceRecentQuery workspaceRecentQuery = new WorkspaceRecentQuery();
        workspaceRecentQuery.setUserId(workspaceRecent.getUser().getId());
        workspaceRecentQuery.setWorkspaceId(workspaceRecent.getWorkspace().getId());

        //查询相应的最近访问
        List<WorkspaceRecentEntity> workspaceRecentEntityList = workspaceRecentDao.findWorkspaceRecentList(workspaceRecentQuery);
        List<WorkspaceRecent> workspaceRecentList = BeanMapper.mapList(workspaceRecentEntityList,WorkspaceRecent.class);

        //更新最近一条
        if(!workspaceRecentList.isEmpty()){
            WorkspaceRecent recent = workspaceRecentList.get(0);

            workspaceRecent.setId(recent.getId());
            workspaceRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            updateWorkspaceRecent(workspaceRecent);
        }else {
            workspaceRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            createWorkspaceRecent(workspaceRecent);
        }
    }


}