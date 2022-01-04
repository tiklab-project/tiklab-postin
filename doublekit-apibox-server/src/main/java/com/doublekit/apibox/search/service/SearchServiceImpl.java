package com.doublekit.apibox.search.service;

import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apidef.service.MethodService;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.apibox.workspace.service.WorkspaceService;
import com.doublekit.dss.client.DssClient;
import com.doublekit.dss.common.model.CountResponse;
import com.doublekit.dss.common.model.PageCondition;
import com.doublekit.dss.common.model.PageResponse;
import com.doublekit.dss.common.model.TopResponse;
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
    DssClient dssClient;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    MethodService methodService;

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
        dssClient.deleteAll(Workspace.class);
        dssClient.deleteAll(MethodEx.class);
    }

    /**
     * 构建索引
     */
    void buildIndex(){
        List<Workspace> workspaceList = workspaceService.findAllWorkspace();
        if(workspaceList != null){
            dssClient.saveBatch(workspaceList);
        }

        List<MethodEx> methodList = methodService.findAllMethod();
        if(methodList != null){
            dssClient.saveBatch(methodList);
        }
    }

    @Override
    public <T> void save(T entity) {
        dssClient.save(entity);
    }

    @Override
    public <T> Map<String, Object> findOne(Class<T> entityClass, String id) {
        return dssClient.findOne(entityClass, id);
    }

    @Override
    public <T> TopResponse<T> searchForTop(Class<T> entityClass, String keyword) {
        return dssClient.searchForTop(entityClass,keyword);
    }

    @Override
    public <T> CountResponse<T> searchForCount(Class<T> entityClass, String keyword) {
        return dssClient.searchForCount(entityClass, keyword);
    }

    @Override
    public <T> PageResponse<T> searchForPage(Class<T> entityClass, String keyword, PageCondition pageCondition) {
        return dssClient.searchForPage(entityClass, keyword, pageCondition);
    }
}