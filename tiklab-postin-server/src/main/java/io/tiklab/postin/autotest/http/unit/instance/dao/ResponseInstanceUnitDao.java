package io.tiklab.postin.autotest.http.unit.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.instance.entity.ResponseInstanceUnitEntity;
import io.tiklab.postin.autotest.http.unit.instance.model.ResponseInstanceUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应数据实例 数据访问
 */
@Repository
public class ResponseInstanceUnitDao {

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应数据实例
     * @param responseInstanceUnitEntity
     * @return
     */
    public String createResponseInstance(ResponseInstanceUnitEntity responseInstanceUnitEntity) {
        return jpaTemplate.save(responseInstanceUnitEntity,String.class);
    }

    /**
     * 更新响应数据实例
     * @param responseInstanceUnitEntity
     */
    public void updateResponseInstance(ResponseInstanceUnitEntity responseInstanceUnitEntity){
        jpaTemplate.update(responseInstanceUnitEntity);
    }

    /**
     * 删除响应数据实例
     * @param id
     */
    public void deleteResponseInstance(String id){
        jpaTemplate.delete(ResponseInstanceUnitEntity.class,id);
    }

    public void deleteResponseInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找响应数据实例
     * @param id
     * @return
     */
    public ResponseInstanceUnitEntity findResponseInstance(String id){
        return jpaTemplate.findOne(ResponseInstanceUnitEntity.class,id);
    }

    /**
    * 查找所有响应数据实例
    * @return
    */
    public List<ResponseInstanceUnitEntity> findAllResponseInstance() {
        return jpaTemplate.findAll(ResponseInstanceUnitEntity.class);
    }

    public List<ResponseInstanceUnitEntity> findResponseInstanceList(List<String> idList) {
        return jpaTemplate.findList(ResponseInstanceUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询响应数据实例列表
     * @param responseInstanceUnitQuery
     * @return
     */
    public List<ResponseInstanceUnitEntity> findResponseInstanceList(ResponseInstanceUnitQuery responseInstanceUnitQuery) {
        return jpaTemplate.findList(responseInstanceUnitQuery, ResponseInstanceUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询响应数据实例
     * @param responseInstanceUnitQuery
     * @return
     */
    public Pagination<ResponseInstanceUnitEntity> findResponseInstancePage(ResponseInstanceUnitQuery responseInstanceUnitQuery) {
        return jpaTemplate.findPage(responseInstanceUnitQuery, ResponseInstanceUnitEntity.class);
    }
}