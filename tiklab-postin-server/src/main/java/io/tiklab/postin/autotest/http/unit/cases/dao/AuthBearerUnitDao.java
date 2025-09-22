package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.unit.cases.entity.AuthBearerUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthBearerUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BearerToken认证 数据访问
 */
@Repository
public class AuthBearerUnitDao {

    private static Logger logger = LoggerFactory.getLogger(AuthBearerUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建BearerToken认证
     * @param authBearerUnitEntity
     * @return
     */
    public String createAuthBearerUnit(AuthBearerUnitEntity authBearerUnitEntity) {
        return jpaTemplate.save(authBearerUnitEntity,String.class);
    }

    /**
     * 更新BearerToken认证
     * @param authBearerUnitEntity
     */
    public void updateAuthBearerUnit(AuthBearerUnitEntity authBearerUnitEntity){
        jpaTemplate.update(authBearerUnitEntity);
    }

    /**
     * 删除BearerToken认证
     * @param id
     */
    public void deleteAuthBearerUnit(String id){
        jpaTemplate.delete(AuthBearerUnitEntity.class,id);
    }

    /**
     * 通过条件删除BearerToken认证
     * @param deleteCondition
     */
    public void deleteAuthBearerUnitList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找BearerToken认证
     * @param id
     * @return
     */
    public AuthBearerUnitEntity findAuthBearerUnit(String id){
        return jpaTemplate.findOne(AuthBearerUnitEntity.class,id);
    }

    /**
    * 查找所有BearerToken认证
    * @return
    */
    public List<AuthBearerUnitEntity> findAllAuthBearerUnit() {
        return jpaTemplate.findAll(AuthBearerUnitEntity.class);
    }

    /**
     * 根据查询参数查找BearerToken认证列表
     * @param authBearerUnitQuery
     * @return
     */
    public List<AuthBearerUnitEntity> findAuthBearerUnitList(AuthBearerUnitQuery authBearerUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthBearerUnitEntity.class)
                .eq("apiUnitId", authBearerUnitQuery.getApiUnitId())
                .orders(authBearerUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, AuthBearerUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查找BearerToken认证列表
     * @param authBearerUnitQuery
     * @return
     */
    public Pagination<AuthBearerUnitEntity> findAuthBearerUnitPage(AuthBearerUnitQuery authBearerUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthBearerUnitEntity.class)
                .eq("apiUnitId", authBearerUnitQuery.getApiUnitId())
                .pagination(authBearerUnitQuery.getPageParam())
                .orders(authBearerUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, AuthBearerUnitEntity.class);
    }
}