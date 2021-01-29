package com.darthcloud.apibox.apimock.jsonresponsemock.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.apimock.jsonresponsemock.model.JsonResponseMock;
import com.darthcloud.apibox.apimock.jsonresponsemock.model.JsonResponseMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface JsonResponseMockService {

    /**
    * 创建用户
    * @param jsonResponseMock
    * @return
    */
    String createJsonResponseMock(@NotNull @Valid JsonResponseMock jsonResponseMock);

    /**
    * 更新用户
    * @param jsonResponseMock
    */
    void updateJsonResponseMock(@NotNull @Valid JsonResponseMock jsonResponseMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteJsonResponseMock(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    JsonResponseMock findJsonResponseMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<JsonResponseMock> findAllJsonResponseMock();

    /**
    * 查询列表
    * @param jsonResponseMockQuery
    * @return
    */
    List<JsonResponseMock> findJsonResponseMockList(JsonResponseMockQuery jsonResponseMockQuery);

    /**
    * 按分页查询
    * @param jsonResponseMockQuery
    * @return
    */
    Pagination<List<JsonResponseMock>> findJsonResponseMockPage(JsonResponseMockQuery jsonResponseMockQuery);

}