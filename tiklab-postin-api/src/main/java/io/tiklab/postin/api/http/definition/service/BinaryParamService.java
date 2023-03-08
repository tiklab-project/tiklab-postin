package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.definition.model.BinaryParam;
import io.tiklab.postin.api.http.definition.model.BinaryParamQuery;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* binary 服务接口
*/
@JoinProvider(model = BinaryParam.class)
public interface BinaryParamService {

    /**
    * 创建binary
    * @param binaryParam
    * @return
    */
    String createBinaryParam(@NotNull @Valid BinaryParam binaryParam);

    /**
    * 更新binary
    * @param binaryParam
    */
    void updateBinaryParam(@NotNull @Valid BinaryParam binaryParam);

    /**
    * 删除binary
    * @param id
    */
    void deleteBinaryParam(@NotNull String id);

    @FindOne
    BinaryParam findOne(@NotNull String id);

    @FindList
    List<BinaryParam> findList(List<String> idList);

    /**
    * 查找binary
    * @param id
    * @return
    */
    BinaryParam findBinaryParam(@NotNull String id);

    /**
    * 查找所有binary
    * @return
    */
    @FindAll
    List<BinaryParam> findAllBinaryParam();

    /**
    * 查询列表binary
    * @param binaryParamQuery
    * @return
    */
    List<BinaryParam> findBinaryParamList(BinaryParamQuery binaryParamQuery);

    /**
    * 按分页查询binary
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