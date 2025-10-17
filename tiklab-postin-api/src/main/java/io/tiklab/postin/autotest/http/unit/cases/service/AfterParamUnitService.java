package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterParamUnitQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 后置操作（主）服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface AfterParamUnitService {

    /**
    * 创建后置操作
    * @param afterParamUnit
    * @return
    */
    String createAfterParamUnit(@NotNull @Valid AfterParamUnit afterParamUnit);

    /**
    * 更新后置操作
    * @param afterParamUnit
    */
    void updateAfterParamUnit(@NotNull @Valid AfterParamUnit afterParamUnit);

    /**
    * 删除后置操作
    * @param id
    */
    void deleteAfterParamUnit(@NotNull String id);


    /**
     * 通过用例Id删除所有后置操作
     * @param id
     */
    void deleteAllAfterParamUnit(@NotNull String id);


    /**
    * 查找后置操作
    * @param id
    * @return
    */
    @FindOne
    AfterParamUnit findAfterParamUnit(@NotNull String id);

    /**
    * 查找后置操作
    * @return
    */
    @FindAll
    List<AfterParamUnit> findAllAfterParamUnit();

    /**
    * 查询列表后置操作
    * @param afterParamUnitQuery
    * @return
    */
    List<AfterParamUnit> findAfterParamUnitList(AfterParamUnitQuery afterParamUnitQuery);

    /**
    * 按分页查询后置操作
    * @param afterParamUnitQuery
    * @return
    */
    Pagination<AfterParamUnit> findAfterParamUnitPage(AfterParamUnitQuery afterParamUnitQuery);

    /**
     * 拖拽排序更新
     * @param id 操作ID
     * @param newSort 新的排序值
     */
    void updateAfterParamUnitSort(@NotNull String id, @NotNull Integer newSort);

}