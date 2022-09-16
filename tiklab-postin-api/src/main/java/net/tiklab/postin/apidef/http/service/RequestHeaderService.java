package net.tiklab.postin.apidef.http.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.apidef.http.model.RequestHeader;
import net.tiklab.postin.apidef.http.model.RequestHeaderQuery;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface RequestHeaderService {

    /**
    * 创建用户
    * @param requestHeader
    * @return
    */
    String createRequestHeader(@NotNull @Valid RequestHeader requestHeader);

    /**
    * 更新用户
    * @param requestHeader
    */
    void updateRequestHeader(@NotNull @Valid RequestHeader requestHeader);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestHeader(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    @FindOne
    RequestHeader findRequestHeader(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<RequestHeader> findAllRequestHeader();

    /**
    * 查询列表
    * @param requestHeaderQuery
    * @return
    */
    List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery);

    /**
    * 按分页查询
    * @param requestHeaderQuery
    * @return
    */
    Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery);

}