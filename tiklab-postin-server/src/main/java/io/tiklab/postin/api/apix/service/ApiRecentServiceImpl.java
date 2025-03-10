package io.tiklab.postin.api.apix.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.service.NodeService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.postin.api.apix.dao.ApiRecentDao;
import io.tiklab.postin.api.apix.entity.ApiRecentEntity;
import io.tiklab.postin.api.apix.model.ApiRecent;
import io.tiklab.postin.api.apix.model.ApiRecentQuery;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.service.WorkspaceService;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


/**
* 最近访问接口 服务
*/
@Service
public class ApiRecentServiceImpl implements ApiRecentService {

    @Autowired
    ApiRecentDao apiRecentDao;

    @Autowired
    ApixService apixService;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    NodeService nodeService;


    @Override
    public String createApiRecent(@NotNull @Valid ApiRecent apiRecent) {
        User user = new User();
        user.setId(LoginContext.getLoginId());
        apiRecent.setUser(user);
        ApiRecentEntity apiRecentEntity = BeanMapper.map(apiRecent, ApiRecentEntity.class);
        apiRecentEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        apiRecentEntity.setUserId(LoginContext.getLoginId());
        return apiRecentDao.createApiRecent(apiRecentEntity);
    }

    @Override
    public void updateApiRecent(@NotNull @Valid ApiRecent apiRecent) {
        ApiRecentEntity apiRecentEntity = BeanMapper.map(apiRecent, ApiRecentEntity.class);

        apiRecentDao.updateApiRecent(apiRecentEntity);
    }

    @Override
    public void deleteApiRecent(@NotNull String id) {
        apiRecentDao.deleteApiRecent(id);
    }

    @Override
    public void deleteApiRecentByApiId(String apiId) {
        ApiRecentQuery apiRecentQuery = new ApiRecentQuery();
        apiRecentQuery.setApixId(apiId);
        List<ApiRecent> apiRecentList = findApiRecentList(apiRecentQuery);
        if(apiRecentList != null && apiRecentList.size() > 0){
            for(ApiRecent apiRecent:apiRecentList){
                deleteApiRecent(apiRecent.getId());
            }
        }
    }

    @Override
    public ApiRecent findOne(String id) {
        ApiRecentEntity apiRecentEntity = apiRecentDao.findApiRecent(id);

        return BeanMapper.map(apiRecentEntity, ApiRecent.class);
    }

    @Override
    public List<ApiRecent> findList(List<String> idList) {
        List<ApiRecentEntity> apiRecentEntityList =  apiRecentDao.findApiRecentList(idList);

        return BeanMapper.mapList(apiRecentEntityList,ApiRecent.class);
    }

    @Override
    public ApiRecent findApiRecent(@NotNull String id) {
        ApiRecent apiRecent = findOne(id);

        joinTemplate.joinQuery(apiRecent);

        return apiRecent;
    }

    @Override
    public List<ApiRecent> findAllApiRecent() {
        List<ApiRecentEntity> apiRecentEntityList =  apiRecentDao.findAllApiRecent();
        List<ApiRecent> apiRecentList =  BeanMapper.mapList(apiRecentEntityList,ApiRecent.class);

        joinTemplate.joinQuery(apiRecentList);

        return apiRecentList;
    }

    @Override
    public List<ApiRecent> findApiRecentList(ApiRecentQuery apiRecentQuery) {

        List<ApiRecentEntity> apiRecentEntityList = apiRecentDao.findApiRecentList(apiRecentQuery);
        List<ApiRecent> apiRecentList = BeanMapper.mapList(apiRecentEntityList,ApiRecent.class);

        //第三层获取不到值，手动设置值
        if(apiRecentList!=null&&apiRecentList.size()>0){
            for (int i = 0; i < Math.min(apiRecentList.size(), 10); i++) {
                ApiRecent apiRecent = apiRecentList.get(i);

                Apix apix = apixService.findApix(apiRecent.getApix().getId());
                Node node = nodeService.findNode(apix.getId());
                apix.setNode(node);

                apiRecent.setApix(apix);

                if(apiRecent.getWorkspace()==null){
                    continue;
                }

                Workspace workspace = workspaceService.findWorkspace(apiRecent.getWorkspace().getId());
                apiRecent.setWorkspace(workspace);
            }
        }


        return apiRecentList;
    }



    @Override
    public Pagination<ApiRecent> findApiRecentPage(ApiRecentQuery apiRecentQuery) {
        apiRecentQuery.setUserId(LoginContext.getLoginId());
        Pagination<ApiRecentEntity>  pagination = apiRecentDao.findApiRecentPage(apiRecentQuery);
        List<ApiRecent> apiRecentList = BeanMapper.mapList(pagination.getDataList(),ApiRecent.class);
        joinTemplate.joinQuery(apiRecentList);

        for(ApiRecent apiRecent:apiRecentList){

            Apix apix = apiRecent.getApix();
            Node node = nodeService.findNode(apix.getId());
            apix.setNode(node);
            apiRecent.setApix(apix);
        }


        return PaginationBuilder.build(pagination,apiRecentList);
    }

    @Override
    public void apiRecent(ApiRecent apiRecent) {

        ApiRecentQuery apiRecentQuery = new ApiRecentQuery();
        apiRecentQuery.setUserId(LoginContext.getLoginId());
        apiRecentQuery.setApixId(apiRecent.getApix().getId());

        //查询相应的最近访问
        List<ApiRecentEntity> apiRecentEntityList = apiRecentDao.findApiRecentList(apiRecentQuery);
        List<ApiRecent> apiRecentList = BeanMapper.mapList(apiRecentEntityList,ApiRecent.class);

        //更新最近一条
        if(apiRecentList!=null&&apiRecentList.size()>0){
            ApiRecent recent = apiRecentList.get(0);

            apiRecent.setId(recent.getId());
            apiRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            updateApiRecent(apiRecent);
        }else {
            apiRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            createApiRecent(apiRecent);
        }
    }



}