package net.tiklab.postin.workspace.service;

import net.tiklab.postin.apidef.apix.model.Apix;
import net.tiklab.postin.apidef.apix.model.ApixQuery;
import net.tiklab.postin.apidef.apix.service.ApixService;
import net.tiklab.postin.category.model.Category;
import net.tiklab.postin.category.model.CategoryQuery;
import net.tiklab.postin.category.service.CategoryService;
import net.tiklab.postin.workspace.dao.WorkspaceRecentDao;
import net.tiklab.postin.workspace.entity.WorkspaceRecentEntity;
import net.tiklab.postin.workspace.model.*;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
* WorkspaceRecentServiceImpl
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
            int categoryCount=0;
            List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setWorkspaceId(workspaceId));
            categoryCount=categoryList.size();
            workspace.setCategoryNum(categoryCount);

            //获取接口总数
            int apiCount=0;
            for(Category category :categoryList){
                List<Apix>  apixList = apixService.findApixList(new ApixQuery().setCategoryId(category.getId()));
                apiCount+=apixList.size();
            }
            workspace.setApiNum(apiCount);

            workspaceList.add(workspace);
        }

        joinTemplate.joinQuery(workspaceList);

        //关注
        WorkspaceFollowQuery workspaceFollowQuery = new WorkspaceFollowQuery();
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(workspaceFollowQuery);

        //设置是否关注
        if(CollectionUtils.isNotEmpty(workspaceList)&&CollectionUtils.isNotEmpty(workspaceFollowList)){
            for(Workspace workspace : workspaceList){
                for(WorkspaceFollow workspaceFollow: workspaceFollowList){
                    if(Objects.equals(workspace.getId(), workspaceFollow.getWorkspace().getId())){
                        workspace.setIsFollow(1);
                    }else {
                        workspace.setIsFollow(0);
                    }
                }
            }
        }else {
            for(Workspace workspace : workspaceList){
                workspace.setIsFollow(0);
            }
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
    public Pagination<WorkspaceRecent> findWorkspaceRecentPage(WorkspaceRecentQuery workspaceRecentQuery) {
        Pagination<WorkspaceRecentEntity>  pagination = workspaceRecentDao.findWorkspaceRecentPage(workspaceRecentQuery);

        List<WorkspaceRecent> workspaceRecentList = BeanMapper.mapList(pagination.getDataList(),WorkspaceRecent.class);

        joinTemplate.joinQuery(workspaceRecentList);

        return PaginationBuilder.build(pagination,workspaceRecentList);
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