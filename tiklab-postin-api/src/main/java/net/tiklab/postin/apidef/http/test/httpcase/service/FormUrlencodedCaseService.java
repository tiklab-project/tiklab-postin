package net.tiklab.postin.apidef.http.test.httpcase.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.apidef.http.test.httpcase.model.FormUrlencodedCase;
import net.tiklab.postin.apidef.http.test.httpcase.model.FormUrlencodedCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* FormUrlencodedCaseService
*/
public interface FormUrlencodedCaseService {

    /**
    * 创建
    * @param formUrlencodedCase
    * @return
    */
    String createFormUrlencodedCase(@NotNull @Valid FormUrlencodedCase formUrlencodedCase);

    /**
    * 更新
    * @param formUrlencodedCase
    */
    void updateFormUrlencodedCase(@NotNull @Valid FormUrlencodedCase formUrlencodedCase);

    /**
    * 删除
    * @param id
    */
    void deleteFormUrlencodedCase(@NotNull String id);

    FormUrlencodedCase findOne(@NotNull String id);

    List<FormUrlencodedCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    FormUrlencodedCase findFormUrlencodedCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<FormUrlencodedCase> findAllFormUrlencodedCase();

    /**
    * 查询列表
    * @param formUrlencodedCaseQuery
    * @return
    */
    List<FormUrlencodedCase> findFormUrlencodedCaseList(FormUrlencodedCaseQuery formUrlencodedCaseQuery);

    /**
    * 按分页查询
    * @param formUrlencodedCaseQuery
    * @return
    */
    Pagination<FormUrlencodedCase> findFormUrlencodedCasePage(FormUrlencodedCaseQuery formUrlencodedCaseQuery);

}