package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.FormParamsUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.FormParamUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * form-data 数据访问
 */
@Repository
public class FormParamUnitDao {

    private static Logger logger = LoggerFactory.getLogger(FormParamUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建form-data
     * @param formParamsUnitEntity
     * @return
     */
    public String createFormParam(FormParamsUnitEntity formParamsUnitEntity) {
        return jpaTemplate.save(formParamsUnitEntity,String.class);
    }

    /**
     * 更新form-data
     * @param formParamsUnitEntity
     */
    public void updateFormParam(FormParamsUnitEntity formParamsUnitEntity){
        jpaTemplate.update(formParamsUnitEntity);
    }

    /**
     * 删除form-data
     * @param id
     */
    public void deleteFormParam(String id){
        jpaTemplate.delete(FormParamsUnitEntity.class,id);
    }

    public void deleteFormParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找form-data
     * @param id
     * @return
     */
    public FormParamsUnitEntity findFormParam(String id){
        return jpaTemplate.findOne(FormParamsUnitEntity.class,id);
    }

    /**
    * 查找所有form-data
    * @return
    */
    public List<FormParamsUnitEntity> findAllFormParam() {
        return jpaTemplate.findAll(FormParamsUnitEntity.class);
    }

    public List<FormParamsUnitEntity> findFormParamList(List<String> idList) {
        return jpaTemplate.findList(FormParamsUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询form-data列表
     * @param formParamUnitQuery
     * @return
     */
    public List<FormParamsUnitEntity> findFormParamList(FormParamUnitQuery formParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamsUnitEntity.class)
                .eq("apiUnitId", formParamUnitQuery.getApiUnitId())
                .orders(formParamUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormParamsUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询form-data
     * @param formParamUnitQuery
     * @return
     */
    public Pagination<FormParamsUnitEntity> findFormParamPage(FormParamUnitQuery formParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamsUnitEntity.class)
                .eq("apiUnitId", formParamUnitQuery.getApiUnitId())
                .orders(formParamUnitQuery.getOrderParams())
                .pagination(formParamUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, FormParamsUnitEntity.class);
    }
}