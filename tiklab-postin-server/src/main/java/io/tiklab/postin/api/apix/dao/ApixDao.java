package io.tiklab.postin.api.apix.dao;

import io.tiklab.postin.api.apix.entity.ApiListEntity;
import io.tiklab.postin.api.apix.entity.ApixEntity;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口公共数据访问
 */
@Repository
public class ApixDao {

    private static Logger logger = LoggerFactory.getLogger(ApixDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apixEntity
     * @return
     */
    public String createApix(ApixEntity apixEntity) {
        return jpaTemplate.save(apixEntity,String.class);
    }

    /**
     * 更新
     * @param apixEntity
     */
    public void updateApix(ApixEntity apixEntity){
        jpaTemplate.update(apixEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApix(String id){
        jpaTemplate.delete(ApixEntity.class,id);
    }

    public void deleteApix(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApixEntity findApix(String id){
        return jpaTemplate.findOne(ApixEntity.class,id);
    }

    /**
    * 查找所有
    * @return
    */
    public List<ApixEntity> findAllApix() {
        return jpaTemplate.findAll(ApixEntity.class);
    }


    /**
     * 通过list查询
     * @param idList
     * @return
     */
    public List<ApixEntity> findApixList(List<String> idList) {
        return jpaTemplate.findList(ApixEntity.class,idList);
    }

    /**
     * 查询总数
     * @param workspaceId
     * @return
     */
    public int findApixNum(String workspaceId) {
        String apxSql = "Select count(1) as total from postin_apix where workspace_id = '" + workspaceId+ "'";
        Integer apiTotal = jpaTemplate.getJdbcTemplate().queryForObject(apxSql, new Object[]{}, Integer.class);
        
        return apiTotal;
    }


    /**
     * 通过查询参数查询List
     * @param apixQuery
     * @return
     */
    public List<ApixEntity> findApixList(ApixQuery apixQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApixEntity.class)
                .eq("categoryId", apixQuery.getCategoryId())
                .eq("protocolType", apixQuery.getProtocolType())
                .eq("version", apixQuery.getVersion())
                .eq("apiUid", apixQuery.getApiUid())
                .eq("workspaceId", apixQuery.getWorkspaceId())
                .orders(apixQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApixEntity.class);
    }



    public Pagination<ApiListEntity> findApiPage(ApixQuery q) {
        // 基础 SQL
        StringBuilder sql = new StringBuilder()
                .append("SELECT pn.id, pn.name, pn.create_time, ")
                .append("       pa.protocol_type, pn.method_type, ")
                .append("       pa.executor_id, pa.path, pa.status_id ")
                .append("FROM postin_node pn ")
                .append("JOIN postin_apix pa ON pn.id = pa.id ")
                .append("LEFT JOIN postin_category pc ON pn.parent_id = pc.id ")
                .append("WHERE pa.version_id IS NULL ");

        List<Object> params = new ArrayList<>();

        // 1）categoryId 或 workspaceId（二选一）
        if (q.getCategoryId() != null) {
            sql.append("AND pn.parent_id = ? ");
            params.add(q.getCategoryId());
        } else if (q.getWorkspaceId() != null) {
            sql.append("AND pa.workspace_id = ? ");
            params.add(q.getWorkspaceId());
        } else {
            throw new IllegalArgumentException("categoryId 和 workspaceId 不能同时为空");
        }

        // 2）名称模糊搜索
        if (StringUtils.isNotBlank(q.getName())) {
            sql.append("AND pn.name LIKE ? ");
            params.add("%" + q.getName().trim() + "%");
        }

        // 3）剔除已有的 IDs（NOT IN）
        if (CollectionUtils.isNotEmpty(q.getExcludeIds())) {
            // 生成 ?,?,? 占位
            String placeholders = q.getExcludeIds().stream()
                    .map(id -> "?")
                    .collect(Collectors.joining(", "));
            sql.append("AND pn.id NOT IN (").append(placeholders).append(") ");
            params.addAll(q.getExcludeIds());
        }

        // 执行分页查询
        return jpaTemplate.getJdbcTemplate().findPage(
                sql.toString(),
                params.toArray(),
                q.getPageParam(),
                new BeanPropertyRowMapper<>(ApiListEntity.class)
        );
    }


}