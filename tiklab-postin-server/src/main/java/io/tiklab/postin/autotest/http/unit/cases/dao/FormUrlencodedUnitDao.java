package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.postin.autotest.http.unit.cases.entity.FormUrlEncodedUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.FormUrlencodedUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * form-urlencoded 数据访问
 */
@Repository
public class FormUrlencodedUnitDao {

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建form-urlencoded
     * @param formUrlencodedUnitEntity
     * @return
     */
    public String createFormUrlencoded(FormUrlEncodedUnitEntity formUrlencodedUnitEntity) {
        return jpaTemplate.save(formUrlencodedUnitEntity,String.class);
    }

    /**
     * 更新form-urlencoded
     * @param formUrlencodedUnitEntity
     */
    public void updateFormUrlencoded(FormUrlEncodedUnitEntity formUrlencodedUnitEntity){
        jpaTemplate.update(formUrlencodedUnitEntity);
    }

    /**
     *  删除form-urlencoded
     * @param id
     */
    public void deleteFormUrlencoded(String id){
        jpaTemplate.delete(FormUrlEncodedUnitEntity.class,id);
    }

    public void deleteFormUrlencoded(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找form-urlencoded
     * @param id
     * @return
     */
    public FormUrlEncodedUnitEntity findFormUrlencoded(String id){
        return jpaTemplate.findOne(FormUrlEncodedUnitEntity.class,id);
    }

    /**
    * 查找所有form-urlencoded
    * @return
    */
    public List<FormUrlEncodedUnitEntity> findAllFormUrlencoded() {
        return jpaTemplate.findAll(FormUrlEncodedUnitEntity.class);
    }

    public List<FormUrlEncodedUnitEntity> findFormUrlencodedList(List<String> idList) {
        return jpaTemplate.findList(FormUrlEncodedUnitEntity.class,idList);
    }

    /**
     * 查询form-urlencoded 列表
     * @param formUrlencodedUnitQuery
     * @return
     */
    public List<FormUrlEncodedUnitEntity> findFormUrlencodedList(FormUrlencodedUnitQuery formUrlencodedUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlEncodedUnitEntity.class)
                .eq("apiUnitId", formUrlencodedUnitQuery.getApiUnitId())
                .orders(formUrlencodedUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormUrlEncodedUnitEntity.class);
    }

    /**
     * 按分页查询form-urlencoded
     * @param formUrlencodedUnitQuery
     * @return
     */
    public Pagination<FormUrlEncodedUnitEntity> findFormUrlencodedPage(FormUrlencodedUnitQuery formUrlencodedUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlEncodedUnitEntity.class)
                .eq("apiUnitId", formUrlencodedUnitQuery.getApiUnitId())
                .orders(formUrlencodedUnitQuery.getOrderParams())
                .pagination(formUrlencodedUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, FormUrlEncodedUnitEntity.class);
    }
}