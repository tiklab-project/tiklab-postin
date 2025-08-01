package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.FormParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.FormParamUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-data 服务接口
*/
public interface FormParamUnitService {

    /**
    * 创建form-data
    * @param formParamUnit
    * @return
    */
    String createFormParam(@NotNull @Valid FormParamUnit formParamUnit);

    /**
    * 更新form-data
    * @param formParamUnit
    */
    void updateFormParam(@NotNull @Valid FormParamUnit formParamUnit);

    /**
    * 删除form-data
    * @param id
    */
    void deleteFormParam(@NotNull String id);

    void deleteAllFormParam( String caseId);

    FormParamUnit findOne(@NotNull String id);

    List<FormParamUnit> findList(List<String> idList);

    /**
    * 根据id查找form-data
    * @param id
    * @return
    */
    FormParamUnit findFormParam(@NotNull String id);

    /**
    * 查找所有form-data
    * @return
    */
    List<FormParamUnit> findAllFormParam();

    /**
    * 根据查询参数查询form-data列表
    * @param formParamUnitQuery
    * @return
    */
    List<FormParamUnit> findFormParamList(FormParamUnitQuery formParamUnitQuery);

    /**
    * 根据查询参数按分页查询form-data
    * @param formParamUnitQuery
    * @return
    */
    Pagination<FormParamUnit> findFormParamPage(FormParamUnitQuery formParamUnitQuery);

}