package io.thoughtware.postin.workspace.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.postin.workspace.dao.WorkspaceFollowDao;
import io.thoughtware.postin.workspace.entity.WorkspaceFollowEntity;
import io.thoughtware.postin.workspace.model.Workspace;
import io.thoughtware.postin.workspace.model.WorkspaceFollow;
import io.thoughtware.postin.workspace.model.WorkspaceFollowQuery;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
* 空间关注 服务
*/
@Service
public class WorkspaceFollowServiceImpl implements WorkspaceFollowService {
    private static Logger logger = LoggerFactory.getLogger(WorkspaceFollowServiceImpl.class);


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
        WorkspaceFollowQuery workspaceFollowQuery = new WorkspaceFollowQuery();
        List<WorkspaceFollow> workspaceFollowList = findWorkspaceFollowList(workspaceFollowQuery);

        String followId = null;
        for(WorkspaceFollow workspaceFollow: workspaceFollowList){
            if(Objects.equals(workspaceFollow.getWorkspace().getId(),id)){
                followId=workspaceFollow.getId();
            }
        }

        workspaceFollowDao.deleteWorkspaceFollow(followId);
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

        //设置是否关注
        if(CollectionUtils.isNotEmpty(workspaceFollowList)){
            for(WorkspaceFollow workspaceFollow: workspaceFollowList){
                workspaceFollow.getWorkspace().setIsFollow(1);
            }
        }

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