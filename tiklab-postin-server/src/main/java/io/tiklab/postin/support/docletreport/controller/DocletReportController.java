package io.tiklab.postin.support.docletreport.controller;


import io.tiklab.core.Result;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.support.docletreport.model.ApiReport;
import io.tiklab.postin.support.docletreport.model.ModuleReport;
import io.tiklab.postin.support.docletreport.service.DocletReportService;
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
