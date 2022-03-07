package com.doublekit.apibox.apidef.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apidef.model.RawParam;
import com.doublekit.apibox.apidef.model.RawParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RawParamService {

    /**
    * 创建用户
    * @param rawParam
    * @return
    */
    String createRawParam(@NotNull @Valid RawParam rawParam);

    /**
    * 更新用户
    * @param rawParam
    */
    void updateRawParam(@NotNull @Valid RawParam rawParam);

    /**
    * 删除用户
    * @param id
    */
    void deleteRawParam(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RawParam findRawParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RawParam> findAllRawParam();

    /**
    * 查询列表
    * @param rawParamQuery
    * @return
    */
    List<RawParam> findRawParamList(RawParamQuery rawParamQuery);

    /**
    * 按分页查询
    * @param rawParamQuery
    * @return
    */
    Pagination<RawParam> findRawParamPage(RawParamQuery rawParamQuery);


}