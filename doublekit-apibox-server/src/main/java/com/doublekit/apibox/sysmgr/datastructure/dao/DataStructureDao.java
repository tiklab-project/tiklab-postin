package com.doublekit.apibox.sysmgr.datastructure.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.sysmgr.datastructure.entity.DataStructureEntity;
import com.doublekit.apibox.sysmgr.datastructure.model.DataStructureQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DataStructureDao
 */
@Repository
public class DataStructureDao{

    private static Logger logger = LoggerFactory.getLogger(DataStructureDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param dataStructureEntity
     * @return
     */
    public String createDataStructure(DataStructureEntity dataStructureEntity) {
        return jpaTemplate.save(dataStructureEntity,String.class);
    }

    /**
     * 更新
     * @param dataStructureEntity
     */
    public void updateDataStructure(DataStructureEntity dataStructureEntity){
        jpaTemplate.update(dataStructureEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDataStructure(String id){
        jpaTemplate.delete(DataStructureEntity.class,id);
    }

    public void deleteDataStructure(DeleteCondition deleteCondition){
        jpaTemplate.delete(DataStructureEntity.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DataStructureEntity findDataStructure(String id){
        return jpaTemplate.findOne(DataStructureEntity.class,id);
    }

    /**
    * findAllDataStructure
    * @return
    */
    public List<DataStructureEntity> findAllDataStructure() {
        return jpaTemplate.findAll(DataStructureEntity.class);
    }

    public List<DataStructureEntity> findDataStructureList(List<String> idList) {
        return jpaTemplate.findList(DataStructureEntity.class,idList);
    }

    public List<DataStructureEntity> findDataStructureList(DataStructureQuery dataStructureQuery) {
        return jpaTemplate.findList(dataStructureQuery, DataStructureEntity.class);
    }

    public Pagination<DataStructureEntity> findDataStructurePage(DataStructureQuery dataStructureQuery) {
        return jpaTemplate.findPage(dataStructureQuery, DataStructureEntity.class);
    }
}