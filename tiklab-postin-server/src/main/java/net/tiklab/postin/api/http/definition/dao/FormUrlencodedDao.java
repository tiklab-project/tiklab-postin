package net.tiklab.postin.api.http.definition.dao;

import net.tiklab.postin.api.http.definition.entity.FormUrlencodedEntity;
import net.tiklab.postin.api.http.definition.model.FormUrlencodedQuery;
import net.tiklab.core.page.Pagination;
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
 * 定义
 * http协议
 * form-urlencoded 数据访问
 */
@Repository
public class FormUrlencodedDao{

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建form-urlencoded
     * @param formUrlencodedEntity
     * @return
     */
    public String createFormUrlencoded(FormUrlencodedEntity formUrlencodedEntity) {
        return jpaTemplate.save(formUrlencodedEntity,String.class);
    }

    /**
     * 更新form-urlencoded
     * @param formUrlencodedEntity
     */
    public void updateFormUrlencoded(FormUrlencodedEntity formUrlencodedEntity){
        jpaTemplate.update(formUrlencodedEntity);
    }

    /**
     * 删除form-urlencoded
     * @param id
     */
    public void deleteFormUrlencoded(String id){
        jpaTemplate.delete(FormUrlencodedEntity.class,id);
    }

    /**
     * 根据条件删除form-urlencoded
     * @param deleteCondition
     */
    public void deleteFormUrlencoded(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找form-urlencoded
     * @param id
     * @return
     */
    public FormUrlencodedEntity findFormUrlencoded(String id){
        return jpaTemplate.findOne(FormUrlencodedEntity.class,id);
    }

    /**
    * 查找所有form-urlencoded
    * @return
    */
    public List<FormUrlencodedEntity> findAllFormUrlencoded() {
        return jpaTemplate.findAll(FormUrlencodedEntity.class);
    }

    /**
     * 根据list 查找form-urlencoded
     * @param idList
     * @return
     */
    public List<FormUrlencodedEntity> findFormUrlencodedList(List<String> idList) {
        return jpaTemplate.findList(FormUrlencodedEntity.class,idList);
    }

    /**
     * 根据查询参数查找form-urlencoded列表
     * @param formUrlencodedQuery
     * @return
     */
    public List<FormUrlencodedEntity> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlencodedEntity.class)
                .eq("httpId", formUrlencodedQuery.getHttpId())
                .orders(formUrlencodedQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,FormUrlencodedEntity.class);
    }

    /**
     * 根据查询参数按分页查找form-urlencoded参数
     * @param formUrlencodedQuery
     * @return
     */
    public Pagination<FormUrlencodedEntity> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlencodedEntity.class)
                .eq("httpId", formUrlencodedQuery.getHttpId())
                .pagination(formUrlencodedQuery.getPageParam())
                .orders(formUrlencodedQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,FormUrlencodedEntity.class);
    }
}