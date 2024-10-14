package io.tiklab.postin.category.dao;

import io.tiklab.postin.category.entity.CategoryEntity;
import io.tiklab.postin.category.model.CategoryQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类管理 数据访问
 */
@Repository
public class CategoryDao{

    private static Logger logger = LoggerFactory.getLogger(CategoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建分类管理
     * @param categoryEntity
     * @return
     */
    public String createCategory(CategoryEntity categoryEntity) {

        return jpaTemplate.save(categoryEntity,String.class);
    }

    /**
     * 更新分类管理
     * @param categoryEntity
     */
    public void updateCategory(CategoryEntity categoryEntity){
        jpaTemplate.update(categoryEntity);
    }

    /**
     * 删除分类管理
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(CategoryEntity.class,id);
    }

    /**
     * 查找分类管理
     * @param id
     * @return
     */
    public CategoryEntity findCategory(String id){
        return jpaTemplate.findOne(CategoryEntity.class,id);
    }

    public List<CategoryEntity> findCategoryList(List<String> idList) {
        return jpaTemplate.findList(CategoryEntity.class,idList);
    }

    /**
    * 查找所有分类
    * @return
    */
    public List<CategoryEntity> findAllCategory() {
        return jpaTemplate.findAll(CategoryEntity.class);
    }

    /**
     * 查询总数
     * @param workspaceId
     * @return
     */
    public int findCategoryNum(String workspaceId) {
        String categorySql = "Select count(1) as total from postin_node where workspace_id = '" + workspaceId+ "' and type = 'category'";
        Integer categoryTotal = jpaTemplate.getJdbcTemplate().queryForObject(categorySql, new Object[]{}, Integer.class);

        return categoryTotal;
    }



    /**
     * 根据查询对象查找分类列表
     * @param categoryQuery
     * @return
     */
    public List<CategoryEntity> findCategoryList(CategoryQuery categoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryEntity.class)
                .orders(categoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, CategoryEntity.class);
    }

    /**
     * 根据查询对象查找分类列表树
     * @param categoryQuery
     * @return
     */
    public Pagination<CategoryEntity> findCategoryPage(CategoryQuery categoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryEntity.class)
                .pagination(categoryQuery.getPageParam())
                .orders(categoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, CategoryEntity.class);
    }
}