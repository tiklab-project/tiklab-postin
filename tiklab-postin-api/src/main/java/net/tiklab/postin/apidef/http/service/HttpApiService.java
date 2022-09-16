package net.tiklab.postin.apidef.http.service;

import net.tiklab.postin.apidef.apix.model.ApixQuery;
import net.tiklab.core.page.Pagination;

import net.tiklab.postin.apidef.http.model.HttpApi;
import net.tiklab.postin.apidef.http.model.HttpApiQuery;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindOne;

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
    String createHttpApi(@NotNull @Valid HttpApi httpApi);

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

    List<HttpApi> findHttpApiListByApix(ApixQuery apixQuery);

}