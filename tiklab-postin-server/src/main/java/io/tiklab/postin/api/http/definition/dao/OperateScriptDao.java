package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.definition.entity.OperateScriptEntity;
import io.tiklab.postin.api.http.definition.model.OperateScriptQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 自定义脚本 数据访问
 */
@Repository
public class OperateScriptDao {

    private static Logger logger = LoggerFactory.getLogger(OperateScriptDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建自定义脚本
     * @param operateScriptEntity
     * @return
     */
    public String createOperateScript(OperateScriptEntity operateScriptEntity) {
        return jpaTemplate.save(operateScriptEntity,String.class);
    }

    /**
     * 更新自定义脚本
     * @param operateScriptEntity
     */
    public void updateOperateScript(OperateScriptEntity operateScriptEntity){
        jpaTemplate.update(operateScriptEntity);
    }

    /**
     * 删除自定义脚本
     * @param id
     */
    public void deleteOperateScript(String id){
        jpaTemplate.delete(OperateScriptEntity.class,id);
    }

    /**
     * 通过条件删除自定义脚本
     * @param deleteCondition
     */
    public void deleteOperateScriptList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找自定义脚本
     * @param id
     * @return
     */
    public OperateScriptEntity findOperateScript(String id){
        return jpaTemplate.findOne(OperateScriptEntity.class,id);
    }

    /**
    * 查找所有自定义脚本
    * @return
    */
    public List<OperateScriptEntity> findAllOperateScript() {
        return jpaTemplate.findAll(OperateScriptEntity.class);
    }

    /**
     * 根据查询参数查找自定义脚本列表
     * @param operateScriptQuery
     * @return
     */
    public List<OperateScriptEntity> findOperateScriptList(OperateScriptQuery operateScriptQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(OperateScriptEntity.class)
                .eq("operationId", operateScriptQuery.getOperationId())
                .orders(operateScriptQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, OperateScriptEntity.class);
    }

    /**
     * 根据查询参数按分页查找自定义脚本列表
     * @param operateScriptQuery
     * @return
     */
    public Pagination<OperateScriptEntity> findOperateScriptPage(OperateScriptQuery operateScriptQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(OperateScriptEntity.class)
                .eq("operationId", operateScriptQuery.getOperationId())
                .pagination(operateScriptQuery.getPageParam())
                .orders(operateScriptQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, OperateScriptEntity.class);
    }
}