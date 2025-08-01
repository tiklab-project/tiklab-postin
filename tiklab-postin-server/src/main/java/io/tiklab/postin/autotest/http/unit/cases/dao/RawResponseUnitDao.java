package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.RawResponseUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.RawResponseUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应中raw 数据访问
 */
@Repository
public class RawResponseUnitDao {

    private static Logger logger = LoggerFactory.getLogger(RawResponseUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应中raw
     * @param rawResponseUnitEntity
     * @return
     */
    public String createRawResponse(RawResponseUnitEntity rawResponseUnitEntity) {
        return jpaTemplate.save(rawResponseUnitEntity,String.class);
    }

    /**
     * 更新响应中raw
     * @param rawResponseUnitEntity
     */
    public void updateRawResponse(RawResponseUnitEntity rawResponseUnitEntity){
        jpaTemplate.update(rawResponseUnitEntity);
    }

    /**
     * 删除响应中raw
     * @param id
     */
    public void deleteRawResponse(String id){
        jpaTemplate.delete(RawResponseUnitEntity.class,id);
    }

    public void deleteRawResponse(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找响应中raw
     * @param id
     * @return
     */
    public RawResponseUnitEntity findRawResponse(String id){
        return jpaTemplate.findOne(RawResponseUnitEntity.class,id);
    }

    /**
    * 查找所有响应中raw
    * @return
    */
    public List<RawResponseUnitEntity> findAllRawResponse() {
        return jpaTemplate.findAll(RawResponseUnitEntity.class);
    }

    public List<RawResponseUnitEntity> findRawResponseList(List<String> idList) {
        return jpaTemplate.findList(RawResponseUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询响应中raw列表
     * @param rawResponseUnitQuery
     * @return
     */
    public List<RawResponseUnitEntity> findRawResponseList(RawResponseUnitQuery rawResponseUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseUnitEntity.class)
                .eq("apiUnitId", rawResponseUnitQuery.getApiUnitId())
                .orders(rawResponseUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawResponseUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询响应中raw
     * @param rawResponseUnitQuery
     * @return
     */
    public Pagination<RawResponseUnitEntity> findRawResponsePage(RawResponseUnitQuery rawResponseUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseUnitEntity.class)
                .eq("apiUnitId", rawResponseUnitQuery.getApiUnitId())
                .orders(rawResponseUnitQuery.getOrderParams())
                .pagination(rawResponseUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RawResponseUnitEntity.class);
    }
}