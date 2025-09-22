package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.PathParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.PathParamUnitQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* Path服务接口
*/
@JoinProvider(model = PathParamUnit.class)
public interface PathParamUnitService {

    /**
    * 创建Path
    * @param pathParamUnit
    * @return
    */
    String createPathParamUnit(@NotNull @Valid PathParamUnit pathParamUnit);

    /**
    * 更新Path
    * @param pathParamUnit
    */
    void updatePathParamUnit(@NotNull @Valid PathParamUnit pathParamUnit);

    /**
    * 删除Path
    * @param id
    */
    void deletePathParamUnit(@NotNull String id);


    /**
     * 通过httpId删除所有Path
     * @param id
     */
    void deleteAllPathParamUnit(@NotNull String id);


    /**
    * 查找Path
    * @param id
    * @return
    */
    @FindOne
    PathParamUnit findPathParamUnit(@NotNull String id);

    /**
    * 查找Path
    * @return
    */
    @FindAll
    List<PathParamUnit> findAllPathParamUnit();

    /**
    * 查询列表Path
    * @param pathParamUnitQuery
    * @return
    */
    List<PathParamUnit> findPathParamUnitList(PathParamUnitQuery pathParamUnitQuery);

    /**
    * 按分页查询Path
    * @param pathParamUnitQuery
    * @return
    */
    Pagination<PathParamUnit> findPathParamUnitPage(PathParamUnitQuery pathParamUnitQuery);

}