package io.tiklab.postin.home.search.service;

//import io.tiklab.dss.client.DssClient;
import io.tiklab.dss.common.document.model.CountResponse;
import io.tiklab.dss.common.document.model.PageCondition;
import io.tiklab.dss.common.document.model.PageResponse;
import io.tiklab.dss.common.document.model.TopResponse;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.home.search.SearchService;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* 搜索 服务
*/
@Service
public class SearchServiceImpl implements SearchService {

//    @Autowired
//    DssClient disClient;

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
//        disClient.deleteAll(Workspace.class);
//        disClient.deleteAll(HttpApi.class);
    }

    /**
     * 构建索引
     */
    void buildIndex(){
        List<Workspace> workspaceList = workspaceService.findAllWorkspace();
        if(workspaceList != null){
//            disClient.saveBatch(workspaceList);
        }

        List<HttpApi> methodList = httpApiService.findAllHttpApi();
        if(methodList != null){
//            disClient.saveBatch(methodList);
        }
    }

    @Override
    public <T> void save(T entity) {
//        disClient.save(entity);
    }

    @Override
    public <T> Map<String, Object> findOne(Class<T> entityClass, String id) {
//        return disClient.findOne(entityClass, id);
        return null;
    }

    @Override
    public <T> TopResponse<T> searchForTop(Class<T> entityClass, String keyword) {
//        return disClient.searchForTop(entityClass,keyword);
        return null;
    }

    @Override
    public <T> CountResponse<T> searchForCount(Class<T> entityClass, String keyword) {
//        return disClient.searchForCount(entityClass, keyword);
        return null;
    }

    @Override
    public <T> PageResponse<T> searchForPage(Class<T> entityClass, String keyword, PageCondition pageCondition) {
//        return disClient.searchForPage(entityClass, keyword, pageCondition);
        return null;
    }
}