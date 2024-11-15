package io.tiklab.postin.api.http.test.instance.dao;

import io.tiklab.postin.api.http.test.instance.entity.HttpInstanceEntity;
import io.tiklab.postin.api.http.test.instance.model.HttpInstanceQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class HttpInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(HttpInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param httpInstanceEntity
     * @return
     */
    public String createTestInstance(HttpInstanceEntity httpInstanceEntity) {
        return jpaTemplate.save(httpInstanceEntity,String.class);
    }

    /**
     * 更新用户
     * @param httpInstanceEntity
     */
    public void updateTestInstance(HttpInstanceEntity httpInstanceEntity){
        jpaTemplate.update(httpInstanceEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteTestInstance(String id){
        jpaTemplate.delete(HttpInstanceEntity.class,id);
    }

    public void deleteAllTestInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public HttpInstanceEntity findTestInstance(String id){
        return jpaTemplate.findOne(HttpInstanceEntity.class,id);
    }

    /**
    * findAllTestInstance
    * @return
    */
    public List<HttpInstanceEntity> findAllTestInstance() {
        return jpaTemplate.findAll(HttpInstanceEntity.class);
    }

    public List<HttpInstanceEntity> findTestInstanceList(List<String> idList) {
        return jpaTemplate.findList(HttpInstanceEntity.class,idList);
    }

    public List<HttpInstanceEntity> findTestInstanceList(HttpInstanceQuery httpInstanceQuery) {
        String sql = "SELECT pi.id,pi.create_time,pi.user_id,pi.size,pi.result,pi.status_code,pi.time,pi.workspace_id,pir.method_type,pir.URL"+
                    " FROM postin_instance pi JOIN postin_instance_http_request pir ON pi.id = pir.http_instance_id "+
                    " WHERE pi.workspace_id = '" + httpInstanceQuery.getWorkspaceId() +"'"+
                    " AND pi.user_id = '" + httpInstanceQuery.getUserId() +"'";

        if(httpInstanceQuery.getUrl()!=null){
            sql = sql + " AND pir.URL like '"+"%"+ httpInstanceQuery.getUrl() +"%"+"'";
        }

        sql = sql + " ORDER BY pi.create_time asc;";

        return jpaTemplate.getJdbcTemplate().query(sql , new BeanPropertyRowMapper(HttpInstanceEntity.class));
    }

    public Pagination<HttpInstanceEntity> findTestInstancePage(HttpInstanceQuery httpInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpInstanceEntity.class)
                .eq("workspaceId",httpInstanceQuery.getWorkspaceId())
                .eq("httpCaseId", httpInstanceQuery.getHttpCaseId())
                .eq("userId",httpInstanceQuery.getUserId())
                .pagination(httpInstanceQuery.getPageParam())
                .orders(httpInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, HttpInstanceEntity.class);
    }
}