package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.definition.model.RawParams;
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
@JoinProvider(model = RawParams.class)
public interface RawParamService {

    /**
    * 创建raw
    * @param rawParams
    * @return
    */
    String createRawParam(@NotNull @Valid RawParams rawParams);

    /**
    * 更新raw
    * @param rawParams
    */
    void updateRawParam(@NotNull @Valid RawParams rawParams);

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
    RawParams findRawParam(@NotNull String id);

    /**
    * 查找所有raw
    * @return
    */
    @FindAll
    List<RawParams> findAllRawParam();

    /**
    * 查询列表raw
    * @param rawParamQuery
    * @return
    */
    List<RawParams> findRawParamList(RawParamQuery rawParamQuery);

    /**
    * 按分页查询raw
    * @param rawParamQuery
    * @return
    */
    Pagination<RawParams> findRawParamPage(RawParamQuery rawParamQuery);


}