package com.doublekit.apibox.apitest.service;

import com.doublekit.common.Result;
import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apitest.model.BinaryParamCase;
import com.doublekit.apibox.apitest.model.BinaryParamCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* BinaryParamCaseService
*/
public interface BinaryParamCaseService {

    /**
    * 创建
    * @param binaryParamCase
    * @return
    */
    String createBinaryParamCase(@NotNull @Valid BinaryParamCase binaryParamCase);

    /**
    * 更新
    * @param binaryParamCase
    */
    void updateBinaryParamCase(@NotNull @Valid BinaryParamCase binaryParamCase);

    /**
    * 删除
    * @param id
    */
    void deleteBinaryParamCase(@NotNull String id);

    BinaryParamCase findOne(@NotNull String id);

    List<BinaryParamCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    BinaryParamCase findBinaryParamCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<BinaryParamCase> findAllBinaryParamCase();

    /**
    * 查询列表
    * @param binaryParamCaseQuery
    * @return
    */
    List<BinaryParamCase> findBinaryParamCaseList(BinaryParamCaseQuery binaryParamCaseQuery);

    /**
    * 按分页查询
    * @param binaryParamCaseQuery
    * @return
    */
    Pagination<BinaryParamCase> findBinaryParamCasePage(BinaryParamCaseQuery binaryParamCaseQuery);


    /**
     * 返回字节流
     * @return
     */
   String findBinaryParamCaseByte(BinaryParamCaseQuery binaryParamCaseQuery);

}