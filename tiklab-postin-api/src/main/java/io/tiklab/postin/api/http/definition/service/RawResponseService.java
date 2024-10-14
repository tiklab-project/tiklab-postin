package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.definition.model.RawResponse;
import io.tiklab.postin.api.http.definition.model.RawResponseQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应raw服务接口
*/
@JoinProvider(model = RawResponse.class)
public interface RawResponseService {

    /**
    * 创建响应raw
    * @param rawResponse
    * @return
    */
    String createRawResponse(@NotNull @Valid RawResponse rawResponse);

    /**
    * 更新响应raw
    * @param rawResponse
    */
    void updateRawResponse(@NotNull @Valid RawResponse rawResponse);

    /**
    * 删除响应raw
    * @param id
    */
    void deleteRawResponse(@NotNull String id);

    /**
    * 查找响应raw
    * @param id
    * @return
    */
    @FindOne
    RawResponse findRawResponse(@NotNull String id);

    /**
    * 查找所有响应raw
    * @return
    */
    @FindAll
    List<RawResponse> findAllRawResponse();

    /**
    * 查询列表响应raw
    * @param rawResponseQuery
    * @return
    */
    List<RawResponse> findRawResponseList(RawResponseQuery rawResponseQuery);

    /**
    * 按分页查询响应raw
    * @param rawResponseQuery
    * @return
    */
    Pagination<RawResponse> findRawResponsePage(RawResponseQuery rawResponseQuery);

}