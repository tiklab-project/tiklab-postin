package io.tiklab.postin.api.http.test.instance.dao;

import io.tiklab.postin.api.http.test.instance.entity.RequestInstancesEntity;
import io.tiklab.postin.api.http.test.instance.model.RequestInstanceQuery;
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
public class RequestInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(RequestInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestInstancesEntity
     * @return
     */
    public String createRequestInstance(RequestInstancesEntity requestInstancesEntity) {
        return jpaTemplate.save(requestInstancesEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestInstancesEntity
     */
    public void updateRequestInstance(RequestInstancesEntity requestInstancesEntity){
        jpaTemplate.update(requestInstancesEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestInstance(String id){
        jpaTemplate.delete(RequestInstancesEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestInstancesEntity findRequestInstance(String id){
        return jpaTemplate.findOne(RequestInstancesEntity.class,id);
    }

    /**
    * findAllRequestInstance
    * @return
    */
    public List<RequestInstancesEntity> findAllRequestInstance() {
        return jpaTemplate.findAll(RequestInstancesEntity.class);
    }

    public List<RequestInstancesEntity> findRequestInstanceList(List<String> idList) {
        return jpaTemplate.findList(RequestInstancesEntity.class,idList);
    }

    public List<RequestInstancesEntity> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestInstancesEntity.class)
                .eq("httpInstanceId", requestInstanceQuery.getHttpInstanceId())
                .like("url",requestInstanceQuery.getUrl())
                .orders(requestInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestInstancesEntity.class);
    }

    public Pagination<RequestInstancesEntity> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestInstancesEntity.class)
                .eq("httpInstanceId", requestInstanceQuery.getHttpInstanceId())
                .like("url",requestInstanceQuery.getUrl())
                .pagination(requestInstanceQuery.getPageParam())
                .orders(requestInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestInstancesEntity.class);
    }

}