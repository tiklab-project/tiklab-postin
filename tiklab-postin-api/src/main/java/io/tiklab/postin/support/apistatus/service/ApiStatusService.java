package io.tiklab.postin.support.apistatus.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.support.apistatus.model.ApiStatus;
import io.tiklab.postin.support.apistatus.model.ApiStatusQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口状态 服务接口
*/
@JoinProvider(model = ApiStatus.class)
public interface ApiStatusService {

    /**
    * 创建接口状态
    * @param apiStatus
    * @return
    */
    String createApiStatus(@NotNull @Valid ApiStatus apiStatus);

    /**
    * 更新接口状态
    * @param apiStatus
    */
    void updateApiStatus(@NotNull @Valid ApiStatus apiStatus);

    /**
    * 删除接口状态
    * @param id
    */
    void deleteApiStatus(@NotNull String id);


    /**
     * 通过workspaceId删除
     * @param workspaceId
     */
    void deleteAllApiStatus(@NotNull String workspaceId);

    @FindOne
    ApiStatus findOne(@NotNull String id);

    @FindList
    List<ApiStatus> findList(List<String> idList);

    /**
    * 查找接口状态
    * @param id
    * @return
    */
    ApiStatus findApiStatus(@NotNull String id);

    /**
    * 查找所有接口状态
    * @return
    */
    @FindAll
    List<ApiStatus> findAllApiStatus();

    /**
    * 查询列表接口状态
    * @param apiStatusQuery
    * @return
    */
    List<ApiStatus> findApiStatusList(ApiStatusQuery apiStatusQuery);

    /**
    * 按分页查询接口状态
    * @param apiStatusQuery
    * @return
    */
    Pagination<ApiStatus> findApiStatusPage(ApiStatusQuery apiStatusQuery);

}