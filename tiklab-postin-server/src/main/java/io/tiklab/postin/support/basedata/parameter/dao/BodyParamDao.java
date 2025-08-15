package io.tiklab.postin.support.basedata.parameter.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.tiklab.postin.support.basedata.parameter.entity.BodyParamEntity;
import io.tiklab.postin.support.basedata.parameter.model.BodyParamQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 基础数据中的body公共参数(formdata/formurlencoded)
 * bodydata 数据访问
 */
@Repository
public class BodyParamDao {

    private static Logger logger = LoggerFactory.getLogger(BodyParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建bodydata
     * @param bodyParamEntity
     * @return
     */
    public String createBodyParam(BodyParamEntity bodyParamEntity) {
        return jpaTemplate.save(bodyParamEntity,String.class);
    }

    /**
     * 更新bodydata
     * @param bodyParamEntity
     */
    public void updateBodyParam(BodyParamEntity bodyParamEntity){
        jpaTemplate.update(bodyParamEntity);
    }

    /**
     * 删除bodydata
     * @param id
     */
    public void deleteBodyParam(String id){
        jpaTemplate.delete(BodyParamEntity.class,id);
    }

    /**
     * 通过条件删除bodydata
     * @param deleteCondition
     */
    public void deleteBodyParamLsit(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找bodydata
     * @param id
     * @return
     */
    public BodyParamEntity findBodyParam(String id){
        return jpaTemplate.findOne(BodyParamEntity.class,id);
    }

    /**
    * 查找所有bodydata
    * @return
    */
    public List<BodyParamEntity> findAllBodyParam() {
        return jpaTemplate.findAll(BodyParamEntity.class);
    }

    /**
     * 根据查询参数查找bodydata列表
     * @param bodyParamQuery
     * @return
     */
    public List<BodyParamEntity> findBodyParamList(BodyParamQuery bodyParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BodyParamEntity.class)
                .eq("workspaceId",bodyParamQuery.getWorkspaceId())
                .orders(bodyParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, BodyParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找bodydata列表
     * @param bodyParamQuery
     * @return
     */
    public Pagination<BodyParamEntity> findBodyParamPage(BodyParamQuery bodyParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BodyParamEntity.class)
                .eq("workspaceId",bodyParamQuery.getWorkspaceId())
                .pagination(bodyParamQuery.getPageParam())
                .orders(bodyParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, BodyParamEntity.class);
    }
}