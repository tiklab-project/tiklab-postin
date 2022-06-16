package com.doublekit.apibox.workspace.service;

import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.apibox.apidef.apix.service.ApixService;
import com.doublekit.apibox.apitest.http.httpcase.model.HttpTestcase;
import com.doublekit.apibox.apitest.http.httpcase.model.HttpTestcaseQuery;
import com.doublekit.apibox.apitest.http.httpcase.service.HttpTestcaseService;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.category.model.CategoryQuery;
import com.doublekit.apibox.category.service.CategoryService;
import com.doublekit.apibox.sysmgr.datastructure.model.DataStructure;
import com.doublekit.apibox.sysmgr.datastructure.model.DataStructureQuery;
import com.doublekit.apibox.sysmgr.datastructure.service.DataStructureService;
import com.doublekit.apibox.workspace.dao.WorkspaceDao;
import com.doublekit.apibox.workspace.entity.WorkspaceEntity;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.apibox.workspace.model.WorkspaceQuery;
import com.doublekit.apibox.workspace.model.WorkspaceTotal;
import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.Pagination;
import com.doublekit.core.page.PaginationBuilder;
import com.doublekit.dis.client.DisClient;
import com.doublekit.join.JoinTemplate;
import com.doublekit.privilege.role.service.DmRoleService;
import com.doublekit.user.user.model.DmUser;
import com.doublekit.user.user.model.DmUserQuery;
import com.doublekit.user.user.model.User;
import com.doublekit.user.user.service.DmUserService;
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
    DisClient disClient;

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


        //删除最近浏览里的表


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