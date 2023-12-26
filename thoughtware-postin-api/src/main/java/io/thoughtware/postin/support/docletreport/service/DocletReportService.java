package io.thoughtware.postin.support.docletreport.service;


import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.postin.support.docletreport.model.ModuleReport;
import io.thoughtware.postin.support.environment.model.Environment;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
* 注释上报 接口
*/
@JoinProvider(model = Environment.class)
public interface DocletReportService {


    /**
     * 分组模块包含下面所有接口
     * 用于一个模块一起提交
     * @param moduleReport
     * @return
     */
    String moduleReport(@NotNull @Valid ModuleReport moduleReport);

}