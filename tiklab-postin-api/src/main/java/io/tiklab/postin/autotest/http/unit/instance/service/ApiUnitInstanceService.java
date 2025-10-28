package io.tiklab.postin.autotest.http.unit.instance.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstanceQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

/**
* 接口单元实例 服务接口
*/
@JoinProvider(model = ApiUnitInstance.class)
public interface ApiUnitInstanceService {

    /**
    * 创建
    * @param testInstance
    * @return
    */
    String createApiUnitInstance(@NotNull @Valid ApiUnitInstance testInstance);

    String createApiUnitInstanceWithNest(@NotNull @Valid List<ApiUnitInstance> testInstances);

    /**
    * 更新
    * @param testInstance
    */
    void updateApiUnitInstance(@NotNull @Valid ApiUnitInstance testInstance);

    /**
    * 删除
    * @param id
    */
    void deleteApiUnitInstance(@NotNull String id);

    /**
     * 通过apiUnitId删除
     * @param apiUnitId
     */
    void deleteApiUnitInstanceByApiUnitId(@NotNull String apiUnitId);

    @FindOne
    ApiUnitInstance findOne(@NotNull String id);

    @FindList
    List<ApiUnitInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiUnitInstance findApiUnitInstance(@NotNull String id);

    ApiUnitInstance findApiUnitInstanceWithNest(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ApiUnitInstance> findAllApiUnitInstance();

    /**
    * 查询列表
    * @param testInstanceQuery
    * @return
    */
    List<ApiUnitInstance> findApiUnitInstanceList(ApiUnitInstanceQuery testInstanceQuery);

    /**
    * 按分页查询
    * @param testInstanceQuery
    * @return
    */
    Pagination<ApiUnitInstance> findApiUnitInstancePage(ApiUnitInstanceQuery testInstanceQuery);


    /**
     * 保存接口单元用例历史到数据库
     * @param apiUnitInstance
     */
    String saveApiUnitInstanceToSql(ApiUnitInstance apiUnitInstance);

    /**
     * 更新接口单元用例历史到数据库（包含深层次实例）
     * @param apiUnitInstance
     */
    void updateApiUnitInstanceToSql(ApiUnitInstance apiUnitInstance);

}