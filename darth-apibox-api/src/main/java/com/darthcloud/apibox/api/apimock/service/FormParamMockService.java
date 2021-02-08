package com.darthcloud.apibox.api.apimock.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.api.apimock.model.FormParamMock;
import com.darthcloud.apibox.api.apimock.model.FormParamMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface FormParamMockService {

    /**
    * 创建用户
    * @param formParamMock
    * @return
    */
    String createFormParamMock(@NotNull @Valid FormParamMock formParamMock);

    /**
    * 更新用户
    * @param formParamMock
    */
    void updateFormParamMock(@NotNull @Valid FormParamMock formParamMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteFormParamMock(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    FormParamMock findFormParamMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<FormParamMock> findAllFormParamMock();

    /**
    * 查询列表
    * @param formParamMockQuery
    * @return
    */
    List<FormParamMock> findFormParamMockList(FormParamMockQuery formParamMockQuery);

    /**
    * 按分页查询
    * @param formParamMockQuery
    * @return
    */
    Pagination<List<FormParamMock>> findFormParamMockPage(FormParamMockQuery formParamMockQuery);

}