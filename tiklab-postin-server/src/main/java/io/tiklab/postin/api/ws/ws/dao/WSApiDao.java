package io.tiklab.postin.api.ws.ws.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.tiklab.postin.api.ws.ws.entity.WSApiEntity;
import io.tiklab.postin.api.ws.ws.model.WSApiQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * ws协议 数据访问
 */
@Repository
public class WSApiDao {

    private static Logger logger = LoggerFactory.getLogger(WSApiDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建ws接口
     * @param methodExPo
     * @return
     */
    public String createWSApi(WSApiEntity methodExPo) {
        return jpaTemplate.save(methodExPo,String.class);
    }

    /**
     * 更新ws接口
     * @param methodExPo
     */
    public void updateWSApi(WSApiEntity methodExPo){
        jpaTemplate.update(methodExPo);
    }

    /**
     * 删除ws接口
     * @param id
     */
    public void deleteWSApi(String id){
        jpaTemplate.delete(WSApiEntity.class,id);
    }

    /**
     * 通过条件删除ws接口
     * @param deleteCondition
     */
    public void deleteWSApi(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }
    /**
     * 根据id查找ws接口
     * @param id
     * @return
     */
    public WSApiEntity findWSApi(String id){
        return jpaTemplate.findOne(WSApiEntity.class,id);
    }

    /**
    * 查找所有ws接口
    * @return
    */
    public List<WSApiEntity> findAllWSApi() {
        return jpaTemplate.findAll(WSApiEntity.class);
    }

    /***
     * 根据list查询列表
     * @param idList
     * @return
     */
    public List<WSApiEntity> findWSApiList(List<String> idList) {
        return jpaTemplate.findList(WSApiEntity.class,idList);
    }

    /**
     * 根据查询参数查找ws接口列表
     * @param wsApiQuery
     * @return
     */
    public List<WSApiEntity> findWSApiList(WSApiQuery wsApiQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WSApiEntity.class)
                .eq("apixId", wsApiQuery.getApixId())
                .orders(wsApiQuery.getOrderParams())
                .get();
        return  jpaTemplate.findList(queryCondition, WSApiEntity.class);
    }

    /**
     * 根据查询参数按分页查找ws接口列表
     * @param wsApiQuery
     * @return
     */
    public Pagination<WSApiEntity> findWSApiPage(WSApiQuery wsApiQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WSApiEntity.class)
                .eq("apixId", wsApiQuery.getApixId())
                .pagination(wsApiQuery.getPageParam())
                .orders(wsApiQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, WSApiEntity.class);
    }


}