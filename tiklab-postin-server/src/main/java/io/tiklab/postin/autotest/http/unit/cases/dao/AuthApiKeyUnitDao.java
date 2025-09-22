package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.unit.cases.entity.AuthApiKeyUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthApiKeyUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiKey自定义认证数据访问
 */
@Repository
public class AuthApiKeyUnitDao {

    private static Logger logger = LoggerFactory.getLogger(AuthApiKeyUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建ApiKey自定义认证
     * @param authApiKeyUnitEntity
     * @return
     */
    public String createAuthApiKeyUnit(AuthApiKeyUnitEntity authApiKeyUnitEntity) {
        return jpaTemplate.save(authApiKeyUnitEntity,String.class);
    }

    /**
     * 更新ApiKey自定义认证
     * @param authApiKeyUnitEntity
     */
    public void updateAuthApiKeyUnit(AuthApiKeyUnitEntity authApiKeyUnitEntity){
        jpaTemplate.update(authApiKeyUnitEntity);
    }

    /**
     * 删除ApiKey自定义认证
     * @param id
     */
    public void deleteAuthApiKeyUnit(String id){
        jpaTemplate.delete(AuthApiKeyUnitEntity.class,id);
    }

    /**
     * 通过条件删除ApiKey自定义认证
     * @param deleteCondition
     */
    public void deleteAuthApiKeyUnitList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找ApiKey自定义认证
     * @param id
     * @return
     */
    public AuthApiKeyUnitEntity findAuthApiKeyUnit(String id){
        return jpaTemplate.findOne(AuthApiKeyUnitEntity.class,id);
    }

    /**
    * 查找所有ApiKey自定义认证
    * @return
    */
    public List<AuthApiKeyUnitEntity> findAllAuthApiKeyUnit() {
        return jpaTemplate.findAll(AuthApiKeyUnitEntity.class);
    }

    /**
     * 根据查询参数查找ApiKey自定义认证列表
     * @param authApiKeyUnitQuery
     * @return
     */
    public List<AuthApiKeyUnitEntity> findAuthApiKeyUnitList(AuthApiKeyUnitQuery authApiKeyUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthApiKeyUnitEntity.class)
                .eq("apiUnitId", authApiKeyUnitQuery.getApiUnitId())
                .orders(authApiKeyUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, AuthApiKeyUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查找ApiKey自定义认证列表
     * @param authApiKeyUnitQuery
     * @return
     */
    public Pagination<AuthApiKeyUnitEntity> findAuthApiKeyUnitPage(AuthApiKeyUnitQuery authApiKeyUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthApiKeyUnitEntity.class)
                .eq("apiUnitId", authApiKeyUnitQuery.getApiUnitId())
                .pagination(authApiKeyUnitQuery.getPageParam())
                .orders(authApiKeyUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, AuthApiKeyUnitEntity.class);
    }
}