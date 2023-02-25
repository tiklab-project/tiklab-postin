package net.tiklab.postin.api.http.test.httpinstance.service;

import net.tiklab.postin.api.http.test.httpinstance.model.ResponseInstanceQuery;
import net.tiklab.postin.api.http.test.httpinstance.model.ResponseInstance;
import net.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface ResponseInstanceService {

    /**
    * 创建用户
    * @param responseInstance
    * @return
    */
    String createResponseInstance(@NotNull @Valid ResponseInstance responseInstance);

    /**
    * 更新用户
    * @param responseInstance
    */
    void updateResponseInstance(@NotNull @Valid ResponseInstance responseInstance);

    /**
    * 删除用户
    * @param id
    */
    void deleteResponseInstance(@NotNull String id);

    ResponseInstance findOne(@NotNull String id);

    List<ResponseInstance> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    ResponseInstance findResponseInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ResponseInstance> findAllResponseInstance();

    /**
    * 查询列表
    * @param responseInstanceQuery
    * @return
    */
    List<ResponseInstance> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery);

    /**
    * 按分页查询
    * @param responseInstanceQuery
    * @return
    */
    Pagination<ResponseInstance> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery);

}