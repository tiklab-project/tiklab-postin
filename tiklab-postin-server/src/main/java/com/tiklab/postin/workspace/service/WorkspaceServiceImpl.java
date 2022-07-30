package com.tiklab.postin.workspace.service;

import com.tiklab.postin.apidef.apix.model.Apix;
import com.tiklab.postin.apidef.apix.model.ApixQuery;
import com.tiklab.postin.apidef.apix.service.ApixService;
import com.tiklab.postin.apitest.http.httpcase.model.HttpTestcase;
import com.tiklab.postin.apitest.http.httpcase.model.HttpTestcaseQuery;
import com.tiklab.postin.apitest.http.httpcase.service.HttpTestcaseService;
import com.tiklab.postin.category.model.Category;
import com.tiklab.postin.category.model.CategoryQuery;
import com.tiklab.postin.category.service.CategoryService;
import com.tiklab.postin.sysmgr.datastructure.model.DataStructure;
import com.tiklab.postin.sysmgr.datastructure.model.DataStructureQuery;
import com.tiklab.postin.sysmgr.datastructure.service.DataStructureService;
import com.tiklab.postin.workspace.dao.WorkspaceDao;
import com.tiklab.postin.workspace.entity.WorkspaceEntity;
import com.tiklab.postin.workspace.model.*;
import com.tiklab.beans.BeanMapper;
import com.tiklab.core.page.Pagination;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.dss.client.DssClient;
import com.tiklab.join.JoinTemplate;
import com.tiklab.privilege.role.service.DmRoleService;
import com.tiklab.user.user.model.DmUser;
import com.tiklab.user.user.model.DmUserQuery;
import com.tiklab.user.user.model.User;
import com.tiklab.user.user.service.DmUserService;
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
    WorkspaceRecentService workspaceRecentService;

    @Autowired
    WorkspaceFollowService workspaceFollowService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApixService apixService;

    @Autowired
    HttpTestcaseService httpTestcaseService;

    @Autowired
    DataStructureService dataStructureService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DssClient disClient;

    @Override
    public String createWorkspace(@NotNull @Valid Workspace workspace) {
        //创建项目
        WorkspaceEntity workspaceEntity = BeanMapper.map(workspace, WorkspaceEntity.class);

        String projectId = workspaceDao.createWorkspace(workspaceEntity);

        String userId =workspace.getUser().getId();
        DmUser dmUser = new DmUser();
        dmUser.setDomainId(projectId);
        dmUser.setUser(new User().setId(userId));
        dmUserService.createDmUser(dmUser);

        //初始化项目权限
        dmRoleService.initDmRoles(projectId,userId);

        //初始化默认分组
        Category category = new Category();
        Workspace ws = new Workspace();
        ws.setId(projectId);
        category.setWorkspace(ws);
        category.setName("默认分组");
        categoryService.createCategory(category);

        //添加索引
        Workspace entity = findWorkspace(projectId);
        disClient.save(entity);

        return projectId;
    }

    @Override
    public void updateWorkspace(@NotNull @Valid Workspace workspace) {
        //更新数据
        WorkspaceEntity workspaceEntity = BeanMapper.map(workspace, WorkspaceEntity.class);

        workspaceDao.updateWorkspace(workspaceEntity);

        //更新索引
        Workspace entity = findWorkspace(workspace.getId());
        disClient.update(entity);
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
        disClient.delete(Workspace.class,id);
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

        joinTemplate.joinQuery(workspaceList);
        return PaginationBuilder.build(pagination,workspaceList);
    }


    @Override
    public List<Workspace> findWorkspaceJoinList(WorkspaceQuery workspaceQuery) {
        List<WorkspaceEntity> workspaceEntityList = workspaceDao.findWorkspaceJoinList(workspaceQuery);

        return BeanMapper.mapList(workspaceEntityList,Workspace.class);
    }

    @Override
    public WorkspaceHomeTotal findWorkspaceHomeTotal(String userId) {
        WorkspaceHomeTotal workspaceHomeTotal = new WorkspaceHomeTotal();

        //所有空间总和
        int all = 0;
        List<Workspace> allWorkspace = findAllWorkspace();
        if(CollectionUtils.isNotEmpty(allWorkspace)){
            all = allWorkspace.size();
        }

        //个人创建的空间 总和
        int create = 0;
        List<Workspace> workspaceList = findWorkspaceList(new WorkspaceQuery().setUserId(userId));
        if(CollectionUtils.isNotEmpty(workspaceList)){
            create=workspaceList.size();
        }

        //个人加入的空间 总和
        int join = 0;
        List<Workspace> workspaceJoinList = findWorkspaceJoinList(new WorkspaceQuery().setUserId(userId));
        if(CollectionUtils.isNotEmpty(workspaceJoinList)){
            join = workspaceJoinList.size();
        }

        //个人关注的空间 总和
        int follow = 0;
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(new WorkspaceFollowQuery().setUserId(userId));
        if(CollectionUtils.isNotEmpty(workspaceFollowList)){
            follow = workspaceFollowList.size();
        }


        workspaceHomeTotal.setAllTotal(all);
        workspaceHomeTotal.setCreateTotal(create);
        workspaceHomeTotal.setJoinTotal(join);
        workspaceHomeTotal.setFollowTotal(follow);


        return workspaceHomeTotal;
    }

    @Override
    public WorkspaceTotal findWorkspaceTotal(String id) {
        WorkspaceTotal workspaceTotal = new WorkspaceTotal();

        //获取分组的总和
        List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setWorkspaceId(id));
        workspaceTotal.setCategoryTotal(categoryList.size());

        //获取接口总和，case总和
        int apiCount=0;
        int caseCount = 0;
        for(Category category :categoryList){
            List<Apix>  apixList = apixService.findApixList(new ApixQuery().setCategoryId(category.getId()));
            apiCount+=apixList.size();

            int apiCaseCount=0;
            for (Apix apix: apixList){
                List<HttpTestcase> testcaseList = httpTestcaseService.findTestcaseList(new HttpTestcaseQuery().setHttpId(apix.getId()));
                apiCaseCount+=testcaseList.size();
            }

            caseCount+=apiCaseCount;
        }

        List<DataStructure> dataStructureList = dataStructureService.findDataStructureList(new DataStructureQuery().setWorkspaceId(id));

        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(id);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

        workspaceTotal.setModelTotal(dataStructureList.size());
        workspaceTotal.setApiTotal(apiCount);
        workspaceTotal.setCaseTotal(caseCount);
        workspaceTotal.setMemberTotal(dmUserList.size());



        return workspaceTotal;
    }

}