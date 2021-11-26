package com.doublekit.apibox.category.dao;

import com.doublekit.apibox.category.entity.CategoryEntity;
import com.doublekit.apibox.category.model.CategoryQuery;
import com.doublekit.common.page.Pagination;
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
public class CategoryDao{

    private static Logger logger = LoggerFactory.getLogger(CategoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param categoryEntity
     * @return
     */
    public String createCategory(CategoryEntity categoryEntity) {

        return jpaTemplate.save(categoryEntity,String.class);
    }

    /**
     * 更新用户
     * @param categoryEntity
     */
    public void updateCategory(CategoryEntity categoryEntity){
        jpaTemplate.update(categoryEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(CategoryEntity.class,id);
    }

    /**
     * 查找用户
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
    * findAllCategory
    * @return
    */
    public List<CategoryEntity> findAllCategory() {
        return jpaTemplate.findAll(CategoryEntity.class);
    }

    public List<CategoryEntity> findCategoryList(CategoryQuery categoryQuery) {
        return jpaTemplate.findList(categoryQuery, CategoryEntity.class);
    }

    public Pagination<CategoryEntity> findCategoryPage(CategoryQuery categoryQuery) {
        return jpaTemplate.findPage(categoryQuery, CategoryEntity.class);
    }
}