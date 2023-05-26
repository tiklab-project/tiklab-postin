package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.definition.model.RawParam;
import io.tiklab.postin.api.http.definition.model.RawParamQuery;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* raw服务接口
*/
@JoinProvider(model = RawParam.class)
public interface RawParamService {

    /**
    * 创建raw
    * @param rawParam
    * @return
    */
    String createRawParam(@NotNull @Valid RawParam rawParam);

    /**
    * 更新raw
    * @param rawParam
    */
    void updateRawParam(@NotNull @Valid RawParam rawParam);

    /**
    * 删除raw
    * @param id
    */
    void deleteRawParam(@NotNull String id);

    /**
    * 查找raw
    * @param id
    * @return
    */
    @FindOne
    RawParam findRawParam(@NotNull String id);

    /**
    * 查找所有raw
    * @return
    */
    @FindAll
    List<RawParam> findAllRawParam();

    /**
    * 查询列表raw
    * @param rawParamQuery
    * @return
    */
    List<RawParam> findRawParamList(RawParamQuery rawParamQuery);

    /**
    * 按分页查询raw
    * @param rawParamQuery
    * @return
    */
    Pagination<RawParam> findRawParamPage(RawParamQuery rawParamQuery);


}