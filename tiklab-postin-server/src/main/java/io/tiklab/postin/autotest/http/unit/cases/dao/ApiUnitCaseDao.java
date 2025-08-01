package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.unit.cases.entity.ApiUnitCaseEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口单元用例 数据访问
 */
@Repository
public class ApiUnitCaseDao {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口单元
     * @param apiUnitCaseEntity
     * @return
     */
    public String createApiUnitCase(ApiUnitCaseEntity apiUnitCaseEntity) {
        return jpaTemplate.save(apiUnitCaseEntity,String.class);
    }

    /**
     * 更新接口单元
     * @param apiUnitCaseEntity
     */
    public void updateApiUnitCase(ApiUnitCaseEntity apiUnitCaseEntity){
        jpaTemplate.update(apiUnitCaseEntity);
    }

    /**
     * 删除接口单元
     * @param id
     */
    public void deleteApiUnitCase(String id){
        jpaTemplate.delete(ApiUnitCaseEntity.class,id);
    }

    public void deleteApiUnitCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查找接口单元
     * @param id
     * @return
     */
    public ApiUnitCaseEntity findApiUnitCase(String id){
        return jpaTemplate.findOne(ApiUnitCaseEntity.class,id);
    }

    /**
    * 查找所有接口单元
    * @return
    */
    public List<ApiUnitCaseEntity> findAllApiUnitCase() {
        return jpaTemplate.findAll(ApiUnitCaseEntity.class);
    }

    public List<ApiUnitCaseEntity> findApiUnitCaseList(List<String> idList) {
        return jpaTemplate.findList(ApiUnitCaseEntity.class,idList);
    }

    /**
     * 根据查询参数查询接口单元列表
     * @param apiUnitCaseQuery
     * @return
     */
    public List<ApiUnitCaseEntity> findApiUnitCaseList(ApiUnitCaseQuery apiUnitCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitCaseEntity.class)
                .eq("testCaseId",apiUnitCaseQuery.getTestCaseId())
                .eq("path",apiUnitCaseQuery.getPath())
                .orders(apiUnitCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiUnitCaseEntity.class);
    }

    /**
     * 根据查询参数按分页查询接口单元
     * @param apiUnitCaseQuery
     * @return
     */
    public Pagination<ApiUnitCaseEntity> findApiUnitCasePage(ApiUnitCaseQuery apiUnitCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitCaseEntity.class)
                .eq("testCaseId",apiUnitCaseQuery.getTestCaseId())
                .orders(apiUnitCaseQuery.getOrderParams())
                .pagination(apiUnitCaseQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiUnitCaseEntity.class);
    }


}