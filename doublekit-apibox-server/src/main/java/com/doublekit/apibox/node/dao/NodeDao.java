package com.doublekit.apibox.node.dao;

import com.doublekit.apibox.node.entity.NodeEntity;
import com.doublekit.apibox.node.model.NodeQuery;
import com.doublekit.common.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class NodeDao{

    private static Logger logger = LoggerFactory.getLogger(NodeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param nodeEntity
     * @return
     */
    public String createNode(NodeEntity nodeEntity) {
        return jpaTemplate.save(nodeEntity,String.class);
    }

    /**
     * 更新用户
     * @param nodeEntity
     */
    public void updateNode(NodeEntity nodeEntity){
        jpaTemplate.update(nodeEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteNode(String id){
        jpaTemplate.delete(NodeEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public NodeEntity findNode(String id){
        return jpaTemplate.findOne(NodeEntity.class,id);
    }

    /**
    * findAllNode
    * @return
    */
    public List<NodeEntity> findAllNode() {
        return jpaTemplate.findAll(NodeEntity.class);
    }

    public List<NodeEntity> findNodeList(NodeQuery nodeQuery) {
        return jpaTemplate.findList(NodeEntity.class,nodeQuery);
    }

    public Pagination<NodeEntity> findNodePage(NodeQuery nodeQuery) {
        return jpaTemplate.findPage(NodeEntity.class,nodeQuery);
    }
}