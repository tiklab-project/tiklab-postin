package net.tiklab.postin.api.http.definition.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.postin.api.http.definition.model.ApiRequest;
import net.tiklab.postin.api.http.definition.model.ApiRequestQuery;

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