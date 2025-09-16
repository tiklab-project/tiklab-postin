package io.tiklab.postin.workspace.dao;

import io.tiklab.postin.workspace.entity.WorkspaceEntity;
import io.tiklab.postin.workspace.model.WorkspaceQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 空间 数据访问
 */
@Repository
public class WorkspaceDao{

    private static Logger logger = LoggerFactory.getLogger(WorkspaceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建空间
     * @param workspaceEntity
     * @return
     */
    public String createWorkspace(WorkspaceEntity workspaceEntity) {
        return jpaTemplate.save(workspaceEntity,String.class);
    }

    /**
     * 更新空间
     * @param workspaceEntity
     */
    public void updateWorkspace(WorkspaceEntity workspaceEntity){
        jpaTemplate.update(workspaceEntity);
    }

    /**
     * 删除空间
     * @param id
     */
    public void deleteWorkspace(String id){
        jpaTemplate.delete(WorkspaceEntity.class,id);
    }

    /**
     * 查找空间
     * @param id
     * @return
     */
    public WorkspaceEntity findWorkspace(String id){
        return jpaTemplate.findOne(WorkspaceEntity.class,id);
    }

    /**
    * 查找所有空间
    * @return
    */
    public List<WorkspaceEntity> findAllWorkspace() {
        return jpaTemplate.findAll(WorkspaceEntity.class);
    }

    public List<WorkspaceEntity> findWorkspaceList(List<String> idList) {
        return jpaTemplate.findList(WorkspaceEntity.class,idList);
    }

    /**
     * 根据查询对象查询空间列表
     * @param workspaceQuery
     * @return
     */
    public List<WorkspaceEntity> findWorkspaceList(WorkspaceQuery workspaceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceEntity.class)
                .eq("userId", workspaceQuery.getUserId())
                .eq("visibility", workspaceQuery.getVisibility())
                .like("workspaceName", workspaceQuery.getWorkspaceName())
                .in("id", workspaceQuery.getIds())
                .orders(workspaceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkspaceEntity.class);
    }

    /**
     * 根据查询对象按分页查询空间
     * @param workspaceQuery
     * @return
     */
    public Pagination<WorkspaceEntity> findWorkspacePage(WorkspaceQuery workspaceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceEntity.class)
                .eq("userId", workspaceQuery.getUserId())
                .eq("visibility", workspaceQuery.getVisibility())
                .like("workspaceName", workspaceQuery.getWorkspaceName())
                .in("id", workspaceQuery.getIds())
                .pagination(workspaceQuery.getPageParam())
                .orders(workspaceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkspaceEntity.class);
    }



}