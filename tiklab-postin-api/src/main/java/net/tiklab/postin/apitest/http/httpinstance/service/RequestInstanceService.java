package net.tiklab.postin.apitest.http.httpinstance.service;

import net.tiklab.postin.apitest.http.httpinstance.model.RequestInstance;
import net.tiklab.postin.apitest.http.httpinstance.model.RequestInstanceQuery;
import net.tiklab.core.page.Pagination;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestInstanceService {

    /**
    * 创建用户
    * @param requestInstance
    * @return
    */
    String createRequestInstance(@NotNull @Valid RequestInstance requestInstance);

    /**
    * 更新用户
    * @param requestInstance
    */
    void updateRequestInstance(@NotNull @Valid RequestInstance requestInstance);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestInstance(@NotNull String id);

    RequestInstance findOne(@NotNull String id);

    List<RequestInstance> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestInstance findRequestInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestInstance> findAllRequestInstance();

    /**
    * 查询列表
    * @param requestInstanceQuery
    * @return
    */
    List<RequestInstance> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery);

    /**
    * 按分页查询
    * @param requestInstanceQuery
    * @return
    */
    Pagination<RequestInstance> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery);

}