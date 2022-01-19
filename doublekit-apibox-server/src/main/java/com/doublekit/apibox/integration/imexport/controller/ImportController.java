package com.doublekit.apibox.integration.imexport.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.apibox.apidef.controller.AfterScriptController;
import com.doublekit.apibox.integration.imexport.service.ImportService;
import com.doublekit.common.Result;
import com.doublekit.common.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/port")
@Api(name = "PortController",desc = "导入导出管理")
public class ImportController {

    private static Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Autowired
    ImportService importService;

    @RequestMapping(path = "/importPostman",method = RequestMethod.POST)
    @ApiMethod(name = "importPostman",desc = "导入数据")
    @ApiParam(name = "workspaceId")
    public Result<Void> importPostman(@NotNull  String workspaceId,@RequestParam("file") MultipartFile file ){
        try {
            if(file!=null){
                InputStream inputStream = file.getInputStream();
                importService.importPostman(workspaceId,inputStream);
            }
        } catch (IOException e) {
            throw new SystemException(e);
        }

        return Result.ok();
    }

    @RequestMapping(path = "/importReport",method = RequestMethod.POST)
    @ApiMethod(name = "importReport",desc = "导入数据")
    @ApiParam(name = "workspaceId")
    public Result<Void> importReport(@NotNull String workspaceId,@RequestParam("file") MultipartFile file ){
        try {
            if(file!=null){
                InputStream inputStream = file.getInputStream();
                importService.importReport(workspaceId,inputStream);
            }
        } catch (IOException e) {
            throw new SystemException(e);
        }

        return Result.ok();
    }



}
