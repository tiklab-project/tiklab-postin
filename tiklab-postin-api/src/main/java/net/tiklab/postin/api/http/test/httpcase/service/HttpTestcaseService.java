package net.tiklab.postin.api.http.test.httpcase.service;

import net.tiklab.postin.api.http.test.httpcase.model.HttpTestcase;
import net.tiklab.postin.api.http.test.httpcase.model.HttpTestcaseQuery;
import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = HttpTestcase.class)
public interface HttpTestcaseService {

    /**
    * 创建用户
    * @param httpTestcase
    * @return
    */
    String createTestcase(@NotNull @Valid HttpTestcase httpTestcase);

    /**
     * 创建测试用例，级联从表、子表
     * @param httpTestcase
     * @return
     */
    String createTestcaseWithNest(@NotNull @Valid HttpTestcase httpTestcase);

    /**
    * 更新用户
    * @param httpTestcase
    */
    void updateTestcase(@NotNull @Valid HttpTestcase httpTestcase);

    /**
    * 删除用户
    * @param id
    */
    void deleteTestcase(@NotNull String id);

    @FindOne
    HttpTestcase findOne(@NotNull String id);

    @FindList
    List<HttpTestcase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    HttpTestcase findTestcase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<HttpTestcase> findAllTestcase();

    /**
    * 查询列表
    * @param httpTestcaseQuery
    * @return
    */
    List<HttpTestcase> findTestcaseList(HttpTestcaseQuery httpTestcaseQuery);

    /**
    * 按分页查询
    * @param httpTestcaseQuery
    * @return
    */
    Pagination<HttpTestcase> findTestcasePage(HttpTestcaseQuery httpTestcaseQuery);

}