package com.tiklab.postlink.apidef.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postlink.apidef.http.model.FormParam;
import com.tiklab.postlink.apidef.http.model.FormParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface FormParamService {

    /**
    * 创建用户
    * @param formParam
    * @return
    */
    String createFormParam(@NotNull @Valid FormParam formParam);

    /**
    * 更新用户
    * @param formParam
    */
    void updateFormParam(@NotNull @Valid FormParam formParam);

    /**
    * 删除用户
    * @param id
    */
    void deleteFormParam(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    FormParam findFormParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<FormParam> findAllFormParam();

    /**
    * 查询列表
    * @param formParamQuery
    * @return
    */
    List<FormParam> findFormParamList(FormParamQuery formParamQuery);

    /**
    * 按分页查询
    * @param formParamQuery
    * @return
    */
    Pagination<FormParam> findFormParamPage(FormParamQuery formParamQuery);

}