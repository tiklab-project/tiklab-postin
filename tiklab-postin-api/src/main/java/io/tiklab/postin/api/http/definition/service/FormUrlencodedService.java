package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.postin.api.http.definition.model.FormUrlencoded;
import io.tiklab.postin.api.http.definition.model.FormUrlencodedQuery;

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