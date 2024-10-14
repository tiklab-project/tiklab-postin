package io.tiklab.postin.api.http.mock.dao;

import io.tiklab.postin.api.http.mock.entity.FormParamMockEntity;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.postin.api.http.mock.model.FormParamMockQuery;
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
 * mock
 * formdata 数据访问
 */
@Repository
public class FormParamMockDao{

    private static Logger logger = LoggerFactory.getLogger(FormParamMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建form-data
     * @param formParamMockEntity
     * @return
     */
    public String createFormParamMock(FormParamMockEntity formParamMockEntity) {
        return jpaTemplate.save(formParamMockEntity,String.class);
    }

    /**
     * 更新form-data
     * @param formParamMockEntity
     */
    public void updateFormParamMock(FormParamMockEntity formParamMockEntity){
        jpaTemplate.update(formParamMockEntity);
    }

    /**
     * 删除form-data
     * @param id
     */
    public void deleteFormParamMock(String id){
        jpaTemplate.delete(FormParamMockEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteFormParamMockList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 查找form-data
     * @param id
     * @return
     */
    public FormParamMockEntity findFormParamMock(String id){
        return jpaTemplate.findOne(FormParamMockEntity.class,id);
    }

    /**
    * 查找所有form-data
    * @return
    */
    public List<FormParamMockEntity> findAllFormParamMock() {
        return jpaTemplate.findAll(FormParamMockEntity.class);
    }

    /**
     * 根据查询参数查找form-data
     * @param formParamMockQuery
     * @return
     */
    public List<FormParamMockEntity> findFormParamMockList(FormParamMockQuery formParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamMockEntity.class)
                .eq("mockId", formParamMockQuery.getMockId())
                .orders(formParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormParamMockEntity.class);
    }

    /**
     * 根据查询参数按分页查找form-data
     * @param formParamMockQuery
     * @return
     */
    public Pagination<FormParamMockEntity> findFormParamMockPage(FormParamMockQuery formParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamMockEntity.class)
                .eq("mockId", formParamMockQuery.getMockId())
                .pagination(formParamMockQuery.getPageParam())
                .orders(formParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, FormParamMockEntity.class);
    }
}