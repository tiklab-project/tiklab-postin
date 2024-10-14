package io.tiklab.postin.node.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.node.entity.NodeEntity;
import io.tiklab.postin.node.model.NodeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 节点数据访问
 */
@Repository
public class NodeDao {

    private static Logger logger = LoggerFactory.getLogger(NodeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建节点
     * @param nodeEntity
     * @return
     */
    public String createNode(NodeEntity nodeEntity) {

        return jpaTemplate.save(nodeEntity,String.class);
    }

    /**
     * 更新节点
     * @param nodeEntity
     */
    public void updateNode(NodeEntity nodeEntity){
        jpaTemplate.update(nodeEntity);
    }

    /**
     * 删除节点
     * @param id
     */
    public void deleteNode(String id){
        jpaTemplate.delete(NodeEntity.class,id);
    }

    /**
     * 查找节点
     * @param id
     * @return
     */
    public NodeEntity findNode(String id){
        return jpaTemplate.findOne(NodeEntity.class,id);
    }

    public List<NodeEntity> findNodeList(List<String> idList) {
        return jpaTemplate.findList(NodeEntity.class,idList);
    }

    /**
    * 查找所有分类
    * @return
    */
    public List<NodeEntity> findAllNode() {
        return jpaTemplate.findAll(NodeEntity.class);
    }

    /**
     * 查询总数
     * @param workspaceId
     * @return
     */
    public int findNodeNum(String workspaceId) {
        String nodeSql = "Select count(1) as total from postin_node where workspace_id = '" + workspaceId+ "'";
        Integer nodeTotal = jpaTemplate.getJdbcTemplate().queryForObject(nodeSql, new Object[]{}, Integer.class);

        return nodeTotal;
    }



    /**
     * 根据查询对象查找分类列表
     * @param nodeQuery
     * @return
     */
    public List<NodeEntity> findNodeList(NodeQuery nodeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(NodeEntity.class)
                .eq("workspaceId", nodeQuery.getWorkspaceId())
                .eq("type",nodeQuery.getType())
                .eq("parentId",nodeQuery.getParentId())
                .like("name", nodeQuery.getName())
                .in("type", nodeQuery.getApiTypeList())
                .orders(nodeQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, NodeEntity.class);
    }

    /**
     * 根据查询对象查找分类列表树
     * @param nodeQuery
     * @return
     */
    public Pagination<NodeEntity> findNodePage(NodeQuery nodeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(NodeEntity.class)
                .eq("workspaceId", nodeQuery.getWorkspaceId())
                .eq("type",nodeQuery.getType())
                .eq("parentId",nodeQuery.getParentId())
                .like("name", nodeQuery.getName())
                .in("type", nodeQuery.getApiTypeList())
                .pagination(nodeQuery.getPageParam())
                .orders(nodeQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, NodeEntity.class);
    }
}