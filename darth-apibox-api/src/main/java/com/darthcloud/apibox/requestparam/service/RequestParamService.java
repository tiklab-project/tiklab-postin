package com.darthcloud.apibox.requestparam.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.requestparam.model.RequestParam;
import com.darthcloud.apibox.requestparam.model.RequestParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestParamService {

    /**
    * 创建用户
    * @param requestParam
    * @return
    */
    String createRequestParam(@NotNull @Valid RequestParam requestParam);

    /**
    * 更新用户
    * @param requestParam
    */
    void updateRequestParam(@NotNull @Valid RequestParam requestParam);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestParam(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestParam findRequestParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestParam> findAllRequestParam();

    /**
    * 查询列表
    * @param requestParamQuery
    * @return
    */
    List<RequestParam> findRequestParamList(RequestParamQuery requestParamQuery);

    /**
    * 按分页查询
    * @param requestParamQuery
    * @return
    */
    Pagination<List<RequestParam>> findRequestParamPage(RequestParamQuery requestParamQuery);

}