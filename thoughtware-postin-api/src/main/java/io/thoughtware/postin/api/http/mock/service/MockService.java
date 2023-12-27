package io.thoughtware.postin.api.http.mock.service;

import io.thoughtware.core.page.Pagination;

import io.thoughtware.postin.api.http.mock.model.Mock;
import io.thoughtware.postin.api.http.mock.model.MockQuery;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = Mock.class)
public interface MockService {

    /**
    * 创建用户
    * @param mock
    * @return
    */
    String createMock(@NotNull @Valid Mock mock);

    /**
    * 更新用户
    * @param mock
    */
    void updateMock(@NotNull @Valid Mock mock);

    /**
    * 删除用户
    * @param id
    */
    void deleteMock(@NotNull String id);

    /**
     * 通过httpId删除所有Mock
     * @param httpId
     */
    void deleteAllMock(@NotNull String httpId);


    @FindOne
    Mock findOne(@NotNull String id);

    @FindList
    List<Mock> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    Mock findMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<Mock> findAllMock();

    /**
    * 查询列表
    * @param mockQuery
    * @return
    */
    List<Mock> findMockList(MockQuery mockQuery);

    /**
    * 按分页查询
    * @param mockQuery
    * @return
    */
    Pagination<Mock> findMockPage(MockQuery mockQuery);

}