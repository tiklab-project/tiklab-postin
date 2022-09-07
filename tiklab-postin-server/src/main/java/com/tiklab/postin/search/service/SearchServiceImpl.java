package com.tiklab.postin.search.service;

import com.tiklab.postin.apidef.http.model.HttpApi;
import com.tiklab.postin.apidef.http.service.HttpApiService;
import com.tiklab.postin.workspace.model.Workspace;
import com.tiklab.postin.workspace.service.WorkspaceService;
import com.tiklab.dss.client.DssClient;
import com.tiklab.dss.common.model.CountResponse;
import com.tiklab.dss.common.model.PageCondition;
import com.tiklab.dss.common.model.PageResponse;
import com.tiklab.dss.common.model.TopResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* 用户服务业务处理
*/
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    DssClient disClient;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    HttpApiService httpApiService;

    @Override
    public void rebuild() {
        //删除索引
        deleteIndex();

        //构建索引
        buildIndex();
    }

    /**
     * 删除索引
     */
    void deleteIndex(){
        disClient.deleteAll(Workspace.class);
        disClient.deleteAll(HttpApi.class);
    }

    /**
     * 构建索引
     */
    void buildIndex(){
        List<Workspace> workspaceList = workspaceService.findAllWorkspace();
        if(workspaceList != null){
            disClient.saveBatch(workspaceList);
        }

        List<HttpApi> methodList = httpApiService.findAllHttpApi();
        if(methodList != null){
            disClient.saveBatch(methodList);
        }
    }

    @Override
    public <T> void save(T entity) {
        disClient.save(entity);
    }

    @Override
    public <T> Map<String, Object> findOne(Class<T> entityClass, String id) {
        return disClient.findOne(entityClass, id);
    }

    @Override
    public <T> TopResponse<T> searchForTop(Class<T> entityClass, String keyword) {
        return disClient.searchForTop(entityClass,keyword);
    }

    @Override
    public <T> CountResponse<T> searchForCount(Class<T> entityClass, String keyword) {
        return disClient.searchForCount(entityClass, keyword);
    }

    @Override
    public <T> PageResponse<T> searchForPage(Class<T> entityClass, String keyword, PageCondition pageCondition) {
        return disClient.searchForPage(entityClass, keyword, pageCondition);
    }
}