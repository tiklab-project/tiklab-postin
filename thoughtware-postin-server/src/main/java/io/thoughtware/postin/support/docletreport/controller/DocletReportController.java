package io.thoughtware.postin.support.docletreport.controller;


import io.thoughtware.core.Result;
import io.thoughtware.postin.category.model.Category;
import io.thoughtware.postin.support.docletreport.model.ApiReport;
import io.thoughtware.postin.support.docletreport.model.ModuleReport;
import io.thoughtware.postin.support.docletreport.service.DocletReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 注释上报管理
 */
@RestController
@RequestMapping("/docletReport")
public class DocletReportController {

    @Autowired
    private DocletReportService docletReportService;



    @RequestMapping(path="/moduleReport",method = RequestMethod.POST)
    public Result<String> moduleReport(@RequestBody @NotNull @Valid ModuleReport moduleReport){
        String data = docletReportService.moduleReport(moduleReport);

        return Result.ok(data);
    }

}
