package com.doublekit.apibox.apidef.service;

import com.doublekit.common.Pagination;

import com.doublekit.apibox.apidef.model.ResponseResult;
import com.doublekit.apibox.apidef.model.ResponseResultQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface ResponseResultService {

    /**
    * 创建用户
    * @param responseResult
    * @return
    */
    String createResponseResult(@NotNull @Valid ResponseResult responseResult);

    /**
    * 更新用户
    * @param responseResult
    */
    void updateResponseResult(@NotNull @Valid ResponseResult responseResult);

    /**
    * 删除用户
    * @param id
    */
    void deleteResponseResult(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    ResponseResult findResponseResult(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ResponseResult> findAllResponseResult();

    /**
    * 查询列表
    * @param responseResultQuery
    * @return
    */
    List<ResponseResult> findResponseResultList(ResponseResultQuery responseResultQuery);

    /**
    * 按分页查询
    * @param responseResultQuery
    * @return
    */
    Pagination<ResponseResult> findResponseResultPage(ResponseResultQuery responseResultQuery);

}