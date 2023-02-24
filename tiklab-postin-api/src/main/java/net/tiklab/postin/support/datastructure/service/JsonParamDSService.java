package net.tiklab.postin.support.datastructure.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.support.datastructure.model.JsonParamDS;
import net.tiklab.postin.support.datastructure.model.JsonParamDSQuery;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* JsonParamDSService
*/
@JoinProvider(model = JsonParamDS.class)
public interface JsonParamDSService {

    /**
    * 创建
    * @param jsonParamDS
    * @return
    */
    String createJsonParamDS(@NotNull @Valid JsonParamDS jsonParamDS);

    /**
    * 更新
    * @param jsonParamDS
    */
    void updateJsonParamDS(@NotNull @Valid JsonParamDS jsonParamDS);

    /**
    * 删除
    * @param id
    */
    void deleteJsonParamDS(@NotNull String id);
    @FindOne
    JsonParamDS findOne(@NotNull String id);
    @FindList
    List<JsonParamDS> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    JsonParamDS findJsonParamDS(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<JsonParamDS> findAllJsonParamDS();

    /**
    * 查询列表
    * @param jsonParamDSQuery
    * @return
    */
    List<JsonParamDS> findJsonParamDSList(JsonParamDSQuery jsonParamDSQuery);

    /**
    * 按分页查询
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