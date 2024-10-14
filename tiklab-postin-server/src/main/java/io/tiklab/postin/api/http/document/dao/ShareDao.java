package io.tiklab.postin.api.http.document.dao;

import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.document.entity.ShareEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.postin.api.http.document.model.ShareQuery;

import io.tiklab.postin.support.environment.entity.EnvironmentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分享 数据访问
 */
@Repository
public class ShareDao{

    private static Logger logger = LoggerFactory.getLogger(ShareDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建分享
     * @param shareEntity
     * @return
     */
    public String createShare(ShareEntity shareEntity) {
        return jpaTemplate.save(shareEntity,String.class);
    }

    /**
     * 更新分享
     * @param shareEntity
     */
    public void updateShare(ShareEntity shareEntity){
        jpaTemplate.update(shareEntity);
    }

    /**
     * 删除分享
     * @param id
     */
    public void deleteShare(String id){
        jpaTemplate.delete(ShareEntity.class,id);
    }

    public void deleteShare(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找分享
     * @param id
     * @return
     */
    public ShareEntity findShare(String id){
        return jpaTemplate.findOne(ShareEntity.class,id);
    }

    /**
    * 查找所有分享
    * @return
    */
    public List<ShareEntity> findAllShare() {
        return jpaTemplate.findAll(ShareEntity.class);
    }

    public List<ShareEntity> findShareList(List<String> idList) {
        return jpaTemplate.findList(ShareEntity.class,idList);
    }

    public List<ShareEntity> findShareList(ShareQuery shareQuery) {

        return jpaTemplate.findList(shareQuery,ShareEntity.class);
    }

    public Pagination<ShareEntity> findSharePage(ShareQuery shareQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ShareEntity.class)
                .eq("workspaceId",shareQuery.getWorkspaceId())
                .eq("targetType",shareQuery.getTargetType())
                .pagination(shareQuery.getPageParam())
                .orders(shareQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,ShareEntity.class);
    }
}