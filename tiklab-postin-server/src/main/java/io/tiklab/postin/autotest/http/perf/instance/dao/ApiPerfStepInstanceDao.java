package io.tiklab.postin.autotest.http.perf.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.perf.instance.entity.ApiPerfStepInstanceEntity;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口性能历史实例 数据访问
 */
@Repository
public class ApiPerfStepInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfStepInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建性能测试汇总报告
     * @param ApiPerfStepInstanceEntity
     * @return
     */
    public String createApiPerfStepInstance(ApiPerfStepInstanceEntity ApiPerfStepInstanceEntity) {
        return jpaTemplate.save(ApiPerfStepInstanceEntity,String.class);
    }

    /**
     * 修改性能测试汇总报告
     * @param ApiPerfStepInstanceEntity
     */
    public void updateApiPerfStepInstance(ApiPerfStepInstanceEntity ApiPerfStepInstanceEntity){
        jpaTemplate.update(ApiPerfStepInstanceEntity);
    }

    /**
     * 删除性能测试汇总报告
     * @param id
     */
    public void deleteApiPerfStepInstance(String id){
        jpaTemplate.delete(ApiPerfStepInstanceEntity.class,id);
    }

    public void deleteApiPerfStepInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查询性能测试汇总报告
     * @param id
     * @return
     */
    public ApiPerfStepInstanceEntity findApiPerfStepInstance(String id){
        return jpaTemplate.findOne(ApiPerfStepInstanceEntity.class,id);
    }

    /**
    * 查询所有性能测试汇总报告
    * @return
    */
    public List<ApiPerfStepInstanceEntity> findAllApiPerfStepInstance() {
        return jpaTemplate.findAll(ApiPerfStepInstanceEntity.class);
    }

    public List<ApiPerfStepInstanceEntity> findApiPerfStepInstanceList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfStepInstanceEntity.class,idList);
    }

    /**
     * 通过条件查询性能测试汇总报告
     * @param ApiPerfStepInstanceQuery
     * @return
     */
    public List<ApiPerfStepInstanceEntity> findApiPerfStepInstanceList(ApiPerfStepInstanceQuery ApiPerfStepInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepInstanceEntity.class)
                .eq("apiPerfInstanceId", ApiPerfStepInstanceQuery.getApiPerfInstanceId())
                .orders(ApiPerfStepInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfStepInstanceEntity.class);
    }

    /**
     * 通过条件分页查询性能测试汇总报告
     * @param ApiPerfStepInstanceQuery
     * @return
     */
    public Pagination<ApiPerfStepInstanceEntity> findApiPerfStepInstancePage(ApiPerfStepInstanceQuery ApiPerfStepInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepInstanceEntity.class)
                .eq("apiPerfInstanceId", ApiPerfStepInstanceQuery.getApiPerfInstanceId())
                .pagination(ApiPerfStepInstanceQuery.getPageParam())
                .orders(ApiPerfStepInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfStepInstanceEntity.class);
    }
}