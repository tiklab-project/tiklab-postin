package net.tiklab.postin.support.datastructure.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.postin.support.datastructure.entity.JsonParamDSEntity;
import net.tiklab.postin.support.datastructure.model.JsonParamDSQuery;
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
 * json 类型的数据结构  数据访问
 */
@Repository
public class JsonParamDSDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamDSDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建json类型的数据结构内容
     * @param jsonParamDSEntity
     * @return
     */
    public String createJsonParamDS(JsonParamDSEntity jsonParamDSEntity) {
        return jpaTemplate.save(jsonParamDSEntity,String.class);
    }

    /**
     * 更新json类型的数据结构内容
     * @param jsonParamDSEntity
     */
    public void updateJsonParamDS(JsonParamDSEntity jsonParamDSEntity){
        jpaTemplate.update(jsonParamDSEntity);
    }

    /**
     * 删除json类型的数据结构内容
     * @param id
     */
    public void deleteJsonParamDS(String id){
        jpaTemplate.delete(JsonParamDSEntity.class,id);
    }

    public void deleteJsonParamDS(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查询json类型的数据结构内容
     * @param id
     * @return
     */
    public JsonParamDSEntity findJsonParamDS(String id){
        return jpaTemplate.findOne(JsonParamDSEntity.class,id);
    }

    /**
    * 查询所有json 类型的数据结构内容
    * @return
    */
    public List<JsonParamDSEntity> findAllJsonParamDS() {
        return jpaTemplate.findAll(JsonParamDSEntity.class);
    }

    public List<JsonParamDSEntity> findJsonParamDSList(List<String> idList) {
        return jpaTemplate.findList(JsonParamDSEntity.class,idList);
    }

    /**
     * 通过查询对象 查询json 类型的数据结构内容
     * @param jsonParamDSQuery
     * @return
     */
    public List<JsonParamDSEntity> findJsonParamDSList(JsonParamDSQuery jsonParamDSQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamDSEntity.class)
                .eq("dataStructureId", jsonParamDSQuery.getDataStructureId())
                .orders(jsonParamDSQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonParamDSEntity.class);
    }

    /**
     * 通过查询对象 查询json 类型的数据结构内容树
     * @param jsonParamDSQuery
     * @return
     */
    public Pagination<JsonParamDSEntity> findJsonParamDSPage(JsonParamDSQuery jsonParamDSQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamDSEntity.class)
                .eq("dataStructureId", jsonParamDSQuery.getDataStructureId())
                .pagination(jsonParamDSQuery.getPageParam())
                .orders(jsonParamDSQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonParamDSEntity.class);
    }
}