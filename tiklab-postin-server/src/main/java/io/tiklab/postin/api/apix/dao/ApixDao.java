package io.tiklab.postin.api.apix.dao;

import io.tiklab.postin.api.apix.entity.ApixEntity;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.core.page.Pagination;
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
 * 接口公共数据访问
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
    * 查找所有
    * @return
    */
    public List<ApixEntity> findAllApix() {
        return jpaTemplate.findAll(ApixEntity.class);
    }


    /**
     * 通过list查询
     * @param idList
     * @return
     */
    public List<ApixEntity> findApixList(List<String> idList) {
        return jpaTemplate.findList(ApixEntity.class,idList);
    }

    /**
     * 通过查询参数查询List
     * @param apixQuery
     * @return
     */
    public List<ApixEntity> findApixList(ApixQuery apixQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApixEntity.class)
                .eq("categoryId", apixQuery.getCategoryId())
                .eq("protocolType", apixQuery.getProtocolType())
                .eq("version", apixQuery.getVersion())
                .eq("apiUid", apixQuery.getApiUid())
                .like("name", apixQuery.getName())
                .orders(apixQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApixEntity.class);
    }

    /**
     * 带分页的List
     * @param apixQuery
     * @return
     */
    public Pagination<ApixEntity> findApixPage(ApixQuery apixQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApixEntity.class)
                .eq("categoryId", apixQuery.getCategoryId())
                .eq("protocolType", apixQuery.getProtocolType())
                .eq("version", apixQuery.getVersion())
                .eq("apiUid", apixQuery.getApiUid())
                .like("name", apixQuery.getName())
                .pagination(apixQuery.getPageParam())
                .orders(apixQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApixEntity.class);
    }


}