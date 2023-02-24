package net.tiklab.postin.apidef.http.definition.dao;

import net.tiklab.postin.apidef.http.definition.entity.HttpApiEntity;
import net.tiklab.postin.apidef.http.definition.model.HttpApiQuery;
import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class HttpApiDao {

    private static Logger logger = LoggerFactory.getLogger(HttpApiDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param methodExPo
     * @return
     */
    public String createHttpApi(HttpApiEntity methodExPo) {
        return jpaTemplate.save(methodExPo,String.class);
    }

    /**
     * 更新用户
     * @param methodExPo
     */
    public void updateHttpApi(HttpApiEntity methodExPo){
        jpaTemplate.update(methodExPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteHttpApi(String id){
        jpaTemplate.delete(HttpApiEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteHttpApi(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }
    /**
     * 查找用户
     * @param id
     * @return
     */
    public HttpApiEntity findHttpApi(String id){
        return jpaTemplate.findOne(HttpApiEntity.class,id);
    }

    /**
    * findAllApxMethod
    * @return
    */
    public List<HttpApiEntity> findAllHttpApi() {
        return jpaTemplate.findAll(HttpApiEntity.class);
    }

    public List<HttpApiEntity> findHttpApiList(List<String> idList) {
        return jpaTemplate.findList(HttpApiEntity.class,idList);
    }

    public List<HttpApiEntity> findHttpApiList(HttpApiQuery httpApiQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpApiEntity.class)
                .eq("apixId", httpApiQuery.getApixId())
                .orders(httpApiQuery.getOrderParams())
                .get();
        return  jpaTemplate.findList(queryCondition, HttpApiEntity.class);
    }

    public Pagination<HttpApiEntity> findHttpApiPage(HttpApiQuery httpApiQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpApiEntity.class)
                .eq("apixId", httpApiQuery.getApixId())
                .pagination(httpApiQuery.getPageParam())
                .orders(httpApiQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, HttpApiEntity.class);
    }

    public Pagination<HttpApiEntity> findHttpApiByCategoryIdlist(String[] ids, HttpApiQuery httpApiQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpApiEntity.class)
                .in("apixId", ids)
                .pagination(httpApiQuery.getPageParam())
                .get();
        Pagination<HttpApiEntity> page = jpaTemplate.findPage(queryCondition, HttpApiEntity.class);
        return page;
    }

}