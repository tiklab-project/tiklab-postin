package io.tiklab.postin.api.http.document.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.document.entity.ShareNodeEntity;
import io.tiklab.postin.api.http.document.model.ShareNodeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分享 数据访问
 */
@Repository
public class ShareNodeDao {

    private static Logger logger = LoggerFactory.getLogger(ShareNodeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建分享
     * @param shareNodeEntity
     * @return
     */
    public String createShareNode(ShareNodeEntity shareNodeEntity) {
        return jpaTemplate.save(shareNodeEntity,String.class);
    }

    /**
     * 更新分享
     * @param shareNodeEntity
     */
    public void updateShareNode(ShareNodeEntity shareNodeEntity){
        jpaTemplate.update(shareNodeEntity);
    }

    /**
     * 删除分享
     * @param id
     */
    public void deleteShareNode(String id){
        jpaTemplate.delete(ShareNodeEntity.class,id);
    }



    public void deleteShareNode(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找分享
     * @param id
     * @return
     */
    public ShareNodeEntity findShareNode(String id){
        return jpaTemplate.findOne(ShareNodeEntity.class,id);
    }

    /**
    * 查找所有分享
    * @return
    */
    public List<ShareNodeEntity> findAllShareNode() {
        return jpaTemplate.findAll(ShareNodeEntity.class);
    }

    public List<ShareNodeEntity> findShareNodeList(List<String> idList) {
        return jpaTemplate.findList(ShareNodeEntity.class,idList);
    }

    public List<ShareNodeEntity> findShareNodeList(ShareNodeQuery shareNodeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ShareNodeEntity.class)
                .eq("shareId",shareNodeQuery.getShareId())
                .pagination(shareNodeQuery.getPageParam())
                .orders(shareNodeQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ShareNodeEntity.class);
    }

    public Pagination<ShareNodeEntity> findShareNodePage(ShareNodeQuery shareNodeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ShareNodeEntity.class)
                .eq("shareId",shareNodeQuery.getShareId())
                .pagination(shareNodeQuery.getPageParam())
                .orders(shareNodeQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,ShareNodeEntity.class);
    }
}