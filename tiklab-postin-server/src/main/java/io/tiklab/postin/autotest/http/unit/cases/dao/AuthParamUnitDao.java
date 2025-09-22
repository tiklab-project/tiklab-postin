package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.unit.cases.entity.AuthParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthParamUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 认证 数据访问
 */
@Repository
public class AuthParamUnitDao {

    private static Logger logger = LoggerFactory.getLogger(AuthParamUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建认证
     * @param authParamUnitEntity
     * @return
     */
    public String createAuthParamUnit(AuthParamUnitEntity authParamUnitEntity) {
        return jpaTemplate.save(authParamUnitEntity,String.class);
    }

    /**
     * 更新认证
     * @param authParamUnitEntity
     */
    public void updateAuthParamUnit(AuthParamUnitEntity authParamUnitEntity){
        jpaTemplate.update(authParamUnitEntity);
    }

    /**
     * 删除认证
     * @param id
     */
    public void deleteAuthParamUnit(String id){
        jpaTemplate.delete(AuthParamUnitEntity.class,id);
    }

    /**
     * 通过条件删除认证
     * @param deleteCondition
     */
    public void deleteAuthParamUnitList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找认证
     * @param id
     * @return
     */
    public AuthParamUnitEntity findAuthParamUnit(String id){
        return jpaTemplate.findOne(AuthParamUnitEntity.class,id);
    }

    /**
    * 查找所有认证
    * @return
    */
    public List<AuthParamUnitEntity> findAllAuthParamUnit() {
        return jpaTemplate.findAll(AuthParamUnitEntity.class);
    }

    /**
     * 根据查询参数查找认证列表
     * @param authParamUnitQuery
     * @return
     */
    public List<AuthParamUnitEntity> findAuthParamUnitList(AuthParamUnitQuery authParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthParamUnitEntity.class)
                .eq("apiUnitId", authParamUnitQuery.getApiUnitId())
                .orders(authParamUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, AuthParamUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查找认证列表
     * @param authParamUnitQuery
     * @return
     */
    public Pagination<AuthParamUnitEntity> findAuthParamUnitPage(AuthParamUnitQuery authParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AuthParamUnitEntity.class)
                .eq("apiUnitId", authParamUnitQuery.getApiUnitId())
                .pagination(authParamUnitQuery.getPageParam())
                .orders(authParamUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, AuthParamUnitEntity.class);
    }
}