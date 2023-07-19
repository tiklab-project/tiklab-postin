package io.tiklab.postin.support.docletreport.service;


import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.support.docletreport.model.ApiReport;
import io.tiklab.postin.support.environment.model.Environment;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
* 注释上报 接口
*/
@JoinProvider(model = Environment.class)
public interface DocletReportService {

    /**
    * 分组目录上报
    * @param category
    * @return
    */
    String categoryReport(@NotNull @Valid Category category);



    /**
     * 接口上报
     * @param apiReport
     * @return
     */
    String apiReport(@NotNull @Valid ApiReport apiReport);


}