package net.tiklab.postin.apimock.http.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.apimock.http.model.FormParamMock;
import net.tiklab.postin.apimock.http.model.FormParamMockQuery;

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
    Pagination<FormParamMock> findFormParamMockPage(FormParamMockQuery formParamMockQuery);

}