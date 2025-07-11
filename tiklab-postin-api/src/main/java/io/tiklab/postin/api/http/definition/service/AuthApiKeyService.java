package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.AuthApiKey;
import io.tiklab.postin.api.http.definition.model.AuthApiKeyQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiKey自定义认证 服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface AuthApiKeyService {

    /**
    * 创建自定义认证
    * @param authApiKey
    * @return
    */
    String createAuthApiKey(@NotNull @Valid AuthApiKey authApiKey);

    /**
    * 更新自定义认证
    * @param authApiKey
    */
    void updateAuthApiKey(@NotNull @Valid AuthApiKey authApiKey);

    /**
    * 删除自定义认证
    * @param id
    */
    void deleteAuthApiKey(@NotNull String id);


    /**
     * 通过接口Id删除所有自定义认证
     * @param id
     */
    void deleteAllAuthApiKey(@NotNull String id);


    /**
    * 查找自定义认证
    * @param id
    * @return
    */
    @FindOne
    AuthApiKey findAuthApiKey(@NotNull String id);

    /**
    * 查找自定义认证
    * @return
    */
    @FindAll
    List<AuthApiKey> findAllAuthApiKey();

    /**
    * 查询列表自定义认证
    * @param pathApiKeyQuery
    * @return
    */
    List<AuthApiKey> findAuthApiKeyList(AuthApiKeyQuery pathApiKeyQuery);

    /**
    * 按分页查询自定义认证
    * @param pathApiKeyQuery
    * @return
    */
    Pagination<AuthApiKey> findAuthApiKeyPage(AuthApiKeyQuery pathApiKeyQuery);

}