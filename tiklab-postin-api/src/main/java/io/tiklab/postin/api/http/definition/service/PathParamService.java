package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.PathParam;
import io.tiklab.postin.api.http.definition.model.PathParamQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* Path服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface PathParamService {

    /**
    * 创建Path
    * @param pathParam
    * @return
    */
    String createPathParam(@NotNull @Valid PathParam pathParam);

    /**
    * 更新Path
    * @param pathParam
    */
    void updatePathParam(@NotNull @Valid PathParam pathParam);

    /**
    * 删除Path
    * @param id
    */
    void deletePathParam(@NotNull String id);


    /**
     * 通过httpId删除所有Path
     * @param id
     */
    void deleteAllPathParam(@NotNull String id);


    /**
    * 查找Path
    * @param id
    * @return
    */
    @FindOne
    PathParam findPathParam(@NotNull String id);

    /**
    * 查找Path
    * @return
    */
    @FindAll
    List<PathParam> findAllPathParam();

    /**
    * 查询列表Path
    * @param pathParamQuery
    * @return
    */
    List<PathParam> findPathParamList(PathParamQuery pathParamQuery);

    /**
    * 按分页查询Path
    * @param pathParamQuery
    * @return
    */
    Pagination<PathParam> findPathParamPage(PathParamQuery pathParamQuery);

}