package com.tiklab.postin.apidef.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postin.apidef.http.model.BinaryParam;
import com.tiklab.postin.apidef.http.model.BinaryParamQuery;
import com.tiklab.join.annotation.FindAll;
import com.tiklab.join.annotation.FindList;
import com.tiklab.join.annotation.FindOne;
import com.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* BinaryParamService
*/
@JoinProvider(model = BinaryParam.class)
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

    @FindOne
    BinaryParam findOne(@NotNull String id);

    @FindList
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
    @FindAll
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