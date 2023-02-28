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
* 数据结构 服务接口
*/
@JoinProvider(model = DataStructure.class)
public interface DataStructureService {

    /**
    * 创建数据结构
    * @param dataStructure
    * @return
    */
    String createDataStructure(@NotNull @Valid DataStructure dataStructure);

    /**
    * 更新数据结构
    * @param dataStructure
    */
    void updateDataStructure(@NotNull @Valid DataStructure dataStructure);

    /**
    * 删除数据结构
    * @param id
    */
    void deleteDataStructure(@NotNull String id);

    @FindOne
    DataStructure findOne(@NotNull String id);

    @FindList
    List<DataStructure> findList(List<String> idList);

    /**
    * 查找数据结构
    * @param id
    * @return
    */
    DataStructure findDataStructure(@NotNull String id);

    /**
    * 查找所有数据结构
    * @return
    */
    @FindAll
    List<DataStructure> findAllDataStructure();

    /**
    * 查询列表数据结构
    * @param dataStructureQuery
    * @return
    */
    List<DataStructure> findDataStructureList(DataStructureQuery dataStructureQuery);

    /**
    * 按分页查询数据结构
    * @param dataStructureQuery
    * @return
    */
    Pagination<DataStructure> findDataStructurePage(DataStructureQuery dataStructureQuery);

}