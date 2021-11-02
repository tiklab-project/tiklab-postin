package com.doublekit.apibox.workspace.dao;

import com.doublekit.apibox.workspace.entity.WorkspaceEntity;
import com.doublekit.apibox.workspace.model.WorkspaceQuery;
import com.doublekit.common.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class WorkspaceDao{

    private static Logger logger = LoggerFactory.getLogger(WorkspaceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param workspaceEntity
     * @return
     */
    public String createWorkspace(WorkspaceEntity workspaceEntity) {
        return jpaTemplate.save(workspaceEntity,String.class);
    }

    /**
     * 更新用户
     * @param workspaceEntity
     */
    public void updateWorkspace(WorkspaceEntity workspaceEntity){
        jpaTemplate.update(workspaceEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteWorkspace(String id){
        jpaTemplate.delete(WorkspaceEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public WorkspaceEntity findWorkspace(String id){
        return jpaTemplate.findOne(WorkspaceEntity.class,id);
    }

    /**
    * findAllWorkspace
    * @return
    */
    public List<WorkspaceEntity> findAllWorkspace() {
        return jpaTemplate.findAll(WorkspaceEntity.class);
    }

    public List<WorkspaceEntity> findWorkspaceList(List<String> idList) {
        return jpaTemplate.findList(WorkspaceEntity.class,idList);
    }

    public List<WorkspaceEntity> findWorkspaceList(WorkspaceQuery workspaceQuery) {
        return jpaTemplate.findList(WorkspaceEntity.class,workspaceQuery);
    }

    public Pagination<WorkspaceEntity> findWorkspacePage(WorkspaceQuery workspaceQuery) {
        return jpaTemplate.findPage(WorkspaceEntity.class,workspaceQuery);
    }
}