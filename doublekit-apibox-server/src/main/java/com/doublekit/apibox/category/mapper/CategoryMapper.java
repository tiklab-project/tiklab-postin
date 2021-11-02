package com.doublekit.apibox.category.mapper;

import com.doublekit.apibox.category.entity.CategoryEntity;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Category.class,target = CategoryEntity.class)
public class CategoryMapper {
}
