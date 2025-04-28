package io.tiklab.postin.version.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.version.model.ApiVersion;
import io.tiklab.postin.version.model.ApiVersionQuery;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口版本 服务接口
*/
@JoinProvider(model = ApiVersion.class)
public interface ApiVersionService {

    /**
    * 创建接口版本
    * @param apiVersion
    * @return
    */
    String createApiVersion(@NotNull @Valid ApiVersion apiVersion);

    /**
    * 更新接口版本
    * @param apiVersion
    */
    void updateApiVersion(@NotNull @Valid ApiVersion apiVersion);

    /**
    * 删除接口版本
    * @param id
    */
    void deleteApiVersion(@NotNull String id);

    /**
     * 通过apiId删除
     * @param apiId
     */
    void deleteAllApiVersion(@NotNull String apiId);


    @FindOne
    ApiVersion findOne(@NotNull String id);

    @FindList
    List<ApiVersion> findList(List<String> idList);

    /**
    * 查找接口版本
    * @param id
    * @return
    */
    ApiVersion findApiVersion(@NotNull String id);

    /**
    * 查找所有接口版本
    * @return
    */
    List<ApiVersion> findAllApiVersion();

    /**
    * 查询列表接口版本
    * @param apiVersionQuery
    * @return
    */
    List<ApiVersion> findApiVersionList(ApiVersionQuery apiVersionQuery);

    /**
    * 按分页查询接口版本
    * @param apiVersionQuery
    * @return
    */
    Pagination<ApiVersion> findApiVersionPage(ApiVersionQuery apiVersionQuery);


    void backVersion(@NotNull String apiId, @NotNull String versionApiId);

}