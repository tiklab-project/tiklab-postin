package io.thoughtware.postin.api.ws.ws.service;

import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.postin.api.ws.ws.model.WSApi;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


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

    /**
    * 查找ws协议
    * @param id
    * @return
    */
    WSApi findWSApi(@NotNull String id);


}