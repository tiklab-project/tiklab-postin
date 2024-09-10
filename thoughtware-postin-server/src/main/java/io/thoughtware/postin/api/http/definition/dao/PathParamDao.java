package io.thoughtware.postin.api.http.definition.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.postin.api.http.definition.entity.PathParamEntity;
import io.thoughtware.postin.api.http.definition.model.PathParamQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Path 数据访问
 */
@Repository
public class PathParamDao {

    private static Logger logger = LoggerFactory.getLogger(PathParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建Path
     * @param pathParamEntity
     * @return
     */
    public String createPathParam(PathParamEntity pathParamEntity) {
        return jpaTemplate.save(pathParamEntity,String.class);
    }

    /**
     * 更新Path
     * @param pathParamEntity
     */
    public void updatePathParam(PathParamEntity pathParamEntity){
        jpaTemplate.update(pathParamEntity);
    }

    /**
     * 删除Path
     * @param id
     */
    public void deletePathParam(String id){
        jpaTemplate.delete(PathParamEntity.class,id);
    }

    /**
     * 通过条件删除Path
     * @param deleteCondition
     */
    public void deletePathParamList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找Path
     * @param id
     * @return
     */
    public PathParamEntity findPathParam(String id){
        return jpaTemplate.findOne(PathParamEntity.class,id);
    }

    /**
    * 查找所有Path
    * @return
    */
    public List<PathParamEntity> findAllPathParam() {
        return jpaTemplate.findAll(PathParamEntity.class);
    }

    /**
     * 根据查询参数查找Path列表
     * @param pathParamQuery
     * @return
     */
    public List<PathParamEntity> findPathParamList(PathParamQuery pathParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PathParamEntity.class)
                .eq("apiId", pathParamQuery.getApiId())
                .eq("workspaceId",pathParamQuery.getWorkspaceId())
                .orders(pathParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, PathParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找Path列表
     * @param pathParamQuery
     * @return
     */
    public Pagination<PathParamEntity> findPathParamPage(PathParamQuery pathParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PathParamEntity.class)
                .eq("apiId", pathParamQuery.getApiId())
                .eq("workspaceId",pathParamQuery.getWorkspaceId())
                .pagination(pathParamQuery.getPageParam())
                .orders(pathParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, PathParamEntity.class);
    }
}