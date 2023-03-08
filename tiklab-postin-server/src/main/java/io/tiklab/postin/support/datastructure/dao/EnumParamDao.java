package io.tiklab.postin.support.datastructure.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.support.datastructure.entity.EnumParamEntity;
import io.tiklab.postin.support.datastructure.model.EnumParamQuery;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * enum类型的数据结构 数据访问
 */
@Repository
public class EnumParamDao{

    private static Logger logger = LoggerFactory.getLogger(EnumParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建enum类型的数据结构
     * @param enumParamEntity
     * @return
     */
    public String createEnumParam(EnumParamEntity enumParamEntity) {
        return jpaTemplate.save(enumParamEntity,String.class);
    }

    /**
     * 更新enum类型的数据结构
     * @param enumParamEntity
     */
    public void updateEnumParam(EnumParamEntity enumParamEntity){
        jpaTemplate.update(enumParamEntity);
    }

    /**
     * 删除enum类型的数据结构
     * @param id
     */
    public void deleteEnumParam(String id){
        jpaTemplate.delete(EnumParamEntity.class,id);
    }

    public void deleteEnumParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找enum类型的数据结构
     * @param id
     * @return
     */
    public EnumParamEntity findEnumParam(String id){
        return jpaTemplate.findOne(EnumParamEntity.class,id);
    }

    /**
    * 查询所有枚举类型内容
    * @return
    */
    public List<EnumParamEntity> findAllEnumParam() {
        return jpaTemplate.findAll(EnumParamEntity.class);
    }

    public List<EnumParamEntity> findEnumParamList(List<String> idList) {
        return jpaTemplate.findList(EnumParamEntity.class,idList);
    }

    /**
     * 根据查询对象 查询枚举类型内容
     * @param enumParamQuery
     * @return
     */
    public List<EnumParamEntity> findEnumParamList(EnumParamQuery enumParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnumParamEntity.class)
                .eq("dataStructureId", enumParamQuery.getDataStructureId())
                .orders(enumParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, EnumParamEntity.class);
    }

    /**
     * 根据查询对象查询枚举类型数据结构内容
     * @param enumParamQuery
     * @return
     */
    public Pagination<EnumParamEntity> findEnumParamPage(EnumParamQuery enumParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnumParamEntity.class)
                .eq("dataStructureId", enumParamQuery.getDataStructureId())
                .pagination(enumParamQuery.getPageParam())
                .orders(enumParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, EnumParamEntity.class);
    }
}