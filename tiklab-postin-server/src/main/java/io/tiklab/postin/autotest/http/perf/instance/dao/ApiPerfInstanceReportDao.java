package io.tiklab.postin.autotest.http.perf.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.perf.instance.entity.ApiPerfInstanceReportEntity;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstanceReportQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口性能历史实例明细 数据访问
 */
@Repository
public class ApiPerfInstanceReportDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfInstanceReportDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建性能测试实例明细
     * @param apiPerfInstanceReportEntity
     * @return
     */
    public String createApiPerfInstanceReport(ApiPerfInstanceReportEntity apiPerfInstanceReportEntity) {
        return jpaTemplate.save(apiPerfInstanceReportEntity,String.class);
    }

    /**
     * 修改性能测试实例明细
     * @param apiPerfInstanceReportEntity
     */
    public void updateApiPerfInstanceReport(ApiPerfInstanceReportEntity apiPerfInstanceReportEntity){
        jpaTemplate.update(apiPerfInstanceReportEntity);
    }

    /**
     * 删除性能测试实例明细
     * @param id
     */
    public void deleteApiPerfInstanceReport(String id){
        jpaTemplate.delete(ApiPerfInstanceReportEntity.class,id);
    }

    public void deleteApiPerfInstanceReport(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查询性能测试实例明细
     * @param id
     * @return
     */
    public ApiPerfInstanceReportEntity findApiPerfInstanceReport(String id){
        return jpaTemplate.findOne(ApiPerfInstanceReportEntity.class,id);
    }

    /**
    * 查询所有性能测试实例明细
    * @return
    */
    public List<ApiPerfInstanceReportEntity> findAllApiPerfInstanceReport() {
        return jpaTemplate.findAll(ApiPerfInstanceReportEntity.class);
    }

    public List<ApiPerfInstanceReportEntity> findApiPerfInstanceReportList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfInstanceReportEntity.class,idList);
    }

    /**
     * 通过条件查询性能测试实例明细
     * @param apiPerfInstanceReportQuery
     * @return
     */
    public List<ApiPerfInstanceReportEntity> findApiPerfInstanceReportList(ApiPerfInstanceReportQuery apiPerfInstanceReportQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfInstanceReportEntity.class)
                .eq("apiPerfInstanceId", apiPerfInstanceReportQuery.getApiPerfInstanceId())
                .orders(apiPerfInstanceReportQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfInstanceReportEntity.class);
    }

    /**
     * 通过条件分页查询性能测试实例明细
     * @param apiPerfInstanceReportQuery
     * @return
     */
    public Pagination<ApiPerfInstanceReportEntity> findApiPerfInstanceReportPage(ApiPerfInstanceReportQuery apiPerfInstanceReportQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfInstanceReportEntity.class)
                .eq("apiPerfInstanceId", apiPerfInstanceReportQuery.getApiPerfInstanceId())
                .pagination(apiPerfInstanceReportQuery.getPageParam())
                .orders(apiPerfInstanceReportQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfInstanceReportEntity.class);
    }
}