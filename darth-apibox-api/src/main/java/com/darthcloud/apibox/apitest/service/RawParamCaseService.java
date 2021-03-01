package com.darthcloud.apibox.apitest.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.apitest.model.RawParamCase;
import com.darthcloud.apibox.apitest.model.RawParamCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RawParamCaseService {

    /**
    * 创建用户
    * @param rawParamCase
    * @return
    */
    String createRawParamCase(@NotNull @Valid RawParamCase rawParamCase);

    /**
    * 更新用户
    * @param rawParamCase
    */
    void updateRawParamCase(@NotNull @Valid RawParamCase rawParamCase);

    /**
    * 删除用户
    * @param id
    */
    void deleteRawParamCase(@NotNull String id);

    RawParamCase findOne(@NotNull String id);

    List<RawParamCase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RawParamCase findRawParamCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RawParamCase> findAllRawParamCase();

    /**
    * 查询列表
    * @param rawParamCaseQuery
    * @return
    */
    List<RawParamCase> findRawParamCaseList(RawParamCaseQuery rawParamCaseQuery);

    /**
    * 按分页查询
    * @param rawParamCaseQuery
    * @return
    */
    Pagination<List<RawParamCase>> findRawParamCasePage(RawParamCaseQuery rawParamCaseQuery);

}