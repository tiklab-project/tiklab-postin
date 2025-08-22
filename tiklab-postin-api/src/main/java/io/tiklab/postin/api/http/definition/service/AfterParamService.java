package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.AfterParam;
import io.tiklab.postin.api.http.definition.model.AfterParamQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 后置操作（主）服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface AfterParamService {

    /**
    * 创建后置操作
    * @param afterParam
    * @return
    */
    String createAfterParam(@NotNull @Valid AfterParam afterParam);

    /**
    * 更新后置操作
    * @param afterParam
    */
    void updateAfterParam(@NotNull @Valid AfterParam afterParam);

    /**
    * 删除后置操作
    * @param id
    */
    void deleteAfterParam(@NotNull String id);


    /**
     * 通过接口Id删除所有后置操作
     * @param id
     */
    void deleteAllAfterParam(@NotNull String id);


    /**
    * 查找后置操作
    * @param id
    * @return
    */
    @FindOne
    AfterParam findAfterParam(@NotNull String id);

    /**
    * 查找后置操作
    * @return
    */
    @FindAll
    List<AfterParam> findAllAfterParam();

    /**
    * 查询列表后置操作
    * @param afterParamQuery
    * @return
    */
    List<AfterParam> findAfterParamList(AfterParamQuery afterParamQuery);

    /**
    * 按分页查询后置操作
    * @param afterParamQuery
    * @return
    */
    Pagination<AfterParam> findAfterParamPage(AfterParamQuery afterParamQuery);

}