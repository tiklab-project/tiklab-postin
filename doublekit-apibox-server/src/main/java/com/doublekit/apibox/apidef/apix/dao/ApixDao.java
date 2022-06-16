package com.doublekit.apibox.apidef.apix.dao;

import com.doublekit.apibox.apidef.apix.entity.ApixEntity;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.core.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.DeleteCondition;
import com.doublekit.dal.jpa.criterial.condition.QueryCondition;
import com.doublekit.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BasedefDao
 */
@Repository
public class ApixDao {

    private static Logger logger = LoggerFactory.getLogger(ApixDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apixEntity
     * @return
     */
    public String createApix(ApixEntity apixEntity) {
        return jpaTemplate.save(apixEntity,String.class);
    }

    /**
     * 更新
     * @param apixEntity
     */
    public void updateApix(ApixEntity apixEntity){
        jpaTemplate.update(apixEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApix(String id){
        jpaTemplate.delete(ApixEntity.class,id);
    }

    public void deleteApix(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApixEntity findApix(String id){
        return jpaTemplate.findOne(ApixEntity.class,id);
    }

    /**
    * findAllApix
    * @return
    */
    public List<ApixEntity> findAllApix() {
        return jpaTemplate.findAll(ApixEntity.class);
    }

    public List<ApixEntity> findApixList(List<String> idList) {
        return jpaTemplate.findList(ApixEntity.class,idList);
    }

    public List<ApixEntity> findApixList(ApixQuery apixQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApixEntity.class)
                .eq("categoryId", apixQuery.getCategoryId())
                .eq("protocolType", apixQuery.getProtocolType())
                .like("name", apixQuery.getName())
                .orders(apixQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApixEntity.class);
    }

    public Pagination<ApixEntity> findApixPage(ApixQuery apixQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApixEntity.class)
                .eq("categoryId", apixQuery.getCategoryId())
                .eq("protocolType", apixQuery.getProtocolType())
                .like("name", apixQuery.getName())
                .pagination(apixQuery.getPageParam())
                .orders(apixQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApixEntity.class);
    }


//    public Pagination<ApixEntity> findApiCount(ApixQuery apixQuery) {
//        QueryCondition queryCondition = QueryBuilders.createQuery(ApixEntity.class)
//                .eq("categoryId", apixQuery.getCategoryId())
//                .eq("protocolType", apixQuery.getProtocolType())
//                .like("name", apixQuery.getName())
//                .pagination(apixQuery.getPageParam())
//                .orders(apixQuery.getOrderParams())
//                .get();
//        return jpaTemplate.findPage(queryCondition, ApixEntity.class);
//    }



}