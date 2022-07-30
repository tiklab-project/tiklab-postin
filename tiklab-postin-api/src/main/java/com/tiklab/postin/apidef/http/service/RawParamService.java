package com.tiklab.postin.apidef.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postin.apidef.http.model.RawParam;
import com.tiklab.postin.apidef.http.model.RawParamQuery;
import com.tiklab.join.annotation.FindAll;
import com.tiklab.join.annotation.FindOne;
import com.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = RawParam.class)
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
    @FindOne
    RawParam findRawParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
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