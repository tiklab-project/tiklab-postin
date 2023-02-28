package net.tiklab.postin.api.http.definition.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.api.http.definition.model.ApiResponse;
import net.tiklab.postin.api.http.definition.model.ApiResponseQuery;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;

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