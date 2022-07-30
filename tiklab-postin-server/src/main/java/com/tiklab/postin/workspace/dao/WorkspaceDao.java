package com.tiklab.postin.workspace.dao;

import com.tiklab.postin.workspace.entity.WorkspaceEntity;
import com.tiklab.postin.workspace.model.WorkspaceQuery;
import com.tiklab.core.page.Pagination;
import com.tiklab.dal.jpa.JpaTemplate;
import com.tiklab.dal.jpa.criterial.condition.QueryCondition;
import com.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceEntity.class)
                .eq("userId", workspaceQuery.getUserId())
                .like("workspaceName", workspaceQuery.getWorkspaceName())
                .orders(workspaceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkspaceEntity.class);
    }

    public Pagination<WorkspaceEntity> findWorkspacePage(WorkspaceQuery workspaceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceEntity.class)
                .eq("userId", workspaceQuery.getUserId())
                .like("workspaceName", workspaceQuery.getWorkspaceName())
                .pagination(workspaceQuery.getPageParam())
                .orders(workspaceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkspaceEntity.class);
    }

    public List<WorkspaceEntity> findWorkspaceJoinList(WorkspaceQuery workspaceQuery) {
        String userId = workspaceQuery.getUserId();
        String sql = "select postin_workspace.workspace_name,postin_workspace.id,postin_workspace.description,postin_workspace.user_id,orc_dm_user.user_id  " +
                "from postin_workspace,orc_dm_user " +
                "where  postin_workspace.id = orc_dm_user.domain_id and orc_dm_user.user_id= ? ";

        List<WorkspaceEntity> workspaceList = jpaTemplate.getJdbcTemplate().query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(WorkspaceEntity.class));
        return workspaceList;
    }

    

}