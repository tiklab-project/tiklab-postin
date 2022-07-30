package com.tiklab.postin.apimock.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postin.apimock.http.model.RequestBodyMock;
import com.tiklab.postin.apimock.http.model.RequestBodyMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestBodyMockService {

    /**
    * 创建用户
    * @param requestBodyMock
    * @return
    */
    String createRequestBodyMock(@NotNull @Valid RequestBodyMock requestBodyMock);

    /**
    * 更新用户
    * @param requestBodyMock
    */
    void updateRequestBodyMock(@NotNull @Valid RequestBodyMock requestBodyMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestBodyMock(@NotNull String id);

    RequestBodyMock findOne(@NotNull String id);

    List<RequestBodyMock> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestBodyMock findRequestBodyMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestBodyMock> findAllRequestBodyMock();

    /**
    * 查询列表
    * @param requestBodyMockQuery
    * @return
    */
    List<RequestBodyMock> findRequestBodyMockList(RequestBodyMockQuery requestBodyMockQuery);

    /**
    * 按分页查询
    * @param requestBodyMockQuery
    * @return
    */
    Pagination<RequestBodyMock> findRequestBodyMockPage(RequestBodyMockQuery requestBodyMockQuery);

}