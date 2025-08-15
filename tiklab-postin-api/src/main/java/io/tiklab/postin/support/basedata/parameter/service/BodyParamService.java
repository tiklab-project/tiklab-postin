package io.tiklab.postin.support.basedata.parameter.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.support.basedata.parameter.model.BodyParam;
import io.tiklab.postin.support.basedata.parameter.model.BodyParamQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 基础数据中的body公共参数(bodydata/bodyurlencoded) 服务接口
*/
@JoinProvider(model = BodyParam.class)
public interface BodyParamService {

    /**
    * 创建
    * @param bodyParam
    * @return
    */
    String createBodyParam(@NotNull @Valid BodyParam bodyParam);

    /**
    * 更新bodydata
    * @param bodyParam
    */
    void updateBodyParam(@NotNull @Valid BodyParam bodyParam);

    /**
    * 删除bodydata
    * @param id
    */
    void deleteBodyParam(@NotNull String id);

    /**
     * 通过httpId删除所有bodydata
     * @param id
     */
    void deleteAllBodyParam(@NotNull String id);

    /**
    * 查找bodydata
    * @param id
    * @return
    */
    BodyParam findBodyParam(@NotNull String id);

    /**
    * 查找所有bodydata
    * @return
    */
    @FindAll
    List<BodyParam> findAllBodyParam();

    /**
    * 查询列表bodydata
    * @param bodyParamQuery
    * @return
    */
    List<BodyParam> findBodyParamList(BodyParamQuery bodyParamQuery);

    /**
    * 按分页bodydata
    * @param bodyParamQuery
    * @return
    */
    Pagination<BodyParam> findBodyParamPage(BodyParamQuery bodyParamQuery);

}