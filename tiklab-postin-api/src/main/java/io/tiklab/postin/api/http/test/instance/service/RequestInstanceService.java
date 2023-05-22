package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.model.RequestInstances;
import io.tiklab.postin.api.http.test.instance.model.RequestInstanceQuery;
import io.tiklab.core.page.Pagination;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestInstanceService {

    /**
    * 创建用户
    * @param requestInstances
    * @return
    */
    String createRequestInstance(@NotNull @Valid RequestInstances requestInstances);

    /**
    * 更新用户
    * @param requestInstances
    */
    void updateRequestInstance(@NotNull @Valid RequestInstances requestInstances);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestInstance(@NotNull String id);

    RequestInstances findOne(@NotNull String id);

    List<RequestInstances> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestInstances findRequestInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestInstances> findAllRequestInstance();

    /**
    * 查询列表
    * @param requestInstanceQuery
    * @return
    */
    List<RequestInstances> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery);

    /**
    * 按分页查询
    * @param requestInstanceQuery
    * @return
    */
    Pagination<RequestInstances> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery);

}