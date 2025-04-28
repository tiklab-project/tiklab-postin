package io.tiklab.postin.api.apix.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.apix.model.ApiList;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口公共字段的接口
*/
@JoinProvider(model = Apix.class)
public interface ApixService {

    /**
    * 创建
    * @param apix
    * @return
    */
    String createApix(@NotNull @Valid Apix apix);

    /**
    * 更新
    * @param apix
    */
    void updateApix(@NotNull @Valid Apix apix);

    /**
    * 删除
    * @param id
    */
    void deleteApix(@NotNull String id);
    @FindOne
    Apix findOne(@NotNull String id);
    @FindList
    List<Apix> findList(List<String> idList);

    /**
     * 查询接口总数
     * @param workspaceId
     * @return
     */
    int findApixNum(String workspaceId);

    /**
    * 查找
    * @param id
    * @return
    */
    Apix findApix(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<Apix> findAllApix();

    /**
    * 查询列表
    * @param apixQuery
    * @return
    */
    List<Apix> findApixList(ApixQuery apixQuery);

    /**
    * 按分页查询
    * @param apixQuery
    * @return
    */
    Pagination<ApiList> findApixPage(ApixQuery apixQuery);

    /**
    * 复制当前接口
    * @param apiId
    */
    String copyCurrentApi(String apiId);

    /**
     * 覆盖接口
     * @param willCoverApiId 当前接口，会被覆盖
     * @param originApiId 通过id获取到接口信息, 然后覆盖当前接口
     */
    void coverApi(String willCoverApiId,String originApiId);

}