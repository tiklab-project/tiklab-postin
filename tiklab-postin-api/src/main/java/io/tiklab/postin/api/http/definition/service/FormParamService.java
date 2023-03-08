package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.postin.api.http.definition.model.FormParam;
import io.tiklab.postin.api.http.definition.model.FormParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* formdata服务接口
*/
@JoinProvider(model = FormParam.class)
public interface FormParamService {

    /**
    * 创建formdata
    * @param formParam
    * @return
    */
    String createFormParam(@NotNull @Valid FormParam formParam);

    /**
    * 更新formdata
    * @param formParam
    */
    void updateFormParam(@NotNull @Valid FormParam formParam);

    /**
    * 删除formdata
    * @param id
    */
    void deleteFormParam(@NotNull String id);

    /**
    * 查找formdata
    * @param id
    * @return
    */
    FormParam findFormParam(@NotNull String id);

    /**
    * 查找所有formdata
    * @return
    */
    @FindAll
    List<FormParam> findAllFormParam();

    /**
    * 查询列表formdata
    * @param formParamQuery
    * @return
    */
    List<FormParam> findFormParamList(FormParamQuery formParamQuery);

    /**
    * 按分页formdata
    * @param formParamQuery
    * @return
    */
    Pagination<FormParam> findFormParamPage(FormParamQuery formParamQuery);

}