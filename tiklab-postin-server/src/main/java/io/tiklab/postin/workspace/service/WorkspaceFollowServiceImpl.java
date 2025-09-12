package io.tiklab.postin.workspace.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.autotest.test.service.TestCaseService;
import io.tiklab.postin.workspace.dao.WorkspaceFollowDao;
import io.tiklab.postin.workspace.entity.WorkspaceFollowEntity;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.model.WorkspaceFollow;
import io.tiklab.postin.workspace.model.WorkspaceFollowQuery;

import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
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

    @Autowired
    ApixService apixService;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    WorkspaceService workspaceService;

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
        workspaceFollowQuery.setUserId(LoginContext.getLoginId());
        List<WorkspaceFollowEntity> workspaceFollowEntityList = workspaceFollowDao.findWorkspaceFollowList(workspaceFollowQuery);

        List<WorkspaceFollow> workspaceFollowList = BeanMapper.mapList(workspaceFollowEntityList,WorkspaceFollow.class);

        joinTemplate.joinQuery(workspaceFollowList, new String[]{
                "workspace",
                "user"
        });

        //设置是否关注
        if(CollectionUtils.isNotEmpty(workspaceFollowList)){
            for(WorkspaceFollow workspaceFollow: workspaceFollowList){
                Workspace workspace = workspaceFollow.getWorkspace();
                workspace.setIsFollow(1);

                int apixNum = apixService.findApixNum(workspace.getId());
                workspace.setApiNum(apixNum);

                int testCaseNumByWorkspaceId = testCaseService.findTestCaseNumByWorkspaceId(workspace.getId());
                workspace.setCaseNum(testCaseNumByWorkspaceId);
            }
        }

        return workspaceFollowList;
    }

    @Override
    public Pagination<WorkspaceFollow> findWorkspaceFollowPage(WorkspaceFollowQuery workspaceFollowQuery) {
        Pagination<WorkspaceFollowEntity>  pagination = workspaceFollowDao.findWorkspaceFollowPage(workspaceFollowQuery);

        List<WorkspaceFollow> workspaceFollowList = BeanMapper.mapList(pagination.getDataList(),WorkspaceFollow.class);


        for(WorkspaceFollow workspaceFollow: workspaceFollowList){
            String id = workspaceFollow.getWorkspace().getId();
            Workspace workspace = workspaceService.findWorkspace(id);
            workspace.setIsFollow(1);

            int apixNum = apixService.findApixNum(workspace.getId());
            workspace.setApiNum(apixNum);

            int testCaseNumByWorkspaceId = testCaseService.findTestCaseNumByWorkspaceId(workspace.getId());
            workspace.setCaseNum(testCaseNumByWorkspaceId);

            workspaceFollow.setWorkspace(workspace);
        }


        return PaginationBuilder.build(pagination,workspaceFollowList);
    }
}