package net.tiklab.postin.support.apistatus.dao;

import net.tiklab.postin.support.apistatus.entity.ApiStatusEntity;
import net.tiklab.postin.support.apistatus.model.ApiStatusQuery;
import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口状态 数据访问
 */
@Repository
public class ApiStatusDao{

    private static Logger logger = LoggerFactory.getLogger(ApiStatusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口状态
     * @param apiStatusEntity
     * @return
     */
    public String createApiStatus(ApiStatusEntity apiStatusEntity) {
        return jpaTemplate.save(apiStatusEntity,String.class);
    }

    /**
     * 更新接口状态
     * @param apiStatusEntity
     */
    public void updateApiStatus(ApiStatusEntity apiStatusEntity){
        jpaTemplate.update(apiStatusEntity);
    }

    /**
     * 删除接口状态
     * @param id
     */
    public void deleteApiStatus(String id){
        jpaTemplate.delete(ApiStatusEntity.class,id);
    }

    public void deleteApiStatus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找接口状态
     * @param id
     * @return
     */
    public ApiStatusEntity findApiStatus(String id){
        return jpaTemplate.findOne(ApiStatusEntity.class,id);
    }

    /**
    * 查找所有接口状态
    * @return
    */
    public List<ApiStatusEntity> findAllApiStatus() {
        return jpaTemplate.findAll(ApiStatusEntity.class);
    }

    public List<ApiStatusEntity> findApiStatusList(List<String> idList) {
        return jpaTemplate.findList(ApiStatusEntity.class,idList);
    }

    /**
     * 根据查询参数查找接口状态
     * @param apiStatusQuery
     * @return
     */
    public List<ApiStatusEntity> findApiStatusList(ApiStatusQuery apiStatusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiStatusEntity.class)
                .eq("workspaceId",apiStatusQuery.getWorkspaceId())
                .eq("type",apiStatusQuery.getType())
                .orders(apiStatusQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiStatusEntity.class);
    }

    /**
     * 根据查询参数按分页查找接口状态
     * @param apiStatusQuery
     * @return
     */
    public Pagination<ApiStatusEntity> findApiStatusPage(ApiStatusQuery apiStatusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiStatusEntity.class)
                .eq("workspaceId",apiStatusQuery.getWorkspaceId())
                .eq("type",apiStatusQuery.getType())
                .orders(apiStatusQuery.getOrderParams())
                .pagination(apiStatusQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiStatusEntity.class);
    }
}