package io.tiklab.postin.api.http.test.instance.dao;

import io.tiklab.postin.api.http.test.instance.entity.RequestInstancesEntity;
import io.tiklab.postin.api.http.test.instance.entity.ResponseInstancesEntity;
import io.tiklab.postin.api.http.test.instance.model.ResponseInstanceQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
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
     * @param responseInstancesEntity
     * @return
     */
    public String createResponseInstance(ResponseInstancesEntity responseInstancesEntity) {
        return jpaTemplate.save(responseInstancesEntity,String.class);
    }

    /**
     * 更新用户
     * @param responseInstancesEntity
     */
    public void updateResponseInstance(ResponseInstancesEntity responseInstancesEntity){
        jpaTemplate.update(responseInstancesEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseInstance(String id){
        jpaTemplate.delete(ResponseInstancesEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseInstancesEntity findResponseInstance(String id){
        return jpaTemplate.findOne(ResponseInstancesEntity.class,id);
    }

    /**
    * findAllResponseInstance
    * @return
    */
    public List<ResponseInstancesEntity> findAllResponseInstance() {
        return jpaTemplate.findAll(ResponseInstancesEntity.class);
    }

    public List<ResponseInstancesEntity> findResponseInstanceList(List<String> idList) {
        return jpaTemplate.findList(ResponseInstancesEntity.class,idList);
    }

    public List<ResponseInstancesEntity> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestInstancesEntity.class)
                .eq("httpInstanceId", responseInstanceQuery.getHttpInstanceId())
                .orders(responseInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseInstancesEntity.class);
    }

    public Pagination<ResponseInstancesEntity> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestInstancesEntity.class)
                .eq("httpInstanceId", responseInstanceQuery.getHttpInstanceId())
                .pagination(responseInstanceQuery.getPageParam())
                .orders(responseInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseInstancesEntity.class);
    }
}