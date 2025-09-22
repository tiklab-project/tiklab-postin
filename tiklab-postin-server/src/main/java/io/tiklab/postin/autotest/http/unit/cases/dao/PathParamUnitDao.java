package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.unit.cases.entity.PathParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.PathParamUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Path 数据访问
 */
@Repository
public class PathParamUnitDao {

    private static Logger logger = LoggerFactory.getLogger(PathParamUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建Path
     * @param pathParamUnitEntity
     * @return
     */
    public String createPathParamUnit(PathParamUnitEntity pathParamUnitEntity) {
        return jpaTemplate.save(pathParamUnitEntity,String.class);
    }

    /**
     * 更新Path
     * @param pathParamUnitEntity
     */
    public void updatePathParamUnit(PathParamUnitEntity pathParamUnitEntity){
        jpaTemplate.update(pathParamUnitEntity);
    }

    /**
     * 删除Path
     * @param id
     */
    public void deletePathParamUnit(String id){
        jpaTemplate.delete(PathParamUnitEntity.class,id);
    }

    /**
     * 通过条件删除Path
     * @param deleteCondition
     */
    public void deletePathParamUnitList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找Path
     * @param id
     * @return
     */
    public PathParamUnitEntity findPathParamUnit(String id){
        return jpaTemplate.findOne(PathParamUnitEntity.class,id);
    }

    /**
    * 查找所有Path
    * @return
    */
    public List<PathParamUnitEntity> findAllPathParamUnit() {
        return jpaTemplate.findAll(PathParamUnitEntity.class);
    }

    /**
     * 根据查询参数查找Path列表
     * @param pathParamUnitQuery
     * @return
     */
    public List<PathParamUnitEntity> findPathParamUnitList(PathParamUnitQuery pathParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PathParamUnitEntity.class)
                .eq("apiUnitId", pathParamUnitQuery.getApiUnitId())
                .orders(pathParamUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, PathParamUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查找Path列表
     * @param pathParamUnitQuery
     * @return
     */
    public Pagination<PathParamUnitEntity> findPathParamUnitPage(PathParamUnitQuery pathParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PathParamUnitEntity.class)
                .eq("apiUnitId", pathParamUnitQuery.getApiUnitId())
                .pagination(pathParamUnitQuery.getPageParam())
                .orders(pathParamUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, PathParamUnitEntity.class);
    }
}