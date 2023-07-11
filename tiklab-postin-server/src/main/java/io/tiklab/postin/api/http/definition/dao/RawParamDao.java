package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.http.definition.entity.RawParamEntity;
import io.tiklab.postin.api.http.definition.model.RawParamQuery;
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
 * 定义
 * http协议
 * 请求中raw 数据访问
 */
@Repository
public class RawParamDao{

    private static Logger logger = LoggerFactory.getLogger(RawParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求中raw
     * @param rawParamEntity
     * @return
     */
    public String createRawParam(RawParamEntity rawParamEntity) {
        return jpaTemplate.save(rawParamEntity,String.class);
    }

    /**
     * 更新请求中raw
     * @param rawParamEntity
     */
    public void updateRawParam(RawParamEntity rawParamEntity){
        jpaTemplate.update(rawParamEntity);
    }

    /**
     * 删除请求中raw
     * @param id
     */
    public void deleteRawParam(String id){
        jpaTemplate.delete(RawParamEntity.class,id);
    }

    /**
     * 通过条件删除请求中raw
     * @param deleteCondition
     */
    public void deleteRawParamlist(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求中raw
     * @param id
     * @return
     */
    public RawParamEntity findRawParam(String id){
        return jpaTemplate.findOne(RawParamEntity.class,id);
    }

    /**
    * 查找所有请求中raw
    * @return
    */
    public List<RawParamEntity> findAllRawParam() {
        return jpaTemplate.findAll(RawParamEntity.class);
    }

    /**
     * 根据查询参数查找请求中raw列表
     * @param rawParamQuery
     * @return
     */
    public List<RawParamEntity> findRawParamList(RawParamQuery rawParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("httpId", rawParamQuery.getHttpId())
                .orders(rawParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找请求中raw列表
     * @param rawParamQuery
     * @return
     */
    public Pagination<RawParamEntity> findRawParamPage(RawParamQuery rawParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("httpId", rawParamQuery.getHttpId())
                .pagination(rawParamQuery.getPageParam())
                .orders(rawParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RawParamEntity.class);
    }


}