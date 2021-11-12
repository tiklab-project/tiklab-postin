package com.doublekit.apibox.sysmgr.datastructure.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.sysmgr.datastructure.model.JsonParamDS;
import com.doublekit.apibox.sysmgr.datastructure.model.JsonParamDSQuery;
import com.doublekit.join.annotation.FindAll;
import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.FindOne;
import com.doublekit.join.annotation.JoinProvider;

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