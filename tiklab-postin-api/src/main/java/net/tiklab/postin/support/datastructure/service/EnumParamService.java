package net.tiklab.postin.support.datastructure.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.support.datastructure.model.EnumParam;
import net.tiklab.postin.support.datastructure.model.EnumParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 枚举结构 服务接口
*/
public interface EnumParamService {

    /**
    * 创建枚举结构
    * @param enumParam
    * @return
    */
    String createEnumParam(@NotNull @Valid EnumParam enumParam);

    /**
    * 更新枚举结构
    * @param enumParam
    */
    void updateEnumParam(@NotNull @Valid EnumParam enumParam);

    /**
    * 删除枚举结构
    * @param id
    */
    void deleteEnumParam(@NotNull String id);

    EnumParam findOne(@NotNull String id);

    List<EnumParam> findList(List<String> idList);

    /**
    * 查找枚举结构
    * @param id
    * @return
    */
    EnumParam findEnumParam(@NotNull String id);

    /**
    * 查找所有枚举结构
    * @return
    */
    List<EnumParam> findAllEnumParam();

    /**
    * 查询列表枚举结构
    * @param enumParamQuery
    * @return
    */
    List<EnumParam> findEnumParamList(EnumParamQuery enumParamQuery);

    /**
    * 按分页查询枚举结构
    * @param enumParamQuery
    * @return
    */
    Pagination<EnumParam> findEnumParamPage(EnumParamQuery enumParamQuery);

}