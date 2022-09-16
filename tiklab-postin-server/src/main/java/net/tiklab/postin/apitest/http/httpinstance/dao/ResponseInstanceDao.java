package net.tiklab.postin.apitest.http.httpinstance.dao;

import net.tiklab.postin.apitest.http.httpinstance.entity.RequestInstanceEntity;
import net.tiklab.postin.apitest.http.httpinstance.model.ResponseInstanceQuery;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.apitest.http.httpinstance.entity.ResponseInstanceEntity;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class ResponseInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseInstanceEntity
     * @return
     */
    public String createResponseInstance(ResponseInstanceEntity responseInstanceEntity) {
        return jpaTemplate.save(responseInstanceEntity,String.class);
    }

    /**
     * 更新用户
     * @param responseInstanceEntity
     */
    public void updateResponseInstance(ResponseInstanceEntity responseInstanceEntity){
        jpaTemplate.update(responseInstanceEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseInstance(String id){
        jpaTemplate.delete(ResponseInstanceEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseInstanceEntity findResponseInstance(String id){
        return jpaTemplate.findOne(ResponseInstanceEntity.class,id);
    }

    /**
    * findAllResponseInstance
    * @return
    */
    public List<ResponseInstanceEntity> findAllResponseInstance() {
        return jpaTemplate.findAll(ResponseInstanceEntity.class);
    }

    public List<ResponseInstanceEntity> findResponseInstanceList(List<String> idList) {
        return jpaTemplate.findList(ResponseInstanceEntity.class,idList);
    }

    public List<ResponseInstanceEntity> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestInstanceEntity.class)
                .eq("httpInstanceId", responseInstanceQuery.getHttpInstanceId())
                .orders(responseInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseInstanceEntity.class);
    }

    public Pagination<ResponseInstanceEntity> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestInstanceEntity.class)
                .eq("httpInstanceId", responseInstanceQuery.getHttpInstanceId())
                .pagination(responseInstanceQuery.getPageParam())
                .orders(responseInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseInstanceEntity.class);
    }
}