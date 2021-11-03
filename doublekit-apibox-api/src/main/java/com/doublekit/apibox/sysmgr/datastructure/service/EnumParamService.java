package com.doublekit.apibox.sysmgr.datastructure.service;

import com.doublekit.common.Pagination;

import com.doublekit.apibox.sysmgr.datastructure.model.EnumParam;
import com.doublekit.apibox.sysmgr.datastructure.model.EnumParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* EnumParamService
*/
public interface EnumParamService {

    /**
    * 创建
    * @param enumParam
    * @return
    */
    String createEnumParam(@NotNull @Valid EnumParam enumParam);

    /**
    * 更新
    * @param enumParam
    */
    void updateEnumParam(@NotNull @Valid EnumParam enumParam);

    /**
    * 删除
    * @param id
    */
    void deleteEnumParam(@NotNull String id);

    EnumParam findOne(@NotNull String id);

    List<EnumParam> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    EnumParam findEnumParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<EnumParam> findAllEnumParam();

    /**
    * 查询列表
    * @param enumParamQuery
    * @return
    */
    List<EnumParam> findEnumParamList(EnumParamQuery enumParamQuery);

    /**
    * 按分页查询
    * @param enumParamQuery
    * @return
    */
    Pagination<EnumParam> findEnumParamPage(EnumParamQuery enumParamQuery);

}