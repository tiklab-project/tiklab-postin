package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthBearerUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthBearerUnitQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* BearerToken认证 服务接口
*/
@JoinProvider(model = AuthBearerUnit.class)
public interface AuthBearerUnitService {

    /**
    * 创建BearerToken认证
    * @param authBearerUnit
    * @return
    */
    String createAuthBearerUnit(@NotNull @Valid AuthBearerUnit authBearerUnit);

    /**
    * 更新BearerToken认证
    * @param authBearerUnit
    */
    void updateAuthBearerUnit(@NotNull @Valid AuthBearerUnit authBearerUnit);

    /**
    * 删除BearerToken认证
    * @param id
    */
    void deleteAuthBearerUnit(@NotNull String id);


    /**
     * 通过接口Id删除所有BearerToken认证
     * @param id
     */
    void deleteAllAuthBearerUnit(@NotNull String id);


    /**
    * 查找BearerToken认证
    * @param id
    * @return
    */
    @FindOne
    AuthBearerUnit findAuthBearerUnit(@NotNull String id);

    /**
    * 查找BearerToken认证
    * @return
    */
    @FindAll
    List<AuthBearerUnit> findAllAuthBearerUnit();

    /**
    * 查询列表BearerToken认证
    * @param pathBearerQuery
    * @return
    */
    List<AuthBearerUnit> findAuthBearerUnitList(AuthBearerUnitQuery pathBearerQuery);

    /**
    * 按分页查询BearerToken认证
    * @param pathBearerQuery
    * @return
    */
    Pagination<AuthBearerUnit> findAuthBearerUnitPage(AuthBearerUnitQuery pathBearerQuery);

}