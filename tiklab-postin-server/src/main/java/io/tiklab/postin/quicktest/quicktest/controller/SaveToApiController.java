package io.tiklab.postin.quicktest.quicktest.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.quicktest.model.SaveToApi;
import io.tiklab.postin.quicktest.service.SaveToApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/quickTest")
//@Api(name = "SaveToApiController",desc = "保存为接口")
public class SaveToApiController {
    private static Logger logger = LoggerFactory.getLogger(SaveToApiController.class);

    @Autowired
    private SaveToApiService saveToApiService;


    @RequestMapping(path="/saveToApi",method = RequestMethod.POST)
//    @ApiMethod(name = "saveToApi",desc = "保存为接口")
//    @ApiParam(name = "saveToApi",desc = "保存为接口",required = true)
    public Result<String> createWorkspace(@RequestBody @NotNull @Valid @ApiParam SaveToApi saveToApi) throws Exception {
        String id = saveToApiService.saveToApi(saveToApi);

        return Result.ok(id);
    }
}
