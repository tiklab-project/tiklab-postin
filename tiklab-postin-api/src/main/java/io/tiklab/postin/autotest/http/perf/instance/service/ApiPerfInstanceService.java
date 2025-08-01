package io.tiklab.postin.autotest.http.perf.instance.service;

import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstance;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstanceQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口性能历史服务接口
*/
public interface ApiPerfInstanceService {

    /**
    * 创建接口性能历史
    * @param perfInstance
    * @return
    */
    String createApiPerfInstance(@NotNull @Valid ApiPerfInstance perfInstance);

    /**
    * 更新
    * @param perfInstance
    */
    void updateApiPerfInstance(@NotNull @Valid ApiPerfInstance perfInstance);

    /**
    * 删除接口性能历史
    * @param id
    */
    void deleteApiPerfInstance(@NotNull String id);

    ApiPerfInstance findOne(@NotNull String id);

    List<ApiPerfInstance> findList(List<String> idList);

    /**
    * 查找接口性能历史
    * @param id
    * @return
    */
    ApiPerfInstance findApiPerfInstance(@NotNull String id);

    /**
    * 查找所有接口性能历史
    * @return
    */
    List<ApiPerfInstance> findAllApiPerfInstance();

    /**
    * 查询列表接口性能历史
    * @param apiPerfInstanceQuery
    * @return
    */
    List<ApiPerfInstance> findApiPerfInstanceList(ApiPerfInstanceQuery apiPerfInstanceQuery);

    /**
    * 按分页查询接口性能历史
    * @param apiPerfInstanceQuery
    * @return
    */
    Pagination<ApiPerfInstance> findApiPerfInstancePage(ApiPerfInstanceQuery apiPerfInstanceQuery);

}