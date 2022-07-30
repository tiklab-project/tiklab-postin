package com.tiklab.postin.apimock.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postin.apimock.http.model.RawResponseMock;
import com.tiklab.postin.apimock.http.model.RawResponseMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RawResponseMockService {

    /**
    * 创建用户
    * @param rawResponseMock
    * @return
    */
    String createRawResponseMock(@NotNull @Valid RawResponseMock rawResponseMock);

    /**
    * 更新用户
    * @param rawResponseMock
    */
    void updateRawResponseMock(@NotNull @Valid RawResponseMock rawResponseMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteRawResponseMock(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RawResponseMock findRawResponseMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RawResponseMock> findAllRawResponseMock();

    /**
    * 查询列表
    * @param rawResponseMockQuery
    * @return
    */
    List<RawResponseMock> findRawResponseMockList(RawResponseMockQuery rawResponseMockQuery);

    /**
    * 按分页查询
    * @param rawResponseMockQuery
    * @return
    */
    Pagination<RawResponseMock> findRawResponseMockPage(RawResponseMockQuery rawResponseMockQuery);

}