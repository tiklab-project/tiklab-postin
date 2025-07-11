package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.definition.entity.AuthBearerEntity;
import io.tiklab.postin.api.http.definition.model.AuthBearerQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BearerToken认证 数据访问
 */
@Repository
public class AuthBearerDao {

    private static Logger logger = LoggerFactory.getLogger(AuthBearerDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建BearerToken认证
     * @param authBearerEntity
     * @return
     */
    public String createAuthBearer(AuthBearerEntity authBearerEntity) {
        return jpaTemplate.save(authBearerEntity,String.class);
    }

    /**
     * 更新BearerToken认证
     * @param authBearerEntity
     */
    public void updateAuthBearer(AuthBearerEntity authBearerEntity){
        jpaTemplate.update(authBearerEntity);
    }

    /**
     * 删除BearerToken认证
     * @param id
     */
    public void deleteAuthBearer(String id){
        jpaTemplate.delete(AuthBearerEntity.class,id);
    }

    /**
     * 通过条件删除BearerToken认证
     * @param deleteCondition
     */
    public void deleteAuthBearerList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找BearerToken认证
     * @param id
     * @return
     */
    public AuthBearerEntity findAuthBearer(String id){
        return jpaTemplate.findOne(AuthBearerEntity.class,id);
    }

    /**
    * 查找所有BearerToken认证
    * @return
    */
    public List<AuthBearerEntity> findAllAuthBearer() {
        return jpaTemplate.findAll(AuthBearerEntity.class);
    }

    /**
     * 根据查询参数查找BearerToken认证列表
     * @param authBearerQuery
     * @return
     */
    public List<AuthBearerEntity> findAuthBearerList(AuthBearerQuery authBearerQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthBearerEntity.class)
                .eq("apiId", authBearerQuery.getApiId())
                .orders(authBearerQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, AuthBearerEntity.class);
    }

    /**
     * 根据查询参数按分页查找BearerToken认证列表
     * @param authBearerQuery
     * @return
     */
    public Pagination<AuthBearerEntity> findAuthBearerPage(AuthBearerQuery authBearerQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthBearerEntity.class)
                .eq("apiId", authBearerQuery.getApiId())
                .pagination(authBearerQuery.getPageParam())
                .orders(authBearerQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, AuthBearerEntity.class);
    }
}