package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.AuthBearer;
import io.tiklab.postin.api.http.definition.model.AuthBearerQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* BearerToken认证 服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface AuthBearerService {

    /**
    * 创建BearerToken认证
    * @param authBearer
    * @return
    */
    String createAuthBearer(@NotNull @Valid AuthBearer authBearer);

    /**
    * 更新BearerToken认证
    * @param authBearer
    */
    void updateAuthBearer(@NotNull @Valid AuthBearer authBearer);

    /**
    * 删除BearerToken认证
    * @param id
    */
    void deleteAuthBearer(@NotNull String id);


    /**
     * 通过接口Id删除所有BearerToken认证
     * @param id
     */
    void deleteAllAuthBearer(@NotNull String id);


    /**
    * 查找BearerToken认证
    * @param id
    * @return
    */
    @FindOne
    AuthBearer findAuthBearer(@NotNull String id);

    /**
    * 查找BearerToken认证
    * @return
    */
    @FindAll
    List<AuthBearer> findAllAuthBearer();

    /**
    * 查询列表BearerToken认证
    * @param pathBearerQuery
    * @return
    */
    List<AuthBearer> findAuthBearerList(AuthBearerQuery pathBearerQuery);

    /**
    * 按分页查询BearerToken认证
    * @param pathBearerQuery
    * @return
    */
    Pagination<AuthBearer> findAuthBearerPage(AuthBearerQuery pathBearerQuery);

}