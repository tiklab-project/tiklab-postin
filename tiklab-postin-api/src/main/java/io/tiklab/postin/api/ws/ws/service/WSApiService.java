package io.tiklab.postin.api.ws.ws.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.postin.api.ws.ws.model.WSApi;
import io.tiklab.postin.api.ws.ws.model.WSApiQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ws协议 服务接口
*/
@JoinProvider(model = WSApi.class)
public interface WSApiService {

    /**
    * 创建ws协议
    * @param
    * @return
    */
    String createWSApi(@NotNull @Valid WSApi WSApi);

    /**
    * 更新ws协议
    * @param WSApi
    */
    void updateWSApi(@NotNull @Valid WSApi WSApi);

    /**
    * 删除ws协议
    * @param id
    */
    void deleteWSApi(@NotNull String id);

    @FindOne
    WSApi findOne(@NotNull String id);

    @FindList
    List<WSApi> findList(List<String> idList);

    /**
    * 查找ws协议
    * @param id
    * @return
    */
    WSApi findWSApi(@NotNull String id);

    /**
    * 查找ws协议
    * @return
    */
    @FindAll
    List<WSApi> findAllWSApi();

    /**
    * 查询列表ws协议
    * @param WSApiQuery
    * @return
    */
    List<WSApi> findWSApiList(WSApiQuery WSApiQuery);

    /**
    * 按分页查询ws协议
    * @param WSApiQuery
    * @return
    */
    Pagination<WSApi> findWSApiPage(WSApiQuery WSApiQuery);

    
}