package com.tiklab.postlink.workspace.dao;

import com.tiklab.postlink.workspace.entity.WorkspaceRecentEntity;
import com.tiklab.postlink.workspace.model.WorkspaceRecentQuery;
import com.tiklab.core.page.Pagination;
import com.tiklab.dal.jpa.JpaTemplate;
import com.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import com.tiklab.dal.jpa.criterial.condition.QueryCondition;
import com.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WorkspaceRecentDao
 */
@Repository
public class WorkspaceRecentDao{

    private static Logger logger = LoggerFactory.getLogger(WorkspaceRecentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param workspaceRecentEntity
     * @return
     */
    public String createWorkspaceRecent(WorkspaceRecentEntity workspaceRecentEntity) {
        return jpaTemplate.save(workspaceRecentEntity,String.class);
    }

    /**
     * 更新
     * @param workspaceRecentEntity
     */
    public void updateWorkspaceRecent(WorkspaceRecentEntity workspaceRecentEntity){
        jpaTemplate.update(workspaceRecentEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteWorkspaceRecent(String id){
        jpaTemplate.delete(WorkspaceRecentEntity.class,id);
    }

    public void deleteWorkspaceRecent(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WorkspaceRecentEntity findWorkspaceRecent(String id){
        return jpaTemplate.findOne(WorkspaceRecentEntity.class,id);
    }

    /**
    * findAllWorkspaceRecent
    * @return
    */
    public List<WorkspaceRecentEntity> findAllWorkspaceRecent() {
        return jpaTemplate.findAll(WorkspaceRecentEntity.class);
    }

    public List<WorkspaceRecentEntity> findWorkspaceRecentList(List<String> idList) {
        return jpaTemplate.findList(WorkspaceRecentEntity.class,idList);
    }

    public List<WorkspaceRecentEntity> findWorkspaceRecentList(WorkspaceRecentQuery workspaceRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceRecentEntity.class)
                .eq("workspaceId",workspaceRecentQuery.getWorkspaceId())
                .eq("userId", workspaceRecentQuery.getUserId())
                .orders(workspaceRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WorkspaceRecentEntity.class);
    }

    public Pagination<WorkspaceRecentEntity> findWorkspaceRecentPage(WorkspaceRecentQuery workspaceRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceRecentEntity.class)
                .eq("workspaceId",workspaceRecentQuery.getWorkspaceId())
                .eq("userId", workspaceRecentQuery.getUserId())
                .pagination(workspaceRecentQuery.getPageParam())
                .orders(workspaceRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,WorkspaceRecentEntity.class);
    }
}