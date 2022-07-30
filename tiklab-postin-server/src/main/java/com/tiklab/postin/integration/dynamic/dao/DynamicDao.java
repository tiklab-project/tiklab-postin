package com.tiklab.postin.integration.dynamic.dao;

import com.tiklab.postin.integration.dynamic.entity.DynamicEntity;
import com.tiklab.postin.integration.dynamic.model.DynamicQuery;
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
 * DynamicDao
 */
@Repository
public class DynamicDao{

    private static Logger logger = LoggerFactory.getLogger(DynamicDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param dynamicEntity
     * @return
     */
    public String createDynamic(DynamicEntity dynamicEntity) {
        return jpaTemplate.save(dynamicEntity,String.class);
    }

    /**
     * 更新
     * @param dynamicEntity
     */
    public void updateDynamic(DynamicEntity dynamicEntity){
        jpaTemplate.update(dynamicEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDynamic(String id){
        jpaTemplate.delete(DynamicEntity.class,id);
    }

    public void deleteDynamic(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DynamicEntity findDynamic(String id){
        return jpaTemplate.findOne(DynamicEntity.class,id);
    }

    /**
    * findAllDynamic
    * @return
    */
    public List<DynamicEntity> findAllDynamic() {
        return jpaTemplate.findAll(DynamicEntity.class);
    }

    public List<DynamicEntity> findDynamicList(List<String> idList) {
        return jpaTemplate.findList(DynamicEntity.class,idList);
    }

    public List<DynamicEntity> findDynamicList(DynamicQuery dynamicQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DynamicEntity.class)
                .eq("workspaceId", dynamicQuery.getWorkspaceId())
                .eq("userId",dynamicQuery.getUserId())
                .eq("model",dynamicQuery.getModel())
                .orders(dynamicQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,DynamicEntity.class);
    }

    public Pagination<DynamicEntity> findDynamicPage(DynamicQuery dynamicQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DynamicEntity.class)
                .eq("workspaceId", dynamicQuery.getWorkspaceId())
                .eq("userId",dynamicQuery.getUserId())
                .eq("model",dynamicQuery.getModel())
                .orders(dynamicQuery.getOrderParams())
                .pagination(dynamicQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,DynamicEntity.class);
    }
}