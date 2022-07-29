package com.tiklab.postlink.apidef.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postlink.apidef.http.model.RawResponse;
import com.tiklab.postlink.apidef.http.model.RawResponseQuery;
import com.tiklab.join.annotation.FindAll;
import com.tiklab.join.annotation.FindOne;
import com.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = RawResponse.class)
public interface RawResponseService {

    /**
    * 创建用户
    * @param rawResponse
    * @return
    */
    String createRawResponse(@NotNull @Valid RawResponse rawResponse);

    /**
    * 更新用户
    * @param rawResponse
    */
    void updateRawResponse(@NotNull @Valid RawResponse rawResponse);

    /**
    * 删除用户
    * @param id
    */
    void deleteRawResponse(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    @FindOne
    RawResponse findRawResponse(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<RawResponse> findAllRawResponse();

    /**
    * 查询列表
    * @param rawResponseQuery
    * @return
    */
    List<RawResponse> findRawResponseList(RawResponseQuery rawResponseQuery);

    /**
    * 按分页查询
    * @param rawResponseQuery
    * @return
    */
    Pagination<RawResponse> findRawResponsePage(RawResponseQuery rawResponseQuery);

}