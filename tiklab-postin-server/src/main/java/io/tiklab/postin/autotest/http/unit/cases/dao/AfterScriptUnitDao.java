package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.AfterScriptUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterScriptUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后置脚本 数据访问
 */
@Repository
public class AfterScriptUnitDao {

    private static Logger logger = LoggerFactory.getLogger(AfterScriptUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param afterScriptUnitEntity
     * @return
     */
    public String createAfterScript(AfterScriptUnitEntity afterScriptUnitEntity) {
        return jpaTemplate.save(afterScriptUnitEntity,String.class);
    }

    /**
     * 更新
     * @param afterScriptUnitEntity
     */
    public void updateAfterScript(AfterScriptUnitEntity afterScriptUnitEntity){
        jpaTemplate.update(afterScriptUnitEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAfterScript(String id){
        jpaTemplate.delete(AfterScriptUnitEntity.class,id);
    }

    public void deleteAfterScript(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AfterScriptUnitEntity findAfterScript(String id){
        return jpaTemplate.findOne(AfterScriptUnitEntity.class,id);
    }

    /**
    * findAllAfterScript
    * @return
    */
    public List<AfterScriptUnitEntity> findAllAfterScript() {
        return jpaTemplate.findAll(AfterScriptUnitEntity.class);
    }

    public List<AfterScriptUnitEntity> findAfterScriptList(List<String> idList) {
        return jpaTemplate.findList(AfterScriptUnitEntity.class,idList);
    }

    public List<AfterScriptUnitEntity> findAfterScriptList(AfterScriptUnitQuery afterScriptUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterScriptUnitEntity.class)
                .eq("apiUnitId", afterScriptUnitQuery.getApiUnitId())
                .orders(afterScriptUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AfterScriptUnitEntity.class);
    }

    public Pagination<AfterScriptUnitEntity> findAfterScriptPage(AfterScriptUnitQuery afterScriptUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterScriptUnitEntity.class)
                .eq("apiUnitId", afterScriptUnitQuery.getApiUnitId())
                .orders(afterScriptUnitQuery.getOrderParams())
                .pagination(afterScriptUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, AfterScriptUnitEntity.class);
    }
}