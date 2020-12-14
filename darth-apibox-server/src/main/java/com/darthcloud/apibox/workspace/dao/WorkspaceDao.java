package com.darthcloud.apibox.workspace.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.workspace.entity.WorkspacePo;
import com.darthcloud.apibox.workspace.model.WorkspaceQuery;
import com.darthcloud.dal.jpa.JpaTemplate;
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
     * @param workspacePo
     * @return
     */
    public String createWorkspace(WorkspacePo workspacePo) {
        return jpaTemplate.save(workspacePo,String.class);
    }

    /**
     * 更新用户
     * @param workspacePo
     */
    public void updateWorkspace(WorkspacePo workspacePo){
        jpaTemplate.update(workspacePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteWorkspace(String id){
        jpaTemplate.delete(WorkspacePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public WorkspacePo findWorkspace(String id){
        return jpaTemplate.findOne(WorkspacePo.class,id);
    }

    /**
    * findAllWorkspace
    * @return
    */
    public List<WorkspacePo> findAllWorkspace() {
        return jpaTemplate.findAll(WorkspacePo.class);
    }

    public List<WorkspacePo> findWorkspaceList(WorkspaceQuery workspaceQuery) {
        return jpaTemplate.criteriaQuery(WorkspacePo.class,workspaceQuery);
    }

    public Pagination<List<WorkspacePo>> findWorkspacePage(WorkspaceQuery workspaceQuery) { 
        return jpaTemplate.criteriaPageQuery(WorkspacePo.class,workspaceQuery);
    }
}