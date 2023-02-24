package net.tiklab.postin.support.datastructure.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.support.datastructure.model.DataStructure;
import net.tiklab.postin.support.datastructure.model.DataStructureQuery;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DataStructureService
*/
@JoinProvider(model = DataStructure.class)
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

    @FindOne
    DataStructure findOne(@NotNull String id);

    @FindList
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
    @FindAll
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