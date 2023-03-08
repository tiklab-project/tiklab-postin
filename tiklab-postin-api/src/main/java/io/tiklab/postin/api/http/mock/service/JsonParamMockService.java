package io.tiklab.postin.api.http.mock.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.mock.model.JsonParamMock;
import io.tiklab.postin.api.http.mock.model.JsonParamMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface JsonParamMockService {

    /**
    * 创建用户
    * @param jsonParamMock
    * @return
    */
    String createJsonParamMock(@NotNull @Valid JsonParamMock jsonParamMock);

    /**
    * 更新用户
    * @param jsonParamMock
    */
    void updateJsonParamMock(@NotNull @Valid JsonParamMock jsonParamMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteJsonParamMock(@NotNull String id);

    JsonParamMock findOne(@NotNull String id);

    List<JsonParamMock> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    JsonParamMock findJsonParamMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<JsonParamMock> findAllJsonParamMock();

    /**
    * 查询列表
    * @param jsonParamMockQuery
    * @return
    */
    List<JsonParamMock> findJsonParamMockList(JsonParamMockQuery jsonParamMockQuery);

    /**
    * 按分页查询
    * @param jsonParamMockQuery
    * @return
    */
    Pagination<JsonParamMock> findJsonParamMockPage(JsonParamMockQuery jsonParamMockQuery);

}