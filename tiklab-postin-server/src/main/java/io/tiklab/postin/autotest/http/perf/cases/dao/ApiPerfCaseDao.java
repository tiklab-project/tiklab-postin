package io.tiklab.postin.autotest.http.perf.cases.dao;

import io.tiklab.postin.autotest.http.perf.cases.entity.ApiPerfCaseEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口性能 数据访问
 */
@Repository
public class ApiPerfCaseDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口性能
     * @param apiPerfCaseEntity
     * @return
     */
    public String createApiPerfCase(ApiPerfCaseEntity apiPerfCaseEntity) {
        return jpaTemplate.save(apiPerfCaseEntity,String.class);
    }

    /**
     * 更新接口性能
     * @param apiPerfCaseEntity
     */
    public void updateApiPerfCase(ApiPerfCaseEntity apiPerfCaseEntity){
        jpaTemplate.update(apiPerfCaseEntity);
    }

    /**
     * 删除接口性能
     * @param id
     */
    public void deleteApiPerfCase(String id){
        jpaTemplate.delete(ApiPerfCaseEntity.class,id);
    }

    public void deleteApiPerfCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找接口性能
     * @param id
     * @return
     */
    public ApiPerfCaseEntity findApiPerfCase(String id){
        return jpaTemplate.findOne(ApiPerfCaseEntity.class,id);
    }

    /**
    * 查找所有接口性能
    * @return
    */
    public List<ApiPerfCaseEntity> findAllApiPerfCase() {
        return jpaTemplate.findAll(ApiPerfCaseEntity.class);
    }

    public List<ApiPerfCaseEntity> findApiPerfCaseList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfCaseEntity.class,idList);
    }

    /**
     * 查询列表接口性能
     * @param apiPerfCaseQuery
     * @return
     */
    public List<ApiPerfCaseEntity> findApiPerfCaseList(ApiPerfCaseQuery apiPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfCaseEntity.class)
                .eq("testCaseId",apiPerfCaseQuery.getTestCaseId())
                .orders(apiPerfCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfCaseEntity.class);
    }

    /**
     * 按分页查询接口性能
     * @param apiPerfCaseQuery
     * @return
     */
    public Pagination<ApiPerfCaseEntity> findApiPerfCasePage(ApiPerfCaseQuery apiPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfCaseEntity.class)
                .eq("testCaseId",apiPerfCaseQuery.getTestCaseId())
                .orders(apiPerfCaseQuery.getOrderParams())
                .pagination(apiPerfCaseQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfCaseEntity.class);
    }
}