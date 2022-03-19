package com.doublekit.apibox.apidef.http.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apidef.http.model.FormUrlencoded;
import com.doublekit.apibox.apidef.http.model.FormUrlencodedQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* FormUrlencodedService
*/
public interface FormUrlencodedService {

    /**
    * 创建
    * @param formUrlencoded
    * @return
    */
    String createFormUrlencoded(@NotNull @Valid FormUrlencoded formUrlencoded);

    /**
    * 更新
    * @param formUrlencoded
    */
    void updateFormUrlencoded(@NotNull @Valid FormUrlencoded formUrlencoded);

    /**
    * 删除
    * @param id
    */
    void deleteFormUrlencoded(@NotNull String id);

    FormUrlencoded findOne(@NotNull String id);

    List<FormUrlencoded> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    FormUrlencoded findFormUrlencoded(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<FormUrlencoded> findAllFormUrlencoded();

    /**
    * 查询列表
    * @param formUrlencodedQuery
    * @return
    */
    List<FormUrlencoded> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery);

    /**
    * 按分页查询
    * @param formUrlencodedQuery
    * @return
    */
    Pagination<FormUrlencoded> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery);

}