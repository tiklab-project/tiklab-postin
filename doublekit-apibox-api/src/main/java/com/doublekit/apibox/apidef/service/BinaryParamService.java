package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apitest.model.BinaryParamCaseQuery;
import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apidef.model.BinaryParam;
import com.doublekit.apibox.apidef.model.BinaryParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* BinaryParamService
*/
public interface BinaryParamService {

    /**
    * 创建
    * @param binaryParam
    * @return
    */
    String createBinaryParam(@NotNull @Valid BinaryParam binaryParam);

    /**
    * 更新
    * @param binaryParam
    */
    void updateBinaryParam(@NotNull @Valid BinaryParam binaryParam);

    /**
    * 删除
    * @param id
    */
    void deleteBinaryParam(@NotNull String id);

    BinaryParam findOne(@NotNull String id);

    List<BinaryParam> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    BinaryParam findBinaryParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<BinaryParam> findAllBinaryParam();

    /**
    * 查询列表
    * @param binaryParamQuery
    * @return
    */
    List<BinaryParam> findBinaryParamList(BinaryParamQuery binaryParamQuery);

    /**
    * 按分页查询
    * @param binaryParamQuery
    * @return
    */
    Pagination<BinaryParam> findBinaryParamPage(BinaryParamQuery binaryParamQuery);

    /**
     * 返回字节流
     * @return
     */
    String findBinaryParamByte(BinaryParamQuery binaryParamQuery);


}