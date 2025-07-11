package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.definition.entity.AuthApiKeyEntity;
import io.tiklab.postin.api.http.definition.model.AuthApiKeyQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiKey自定义认证数据访问
 */
@Repository
public class AuthApiKeyDao {

    private static Logger logger = LoggerFactory.getLogger(AuthApiKeyDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建ApiKey自定义认证
     * @param authApiKeyEntity
     * @return
     */
    public String createAuthApiKey(AuthApiKeyEntity authApiKeyEntity) {
        return jpaTemplate.save(authApiKeyEntity,String.class);
    }

    /**
     * 更新ApiKey自定义认证
     * @param authApiKeyEntity
     */
    public void updateAuthApiKey(AuthApiKeyEntity authApiKeyEntity){
        jpaTemplate.update(authApiKeyEntity);
    }

    /**
     * 删除ApiKey自定义认证
     * @param id
     */
    public void deleteAuthApiKey(String id){
        jpaTemplate.delete(AuthApiKeyEntity.class,id);
    }

    /**
     * 通过条件删除ApiKey自定义认证
     * @param deleteCondition
     */
    public void deleteAuthApiKeyList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找ApiKey自定义认证
     * @param id
     * @return
     */
    public AuthApiKeyEntity findAuthApiKey(String id){
        return jpaTemplate.findOne(AuthApiKeyEntity.class,id);
    }

    /**
    * 查找所有ApiKey自定义认证
    * @return
    */
    public List<AuthApiKeyEntity> findAllAuthApiKey() {
        return jpaTemplate.findAll(AuthApiKeyEntity.class);
    }

    /**
     * 根据查询参数查找ApiKey自定义认证列表
     * @param authApiKeyQuery
     * @return
     */
    public List<AuthApiKeyEntity> findAuthApiKeyList(AuthApiKeyQuery authApiKeyQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthApiKeyEntity.class)
                .eq("apiId", authApiKeyQuery.getApiId())
                .orders(authApiKeyQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, AuthApiKeyEntity.class);
    }

    /**
     * 根据查询参数按分页查找ApiKey自定义认证列表
     * @param authApiKeyQuery
     * @return
     */
    public Pagination<AuthApiKeyEntity> findAuthApiKeyPage(AuthApiKeyQuery authApiKeyQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthApiKeyEntity.class)
                .eq("apiId", authApiKeyQuery.getApiId())
                .pagination(authApiKeyQuery.getPageParam())
                .orders(authApiKeyQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, AuthApiKeyEntity.class);
    }
}