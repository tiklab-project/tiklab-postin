package io.tiklab.postin.api.apix.dao;

import io.tiklab.postin.api.apix.entity.ApiRecentEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.apix.model.ApiRecentQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 最近访问空间 数据访问
 */
@Repository
public class ApiRecentDao {

    private static Logger logger = LoggerFactory.getLogger(ApiRecentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建最近访问空间
     * @param apiRecentEntity
     * @return
     */
    public String createApiRecent(ApiRecentEntity apiRecentEntity) {
        return jpaTemplate.save(apiRecentEntity,String.class);
    }

    /**
     * 更新最近访问空间
     * @param apiRecentEntity
     */
    public void updateApiRecent(ApiRecentEntity apiRecentEntity){
        jpaTemplate.update(apiRecentEntity);
    }

    /**
     * 删除最近访问空间
     * @param id
     */
    public void deleteApiRecent(String id){
        jpaTemplate.delete(ApiRecentEntity.class,id);
    }

    public void deleteApiRecent(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查找最近访问空间
     * @param id
     * @return
     */
    public ApiRecentEntity findApiRecent(String id){
        return jpaTemplate.findOne(ApiRecentEntity.class,id);
    }

    /**
    * 查找所有最近访问空间
    * @return
    */
    public List<ApiRecentEntity> findAllApiRecent() {
        return jpaTemplate.findAll(ApiRecentEntity.class);
    }

    public List<ApiRecentEntity> findApiRecentList(List<String> idList) {
        return jpaTemplate.findList(ApiRecentEntity.class,idList);
    }

    /**
     * 根据查询参数查找最近访问空间
     * @param apiRecentQuery
     * @return
     */
    public List<ApiRecentEntity> findApiRecentList(ApiRecentQuery apiRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiRecentEntity.class)
                .eq("apixId",apiRecentQuery.getApixId())
                .eq("userId", apiRecentQuery.getUserId())
                .orders(apiRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiRecentEntity.class);
    }

    /**
     * 根据查询参数按分页查找最近访问空间
     * @param apiRecentQuery
     * @return
     */
    public Pagination<ApiRecentEntity> findApiRecentPage(ApiRecentQuery apiRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiRecentEntity.class)
                .eq("apixId",apiRecentQuery.getApixId())
                .eq("userId", apiRecentQuery.getUserId())
                .pagination(apiRecentQuery.getPageParam())
                .orders(apiRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiRecentEntity.class);
    }
}