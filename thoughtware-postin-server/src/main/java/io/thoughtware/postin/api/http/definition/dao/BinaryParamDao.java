package io.thoughtware.postin.api.http.definition.dao;

import io.thoughtware.postin.api.http.definition.entity.BinaryParamEntity;
import io.thoughtware.postin.api.http.definition.model.BinaryParamQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * http协议
 * binary 数据访问
 */
@Repository
public class BinaryParamDao{

    private static Logger logger = LoggerFactory.getLogger(BinaryParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建binary参数
     * @param binaryParamEntity
     * @return
     */
    public String createBinaryParam(BinaryParamEntity binaryParamEntity) {
        return jpaTemplate.save(binaryParamEntity,String.class);
    }

    /**
     * 更新binary参数
     * @param binaryParamEntity
     */
    public void updateBinaryParam(BinaryParamEntity binaryParamEntity){
        jpaTemplate.update(binaryParamEntity);
    }

    /**
     * 删除binary参数
     * @param id
     */
    public void deleteBinaryParam(String id){
        jpaTemplate.delete(BinaryParamEntity.class,id);
    }

    /**
     * 更加条件删除binary
     * @param deleteCondition
     */
    public void deleteBinaryParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查找binary参数
     * @param id
     * @return
     */
    public BinaryParamEntity findBinaryParam(String id){
        return jpaTemplate.findOne(BinaryParamEntity.class,id);
    }

    /**
    * 查找所有binary
    * @return
    */
    public List<BinaryParamEntity> findAllBinaryParam() {
        return jpaTemplate.findAll(BinaryParamEntity.class);
    }

    /**
     * 通过list查找binary列表
     * @param idList
     * @return
     */
    public List<BinaryParamEntity> findBinaryParamList(List<String> idList) {
        return jpaTemplate.findList(BinaryParamEntity.class,idList);
    }

    /**
     * 根据查询参数查找binary列表
     * @param binaryParamQuery
     * @return
     */
    public List<BinaryParamEntity> findBinaryParamList(BinaryParamQuery binaryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BinaryParamEntity.class)
                .eq("httpId",binaryParamQuery.getHttpId())
                .orders(binaryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,BinaryParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找binary列表
     * @param binaryParamQuery
     * @return
     */
    public Pagination<BinaryParamEntity> findBinaryParamPage(BinaryParamQuery binaryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BinaryParamEntity.class)
                .eq("httpId",binaryParamQuery.getHttpId())
                .pagination(binaryParamQuery.getPageParam())
                .orders(binaryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,BinaryParamEntity.class);
    }
}