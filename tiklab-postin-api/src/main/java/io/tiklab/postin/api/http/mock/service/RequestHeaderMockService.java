package io.tiklab.postin.api.http.mock.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.mock.model.RequestHeaderMock;
import io.tiklab.postin.api.http.mock.model.RequestHeaderMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestHeaderMockService {

    /**
    * 创建用户
    * @param requestHeaderMock
    * @return
    */
    String createRequestHeaderMock(@NotNull @Valid RequestHeaderMock requestHeaderMock);

    /**
    * 更新用户
    * @param requestHeaderMock
    */
    void updateRequestHeaderMock(@NotNull @Valid RequestHeaderMock requestHeaderMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestHeaderMock(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestHeaderMock findRequestHeaderMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestHeaderMock> findAllRequestHeaderMock();

    /**
    * 查询列表
    * @param requestHeaderMockQuery
    * @return
    */
    List<RequestHeaderMock> findRequestHeaderMockList(RequestHeaderMockQuery requestHeaderMockQuery);

    /**
    * 按分页查询
    * @param requestHeaderMockQuery
    * @return
    */
    Pagination<RequestHeaderMock> findRequestHeaderMockPage(RequestHeaderMockQuery requestHeaderMockQuery);

}