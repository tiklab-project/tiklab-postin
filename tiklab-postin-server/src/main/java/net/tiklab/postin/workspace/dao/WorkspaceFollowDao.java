package net.tiklab.postin.workspace.dao;

import net.tiklab.postin.workspace.entity.WorkspaceFollowEntity;
import net.tiklab.postin.workspace.model.WorkspaceFollowQuery;
import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WorkspaceFollowDao
 */
@Repository
public class WorkspaceFollowDao{

    private static Logger logger = LoggerFactory.getLogger(WorkspaceFollowDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param workspaceFollowEntity
     * @return
     */
    public String createWorkspaceFollow(WorkspaceFollowEntity workspaceFollowEntity) {
        return jpaTemplate.save(workspaceFollowEntity,String.class);
    }

    /**
     * 更新
     * @param workspaceFollowEntity
     */
    public void updateWorkspaceFollow(WorkspaceFollowEntity workspaceFollowEntity){
        jpaTemplate.update(workspaceFollowEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteWorkspaceFollow(String id){
        jpaTemplate.delete(WorkspaceFollowEntity.class,id);
    }

    public void deleteWorkspaceFollow(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WorkspaceFollowEntity findWorkspaceFollow(String id){
        return jpaTemplate.findOne(WorkspaceFollowEntity.class,id);
    }

    /**
    * findAllWorkspaceFollow
    * @return
    */
    public List<WorkspaceFollowEntity> findAllWorkspaceFollow() {
        return jpaTemplate.findAll(WorkspaceFollowEntity.class);
    }

    public List<WorkspaceFollowEntity> findWorkspaceFollowList(List<String> idList) {
        return jpaTemplate.findList(WorkspaceFollowEntity.class,idList);
    }

    public List<WorkspaceFollowEntity> findWorkspaceFollowList(WorkspaceFollowQuery workspaceFollowQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceFollowEntity.class)
                .eq("userId", workspaceFollowQuery.getUserId())
                .orders(workspaceFollowQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WorkspaceFollowEntity.class);
    }

    public Pagination<WorkspaceFollowEntity> findWorkspaceFollowPage(WorkspaceFollowQuery workspaceFollowQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceFollowEntity.class)
                .eq("userId", workspaceFollowQuery.getUserId())
                .pagination(workspaceFollowQuery.getPageParam())
                .orders(workspaceFollowQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,WorkspaceFollowEntity.class);
    }
}