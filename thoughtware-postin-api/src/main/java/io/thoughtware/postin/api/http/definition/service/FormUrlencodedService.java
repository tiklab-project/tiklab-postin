package io.thoughtware.postin.api.http.definition.service;

import io.thoughtware.core.page.Pagination;

import io.thoughtware.join.annotation.FindAll;
import io.thoughtware.join.annotation.FindList;
import io.thoughtware.join.annotation.FindOne;
import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.postin.api.http.definition.model.FormUrlencoded;
import io.thoughtware.postin.api.http.definition.model.FormUrlencodedQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-urlencoded 服务接口
*/
@JoinProvider(model = FormUrlencoded.class)
public interface FormUrlencodedService {

    /**
    * 创建 form-urlencoded
    * @param formUrlencoded
    * @return
    */
    String createFormUrlencoded(@NotNull @Valid FormUrlencoded formUrlencoded);

    /**
    * 更新 form-urlencoded
    * @param formUrlencoded
    */
    void updateFormUrlencoded(@NotNull @Valid FormUrlencoded formUrlencoded);

    /**
     * 通过httpId删除所有form-urlencoded
     * @param id
     */
    void deleteAllFormUrlencoded(@NotNull String id);


    /**
    * 删除 form-urlencoded
    * @param id
    */
    void deleteFormUrlencoded(@NotNull String id);

    @FindOne
    FormUrlencoded findOne(@NotNull String id);

    @FindList
    List<FormUrlencoded> findList(List<String> idList);

    /**
    * 查找 form-urlencoded
    * @param id
    * @return
    */
    FormUrlencoded findFormUrlencoded(@NotNull String id);

    /**
    * 查找所有 form-urlencoded
    * @return
    */
    @FindAll
    List<FormUrlencoded> findAllFormUrlencoded();

    /**
    * 查询列表 form-urlencoded
    * @param formUrlencodedQuery
    * @return
    */
    List<FormUrlencoded> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery);

    /**
    * 按分页查询 form-urlencoded
    * @param formUrlencodedQuery
    * @return
    */
    Pagination<FormUrlencoded> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery);

}