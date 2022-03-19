package com.doublekit.apibox.apidef.http.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apidef.http.model.HttpApi;
import com.doublekit.apibox.apidef.http.model.HttpApiQuery;
import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.JoinProvider;
import com.doublekit.join.annotation.FindAll;
import com.doublekit.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = HttpApi.class)
public interface HttpApiService {

    /**
    * 创建用户
    * @param
    * @return
    */
    String createHttpApi();

    /**
    * 更新用户
    * @param httpApi
    */
    void updateHttpApi(@NotNull @Valid HttpApi httpApi);

    /**
    * 删除用户
    * @param id
    */
    void deleteHttpApi(@NotNull String id);

    @FindOne
    HttpApi findOne(@NotNull String id);

    @FindList
    List<HttpApi> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    HttpApi findHttpApi(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<HttpApi> findAllHttpApi();

    /**
    * 查询列表
    * @param httpApiQuery
    * @return
    */
    List<HttpApi> findHttpApiList(HttpApiQuery httpApiQuery);

    /**
    * 按分页查询
    * @param httpApiQuery
    * @return
    */
    Pagination<HttpApi> findHttpApiPage(HttpApiQuery httpApiQuery);

}