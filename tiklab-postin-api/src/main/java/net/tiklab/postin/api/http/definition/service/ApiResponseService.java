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
* 用户服务接口
*/
@JoinProvider(model = ApiResponse.class)
public interface ApiResponseService {

    /**
    * 创建用户
    * @param apiResponse
    * @return
    */
    String createApiResponse(@NotNull @Valid ApiResponse apiResponse);

    /**
    * 更新用户
    * @param apiResponse
    */
    void updateApiResponse(@NotNull @Valid ApiResponse apiResponse);

    /**
    * 删除用户
    * @param id
    */
    void deleteApiResponse(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    @FindOne
    ApiResponse findApiResponse(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ApiResponse> findAllApiResponse();

    /**
    * 查询列表
    * @param apiResponseQuery
    * @return
    */
    List<ApiResponse> findApiResponseList(ApiResponseQuery apiResponseQuery);

    /**
    * 按分页查询
    * @param apiResponseQuery
    * @return
    */
    Pagination<ApiResponse> findApiResponsePage(ApiResponseQuery apiResponseQuery);

}