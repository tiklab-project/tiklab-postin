package net.tiklab.postin.apidef.http.definition.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.apidef.http.definition.model.BinaryParam;
import net.tiklab.postin.apidef.http.definition.model.BinaryParamQuery;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;

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