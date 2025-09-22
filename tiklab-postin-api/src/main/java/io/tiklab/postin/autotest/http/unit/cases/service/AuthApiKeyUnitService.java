package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthApiKeyUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthApiKeyUnitQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiKey自定义认证 服务接口
*/
@JoinProvider(model = AuthApiKeyUnit.class)
public interface AuthApiKeyUnitService {

    /**
    * 创建自定义认证
    * @param authApiKeyUnit
    * @return
    */
    String createAuthApiKeyUnit(@NotNull @Valid AuthApiKeyUnit authApiKeyUnit);

    /**
    * 更新自定义认证
    * @param authApiKeyUnit
    */
    void updateAuthApiKeyUnit(@NotNull @Valid AuthApiKeyUnit authApiKeyUnit);

    /**
    * 删除自定义认证
    * @param id
    */
    void deleteAuthApiKeyUnit(@NotNull String id);


    /**
     * 通过接口Id删除所有自定义认证
     * @param id
     */
    void deleteAllAuthApiKeyUnit(@NotNull String id);


    /**
    * 查找自定义认证
    * @param id
    * @return
    */
    @FindOne
    AuthApiKeyUnit findAuthApiKeyUnit(@NotNull String id);

    /**
    * 查找自定义认证
    * @return
    */
    @FindAll
    List<AuthApiKeyUnit> findAllAuthApiKeyUnit();

    /**
    * 查询列表自定义认证
    * @param pathApiKeyQuery
    * @return
    */
    List<AuthApiKeyUnit> findAuthApiKeyUnitList(AuthApiKeyUnitQuery pathApiKeyQuery);

    /**
    * 按分页查询自定义认证
    * @param pathApiKeyQuery
    * @return
    */
    Pagination<AuthApiKeyUnit> findAuthApiKeyUnitPage(AuthApiKeyUnitQuery pathApiKeyQuery);

}