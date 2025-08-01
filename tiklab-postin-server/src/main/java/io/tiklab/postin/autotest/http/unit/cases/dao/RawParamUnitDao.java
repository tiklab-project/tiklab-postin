package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.RawParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.RawParamUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * raw类型 数据访问
 */
@Repository
public class RawParamUnitDao {

    private static Logger logger = LoggerFactory.getLogger(RawParamUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建raw
     * @param rawParamUnitEntity
     * @return
     */
    public String createRawParam(RawParamUnitEntity rawParamUnitEntity) {
        return jpaTemplate.save(rawParamUnitEntity,String.class);
    }

    /**
     * 更新raw
     * @param rawParamUnitEntity
     */
    public void updateRawParam(RawParamUnitEntity rawParamUnitEntity){
        jpaTemplate.update(rawParamUnitEntity);
    }

    /**
     * 删除raw
     * @param id
     */
    public void deleteRawParam(String id){
        jpaTemplate.delete(RawParamUnitEntity.class,id);
    }

    public void deleteRawParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找raw
     * @param id
     * @return
     */
    public RawParamUnitEntity findRawParam(String id){
        return jpaTemplate.findOne(RawParamUnitEntity.class,id);
    }

    /**
    * 查找所有raw
    * @return
    */
    public List<RawParamUnitEntity> findAllRawParam() {
        return jpaTemplate.findAll(RawParamUnitEntity.class);
    }

    public List<RawParamUnitEntity> findRawParamList(List<String> idList) {
        return jpaTemplate.findList(RawParamUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询raw列表
     * @param rawParamUnitQuery
     * @return
     */
    public List<RawParamUnitEntity> findRawParamList(RawParamUnitQuery rawParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamUnitEntity.class)
                .eq("apiUnitId", rawParamUnitQuery.getApiUnitId())
                .orders(rawParamUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawParamUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询raw
     * @param rawParamUnitQuery
     * @return
     */
    public Pagination<RawParamUnitEntity> findRawParamPage(RawParamUnitQuery rawParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamUnitEntity.class)
                .eq("apiUnitId", rawParamUnitQuery.getApiUnitId())
                .orders(rawParamUnitQuery.getOrderParams())
                .pagination(rawParamUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RawParamUnitEntity.class);
    }
}