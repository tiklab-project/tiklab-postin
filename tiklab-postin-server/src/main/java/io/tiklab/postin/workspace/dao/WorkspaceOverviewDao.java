package io.tiklab.postin.workspace.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.workspace.entity.WorkspaceFollowEntity;
import io.tiklab.postin.workspace.model.WorkspaceFollowQuery;
import io.tiklab.postin.workspace.model.WorkspaceTotal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 空间关注 数据访问
 */
@Repository
public class WorkspaceOverviewDao {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceOverviewDao.class);

    @Autowired
    JpaTemplate jpaTemplate;


    /**
     * 根据查询参数按分页查找空间关注
     * @param workspaceId
     * @return
     */
    public WorkspaceTotal findWorkspaceOverview(String workspaceId) {

        String categorySql = "Select count(1) as total from postin_category where workspace_id = '" + workspaceId+ "'";
        Integer categoryTotal = jpaTemplate.getJdbcTemplate().queryForObject(categorySql, new Object[]{}, Integer.class);

        String apxSql = "Select count(1) as total from postin_apix where workspace_id = '" + workspaceId+ "'";
        Integer apiTotal = jpaTemplate.getJdbcTemplate().queryForObject(apxSql, new Object[]{}, Integer.class);

        String modelSql = "Select count(1) as total from postin_model where workspace_id = '" + workspaceId+ "'";
        Integer modelTotal = jpaTemplate.getJdbcTemplate().queryForObject(modelSql, new Object[]{}, Integer.class);

        WorkspaceTotal workspaceTotal = new WorkspaceTotal();
        workspaceTotal.setApiTotal(apiTotal);
        workspaceTotal.setCategoryTotal(categoryTotal);
        workspaceTotal.setModelTotal(modelTotal);

        return workspaceTotal;
    }
}