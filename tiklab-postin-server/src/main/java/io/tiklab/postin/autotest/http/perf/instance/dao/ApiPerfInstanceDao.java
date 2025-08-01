package io.tiklab.postin.autotest.http.perf.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.perf.instance.entity.ApiPerfInstanceEntity;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口性能历史实例 数据访问
 */
@Repository
public class ApiPerfInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建性能测试汇总报告
     * @param apiPerfInstanceEntity
     * @return
     */
    public String createApiPerfInstance(ApiPerfInstanceEntity apiPerfInstanceEntity) {
        return jpaTemplate.save(apiPerfInstanceEntity,String.class);
    }

    /**
     * 修改性能测试汇总报告
     * @param apiPerfInstanceEntity
     */
    public void updateApiPerfInstance(ApiPerfInstanceEntity apiPerfInstanceEntity){
        jpaTemplate.update(apiPerfInstanceEntity);
    }

    /**
     * 删除性能测试汇总报告
     * @param id
     */
    public void deleteApiPerfInstance(String id){
        jpaTemplate.delete(ApiPerfInstanceEntity.class,id);
    }

    public void deleteApiPerfInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查询性能测试汇总报告
     * @param id
     * @return
     */
    public ApiPerfInstanceEntity findApiPerfInstance(String id){
        return jpaTemplate.findOne(ApiPerfInstanceEntity.class,id);
    }

    /**
    * 查询所有性能测试汇总报告
    * @return
    */
    public List<ApiPerfInstanceEntity> findAllApiPerfInstance() {
        return jpaTemplate.findAll(ApiPerfInstanceEntity.class);
    }

    public List<ApiPerfInstanceEntity> findApiPerfInstanceList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfInstanceEntity.class,idList);
    }

    /**
     * 通过条件查询性能测试汇总报告
     * @param apiPerfInstanceQuery
     * @return
     */
    public List<ApiPerfInstanceEntity> findApiPerfInstanceList(ApiPerfInstanceQuery apiPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfInstanceEntity.class)
                .eq("apiPerfId", apiPerfInstanceQuery.getApiPerfId())
                .orders(apiPerfInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfInstanceEntity.class);
    }

    /**
     * 通过条件分页查询性能测试汇总报告
     * @param apiPerfInstanceQuery
     * @return
     */
    public Pagination<ApiPerfInstanceEntity> findApiPerfInstancePage(ApiPerfInstanceQuery apiPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfInstanceEntity.class)
                .eq("apiPerfId", apiPerfInstanceQuery.getApiPerfId())
                .pagination(apiPerfInstanceQuery.getPageParam())
                .orders(apiPerfInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfInstanceEntity.class);
    }
}