package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.postin.api.http.definition.entity.HttpApiEntity;
import io.tiklab.postin.api.http.definition.model.HttpApiQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * http协议 数据访问
 */
@Repository
public class HttpApiDao {

    private static Logger logger = LoggerFactory.getLogger(HttpApiDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建http接口
     * @param methodExPo
     * @return
     */
    public String createHttpApi(HttpApiEntity methodExPo) {
        return jpaTemplate.save(methodExPo,String.class);
    }

    /**
     * 更新http接口
     * @param methodExPo
     */
    public void updateHttpApi(HttpApiEntity methodExPo){
        jpaTemplate.update(methodExPo);
    }

    /**
     * 删除http接口
     * @param id
     */
    public void deleteHttpApi(String id){
        jpaTemplate.delete(HttpApiEntity.class,id);
    }

    /**
     * 通过条件删除http接口
     * @param deleteCondition
     */
    public void deleteHttpApi(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }
    /**
     * 根据id查找http接口
     * @param id
     * @return
     */
    public HttpApiEntity findHttpApi(String id){
        return jpaTemplate.findOne(HttpApiEntity.class,id);
    }

    /**
    * 查找所有http接口
    * @return
    */
    public List<HttpApiEntity> findAllHttpApi() {
        return jpaTemplate.findAll(HttpApiEntity.class);
    }

    /***
     * 根据list查询列表
     * @param idList
     * @return
     */
    public List<HttpApiEntity> findHttpApiList(List<String> idList) {
        return jpaTemplate.findList(HttpApiEntity.class,idList);
    }

    /**
     * 根据查询参数查找http接口列表
     * @param httpApiQuery
     * @return
     */
    public List<HttpApiEntity> findHttpApiList(HttpApiQuery httpApiQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpApiEntity.class)
                .eq("apixId", httpApiQuery.getApixId())
                .orders(httpApiQuery.getOrderParams())
                .get();
        return  jpaTemplate.findList(queryCondition, HttpApiEntity.class);
    }

    /**
     * 根据查询参数按分页查找http接口列表
     * @param httpApiQuery
     * @return
     */
    public Pagination<HttpApiEntity> findHttpApiPage(HttpApiQuery httpApiQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpApiEntity.class)
                .eq("apixId", httpApiQuery.getApixId())
                .pagination(httpApiQuery.getPageParam())
                .orders(httpApiQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, HttpApiEntity.class);
    }


}