package io.tiklab.postin.support.datastructure.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.support.datastructure.entity.DataStructureEntity;
import io.tiklab.postin.support.datastructure.model.DataStructureQuery;
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
 * 数据结构 数据访问
 */
@Repository
public class DataStructureDao{

    private static Logger logger = LoggerFactory.getLogger(DataStructureDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建数据结构
     * @param dataStructureEntity
     * @return
     */
    public String createDataStructure(DataStructureEntity dataStructureEntity) {
        return jpaTemplate.save(dataStructureEntity,String.class);
    }

    /**
     * 更新数据结构
     * @param dataStructureEntity
     */
    public void updateDataStructure(DataStructureEntity dataStructureEntity){
        jpaTemplate.update(dataStructureEntity);
    }

    /**
     * 删除数据结构
     * @param id
     */
    public void deleteDataStructure(String id){
        jpaTemplate.delete(DataStructureEntity.class,id);
    }

    public void deleteDataStructure(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找数据结构
     * @param id
     * @return
     */
    public DataStructureEntity findDataStructure(String id){
        return jpaTemplate.findOne(DataStructureEntity.class,id);
    }

    /**
    * 查询所有数据结构
    * @return
    */
    public List<DataStructureEntity> findAllDataStructure() {
        return jpaTemplate.findAll(DataStructureEntity.class);
    }

    public List<DataStructureEntity> findDataStructureList(List<String> idList) {
        return jpaTemplate.findList(DataStructureEntity.class,idList);
    }

    /**
     * 根据查询对象查询数据结构
     * @param dataStructureQuery
     * @return
     */
    public List<DataStructureEntity> findDataStructureList(DataStructureQuery dataStructureQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DataStructureEntity.class)
                .eq("workspaceId",dataStructureQuery.getWorkspaceId())
                .eq("dataType",dataStructureQuery.getDataType())
                .like("name", dataStructureQuery.getName())
                .orders(dataStructureQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, DataStructureEntity.class);
    }

    /**
     * 根据查询对象分页查询数据结构
     * @param dataStructureQuery
     * @return
     */
    public Pagination<DataStructureEntity> findDataStructurePage(DataStructureQuery dataStructureQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DataStructureEntity.class)
                .eq("workspaceId",dataStructureQuery.getWorkspaceId())
                .eq("dataType",dataStructureQuery.getDataType())
                .like("name", dataStructureQuery.getName())
                .pagination(dataStructureQuery.getPageParam())
                .orders(dataStructureQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, DataStructureEntity.class);
    }
}