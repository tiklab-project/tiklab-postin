package com.doublekit.apibox.datastructure.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.datastructure.entity.DataStructurePo;
import com.doublekit.apibox.datastructure.model.DataStructureQuery;
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
     * @param dataStructurePo
     * @return
     */
    public String createDataStructure(DataStructurePo dataStructurePo) {
        return jpaTemplate.save(dataStructurePo,String.class);
    }

    /**
     * 更新
     * @param dataStructurePo
     */
    public void updateDataStructure(DataStructurePo dataStructurePo){
        jpaTemplate.update(dataStructurePo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDataStructure(String id){
        jpaTemplate.delete(DataStructurePo.class,id);
    }

    public void deleteDataStructure(DeleteCondition deleteCondition){
        jpaTemplate.delete(DataStructurePo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DataStructurePo findDataStructure(String id){
        return jpaTemplate.findOne(DataStructurePo.class,id);
    }

    /**
    * findAllDataStructure
    * @return
    */
    public List<DataStructurePo> findAllDataStructure() {
        return jpaTemplate.findAll(DataStructurePo.class);
    }

    public List<DataStructurePo> findDataStructureList(List<String> idList) {
        return jpaTemplate.findList(DataStructurePo.class,idList);
    }

    public List<DataStructurePo> findDataStructureList(DataStructureQuery dataStructureQuery) {
        return jpaTemplate.findList(DataStructurePo.class,dataStructureQuery);
    }

    public Pagination<DataStructurePo> findDataStructurePage(DataStructureQuery dataStructureQuery) {
        return jpaTemplate.findPage(DataStructurePo.class,dataStructureQuery);
    }
}