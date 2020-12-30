package com.darthcloud.apibox.node.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.node.entity.NodePo;
import com.darthcloud.apibox.node.model.NodeQuery;
import com.darthcloud.dal.jpa.JpaTemplate;
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
     * @param nodePo
     * @return
     */
    public String createNode(NodePo nodePo) {
        return jpaTemplate.save(nodePo,String.class);
    }

    /**
     * 更新用户
     * @param nodePo
     */
    public void updateNode(NodePo nodePo){
        jpaTemplate.update(nodePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteNode(String id){
        jpaTemplate.delete(NodePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public NodePo findNode(String id){
        return jpaTemplate.findOne(NodePo.class,id);
    }

    /**
    * findAllNode
    * @return
    */
    public List<NodePo> findAllNode() {
        return jpaTemplate.findAll(NodePo.class);
    }

    public List<NodePo> findNodeList(NodeQuery nodeQuery) {
        return jpaTemplate.createCriteriaForQuery(NodePo.class).list(nodeQuery);
    }

    public Pagination<List<NodePo>> findNodePage(NodeQuery nodeQuery) { 
        return jpaTemplate.createCriteriaForQuery(NodePo.class).page(nodeQuery);
    }
}