package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.unit.cases.entity.PreScriptUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.PreScriptUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 前置脚本 数据访问
 */
@Repository
public class PreScriptUnitDao {

    private static Logger logger = LoggerFactory.getLogger(PreScriptUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建前置脚本
     * @param preScriptUnitEntity
     * @return
     */
    public String createPreScript(PreScriptUnitEntity preScriptUnitEntity) {
        return jpaTemplate.save(preScriptUnitEntity,String.class);
    }

    /**
     * 更新前置脚本
     * @param preScriptUnitEntity
     */
    public void updatePreScript(PreScriptUnitEntity preScriptUnitEntity){
        jpaTemplate.update(preScriptUnitEntity);
    }

    /**
     * 删除前置脚本
     * @param id
     */
    public void deletePreScript(String id){
        jpaTemplate.delete(PreScriptUnitEntity.class,id);
    }

    public void deletePreScript(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找前置脚本
     * @param id
     * @return
     */
    public PreScriptUnitEntity findPreScript(String id){
        return jpaTemplate.findOne(PreScriptUnitEntity.class,id);
    }

    /**
    * 查找所有前置脚本
    * @return
    */
    public List<PreScriptUnitEntity> findAllPreScript() {
        return jpaTemplate.findAll(PreScriptUnitEntity.class);
    }

    public List<PreScriptUnitEntity> findPreScriptList(List<String> idList) {
        return jpaTemplate.findList(PreScriptUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询前置脚本列表
     * @param preScriptUnitQuery
     * @return
     */
    public List<PreScriptUnitEntity> findPreScriptList(PreScriptUnitQuery preScriptUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptUnitEntity.class)
                .eq("apiUnitId", preScriptUnitQuery.getApiUnitId())
                .orders(preScriptUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, PreScriptUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询前置脚本
     * @param preScriptUnitQuery
     * @return
     */
    public Pagination<PreScriptUnitEntity> findPreScriptPage(PreScriptUnitQuery preScriptUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptUnitEntity.class)
                .eq("apiUnitId", preScriptUnitQuery.getApiUnitId())
                .orders(preScriptUnitQuery.getOrderParams())
                .pagination(preScriptUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, PreScriptUnitEntity.class);
    }
}