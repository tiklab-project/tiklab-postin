package io.tiklab.postin.api.http.definition.service;

import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.model.HttpApiQuery;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* http协议 服务接口
*/
@JoinProvider(model = HttpApi.class)
public interface HttpApiService {

    /**
    * 创建http协议
    * @param
    * @return
    */
    String createHttpApi(@NotNull @Valid HttpApi httpApi);

    /**
    * 更新http协议
    * @param httpApi
    */
    void updateHttpApi(@NotNull @Valid HttpApi httpApi);

    /**
    * 删除http协议
    * @param id
    */
    void deleteHttpApi(@NotNull String id);

    @FindOne
    HttpApi findOne(@NotNull String id);

    @FindList
    List<HttpApi> findList(List<String> idList);

    /**
    * 查找http协议
    * @param id
    * @return
    */
    HttpApi findHttpApi(@NotNull String id);

    /**
    * 查找http协议
    * @return
    */
    @FindAll
    List<HttpApi> findAllHttpApi();

    /**
    * 查询列表http协议
    * @param httpApiQuery
    * @return
    */
    List<HttpApi> findHttpApiList(HttpApiQuery httpApiQuery);

    /**
    * 按分页查询http协议
    * @param httpApiQuery
    * @return
    */
    Pagination<HttpApi> findHttpApiPage(HttpApiQuery httpApiQuery);

    /**
     * 通过apix 查询接口
     * @param apixQuery
     * @return
     */
    List<HttpApi> findHttpApiListByApix(ApixQuery apixQuery);





}