package com.darthcloud.apibox.apidef.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.apidef.model.RawResponse;
import com.darthcloud.apibox.apidef.model.RawResponseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
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
    RawResponse findRawResponse(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
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
    Pagination<List<RawResponse>> findRawResponsePage(RawResponseQuery rawResponseQuery);

}