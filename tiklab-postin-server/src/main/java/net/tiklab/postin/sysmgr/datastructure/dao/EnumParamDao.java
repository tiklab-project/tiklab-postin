package net.tiklab.postin.sysmgr.datastructure.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.postin.sysmgr.datastructure.entity.EnumParamEntity;
import net.tiklab.postin.sysmgr.datastructure.model.EnumParamQuery;
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
 * EnumParamDao
 */
@Repository
public class EnumParamDao{

    private static Logger logger = LoggerFactory.getLogger(EnumParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param enumParamEntity
     * @return
     */
    public String createEnumParam(EnumParamEntity enumParamEntity) {
        return jpaTemplate.save(enumParamEntity,String.class);
    }

    /**
     * 更新
     * @param enumParamEntity
     */
    public void updateEnumParam(EnumParamEntity enumParamEntity){
        jpaTemplate.update(enumParamEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteEnumParam(String id){
        jpaTemplate.delete(EnumParamEntity.class,id);
    }

    public void deleteEnumParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public EnumParamEntity findEnumParam(String id){
        return jpaTemplate.findOne(EnumParamEntity.class,id);
    }

    /**
    * findAllEnumParam
    * @return
    */
    public List<EnumParamEntity> findAllEnumParam() {
        return jpaTemplate.findAll(EnumParamEntity.class);
    }

    public List<EnumParamEntity> findEnumParamList(List<String> idList) {
        return jpaTemplate.findList(EnumParamEntity.class,idList);
    }

    public List<EnumParamEntity> findEnumParamList(EnumParamQuery enumParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnumParamEntity.class)
                .eq("dataStructureId", enumParamQuery.getDataStructureId())
                .orders(enumParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, EnumParamEntity.class);
    }

    public Pagination<EnumParamEntity> findEnumParamPage(EnumParamQuery enumParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnumParamEntity.class)
                .eq("dataStructureId", enumParamQuery.getDataStructureId())
                .pagination(enumParamQuery.getPageParam())
                .orders(enumParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, EnumParamEntity.class);
    }
}