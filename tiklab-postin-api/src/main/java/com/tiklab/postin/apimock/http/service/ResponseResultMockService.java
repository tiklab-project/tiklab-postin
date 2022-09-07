package com.tiklab.postin.apimock.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postin.apimock.http.model.ResponseResultMock;
import com.tiklab.postin.apimock.http.model.ResponseResultMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface ResponseResultMockService {

    /**
    * 创建用户
    * @param responseResultMock
    * @return
    */
    String createResponseResultMock(@NotNull @Valid ResponseResultMock responseResultMock);

    /**
    * 更新用户
    * @param responseResultMock
    */
    void updateResponseResultMock(@NotNull @Valid ResponseResultMock responseResultMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteResponseResultMock(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    ResponseResultMock findResponseResultMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ResponseResultMock> findAllResponseResultMock();

    /**
    * 查询列表
    * @param responseResultMockQuery
    * @return
    */
    List<ResponseResultMock> findResponseResultMockList(ResponseResultMockQuery responseResultMockQuery);

    /**
    * 按分页查询
    * @param responseResultMockQuery
    * @return
    */
    Pagination<ResponseResultMock> findResponseResultMockPage(ResponseResultMockQuery responseResultMockQuery);

}