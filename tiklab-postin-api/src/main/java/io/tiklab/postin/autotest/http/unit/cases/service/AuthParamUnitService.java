package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthParamUnitQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 认证（主）服务接口
*/
@JoinProvider(model = AuthParamUnit.class)
public interface AuthParamUnitService {

    /**
    * 创建认证
    * @param authParamUnit
    * @return
    */
    String createAuthParamUnit(@NotNull @Valid AuthParamUnit authParamUnit);

    /**
    * 更新认证
    * @param authParamUnit
    */
    void updateAuthParamUnit(@NotNull @Valid AuthParamUnit authParamUnit);

    /**
    * 删除认证
    * @param id
    */
    void deleteAuthParamUnit(@NotNull String id);


    /**
     * 通过接口Id删除所有认证
     * @param id
     */
    void deleteAllAuthParamUnit(@NotNull String id);


    /**
    * 查找认证
    * @param id
    * @return
    */
    @FindOne
    AuthParamUnit findAuthParamUnit(@NotNull String id);

    /**
    * 查找认证
    * @return
    */
    @FindAll
    List<AuthParamUnit> findAllAuthParamUnit();

    /**
    * 查询列表认证
    * @param pathParamQuery
    * @return
    */
    List<AuthParamUnit> findAuthParamUnitList(AuthParamUnitQuery pathParamQuery);

    /**
    * 按分页查询认证
    * @param pathParamQuery
    * @return
    */
    Pagination<AuthParamUnit> findAuthParamUnitPage(AuthParamUnitQuery pathParamQuery);

}