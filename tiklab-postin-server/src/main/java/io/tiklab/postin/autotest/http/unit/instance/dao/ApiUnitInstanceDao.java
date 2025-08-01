package io.tiklab.postin.autotest.http.unit.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.instance.entity.ApiUnitInstanceEntity;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口单元实例 数据访问
 */
@Repository
public class ApiUnitInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建步骤实例
     * @param testStepInstanceEntity
     * @return
     */
    public String createApiUnitInstance(ApiUnitInstanceEntity testStepInstanceEntity) {
        return jpaTemplate.save(testStepInstanceEntity,String.class);
    }

    /**
     * 更新测试用例实例
     * @param testStepInstanceEntity
     */
    public void updateApiUnitInstance(ApiUnitInstanceEntity testStepInstanceEntity){
        jpaTemplate.update(testStepInstanceEntity);
    }

    /**
     * 删除测试用例实例
     * @param id
     */
    public void deleteApiUnitInstance(String id){
        jpaTemplate.delete(ApiUnitInstanceEntity.class,id);
    }

    public void deleteApiUnitInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查询测试用例实例
     * @param id
     * @return
     */
    public ApiUnitInstanceEntity findApiUnitInstance(String id){
        return jpaTemplate.findOne(ApiUnitInstanceEntity.class,id);
    }

    /**
    * 查询所有测试用例实例
    * @return
    */
    public List<ApiUnitInstanceEntity> findAllApiUnitInstance() {
        return jpaTemplate.findAll(ApiUnitInstanceEntity.class);
    }

    public List<ApiUnitInstanceEntity> findApiUnitInstanceList(List<String> idList) {
        return jpaTemplate.findList(ApiUnitInstanceEntity.class,idList);
    }

    /**
     * 通过查询对象查询测试用例实例
     * @param apiUnitInstanceQuery
     * @return
     */
    public List<ApiUnitInstanceEntity> findApiUnitInstanceList(ApiUnitInstanceQuery apiUnitInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitInstanceEntity.class)
                .eq("apiUnitId", apiUnitInstanceQuery.getApiUnitId())
                .orders(apiUnitInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiUnitInstanceEntity.class);
    }

    /**
     * 通过查询对象分页查询测试用例实例
     * @param apiUnitInstanceQuery
     * @return
     */
    public Pagination<ApiUnitInstanceEntity> findApiUnitInstancePage(ApiUnitInstanceQuery apiUnitInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitInstanceEntity.class)
                .eq("apiUnitId", apiUnitInstanceQuery.getApiUnitId())
                .orders(apiUnitInstanceQuery.getOrderParams())
                .pagination(apiUnitInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiUnitInstanceEntity.class);
    }

}