package com.doublekit.apibox.apimock.http.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apimock.http.model.ResponseMock;
import com.doublekit.apibox.apimock.http.model.ResponseMockQuery;

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
    Pagination<ResponseMock> findResponseMockPage(ResponseMockQuery responseMockQuery);

}