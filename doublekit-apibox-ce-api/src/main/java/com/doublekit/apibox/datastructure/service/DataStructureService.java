package com.doublekit.apibox.datastructure.service;

import com.doublekit.common.Pagination;

import com.doublekit.apibox.datastructure.model.DataStructure;
import com.doublekit.apibox.datastructure.model.DataStructureQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DataStructureService
*/
public interface DataStructureService {

    /**
    * 创建
    * @param dataStructure
    * @return
    */
    String createDataStructure(@NotNull @Valid DataStructure dataStructure);

    /**
    * 更新
    * @param dataStructure
    */
    void updateDataStructure(@NotNull @Valid DataStructure dataStructure);

    /**
    * 删除
    * @param id
    */
    void deleteDataStructure(@NotNull String id);

    DataStructure findOne(@NotNull String id);

    List<DataStructure> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    DataStructure findDataStructure(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<DataStructure> findAllDataStructure();

    /**
    * 查询列表
    * @param dataStructureQuery
    * @return
    */
    List<DataStructure> findDataStructureList(DataStructureQuery dataStructureQuery);

    /**
    * 按分页查询
    * @param dataStructureQuery
    * @return
    */
    Pagination<DataStructure> findDataStructurePage(DataStructureQuery dataStructureQuery);

}