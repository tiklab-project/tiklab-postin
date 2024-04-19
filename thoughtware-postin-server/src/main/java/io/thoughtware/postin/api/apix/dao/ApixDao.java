package io.thoughtware.postin.api.apix.dao;

import io.thoughtware.postin.api.apix.entity.ApiListEntity;
import io.thoughtware.postin.api.apix.entity.ApixEntity;
import io.thoughtware.postin.api.apix.model.ApixQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                .orders(apixQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApixEntity.class);
    }



    public Pagination<ApiListEntity> findApiPage(ApixQuery apixQuery) {
        String sql = "SELECT pn.id, pn.name, pn.create_time, pa.protocol_type, pa.method_type,pa.executor_id,pa.path,pa.status_id\n" +
                "FROM  postin_node pn\n" +
                "JOIN  postin_apix pa\n" +
                "ON pn.id = pa.id \n" +
                "WHERE pn.parent_id = ? ";

        if(apixQuery.getName()!=null){
            sql += " AND pn.name LIKE '%"+apixQuery.getName()+"%'";
        }


        Object[] params = {apixQuery.getCategoryId()};
        Pagination<ApiListEntity> page = jpaTemplate.getJdbcTemplate().findPage(sql, params, apixQuery.getPageParam(), new BeanPropertyRowMapper<>(ApiListEntity.class));
        return page;
    }



}