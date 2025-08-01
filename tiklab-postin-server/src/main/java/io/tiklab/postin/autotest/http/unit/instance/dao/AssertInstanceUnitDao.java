package io.tiklab.postin.autotest.http.unit.instance.dao;

import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.instance.entity.AssertInstanceUnitEntity;
import io.tiklab.postin.autotest.http.unit.instance.model.AssertInstanceUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 断言实例 舒服访问
 */
@Repository
public class AssertInstanceUnitDao {

    private static Logger logger = LoggerFactory.getLogger(AssertInstanceUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建断言实例
     * @param assertInstanceUnitEntity
     * @return
     */
    public String createAssertInstance(AssertInstanceUnitEntity assertInstanceUnitEntity) {
        return jpaTemplate.save(assertInstanceUnitEntity,String.class);
    }

    /**
     * 更新
     * @param assertInstanceUnitEntity
     */
    public void updateAssertInstance(AssertInstanceUnitEntity assertInstanceUnitEntity){
        jpaTemplate.update(assertInstanceUnitEntity);
    }

    /**
     * 删除断言实例
     * @param id
     */
    public void deleteAssertInstance(String id){
        jpaTemplate.delete(AssertInstanceUnitEntity.class,id);
    }

    public void deleteAssertInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找断言实例
     * @param id
     * @return
     */
    public AssertInstanceUnitEntity findAssertInstance(String id){
        return jpaTemplate.findOne(AssertInstanceUnitEntity.class,id);
    }

    /**
    * 查找所有断言实例
    * @return
    */
    public List<AssertInstanceUnitEntity> findAllAssertInstance() {
        return jpaTemplate.findAll(AssertInstanceUnitEntity.class);
    }

    public List<AssertInstanceUnitEntity> findAssertInstanceList(List<String> idList) {
        return jpaTemplate.findList(AssertInstanceUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询断言实例列表
     * @param assertInstanceUnitQuery
     * @return
     */
    public List<AssertInstanceUnitEntity> findAssertInstanceList(AssertInstanceUnitQuery assertInstanceUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertInstanceUnitEntity.class)
                .eq("instanceId", assertInstanceUnitQuery.getInstanceId())
                .orders(assertInstanceUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AssertInstanceUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询断言实例
     * @param assertInstanceUnitQuery
     * @return
     */
    public Pagination<AssertInstanceUnitEntity> findAssertInstancePage(AssertInstanceUnitQuery assertInstanceUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertInstanceUnitEntity.class)
                .eq("instanceId", assertInstanceUnitQuery.getInstanceId())
                .pagination(assertInstanceUnitQuery.getPageParam())
                .orders(assertInstanceUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AssertInstanceUnitEntity.class);
    }
}