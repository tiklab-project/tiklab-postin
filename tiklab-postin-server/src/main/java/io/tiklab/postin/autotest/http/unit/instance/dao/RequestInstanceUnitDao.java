package io.tiklab.postin.autotest.http.unit.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.instance.entity.RequestInstanceUnitEntity;
import io.tiklab.postin.autotest.http.unit.instance.model.RequestInstanceUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RequestInstanceDao
 */
@Repository
public class RequestInstanceUnitDao {

    private static Logger logger = LoggerFactory.getLogger(RequestInstanceUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求数据实例
     * @param requestInstanceUnitEntity
     * @return
     */
    public String createRequestInstance(RequestInstanceUnitEntity requestInstanceUnitEntity) {
        return jpaTemplate.save(requestInstanceUnitEntity,String.class);
    }

    /**
     * 更新
     * @param requestInstanceUnitEntity
     */
    public void updateRequestInstance(RequestInstanceUnitEntity requestInstanceUnitEntity){
        jpaTemplate.update(requestInstanceUnitEntity);
    }

    /**
     * 删除请求数据实例
     * @param id
     */
    public void deleteRequestInstance(String id){
        jpaTemplate.delete(RequestInstanceUnitEntity.class,id);
    }

    public void deleteRequestInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求数据实例
     * @param id
     * @return
     */
    public RequestInstanceUnitEntity findRequestInstance(String id){
        return jpaTemplate.findOne(RequestInstanceUnitEntity.class,id);
    }

    /**
    * 查找所有请求数据实例
    * @return
    */
    public List<RequestInstanceUnitEntity> findAllRequestInstance() {
        return jpaTemplate.findAll(RequestInstanceUnitEntity.class);
    }

    public List<RequestInstanceUnitEntity> findRequestInstanceList(List<String> idList) {
        return jpaTemplate.findList(RequestInstanceUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询请求数据实例列表
     * @param requestInstanceUnitQuery
     * @return
     */
    public List<RequestInstanceUnitEntity> findRequestInstanceList(RequestInstanceUnitQuery requestInstanceUnitQuery) {
        return jpaTemplate.findList(requestInstanceUnitQuery, RequestInstanceUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询请求数据实例
     * @param requestInstanceUnitQuery
     * @return
     */
    public Pagination<RequestInstanceUnitEntity> findRequestInstancePage(RequestInstanceUnitQuery requestInstanceUnitQuery) {
        return jpaTemplate.findPage(requestInstanceUnitQuery, RequestInstanceUnitEntity.class);
    }
}