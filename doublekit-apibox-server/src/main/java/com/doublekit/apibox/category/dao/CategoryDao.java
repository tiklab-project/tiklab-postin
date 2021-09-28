package com.doublekit.apibox.category.dao;

import com.doublekit.apibox.category.entity.CategoryPo;
import com.doublekit.apibox.category.model.CategoryQuery;
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
public class CategoryDao{

    private static Logger logger = LoggerFactory.getLogger(CategoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param categoryPo
     * @return
     */
    public String createCategory(CategoryPo categoryPo) {
        return jpaTemplate.save(categoryPo,String.class);
    }

    /**
     * 更新用户
     * @param categoryPo
     */
    public void updateCategory(CategoryPo categoryPo){
        jpaTemplate.update(categoryPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(CategoryPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public CategoryPo findCategory(String id){
        return jpaTemplate.findOne(CategoryPo.class,id);
    }

    public List<CategoryPo> findCategoryList(List<String> idList) {
        return jpaTemplate.findList(CategoryPo.class,idList);
    }

    /**
    * findAllCategory
    * @return
    */
    public List<CategoryPo> findAllCategory() {
        return jpaTemplate.findAll(CategoryPo.class);
    }

    public List<CategoryPo> findCategoryList(CategoryQuery categoryQuery) {
        return jpaTemplate.findList(CategoryPo.class,categoryQuery);
    }

    public Pagination<CategoryPo> findCategoryPage(CategoryQuery categoryQuery) {
        return jpaTemplate.findPage(CategoryPo.class,categoryQuery);
    }
}