package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.definition.entity.AuthParamEntity;
import io.tiklab.postin.api.http.definition.model.AuthParamQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 认证 数据访问
 */
@Repository
public class AuthParamDao {

    private static Logger logger = LoggerFactory.getLogger(AuthParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建认证
     * @param authParamEntity
     * @return
     */
    public String createAuthParam(AuthParamEntity authParamEntity) {
        return jpaTemplate.save(authParamEntity,String.class);
    }

    /**
     * 更新认证
     * @param authParamEntity
     */
    public void updateAuthParam(AuthParamEntity authParamEntity){
        jpaTemplate.update(authParamEntity);
    }

    /**
     * 删除认证
     * @param id
     */
    public void deleteAuthParam(String id){
        jpaTemplate.delete(AuthParamEntity.class,id);
    }

    /**
     * 通过条件删除认证
     * @param deleteCondition
     */
    public void deleteAuthParamList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找认证
     * @param id
     * @return
     */
    public AuthParamEntity findAuthParam(String id){
        return jpaTemplate.findOne(AuthParamEntity.class,id);
    }

    /**
    * 查找所有认证
    * @return
    */
    public List<AuthParamEntity> findAllAuthParam() {
        return jpaTemplate.findAll(AuthParamEntity.class);
    }

    /**
     * 根据查询参数查找认证列表
     * @param authParamQuery
     * @return
     */
    public List<AuthParamEntity> findAuthParamList(AuthParamQuery authParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthParamEntity.class)
                .eq("apiId", authParamQuery.getApiId())
                .orders(authParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, AuthParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找认证列表
     * @param authParamQuery
     * @return
     */
    public Pagination<AuthParamEntity> findAuthParamPage(AuthParamQuery authParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthParamEntity.class)
                .eq("apiId", authParamQuery.getApiId())
                .pagination(authParamQuery.getPageParam())
                .orders(authParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, AuthParamEntity.class);
    }
}