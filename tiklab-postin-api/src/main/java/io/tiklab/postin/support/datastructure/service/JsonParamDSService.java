package io.tiklab.postin.support.datastructure.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.support.datastructure.model.JsonParamDS;
import io.tiklab.postin.support.datastructure.model.JsonParamDSQuery;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* json结构 服务接口
*/
@JoinProvider(model = JsonParamDS.class)
public interface JsonParamDSService {

    /**
    * 创建json结构
    * @param jsonParamDS
    * @return
    */
    String createJsonParamDS(@NotNull @Valid JsonParamDS jsonParamDS);

    /**
    * 更新json结构
    * @param jsonParamDS
    */
    void updateJsonParamDS(@NotNull @Valid JsonParamDS jsonParamDS);

    /**
    * 删除json结构
    * @param id
    */
    void deleteJsonParamDS(@NotNull String id);
    @FindOne
    JsonParamDS findOne(@NotNull String id);
    @FindList
    List<JsonParamDS> findList(List<String> idList);

    /**
    * 查找json结构
    * @param id
    * @return
    */
    JsonParamDS findJsonParamDS(@NotNull String id);

    /**
    * 查找所有json结构
    * @return
    */
    @FindAll
    List<JsonParamDS> findAllJsonParamDS();

    /**
    * 查询列表json结构
    * @param jsonParamDSQuery
    * @return
    */
    List<JsonParamDS> findJsonParamDSList(JsonParamDSQuery jsonParamDSQuery);

    /**
    * 按分页查询json结构
    * @param jsonParamDSQuery
    * @return
    */
    Pagination<JsonParamDS> findJsonParamDSPage(JsonParamDSQuery jsonParamDSQuery);

    /**
     * 查询json数据结构树
     * @param jsonParamDSQuery
     * @return
     */
    List<JsonParamDS> findJsonParamDSListTree(JsonParamDSQuery jsonParamDSQuery);
}