package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.definition.entity.PreParamEntity;
import io.tiklab.postin.api.http.definition.model.PreParamQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 前置操作 数据访问
 */
@Repository
public class PreParamDao {

    private static Logger logger = LoggerFactory.getLogger(PreParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建前置操作
     * @param preParamEntity
     * @return
     */
    public String createPreParam(PreParamEntity preParamEntity) {
        return jpaTemplate.save(preParamEntity,String.class);
    }

    /**
     * 更新前置操作
     * @param preParamEntity
     */
    public void updatePreParam(PreParamEntity preParamEntity){
        jpaTemplate.update(preParamEntity);
    }

    /**
     * 删除前置操作
     * @param id
     */
    public void deletePreParam(String id){
        jpaTemplate.delete(PreParamEntity.class,id);
    }

    /**
     * 通过条件删除前置操作
     * @param deleteCondition
     */
    public void deletePreParamList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找前置操作
     * @param id
     * @return
     */
    public PreParamEntity findPreParam(String id){
        return jpaTemplate.findOne(PreParamEntity.class,id);
    }

    /**
    * 查找所有前置操作
    * @return
    */
    public List<PreParamEntity> findAllPreParam() {
        return jpaTemplate.findAll(PreParamEntity.class);
    }

    /**
     * 根据查询参数查找前置操作列表
     * @param preParamQuery
     * @return
     */
    public List<PreParamEntity> findPreParamList(PreParamQuery preParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreParamEntity.class)
                .eq("apiId", preParamQuery.getApiId())
                .orders(preParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, PreParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找前置操作列表
     * @param preParamQuery
     * @return
     */
    public Pagination<PreParamEntity> findPreParamPage(PreParamQuery preParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreParamEntity.class)
                .eq("apiId", preParamQuery.getApiId())
                .pagination(preParamQuery.getPageParam())
                .orders(preParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, PreParamEntity.class);
    }

    public int bigSort(String apiId) {
        try {
            String sql = "SELECT COALESCE(MAX(sort), 0) FROM postin_api_request_pre WHERE api_id = ?";
            Integer result = jpaTemplate.getJdbcTemplate().queryForObject(sql, Integer.class, apiId);
            return result != null ? result : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}