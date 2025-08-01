package io.tiklab.postin.autotest.http.perf.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.perf.instance.entity.ApiPerfStepUnitCalcEntity;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepUnitCalcQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口性能历史实例 数据访问
 */
@Repository
public class ApiPerfStepUnitCalcDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfStepUnitCalcDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建性能测试汇总报告
     * @param ApiPerfStepUnitCalcEntity
     * @return
     */
    public String createApiPerfStepUnitCalc(ApiPerfStepUnitCalcEntity ApiPerfStepUnitCalcEntity) {
        return jpaTemplate.save(ApiPerfStepUnitCalcEntity,String.class);
    }

    /**
     * 修改性能测试汇总报告
     * @param ApiPerfStepUnitCalcEntity
     */
    public void updateApiPerfStepUnitCalc(ApiPerfStepUnitCalcEntity ApiPerfStepUnitCalcEntity){
        jpaTemplate.update(ApiPerfStepUnitCalcEntity);
    }

    /**
     * 删除性能测试汇总报告
     * @param id
     */
    public void deleteApiPerfStepUnitCalc(String id){
        jpaTemplate.delete(ApiPerfStepUnitCalcEntity.class,id);
    }

    public void deleteApiPerfStepUnitCalc(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查询性能测试汇总报告
     * @param id
     * @return
     */
    public ApiPerfStepUnitCalcEntity findApiPerfStepUnitCalc(String id){
        return jpaTemplate.findOne(ApiPerfStepUnitCalcEntity.class,id);
    }

    /**
    * 查询所有性能测试汇总报告
    * @return
    */
    public List<ApiPerfStepUnitCalcEntity> findAllApiPerfStepUnitCalc() {
        return jpaTemplate.findAll(ApiPerfStepUnitCalcEntity.class);
    }

    public List<ApiPerfStepUnitCalcEntity> findApiPerfStepUnitCalcList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfStepUnitCalcEntity.class,idList);
    }

    /**
     * 通过条件查询性能测试汇总报告
     * @param apiPerfStepUnitCalcQuery
     * @return
     */
    public List<ApiPerfStepUnitCalcEntity> findApiPerfStepUnitCalcList(ApiPerfStepUnitCalcQuery apiPerfStepUnitCalcQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepUnitCalcEntity.class)
                .eq("apiPerfStepInstanceId", apiPerfStepUnitCalcQuery.getApiPerfStepInstanceId())
                .orders(apiPerfStepUnitCalcQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfStepUnitCalcEntity.class);
    }

    /**
     * 通过条件分页查询性能测试汇总报告
     * @param apiPerfStepUnitCalcQuery
     * @return
     */
    public Pagination<ApiPerfStepUnitCalcEntity> findApiPerfStepUnitCalcPage(ApiPerfStepUnitCalcQuery apiPerfStepUnitCalcQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepUnitCalcEntity.class)
                .eq("apiPerfStepInstanceId", apiPerfStepUnitCalcQuery.getApiPerfStepInstanceId())
                .pagination(apiPerfStepUnitCalcQuery.getPageParam())
                .orders(apiPerfStepUnitCalcQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfStepUnitCalcEntity.class);
    }
}