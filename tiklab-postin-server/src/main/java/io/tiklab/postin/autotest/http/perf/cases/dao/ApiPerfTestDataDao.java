package io.tiklab.postin.autotest.http.perf.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.perf.cases.entity.ApiPerfTestDataEntity;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfTestDataQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口性能测试数据 数据访问
 */
@Repository
public class ApiPerfTestDataDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfTestDataDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口性能测试数据
     * @param apiPerfTestDataEntity
     * @return
     */
    public String createApiPerfTestData(ApiPerfTestDataEntity apiPerfTestDataEntity) {
        return jpaTemplate.save(apiPerfTestDataEntity,String.class);
    }

    /**
     * 更新接口性能测试数据
     * @param apiPerfTestDataEntity
     */
    public void updateApiPerfTestData(ApiPerfTestDataEntity apiPerfTestDataEntity){
        jpaTemplate.update(apiPerfTestDataEntity);
    }

    /**
     * 删除接口性能测试数据
     * @param id
     */
    public void deleteApiPerfTestData(String id){
        jpaTemplate.delete(ApiPerfTestDataEntity.class,id);
    }

    public void deleteApiPerfTestData(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找接口性能测试数据
     * @param id
     * @return
     */
    public ApiPerfTestDataEntity findApiPerfTestData(String id){
        return jpaTemplate.findOne(ApiPerfTestDataEntity.class,id);
    }



    /**
    * 查找所有接口性能测试数据
    * @return
    */
    public List<ApiPerfTestDataEntity> findAllApiPerfTestData() {
        return jpaTemplate.findAll(ApiPerfTestDataEntity.class);
    }

    public List<ApiPerfTestDataEntity> findApiPerfTestDataList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfTestDataEntity.class,idList);
    }

    /**
     * 查询列表接口性能测试数据
     * @param apiPerfTestDataQuery
     * @return
     */
    public List<ApiPerfTestDataEntity> findApiPerfTestDataList(ApiPerfTestDataQuery apiPerfTestDataQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfTestDataEntity.class)
                .eq("caseId",apiPerfTestDataQuery.getCaseId())
                .orders(apiPerfTestDataQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfTestDataEntity.class);
    }

    /**
     * 按分页查询接口性能测试数据
     * @param apiPerfTestDataQuery
     * @return
     */
    public Pagination<ApiPerfTestDataEntity> findApiPerfTestDataPage(ApiPerfTestDataQuery apiPerfTestDataQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfTestDataEntity.class)
                .eq("caseId",apiPerfTestDataQuery.getCaseId())
                .orders(apiPerfTestDataQuery.getOrderParams())
                .pagination(apiPerfTestDataQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfTestDataEntity.class);
    }

    public ApiPerfTestDataEntity findApiPerfTestDataByCaseId(String caseId) {
        String sql = "select * from autotest_api_perf_testdata where case_id = ?";
        try {
            return jpaTemplate.getJdbcTemplate().queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(ApiPerfTestDataEntity.class),
                    caseId
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // 或者抛出自定义异常
        }
    }

}