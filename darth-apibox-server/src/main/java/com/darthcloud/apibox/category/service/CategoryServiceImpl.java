package com.darthcloud.apibox.category.service;

import com.darthcloud.apibox.category.dao.CategoryDao;
import com.darthcloud.apibox.category.entity.CategoryPo;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.apibox.category.model.CategoryQuery;

import com.darthcloud.apibox.node.dao.NodeDao;
import com.darthcloud.apibox.node.entity.NodePo;
import com.darthcloud.apibox.node.model.Node;
import com.darthcloud.apibox.node.service.NodeService;
import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.rpc.client.nodeprovider.NodeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    NodeDao nodeDao;

    @Override
    public String createCategory(@NotNull @Valid Category category) {
        CategoryPo categoryPo = BeanMapper.map(category, CategoryPo.class);

        String id = categoryDao.createCategory(categoryPo);

        //创建node TODO 设置id
        NodePo nodePo = BeanMapper.map(categoryPo, NodePo.class);

        nodeDao.createNode(nodePo);
        return id;
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {
        CategoryPo categoryPo = BeanMapper.map(category, CategoryPo.class);

        categoryDao.updateCategory(categoryPo);

        //更新node
        NodePo nodePo = BeanMapper.map(categoryPo, NodePo.class);

        nodeDao.createNode(nodePo);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        categoryDao.deleteCategory(id);

        //删除node
        nodeDao.deleteNode(id);
    }

    @Override
    public Category findCategory(@NotNull String id) {
        CategoryPo categoryPo = categoryDao.findCategory(id);

        return BeanMapper.map(categoryPo, Category.class);
    }

    @Override
    public List<Category> findAllCategory() {
        List<CategoryPo> categoryPoList =  categoryDao.findAllCategory();

        return BeanMapper.mapList(categoryPoList,Category.class);
    }

    @Override
    public List<Category> findCategoryList(CategoryQuery categoryQuery) {
        List<CategoryPo> categoryPoList = categoryDao.findCategoryList(categoryQuery);

        return BeanMapper.mapList(categoryPoList,Category.class);
    }

    @Override
    public Pagination<List<Category>> findCategoryPage(CategoryQuery categoryQuery) {
        Pagination<List<Category>> pg = new Pagination<>();

        Pagination<List<CategoryPo>>  pagination = categoryDao.findCategoryPage(categoryQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Category> categoryList = BeanMapper.mapList(pagination.getDataList(),Category.class);
        pg.setDataList(categoryList);
        return pg;
    }
}