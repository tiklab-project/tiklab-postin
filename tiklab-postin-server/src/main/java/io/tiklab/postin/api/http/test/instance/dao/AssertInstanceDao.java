package io.tiklab.postin.api.http.test.instance.dao;

import io.tiklab.postin.api.http.test.instance.entity.AssertInstancesEntity;
import io.tiklab.postin.api.http.test.instance.model.AssertInstanceQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class AssertInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(AssertInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param assertInstancesEntity
     * @return
     */
    public String createAssertInstance(AssertInstancesEntity assertInstancesEntity) {
        return jpaTemplate.save(assertInstancesEntity,String.class);
    }

    /**
     * 更新用户
     * @param assertInstancesEntity
     */
    public void updateAssertInstance(AssertInstancesEntity assertInstancesEntity){
        jpaTemplate.update(assertInstancesEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAssertInstance(String id){
        jpaTemplate.delete(AssertInstancesEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public AssertInstancesEntity findAssertInstance(String id){
        return jpaTemplate.findOne(AssertInstancesEntity.class,id);
    }

    /**
    * findAllAssertInstance
    * @return
    */
    public List<AssertInstancesEntity> findAllAssertInstance() {
        return jpaTemplate.findAll(AssertInstancesEntity.class);
    }

    public List<AssertInstancesEntity> findAssertInstanceList(List<String> idList) {
        return jpaTemplate.findList(AssertInstancesEntity.class,idList);
    }

    public List<AssertInstancesEntity> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertInstancesEntity.class)
                .eq("httpInstanceId", assertInstanceQuery.getHttpInstanceId())
                .orders(assertInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AssertInstancesEntity.class);
    }

    public Pagination<AssertInstancesEntity> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertInstancesEntity.class)
                .eq("httpInstanceId", assertInstanceQuery.getHttpInstanceId())
                .pagination(assertInstanceQuery.getPageParam())
                .orders(assertInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AssertInstancesEntity.class);
    }
}