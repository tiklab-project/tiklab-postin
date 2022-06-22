package com.doublekit.apibox.integration.dynamic.service;

import com.doublekit.apibox.integration.dynamic.model.Dynamic;
import com.doublekit.apibox.integration.dynamic.model.DynamicQuery;
import com.doublekit.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DynamicService
*/
public interface DynamicService {

    /**
    * 创建
    * @param dynamic
    * @return
    */
    String createDynamic(@NotNull @Valid Dynamic dynamic);

    /**
    * 更新
    * @param dynamic
    */
    void updateDynamic(@NotNull @Valid Dynamic dynamic);

    /**
    * 删除
    * @param id
    */
    void deleteDynamic(@NotNull String id);

    Dynamic findOne(@NotNull String id);

    List<Dynamic> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    Dynamic findDynamic(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<Dynamic> findAllDynamic();

    /**
    * 查询列表
    * @param dynamicQuery
    * @return
    */
    List<Dynamic> findDynamicList(DynamicQuery dynamicQuery);

    /**
    * 按分页查询
    * @param dynamicQuery
    * @return
    */
    Pagination<Dynamic> findDynamicPage(DynamicQuery dynamicQuery);


}