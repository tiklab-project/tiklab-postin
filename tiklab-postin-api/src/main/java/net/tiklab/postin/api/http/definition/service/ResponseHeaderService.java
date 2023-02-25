package net.tiklab.postin.api.http.definition.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.api.http.definition.model.ResponseHeader;
import net.tiklab.postin.api.http.definition.model.ResponseHeaderQuery;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = ResponseHeader.class)
public interface ResponseHeaderService {

    /**
    * 创建用户
    * @param responseHeader
    * @return
    */
    String createResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

    /**
    * 更新用户
    * @param responseHeader
    */
    void updateResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

    /**
    * 删除用户
    * @param id
    */
    void deleteResponseHeader(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    @FindOne
    ResponseHeader findResponseHeader(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ResponseHeader> findAllResponseHeader();

    /**
    * 查询列表
    * @param responseHeaderQuery
    * @return
    */
    List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery);

    /**
    * 按分页查询
    * @param responseHeaderQuery
    * @return
    */
    Pagination<ResponseHeader> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery);

}