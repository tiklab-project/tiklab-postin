package net.tiklab.postin.apimock.http.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.apimock.http.model.Mock;
import net.tiklab.postin.apimock.http.model.MockQuery;
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