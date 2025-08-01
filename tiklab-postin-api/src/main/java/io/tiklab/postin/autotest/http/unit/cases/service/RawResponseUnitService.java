package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.RawResponseUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RawResponseUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应中raw 服务接口
*/
public interface RawResponseUnitService {

    /**
    * 创建响应中raw
    * @param rawResponseUnit
    * @return
    */
    String createRawResponse(@NotNull @Valid RawResponseUnit rawResponseUnit);

    /**
    * 更新响应中raw
    * @param rawResponseUnit
    */
    void updateRawResponse(@NotNull @Valid RawResponseUnit rawResponseUnit);

    /**
    * 删除响应中raw
    * @param id
    */
    void deleteRawResponse(@NotNull String id);

    RawResponseUnit findOne(@NotNull String id);

    List<RawResponseUnit> findList(List<String> idList);

    /**
    * 查找响应中raw
    * @param id
    * @return
    */
    RawResponseUnit findRawResponse(@NotNull String id);

    /**
    * 查找所有响应中raw
    * @return
    */
    List<RawResponseUnit> findAllRawResponse();

    /**
    * 根据查询参数查询响应中raw列表
    * @param rawResponseUnitQuery
    * @return
    */
    List<RawResponseUnit> findRawResponseList(RawResponseUnitQuery rawResponseUnitQuery);

    /**
    * 根据查询参数按分页查询响应中raw
    * @param rawResponseUnitQuery
    * @return
    */
    Pagination<RawResponseUnit> findRawResponsePage(RawResponseUnitQuery rawResponseUnitQuery);

}