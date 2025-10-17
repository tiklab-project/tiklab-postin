package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.autotest.http.unit.cases.model.PreParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.PreParamUnitQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 前置操作（主）服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface PreParamUnitService {

    /**
    * 创建前置操作
    * @param preParamUnit
    * @return
    */
    String createPreParamUnit(@NotNull @Valid PreParamUnit preParamUnit);

    /**
    * 更新前置操作
    * @param preParamUnit
    */
    void updatePreParamUnit(@NotNull @Valid PreParamUnit preParamUnit);

    /**
    * 删除前置操作
    * @param id
    */
    void deletePreParamUnit(@NotNull String id);


    /**
     * 通过用例Id删除所有前置操作
     * @param id
     */
    void deleteAllPreParamUnit(@NotNull String id);


    /**
    * 查找前置操作
    * @param id
    * @return
    */
    @FindOne
    PreParamUnit findPreParamUnit(@NotNull String id);

    /**
    * 查找前置操作
    * @return
    */
    @FindAll
    List<PreParamUnit> findAllPreParamUnit();

    /**
    * 查询列表前置操作
    * @param preParamUnitQuery
    * @return
    */
    List<PreParamUnit> findPreParamUnitList(PreParamUnitQuery preParamUnitQuery);

    /**
    * 按分页查询前置操作
    * @param preParamUnitQuery
    * @return
    */
    Pagination<PreParamUnit> findPreParamUnitPage(PreParamUnitQuery preParamUnitQuery);

    /**
     * 拖拽排序更新
     * @param id 操作ID
     * @param newSort 新的排序值
     */
    void updatePreParamUnitSort(@NotNull String id, @NotNull Integer newSort);

}