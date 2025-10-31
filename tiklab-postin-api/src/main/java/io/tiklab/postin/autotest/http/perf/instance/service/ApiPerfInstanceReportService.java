package io.tiklab.postin.autotest.http.perf.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstanceReport;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstanceReportQuery;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepInstance;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口性能历史服务接口
*/
@JoinProvider(model = ApiPerfInstanceReport.class)
public interface ApiPerfInstanceReportService {

    /**
    * 创建接口性能历史
    * @param perfInstance
    * @return
    */
    String createApiPerfInstanceReport(@NotNull @Valid ApiPerfInstanceReport perfInstance);

    /**
    * 更新
    * @param perfInstance
    */
    void updateApiPerfInstanceReport(@NotNull @Valid ApiPerfInstanceReport perfInstance);

    /**
    * 删除接口性能历史
    * @param id
    */
    void deleteApiPerfInstanceReport(@NotNull String id);
    @FindOne
    ApiPerfInstanceReport findOne(@NotNull String id);
    @FindList
    List<ApiPerfInstanceReport> findList(List<String> idList);

    /**
    * 查找接口性能历史
    * @param id
    * @return
    */
    ApiPerfInstanceReport findApiPerfInstanceReport(@NotNull String id);

    /**
    * 查找所有接口性能历史
    * @return
    */
    @FindAll
    List<ApiPerfInstanceReport> findAllApiPerfInstanceReport();

    /**
    * 查询列表接口性能历史
    * @param apiPerfInstanceReportQuery
    * @return
    */
    List<ApiPerfInstanceReport> findApiPerfInstanceReportList(ApiPerfInstanceReportQuery apiPerfInstanceReportQuery);

    /**
    * 按分页查询接口性能历史
    * @param apiPerfInstanceReportQuery
    * @return
    */
    Pagination<ApiPerfInstanceReport> findApiPerfInstanceReportPage(ApiPerfInstanceReportQuery apiPerfInstanceReportQuery);

}