package io.tiklab.postin.autotest.http.unit.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.tiklab.postin.autotest.http.unit.instance.entity.ApiUnitInstanceBindEntity;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstanceBindQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口单元公共实例 数据访问
 */
@Repository
public class ApiUnitInstanceBindDao{

    private static Logger logger = LoggerFactory.getLogger(ApiUnitInstanceBindDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口单元公共实例
     * @param apiUnitInstanceBindEntity
     * @return
     */
    public String createApiUnitInstanceBind(ApiUnitInstanceBindEntity apiUnitInstanceBindEntity) {
        return jpaTemplate.save(apiUnitInstanceBindEntity,String.class);
    }

    /**
     * 更新接口单元公共实例
     * @param apiUnitInstanceBindEntity
     */
    public void updateApiUnitInstanceBind(ApiUnitInstanceBindEntity apiUnitInstanceBindEntity){
        jpaTemplate.update(apiUnitInstanceBindEntity);
    }

    /**
     * 删除接口单元公共实例
     * @param id
     */
    public void deleteApiUnitInstanceBind(String id){
        jpaTemplate.delete(ApiUnitInstanceBindEntity.class,id);
    }

    public void deleteApiUnitInstanceBind(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找接口单元公共实例
     * @param id
     * @return
     */
    public ApiUnitInstanceBindEntity findApiUnitInstanceBind(String id){
        return jpaTemplate.findOne(ApiUnitInstanceBindEntity.class,id);
    }

    /**
    * 查找所有接口单元公共实例
    * @return
    */
    public List<ApiUnitInstanceBindEntity> findAllApiUnitInstanceBind() {
        return jpaTemplate.findAll(ApiUnitInstanceBindEntity.class);
    }

    public List<ApiUnitInstanceBindEntity> findApiUnitInstanceBindList(List<String> idList) {
        return jpaTemplate.findList(ApiUnitInstanceBindEntity.class,idList);
    }

    /**
     * 查询接口单元公共实例列表
     * @param apiUnitInstanceBindQuery
     * @return
     */
    public List<ApiUnitInstanceBindEntity> findApiUnitInstanceBindList(ApiUnitInstanceBindQuery apiUnitInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitInstanceBindEntity.class)
                .eq("apiUnitId", apiUnitInstanceBindQuery.getApiUnitId())
                .orders(apiUnitInstanceBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiUnitInstanceBindEntity.class);
    }

    /**
     * 按分页查询接口单元公共实例
     * @param apiUnitInstanceBindQuery
     * @return
     */
    public Pagination<ApiUnitInstanceBindEntity> findApiUnitInstanceBindPage(ApiUnitInstanceBindQuery apiUnitInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitInstanceBindEntity.class)
                .eq("apiUnitId", apiUnitInstanceBindQuery.getApiUnitId())
                .orders(apiUnitInstanceBindQuery.getOrderParams())
                .pagination(apiUnitInstanceBindQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiUnitInstanceBindEntity.class);
    }
}