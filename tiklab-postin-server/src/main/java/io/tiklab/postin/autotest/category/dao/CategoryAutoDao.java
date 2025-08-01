package io.tiklab.postin.autotest.category.dao;

import io.tiklab.postin.autotest.category.entity.CategoryAutoEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.category.model.CategoryAutoQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 目录 数据访问
 */
@Repository
public class CategoryAutoDao {

    private static Logger logger = LoggerFactory.getLogger(CategoryAutoDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建目录
     * @param categoryAutoEntity
     * @return
     */
    public String createCategory(CategoryAutoEntity categoryAutoEntity) {
        return jpaTemplate.save(categoryAutoEntity,String.class);
    }

    /**
     * 更新目录
     * @param categoryAutoEntity
     */
    public void updateCategory(CategoryAutoEntity categoryAutoEntity){
        jpaTemplate.update(categoryAutoEntity);
    }

    /**
     * 删除目录
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(CategoryAutoEntity.class,id);
    }

    public void deleteCategory(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找目录
     * @param id
     * @return
     */
//    public CategoryAutoEntity findCategory(String id){
//        return jpaTemplate.findOne(CategoryAutoEntity.class,id);
//    }
    public CategoryAutoEntity findCategory(String id){
        String sql = "SELECT name, id, workspace_id, parent_id " +
                "FROM postin_node " +
                "WHERE id = ? AND type = 'category'";
        return jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(CategoryAutoEntity.class));
    }


    /**
     * 查询总数
     * @param workspaceId
     * @return
     */
    public int findCategoryNum(String workspaceId) {
        String categorySql = "Select count(1) as total from autotest_category where workspace_id = '" + workspaceId+ "'";
        Integer categoryTotal = jpaTemplate.getJdbcTemplate().queryForObject(categorySql, new Object[]{}, Integer.class);

        return categoryTotal;
    }



    /**
    * 查找所有目录
    * @return
    */
    public List<CategoryAutoEntity> findAllCategory() {
        return jpaTemplate.findAll(CategoryAutoEntity.class);
    }

    public List<CategoryAutoEntity> findCategoryList(List<String> idList) {
        return jpaTemplate.findList(CategoryAutoEntity.class,idList);
    }

    /**
     * 查询目录列表
     * @param categoryAutoQuery
     * @return
     */
//    public List<CategoryAutoEntity> findCategoryList(CategoryAutoQuery categoryAutoQuery) {
//        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryAutoEntity.class)
//                .eq("workspaceId", categoryAutoQuery.getWorkspaceId())
//                .like("name", categoryAutoQuery.getName())
//                .orders(categoryAutoQuery.getOrderParams())
//                .get();
//        return jpaTemplate.findList(queryCondition, CategoryAutoEntity.class);
//    }
    public List<CategoryAutoEntity> findCategoryList(CategoryAutoQuery categoryAutoQuery) {
        String sql ="SELECT name, id, workspace_id, parent_id " +
                    "FROM postin_node " +
                    "WHERE workspace_id = ? AND type = 'category'";

        return jpaTemplate.getJdbcTemplate().query(
                sql,
                new Object[]{categoryAutoQuery.getWorkspaceId()},
                new BeanPropertyRowMapper<>(CategoryAutoEntity.class)
        );
    }


    /**
     * 按分页查询目录
     * @param categoryAutoQuery
     * @return
     */
    public Pagination<CategoryAutoEntity> findCategoryPage(CategoryAutoQuery categoryAutoQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryAutoEntity.class)
                .eq("workspaceId", categoryAutoQuery.getWorkspaceId())
                .like("name", categoryAutoQuery.getName())
                .pagination(categoryAutoQuery.getPageParam())
                .orders(categoryAutoQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, CategoryAutoEntity.class);
    }


}