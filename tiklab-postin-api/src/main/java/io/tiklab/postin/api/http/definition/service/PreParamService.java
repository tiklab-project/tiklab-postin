package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.PreParam;
import io.tiklab.postin.api.http.definition.model.PreParamQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 前置操作（主）服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface PreParamService {

    /**
    * 创建前置操作
    * @param preParam
    * @return
    */
    String createPreParam(@NotNull @Valid PreParam preParam);

    /**
    * 更新前置操作
    * @param preParam
    */
    void updatePreParam(@NotNull @Valid PreParam preParam);

    /**
    * 删除前置操作
    * @param id
    */
    void deletePreParam(@NotNull String id);


    /**
     * 通过接口Id删除所有前置操作
     * @param id
     */
    void deleteAllPreParam(@NotNull String id);


    /**
    * 查找前置操作
    * @param id
    * @return
    */
    @FindOne
    PreParam findPreParam(@NotNull String id);

    /**
    * 查找前置操作
    * @return
    */
    @FindAll
    List<PreParam> findAllPreParam();

    /**
    * 查询列表前置操作
    * @param preParamQuery
    * @return
    */
    List<PreParam> findPreParamList(PreParamQuery preParamQuery);

    /**
    * 按分页查询前置操作
    * @param preParamQuery
    * @return
    */
    Pagination<PreParam> findPreParamPage(PreParamQuery preParamQuery);

}