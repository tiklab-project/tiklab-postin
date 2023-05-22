package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.model.ResponseInstanceQuery;
import io.tiklab.postin.api.http.test.instance.model.ResponseInstances;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface ResponseInstanceService {

    /**
    * 创建用户
    * @param responseInstances
    * @return
    */
    String createResponseInstance(@NotNull @Valid ResponseInstances responseInstances);

    /**
    * 更新用户
    * @param responseInstances
    */
    void updateResponseInstance(@NotNull @Valid ResponseInstances responseInstances);

    /**
    * 删除用户
    * @param id
    */
    void deleteResponseInstance(@NotNull String id);

    ResponseInstances findOne(@NotNull String id);

    List<ResponseInstances> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    ResponseInstances findResponseInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ResponseInstances> findAllResponseInstance();

    /**
    * 查询列表
    * @param responseInstanceQuery
    * @return
    */
    List<ResponseInstances> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery);

    /**
    * 按分页查询
    * @param responseInstanceQuery
    * @return
    */
    Pagination<ResponseInstances> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery);

}