package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.AuthParam;
import io.tiklab.postin.api.http.definition.model.AuthParamQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 认证（主）服务接口
*/
@JoinProvider(model = AuthParam.class)
public interface AuthParamService {

    /**
    * 创建认证
    * @param authParam
    * @return
    */
    String createAuthParam(@NotNull @Valid AuthParam authParam);

    /**
    * 更新认证
    * @param authParam
    */
    void updateAuthParam(@NotNull @Valid AuthParam authParam);

    /**
    * 删除认证
    * @param id
    */
    void deleteAuthParam(@NotNull String id);


    /**
     * 通过接口Id删除所有认证
     * @param id
     */
    void deleteAllAuthParam(@NotNull String id);


    /**
    * 查找认证
    * @param id
    * @return
    */
    @FindOne
    AuthParam findAuthParam(@NotNull String id);

    /**
    * 查找认证
    * @return
    */
    @FindAll
    List<AuthParam> findAllAuthParam();

    /**
    * 查询列表认证
    * @param pathParamQuery
    * @return
    */
    List<AuthParam> findAuthParamList(AuthParamQuery pathParamQuery);

    /**
    * 按分页查询认证
    * @param pathParamQuery
    * @return
    */
    Pagination<AuthParam> findAuthParamPage(AuthParamQuery pathParamQuery);

}