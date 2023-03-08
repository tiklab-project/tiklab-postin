package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.test.cases.model.BinaryParamCase;
import io.tiklab.postin.api.http.test.cases.model.BinaryParamCaseQuery;

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