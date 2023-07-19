package io.tiklab.postin.support.docletreport.controller;


import io.tiklab.core.Result;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.support.docletreport.model.ApiReport;
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

    @RequestMapping(path="/category",method = RequestMethod.POST)
    public Result<String> categoryReport(@RequestBody @NotNull @Valid Category category){
        String id = docletReportService.categoryReport(category);

        return Result.ok(id);
    }

    @RequestMapping(path="/api",method = RequestMethod.POST)
    public Result<String> apiReport(@RequestBody @NotNull @Valid ApiReport apiReport){
        String s = docletReportService.apiReport(apiReport);

        return Result.ok(s);
    }

}
