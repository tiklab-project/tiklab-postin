package net.tiklab.postin.api.http.definition.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.api.http.definition.model.QueryParam;
import net.tiklab.postin.api.http.definition.model.QueryParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface QueryParamService {

    /**
    * 创建用户
    * @param queryParam
    * @return
    */
    String createQueryParam(@NotNull @Valid QueryParam queryParam);

    /**
    * 更新用户
    * @param queryParam
    */
    void updateQueryParam(@NotNull @Valid QueryParam queryParam);

    /**
    * 删除用户
    * @param id
    */
    void deleteQueryParam(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    QueryParam findQueryParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<QueryParam> findAllQueryParam();

    /**
    * 查询列表
    * @param queryParamQuery
    * @return
    */
    List<QueryParam> findQueryParamList(QueryParamQuery queryParamQuery);

    /**
    * 按分页查询
    * @param queryParamQuery
    * @return
    */
    Pagination<QueryParam> findQueryParamPage(QueryParamQuery queryParamQuery);

}