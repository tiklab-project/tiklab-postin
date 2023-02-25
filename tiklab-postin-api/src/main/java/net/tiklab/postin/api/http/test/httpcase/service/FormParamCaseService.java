package net.tiklab.postin.api.http.test.httpcase.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.api.http.test.httpcase.model.FormParamCase;
import net.tiklab.postin.api.http.test.httpcase.model.FormParamCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface FormParamCaseService {

    /**
    * 创建用户
    * @param formParamCase
    * @return
    */
    String createFormParamCase(@NotNull @Valid FormParamCase formParamCase);

    /**
    * 更新用户
    * @param formParamCase
    */
    void updateFormParamCase(@NotNull @Valid FormParamCase formParamCase);

    /**
    * 删除用户
    * @param id
    */
    void deleteFormParamCase(@NotNull String id);

    FormParamCase findOne(@NotNull String id);

    List<FormParamCase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    FormParamCase findFormParamCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<FormParamCase> findAllFormParamCase();

    /**
    * 查询列表
    * @param formParamCaseQuery
    * @return
    */
    List<FormParamCase> findFormParamCaseList(FormParamCaseQuery formParamCaseQuery);

    /**
    * 按分页查询
    * @param formParamCaseQuery
    * @return
    */
    Pagination<FormParamCase> findFormParamCasePage(FormParamCaseQuery formParamCaseQuery);

}