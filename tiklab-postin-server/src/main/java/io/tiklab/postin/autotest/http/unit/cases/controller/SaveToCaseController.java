package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.unit.cases.model.SaveToCase;
import io.tiklab.postin.autotest.http.unit.cases.service.SaveToCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 保存为用例 控制器
 */
@RestController
@RequestMapping("/saveToCase")
//@Api(name = "ApiUnitCaseController",desc = "保存为用例")
public class SaveToCaseController {

    private static Logger logger = LoggerFactory.getLogger(SaveToCaseController.class);

    @Autowired
    private SaveToCaseService saveToCaseService;

    @RequestMapping(path="/save",method = RequestMethod.POST)
//    @ApiMethod(name = "save",desc = "保存为用例")
//    @ApiParam(name = "saveToCase",desc = "saveToCase",required = true)
    public Result<String> createApiUnitCase(@RequestBody @NotNull @Valid SaveToCase saveToCase){
        String id = saveToCaseService.saveToCase(saveToCase);

        return Result.ok(id);
    }



}
