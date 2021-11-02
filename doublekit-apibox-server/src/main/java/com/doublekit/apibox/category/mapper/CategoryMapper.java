package com.doublekit.apibox.category.mapper;

import com.doublekit.apibox.category.entity.CategoryPo;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Category.class,target = CategoryPo.class)
public class CategoryMapper {
}
