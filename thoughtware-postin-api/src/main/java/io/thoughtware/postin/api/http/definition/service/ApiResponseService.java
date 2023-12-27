package io.thoughtware.postin.api.http.definition.service;

import io.thoughtware.core.page.Pagination;

import io.thoughtware.postin.api.http.definition.model.ApiResponse;
import io.thoughtware.postin.api.http.definition.model.ApiResponseQuery;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口定义中响应区服务接口
*/
@JoinProvider(model = ApiResponse.class)
public interface ApiResponseService {

    /**
    * 创建接口定义中响应区
    * @param apiResponse
    * @return
    */
    String createApiResponse(@NotNull @Valid ApiResponse apiResponse);

    /**
    * 更新接口定义中响应区
    * @param apiResponse
    */
    void updateApiResponse(@NotNull @Valid ApiResponse apiResponse);

    /**
    * 删除接口定义中响应区
    * @param id
    */
    void deleteApiResponse(@NotNull String id);

    /**
    * 查找接口定义中响应区
    * @param id
    * @return
    */
    @FindOne
    ApiResponse findApiResponse(@NotNull String id);

    /**
    * 查找接口定义中响应区
    * @return
    */
    @FindAll
    List<ApiResponse> findAllApiResponse();

    /**
    * 查询接口定义中响应区
    * @param apiResponseQuery
    * @return
    */
    List<ApiResponse> findApiResponseList(ApiResponseQuery apiResponseQuery);

    /**
    * 按分页接口定义中响应区
    * @param apiResponseQuery
    * @return
    */
    Pagination<ApiResponse> findApiResponsePage(ApiResponseQuery apiResponseQuery);

}