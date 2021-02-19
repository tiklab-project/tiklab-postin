package com.darthcloud.apibox.api.apimock.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.api.apimock.model.ResponseMock;
import com.darthcloud.apibox.api.apimock.model.ResponseMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface ResponseMockService {

    /**
    * 创建用户
    * @param responseMock
    * @return
    */
    String createResponseMock(@NotNull @Valid ResponseMock responseMock);

    /**
    * 更新用户
    * @param responseMock
    */
    void updateResponseMock(@NotNull @Valid ResponseMock responseMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteResponseMock(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    ResponseMock findResponseMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ResponseMock> findAllResponseMock();

    /**
    * 查询列表
    * @param responseMockQuery
    * @return
    */
    List<ResponseMock> findResponseMockList(ResponseMockQuery responseMockQuery);

    /**
    * 按分页查询
    * @param responseMockQuery
    * @return
    */
    Pagination<List<ResponseMock>> findResponseMockPage(ResponseMockQuery responseMockQuery);

}