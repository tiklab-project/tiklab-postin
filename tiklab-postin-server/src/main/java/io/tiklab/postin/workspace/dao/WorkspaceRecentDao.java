package io.tiklab.postin.workspace.dao;

import io.tiklab.postin.workspace.entity.WorkspaceRecentEntity;
import io.tiklab.postin.workspace.model.WorkspaceRecentQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 最近访问空间 数据访问
 */
@Repository
public class WorkspaceRecentDao{

    private static Logger logger = LoggerFactory.getLogger(WorkspaceRecentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建最近访问空间
     * @param workspaceRecentEntity
     * @return
     */
    public String createWorkspaceRecent(WorkspaceRecentEntity workspaceRecentEntity) {
        return jpaTemplate.save(workspaceRecentEntity,String.class);
    }

    /**
     * 更新最近访问空间
     * @param workspaceRecentEntity
     */
    public void updateWorkspaceRecent(WorkspaceRecentEntity workspaceRecentEntity){
        jpaTemplate.update(workspaceRecentEntity);
    }

    /**
     * 删除最近访问空间
     * @param id
     */
    public void deleteWorkspaceRecent(String id){
        jpaTemplate.delete(WorkspaceRecentEntity.class,id);
    }

    public void deleteWorkspaceRecent(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查找最近访问空间
     * @param id
     * @return
     */
    public WorkspaceRecentEntity findWorkspaceRecent(String id){
        return jpaTemplate.findOne(WorkspaceRecentEntity.class,id);
    }

    /**
    * 查找所有最近访问空间
    * @return
    */
    public List<WorkspaceRecentEntity> findAllWorkspaceRecent() {
        return jpaTemplate.findAll(WorkspaceRecentEntity.class);
    }

    public List<WorkspaceRecentEntity> findWorkspaceRecentList(List<String> idList) {
        return jpaTemplate.findList(WorkspaceRecentEntity.class,idList);
    }

    /**
     * 根据查询参数查找最近访问空间
     * @param workspaceRecentQuery
     * @return
     */
    public List<WorkspaceRecentEntity> findWorkspaceRecentList(WorkspaceRecentQuery workspaceRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceRecentEntity.class)
                .eq("workspaceId",workspaceRecentQuery.getWorkspaceId())
                .eq("userId", workspaceRecentQuery.getUserId())
                .orders(workspaceRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WorkspaceRecentEntity.class);
    }

    /**
     * 根据查询参数按分页查找最近访问空间
     * @param workspaceRecentQuery
     * @return
     */
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