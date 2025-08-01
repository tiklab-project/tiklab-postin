package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.FormUrlEncodedUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.FormUrlencodedUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-urlencoded 服务接口
*/
public interface FormUrlencodedUnitService {

    /**
    * 创建form-urlencoded
    * @param formUrlencodedUnit
    * @return
    */
    String createFormUrlencoded(@NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit);

    /**
    * 更新form-urlencoded
    * @param formUrlencodedUnit
    */
    void updateFormUrlencoded(@NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit);

    /**
    * 删除form-urlencoded
    * @param id
    */
    void deleteFormUrlencoded(@NotNull String id);

    void deleteAllFormUrlencoded( String caseId);

    FormUrlEncodedUnit findOne(@NotNull String id);

    List<FormUrlEncodedUnit> findList(List<String> idList);

    /**
    * 根据id查找form-urlencoded
    * @param id
    * @return
    */
    FormUrlEncodedUnit findFormUrlencoded(@NotNull String id);

    /**
    * 查找所有form-urlencoded
    * @return
    */
    List<FormUrlEncodedUnit> findAllFormUrlencoded();

    /**
    * 查询form-urlencoded 列表
    * @param formUrlencodedUnitQuery
    * @return
    */
    List<FormUrlEncodedUnit> findFormUrlencodedList(FormUrlencodedUnitQuery formUrlencodedUnitQuery);

    /**
    * 按分页查询form-urlencoded
    * @param formUrlencodedUnitQuery
    * @return
    */
    Pagination<FormUrlEncodedUnit> findFormUrlencodedPage(FormUrlencodedUnitQuery formUrlencodedUnitQuery);

}