package io.tiklab.postin.api.http.mock.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.mock.model.ResponseHeaderMock;
import io.tiklab.postin.api.http.mock.model.ResponseHeaderMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface ResponseHeaderMockService {

    /**
    * 创建用户
    * @param responseHeaderMock
    * @return
    */
    String createResponseHeaderMock(@NotNull @Valid ResponseHeaderMock responseHeaderMock);

    /**
    * 更新用户
    * @param responseHeaderMock
    */
    void updateResponseHeaderMock(@NotNull @Valid ResponseHeaderMock responseHeaderMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteResponseHeaderMock(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    ResponseHeaderMock findResponseHeaderMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ResponseHeaderMock> findAllResponseHeaderMock();

    /**
    * 查询列表
    * @param responseHeaderMockQuery
    * @return
    */
    List<ResponseHeaderMock> findResponseHeaderMockList(ResponseHeaderMockQuery responseHeaderMockQuery);

    /**
    * 按分页查询
    * @param responseHeaderMockQuery
    * @return
    */
    Pagination<ResponseHeaderMock> findResponseHeaderMockPage(ResponseHeaderMockQuery responseHeaderMockQuery);

}