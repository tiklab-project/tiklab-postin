package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.RawParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RawParamUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* raw类型 服务接口
*/
public interface RawParamUnitService {

    /**
    * 创建raw
    * @param rawParamUnit
    * @return
    */
    String createRawParam(@NotNull @Valid RawParamUnit rawParamUnit);

    /**
    * 更新raw
    * @param rawParamUnit
    */
    void updateRawParam(@NotNull @Valid RawParamUnit rawParamUnit);

    /**
    * 删除raw
    * @param id
    */
    void deleteRawParam(@NotNull String id);

    void deleteAllRawParam(String caseId);

    RawParamUnit findOne(@NotNull String id);

    List<RawParamUnit> findList(List<String> idList);

    /**
    * 根据id查找raw
    * @param id
    * @return
    */
    RawParamUnit findRawParam(@NotNull String id);

    /**
    * 查找所有raw
    * @return
    */
    List<RawParamUnit> findAllRawParam();

    /**
    * 根据查询参数查询raw列表
    * @param rawParamUnitQuery
    * @return
    */
    List<RawParamUnit> findRawParamList(RawParamUnitQuery rawParamUnitQuery);

    /**
    * 根据查询参数按分页查询raw
    * @param rawParamUnitQuery
    * @return
    */
    Pagination<RawParamUnit> findRawParamPage(RawParamUnitQuery rawParamUnitQuery);

}