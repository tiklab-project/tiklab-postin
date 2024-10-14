package io.tiklab.postin.api.apix.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.postin.api.apix.model.ApiRequest;
import io.tiklab.postin.api.apix.model.ApiRequestQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口定义中请求区 接口
*/
@JoinProvider(model = ApiRequest.class)
public interface ApiRequestService {

    /**
    * 创建接口定义中请求区
    * @param apiRequest
    * @return
    */
    String createApiRequest(@NotNull @Valid ApiRequest apiRequest);

    /**
    * 更新接口定义中请求区
    * @param apiRequest
    */
    void updateApiRequest(@NotNull @Valid ApiRequest apiRequest);

    /**
    * 删除接口定义中请求区
    * @param id
    */
    void deleteApiRequest(@NotNull String id);

    @FindOne
    ApiRequest findOne(@NotNull String id);

    @FindList
    List<ApiRequest> findList(List<String> idList);

    /**
    * 查找接口定义中请求区
    * @param id
    * @return
    */
    ApiRequest findApiRequest(@NotNull String id);

    /**
    * 查找所有接口定义中请求区
    * @return
    */
    @FindAll
    List<ApiRequest> findAllApiRequest();

    /**
    * 查询列表接口定义中请求区
    * @param apiRequestQuery
    * @return
    */
    List<ApiRequest> findApiRequestList(ApiRequestQuery apiRequestQuery);

    /**
    * 按分页查询接口定义中请求区
    * @param apiRequestQuery
    * @return
    */
    Pagination<ApiRequest> findApiRequestPage(ApiRequestQuery apiRequestQuery);

}