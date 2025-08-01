package io.tiklab.postin.autotest.common.stepassert.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.postin.autotest.common.stepassert.entity.VariableAssertEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 变量类型的断言 数据访问
 */
@Repository
public class VariableAssertDao {

    private static Logger logger = LoggerFactory.getLogger(VariableAssertDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param variableAssertEntity
     * @return
     */
    public String createVariableAssert(VariableAssertEntity variableAssertEntity) {
        return jpaTemplate.save(variableAssertEntity,String.class);
    }

    /**
     * 更新
     * @param variableAssertEntity
     */
    public void updateVariableAssert(VariableAssertEntity variableAssertEntity){
        jpaTemplate.update(variableAssertEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteVariableAssert(String id){
        jpaTemplate.delete(VariableAssertEntity.class,id);
    }

    public void deleteVariableAssert(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public VariableAssertEntity findVariableAssert(String id){
        return jpaTemplate.findOne(VariableAssertEntity.class,id);
    }


 
}